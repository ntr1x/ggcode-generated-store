WITH
  selected_regions (id, topic) AS (
    SELECT item.id, 'public_region' FROM "public"."region" item
  ),
  selected_shops (id, topic) AS (
    SELECT item.id, 'public_shop' FROM "public"."shop" item
  ),
  selected_categories (id, topic) AS (
    SELECT item.id, 'public_category' FROM "public"."category" item
  ),
  selected_products (id, topic) AS (
    SELECT item.id, 'public_product' FROM "public"."product" item
  ),
  selected_items (id, topic) AS (
    SELECT item.id, 'public_item' FROM "public"."item" item
  ),
  selected_customers (id, topic) AS (
    SELECT item.id, 'public_customer' FROM "public"."customer" item
  ),
  selected_agents (id, topic) AS (
    SELECT item.id, 'public_agent' FROM "public"."agent" item
  ),
  selected_baskets (id, topic) AS (
    SELECT item.id, 'public_basket' FROM "public"."basket" item
  ),
  selected_orders (id, topic) AS (
    SELECT item.id, 'public_order' FROM "public"."order" item
  ),
  selected_payments (id, topic) AS (
    SELECT item.id, 'public_payment' FROM "public"."payment" item
  ),
  selected_promotions (id, topic) AS (
    SELECT item.id, 'public_promotion' FROM "public"."promotion" item
  ),
  prepared_objects (id, topic) AS (
    SELECT * FROM selected_regions
    UNION
    SELECT * FROM selected_shops
    UNION
    SELECT * FROM selected_categories
    UNION
    SELECT * FROM selected_products
    UNION
    SELECT * FROM selected_items
    UNION
    SELECT * FROM selected_customers
    UNION
    SELECT * FROM selected_agents
    UNION
    SELECT * FROM selected_baskets
    UNION
    SELECT * FROM selected_orders
    UNION
    SELECT * FROM selected_payments
    UNION
    SELECT * FROM selected_promotions
  )
INSERT
  INTO "public"."event" (id, topic, content_type, ce_type, ce_source, ce_specification, ce_id, created_at, payload)
  SELECT
    gen_random_uuid() AS id,
    po.topic,
    'application/json' AS content_type,
    'created' AS ce_type,
    'migrations' AS ce_source,
    '1.0' AS ce_specification,
    gen_random_uuid() AS ce_id,
    NOW() AS created_at,
    ('{ "id": "' || po.id || '" }')::jsonb AS payload
  FROM prepared_objects po
;