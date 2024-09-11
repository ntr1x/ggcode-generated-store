INSERT
  INTO "public"."event_type_info" (name, description)
	VALUES
    ('created', 'Запись создана')
  , ('updated', 'Запись изменена')
  , ('upserted', 'Запись заменена (upsert)')
  , ('removed', 'Запись удалена')
  , ('replaced', 'Запись заменена (replace)')
;
