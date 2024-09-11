WITH
  selection_baskets (basket_id, count) AS (
    SELECT
      b.id AS basket_id,
      1 + floor(random() * 4)::int4 AS count
    FROM "public"."basket" b
  ),
  selection_indices (basket_id, idx) AS (
    SELECT sb.basket_id, idx
    FROM selection_baskets sb
    CROSS JOIN generate_series(1, sb.count) AS idx
  ),
  selection_items (basket_id, product_id) AS (
    SELECT
      si.basket_id,
      np.product_id
    FROM selection_indices si
    JOIN (
      SELECT
        p.id AS product_id,
        row_number() OVER () AS idx
      FROM "public"."product" p
      ORDER BY random()
    ) AS np ON np.idx = si.idx
  )
INSERT
  INTO "public"."basket_entry" (id, basket_id, product_id, quantity)
  SELECT
    gen_random_uuid()::uuid AS id,
    si.basket_id,
    si.product_id,
    1 + floor(random() * 2)::int4 AS quantity
  FROM selection_items si
