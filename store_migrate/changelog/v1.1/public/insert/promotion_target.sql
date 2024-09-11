INSERT
  INTO "public"."promotion_target" (
    "id",
    "promotion_id",
    "source_type_id",
    "category_id",
    "product_id",
    "region_id",
    "shop_id"
  )
	VALUES
  -- Скидка на все холодильники
    (gen_random_uuid(), '5569af33-cc4e-4a2f-8b52-73dcbf382b25', NULL, '9bfc544b-413e-4071-ab91-eecb1f8bb2e3', NULL, NULL, NULL)
  -- Скидка на мотор кондиционера
  , (gen_random_uuid(), '5569af33-cc4e-4a2f-8b52-73dcbf382b25', NULL, NULL, 'fa0647ee-2b39-4063-bee5-4a4d7344e26c', NULL, NULL)
  -- Скидка на всё в новом магазине
  , (gen_random_uuid(), '5569af33-cc4e-4a2f-8b52-73dcbf382b25', NULL, NULL, NULL, NULL, '8bdbddb1-b241-4b37-8b77-656c20f8e914')
  -- Бонусы при покупке
  , (gen_random_uuid(), '188103d9-b677-42b3-ad4a-5ed77c5303f0', NULL, NULL, NULL, NULL, NULL)
  -- Двойные бонусы при покупке в мобильном приложении
  , (gen_random_uuid(), '80ac6a9c-2ef8-44f7-b89a-00e960d6efc8', 2, NULL, NULL, NULL, NULL)
  -- Бесплатная доставка
  , (gen_random_uuid(), '69eb7f95-5039-4f47-bd5c-6f44c5ac1d5d', NULL, 'ca4fcb9f-3ca3-412f-a6cc-2efb3e77ba7c', NULL, NULL, NULL)
  , (gen_random_uuid(), '69eb7f95-5039-4f47-bd5c-6f44c5ac1d5d', NULL, 'a05e2555-7a28-4283-abbc-711fef04de97', NULL, NULL, NULL)
  , (gen_random_uuid(), '69eb7f95-5039-4f47-bd5c-6f44c5ac1d5d', NULL, '87c5e0de-0151-4dbf-bc65-5ae7245add56', NULL, NULL, NULL)
;
