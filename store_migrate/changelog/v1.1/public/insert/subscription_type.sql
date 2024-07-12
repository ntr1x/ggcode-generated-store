INSERT
  INTO "public"."subscription_type" (id, name, description)
	VALUES
    (1, 'FCM', 'Firebase Cloud Messaging')
  , (2, 'HMS', 'Huawei Messaging Service')
  , (3, 'SMS', 'SMS Message')
  , (4, 'WS', 'WebSocket Message')
  , (5, 'EMAIL', 'Email Message')
;
