INSERT
  INTO "public"."template_type" (id, name, description)
  VALUES
    (1, 'message:info', 'Информационное сообщение')
  , (2, 'security:signup', 'Регистрация')
  , (3, 'security:signin', 'Вход')
  , (4, 'profile:updated', 'Профиль обновлён')
  , (5, 'order:created', 'Заказ сформирован')
  , (6, 'order:packaged', 'Заказ собран')
  , (7, 'order:paid', 'Заказ оплачен')
  , (8, 'order:failed', 'Проблема с заказом')
  , (9, 'order:canceled', 'Заказ отменён')
  , (10, 'order:ready', 'Заказ ожидает в точке выдачи')
  , (11, 'order:complete', 'Заказ получен покупателем')
;
