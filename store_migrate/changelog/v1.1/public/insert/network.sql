INSERT
  INTO "public"."network" (id, type_id, shop_id, name, password, hidden, created_at, updated_at)
  VALUES
    (gen_random_uuid()::uuid, 1, NULL, 'MTS_for_RBT_Free', NULL, false, NOW(), NULL)
;
