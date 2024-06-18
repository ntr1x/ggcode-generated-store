INSERT
  INTO "public"."dispatch_status" (id, name, description)
	VALUES
    (1, 'NEW', 'Новый')
  , (2, 'DISPATCHED', 'Доставка инициирована')
  , (3, 'CONFIRMED', 'Доставка подтверждена')
  , (4, 'FAILED', 'Сбой доставки')
;
