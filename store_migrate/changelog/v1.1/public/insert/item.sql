WITH
  booking (shop_id, product_id, count) AS (
    SELECT s.id, p.id, floor(random() * 5)::int4
    FROM "public"."product" p
    CROSS JOIN "public"."shop" s
  ),
  rack (shop_id, product_id, idx) AS (
    SELECT b.shop_id, b.product_id, idx
    FROM booking b
    CROSS JOIN generate_series(1, b.count) AS idx
  )
INSERT INTO "public"."item" (id, product_id, shop_id, ean_13, code_128)
SELECT
  gen_random_uuid(),
  p.id,
  s.id,
  p.ean_13,
  NULL
FROM rack
LEFT JOIN "public"."product" p ON p.id = rack.product_id
LEFT JOIN "public"."shop" s ON s.id = rack.shop_id
