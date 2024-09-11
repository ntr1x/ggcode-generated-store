INSERT
  INTO "public"."order_entry" (id, order_id, product_id, quantity)
  SELECT
    gen_random_uuid()::uuid AS id,
    o.id AS order_id,
    e.product_id,
    e.quantity
  FROM "public"."order" o
  INNER JOIN "public"."basket" b ON b.id = o.basket_id
  LEFT JOIN "public"."basket_entry" e ON e.basket_id = b.id
;

WITH
  selection_totals AS (
    SELECT
      e.order_id AS order_id,
      SUM(e.quantity * p.price) AS price
    FROM "public"."order_entry" e
    INNER JOIN "public"."product" p ON p.id = e.product_id
    GROUP BY e.order_id
  )
UPDATE "public"."order" o
  SET
    price = st.price
  FROM selection_totals st
  WHERE st.order_id = o.id
;
