INSERT
  INTO "public"."template" (id, type_id, shape_id, name, payload)
  VALUES
  -- message:info
    (gen_random_uuid()::uuid, 1, 1, 'message', '{ "title": "${payload.title}", "body": "${payload.detail}" }')
  , (gen_random_uuid()::uuid, 1, 2, 'message', '{ "title": "${payload.title}", "body": "<html><body>${payload.detail}</body></html>" }')
  -- security:signup
  , (gen_random_uuid()::uuid, 2, 1, 'message', '{ "title": "Подтверждение аккаунта", "body": "Используйте этот код для подтверждения регистрации: ${payload.code}" }')
  -- security:signin
  , (gen_random_uuid()::uuid, 3, 1, 'message', '{ "title": "Код для входа", "body": "Используйте этот код для входа: ${payload.code}" }')
  -- profile:updated
  , (gen_random_uuid()::uuid, 4, 1, 'message', '{ "title": "Обновление профиля", "body": "Ваш профиль был обновлён" }')
  , (gen_random_uuid()::uuid, 4, 2, 'message', '{ "title": "Обновление профиля", "body": "<html><body>Ваш профиль был обновлён</body></html>" }')
  -- order:created
  , (gen_random_uuid()::uuid, 5, 1, 'message', '{ "title": "Спасибо за заказ", "body": "Мы присвоили вашему заказу номер ${order.id}" }')
  , (gen_random_uuid()::uuid, 5, 2, 'message', '{ "title": "Заказ номер ${order.id}: спасибо за заказ", "body": "<html><body>Мы присвоили вашему заказу номер <b>${order.id}</b></body></html>" }')
  -- order:packaged
  , (gen_random_uuid()::uuid, 6, 1, 'message', '{ "title": "Заказ собран", "body": "Мы собрали ваш заказ ${order.id} и скоро отправим его вам" }')
  , (gen_random_uuid()::uuid, 6, 2, 'message', '{ "title": "Заказ номер ${order.id}: заказ собран", "body": "<html><body>Мы собрали ваш заказ <b>${order.id}</b> и скоро отправим его вам</body></html>" }')
  -- order:paid
  , (gen_random_uuid()::uuid, 7, 1, 'message', '{ "title": "Оплата получена", "body": "Получена оплата по заказу ${order.id}" }')
  , (gen_random_uuid()::uuid, 7, 2, 'message', '{ "title": "Заказ номер ${order.id}: оплата получена", "body": "<html><body>Получена оплата по заказу <b>${order.id}</b></body></html>" }')
  -- order:failed
  , (gen_random_uuid()::uuid, 8, 1, 'message', '{ "title": "Проблема с заказом", "body": "Проблема с заказом ${order.id}, мы скоро свяжемся с вами решения данной проблемы" }')
  , (gen_random_uuid()::uuid, 8, 2, 'message', '{ "title": "Заказ номер ${order.id}: проблема с заказом", "body": "<html><body>Проблема с заказом <b>${order.id}</b>, мы скоро свяжемся с вами решения данной проблемы</body></html>" }')
  -- order:canceled
  , (gen_random_uuid()::uuid, 9, 1, 'message', '{ "title": "Заказ выдан", "body": "Заказ ${order.id} выдан покупателю" }')
  , (gen_random_uuid()::uuid, 9, 2, 'message', '{ "title": "Заказ номер ${order.id}: выдан покупателю", "body": "<html><body>Заказ <b>${order.id}</b> выдан покупателю</body></html>" }')
  -- order:ready
  , (gen_random_uuid()::uuid, 10, 1, 'message', '{ "title": "Заказ ожидает", "body": "Заказ ${order.id} ожидает в точке выдачи" }')
  , (gen_random_uuid()::uuid, 10, 2, 'message', '{ "title": "Заказ номер ${order.id}: ожидает в точке выдачи", "body": "<html><body>Заказ <b>${order.id}</b> выдан покупателю</body></html>" }')
  -- order:complete
  , (gen_random_uuid()::uuid, 11, 1, 'message', '{ "title": "Заказ выдан", "body": "Заказ ${order.id} выдан покупателю" }')
  , (gen_random_uuid()::uuid, 11, 2, 'message', '{ "title": "Заказ номер ${order.id}: выдан покупателю", "body": "<html><body>Заказ <b>${order.id}</b> выдан покупателю</body></html>" }')
;
