INSERT
  INTO "public"."promotion" (id, type_id, name, description, config)
	VALUES
    ('5569af33-cc4e-4a2f-8b52-73dcbf382b25', 1, 'Скидка на все холодильники', NULL, '{ "discount_percent": "0.05" }')
  , ('ec6e4516-a3b8-4ffc-b58c-765cd23adc6f', 1, 'Скидка на всё в новом магазине', NULL, '{ "discount_percent": "0.075" }')
  , ('188103d9-b677-42b3-ad4a-5ed77c5303f0', 2, 'Бонусы при покупке', NULL, '{ "bonus_value": "300" }')
  , ('80ac6a9c-2ef8-44f7-b89a-00e960d6efc8', 2, 'Двойные бонусы при покупке в мобильном приложении', NULL, '{ "bonus_value": "600" }')
  , ('69eb7f95-5039-4f47-bd5c-6f44c5ac1d5d', 3, 'Бесплатная доставка', NULL, NULL)
;
