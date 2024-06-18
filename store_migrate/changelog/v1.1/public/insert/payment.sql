WITH
  selection_accepted_cash_inplace (id, customer_id, order_id, payment_type_id, payment_status_id, value, created_at, updated_at) AS (
    SELECT
      gen_random_uuid()::uuid AS id,
      o.customer_id,
      o.id AS order_id,
      2 AS payment_type_id,
      1 AS payment_status_id,
      o.price - o.discount - o.spend AS value,
      now() AS created_at,
      NULL::timestamp AS updated_at
    FROM "public"."order" o
    WHERE o.order_type_id = 1
  ),
  selection_accepted_card_inplace (id, customer_id, order_id, payment_type_id, payment_status_id, value, created_at, updated_at) AS (
    SELECT
      gen_random_uuid()::uuid AS id,
      o.customer_id,
      o.id AS order_id,
      2 AS payment_type_id,
      2 AS payment_status_id,
      o.price - o.discount - o.spend AS value,
      now() AS created_at,
      NULL::timestamp AS updated_at
    FROM "public"."order" o
    WHERE o.order_type_id = 2
  ),
  selection_accepted_online (id, customer_id, order_id, payment_type_id, payment_status_id, value, created_at, updated_at) AS (
    SELECT
      gen_random_uuid()::uuid AS id,
      o.customer_id,
      o.id AS order_id,
      3 AS payment_type_id,
      2 AS payment_status_id,
      o.price - o.discount - o.spend AS value,
      now() AS created_at,
      NULL::timestamp AS updated_at
    FROM "public"."order" o
    WHERE o.order_type_id = 3
  )
INSERT
  INTO "public"."payment" (id, customer_id, order_id, payment_type_id, payment_status_id, value, created_at, updated_at)
  SELECT * FROM selection_accepted_cash_inplace
  UNION
  SELECT * FROM selection_accepted_card_inplace
  UNION
  SELECT * FROM selection_accepted_online
;
