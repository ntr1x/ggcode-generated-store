INSERT
  INTO "public"."shop" (id, region_id, lon, lat, name, description, address)
	VALUES
    -- Город Челябинск и Челябинская область
    ('64330d0b-15c2-4a4f-ab1f-4cdcd8996f52', 'ff9136b5-0697-48c8-986e-5fe78aff242a', NULL, NULL, 'ТК "Калибр"', 'Мы находимся в ТК "Калибр"', 'г. Челябинск, ул. Худякова, 12/2')
  , ('8430ea6e-4576-4c99-bad4-017e08e187d3', 'ff9136b5-0697-48c8-986e-5fe78aff242a', NULL, NULL, 'ТЦ "SPAR"', 'Мы находимся в ТЦ "SPAR"', 'г. Челябинск, ул. Братьев Кашириных, 133')
  , ('8bdbddb1-b241-4b37-8b77-656c20f8e914', 'ff9136b5-0697-48c8-986e-5fe78aff242a', NULL, NULL, 'ТРК "Куба"', 'Мы находимся в ТРК "Куба"', 'г. Челябинск, ул. Цвиллинга 25')
    -- Город Екатеринбург и Свердловская область
  , ('2b7599e8-18a4-4d48-a2fb-8248a6f34883', '95d961a8-0d11-4292-a74a-8e680bd63cfe', NULL, NULL, 'ТЦ "Магнит Семейный"', 'Мы находимся в ТЦ "Магнит Семейный"', 'г. Екатеринбург, ул. Луначарского, 205')
    -- Город Новосибирск и Новосибирская область
  , ('daa54eee-e5ad-4445-9b19-0594e4c0d7ff', '2ffb43a0-95c5-4d5f-b16d-46ee6d91ec27', NULL, NULL, 'Магазин', 'Мы находимся рядом с кольцевым перекрестком Курчатова и Мясниковой"', 'г. Новосибирск, ул. Курчатова, 1')
  , ('7bdc170c-d28f-4089-91f7-39b6a81e9c9c', '2ffb43a0-95c5-4d5f-b16d-46ee6d91ec27', NULL, NULL, 'ТК "Фантазия"', 'Мы находимся в ТК "Фантазия" на цокольном этаже', 'г. Новосибирск, пл. Карла Маркса, 2')
;
