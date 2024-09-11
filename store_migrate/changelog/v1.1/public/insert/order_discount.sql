WITH
  entries AS (
    SELECT
      p.price,
      e.quantity,
      o.id AS order_id,
      o.source_type_id,
      o.region_id,
      o.shop_id,
      e.product_id,
      p.category_id
    FROM "public"."order" o
    LEFT JOIN "public"."order_entry" e ON e.order_id = o.id
    INNER JOIN "public"."product" p ON p.id = e.product_id
  )
INSERT
  INTO "public"."order_discount" (id, order_id, product_id, discount, bonus)
  SELECT
    gen_random_uuid()::uuid AS id,
    e.order_id,
    e.product_id,
    (COALESCE((p1.config->>'discount_percent')::decimal, 0) * e.price + COALESCE((p1.config->>'discount_value')::decimal, 0)) * e.quantity AS discount,
    (COALESCE((p1.config->>'bonus_percent')::decimal, 0) * e.price + COALESCE((p1.config->>'bonus_value')::decimal, 0)) * e.quantity AS bonus
  FROM entries e
  CROSS JOIN "public"."promotion_target" t
  LEFT JOIN "public"."promotion" p1 ON p1.id = t.promotion_id
  WHERE p1.config IS NOT NULL
    AND (t.source_type_id IS NULL OR t.source_type_id = e.source_type_id)
    AND (t.category_id IS NULL OR t.category_id = e.category_id)
    AND (t.product_id IS NULL OR t.product_id = e.product_id)
    AND (t.region_id IS NULL OR t.region_id = e.region_id)
    AND (t.shop_id IS NULL OR t.shop_id = e.shop_id)
;

WITH
  selection_product_discounts AS (
    SELECT
      d.order_id,
      d.product_id,
      MAX(d.discount) AS discount,
      MAX(d.bonus) AS bonus
    FROM "public"."order_discount" d
    GROUP BY d.order_id, d.product_id
  ),
  selection_discounts AS (
    SELECT
      spd.order_id,
      SUM(spd.discount) AS discount,
      SUM(spd.bonus) AS bonus
    FROM selection_product_discounts spd
    GROUP BY spd.order_id
  )
UPDATE "public"."order" o
  SET
    discount = sd.discount,
    bonus = sd.bonus
  FROM selection_discounts sd
  WHERE sd.order_id = o.id
;
