INSERT
  INTO "public"."subscription" (id, customer_id, type_id, session_id, created_at, payload)
  SELECT
    gen_random_uuid()::uuid AS id,
    pc.id,
    1 + ((row_number() OVER ()) % 2) AS type_id,
    NULL::text as session_id,
    NOW() as created_at,
    '{ "token": "C+oHmpYYPzXJnagrG92Rn2a/9i7V5kl8DkHK1BzE+to=" }'::jsonb AS payload
  FROM "public"."customer" pc
;