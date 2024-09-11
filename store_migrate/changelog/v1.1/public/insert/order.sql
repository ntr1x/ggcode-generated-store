WITH
  selection_baskets AS (
    SELECT
      b.*,
      row_number() OVER () AS idx
    FROM "public"."basket" b
  ),
  selection_orders (id, source_type_id, region_id, shop_id, customer_id, basket_id, agent_id, order_type_id, order_status_id, spend, created_at, updated_at) AS (
    SELECT
      gen_random_uuid()::uuid AS id,
      1 + (b.idx % 2) AS source_type_id,
      b.region_id,
      b.shop_id,
      b.customer_id AS customer_id,
      b.id AS basket_id,
      b.agent_id AS agent_id,
      1 + (b.idx % 3) AS order_type_id,
      1 + (b.idx % 4) AS order_status_id,
      0 AS spend,
      random() * (now() - '2024-01-01 00:00:00'::timestamp) + '2024-01-01 00:00:00'::timestamp AS created_at,
      NULL::timestamp AS updated_at
    FROM selection_baskets b
  )
INSERT
  INTO "public"."order" (id, source_type_id, region_id, shop_id, customer_id, basket_id, agent_id, order_type_id, order_status_id, spend, created_at, updated_at)
  SELECT * FROM selection_orders
