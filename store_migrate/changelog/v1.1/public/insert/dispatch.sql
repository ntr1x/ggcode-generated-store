INSERT
  INTO "public"."dispatch" (id, customer_id, session_id, subscription_id, type_id, status_id, created_at, updated_at, payload)
  SELECT
    gen_random_uuid()::uuid AS id,
    ps.customer_id,
    ps.session_id,
    ps.id,
    1 AS type_id,
    1 + ((row_number() OVER ()) % 4) AS status_id,
    NOW() as created_at,
    NULL as updated_at,
    '{}'::jsonb AS payload
  FROM "public"."subscription" ps
;
