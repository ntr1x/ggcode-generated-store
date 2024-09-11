WITH
  selection_personal_baskets AS (
    SELECT
      gen_random_uuid()::uuid AS id,
      'ff9136b5-0697-48c8-986e-5fe78aff242a'::uuid AS region_id,
      NULL::uuid AS shop_id,
      c.id AS customer_id,
      NULL::uuid AS agent_id
    FROM "public"."customer" c
  ),
  selection_promoted_baskets AS (
    SELECT
      gen_random_uuid()::uuid AS id,
      'ff9136b5-0697-48c8-986e-5fe78aff242a'::uuid AS region_id,
      NULL::uuid AS shop_id,
      c.id AS customer_id,
      a.id AS agent_id
    FROM "public"."customer" c
    CROSS JOIN "public"."agent" a
    WHERE a.customer_id <> c.id
  )
INSERT
  INTO "public"."basket" (id, region_id, shop_id, customer_id, agent_id)
  SELECT * FROM selection_personal_baskets
  UNION
  SELECT * FROM selection_promoted_baskets
