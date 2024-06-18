WITH
  selection_colors (color) AS (
    VALUES ('orange'), ('pink'), ('green'), ('blue')
  ),
  selection_assets (id, promotion_id, color) AS (
    SELECT
      gen_random_uuid()::uuid,
      p.id,
      c.color
    FROM "public"."promotion" p
    CROSS JOIN selection_colors c
  ),
  selection_formats (type_id, image_size, image_format) AS (
    VALUES
      (1, '2048x2048', 'jpg')
    , (2, '2048x2048', 'jpg')
    , (101, '256x256', 'png')
    , (102, '192x256', 'png')
    , (103, '256x192', 'png')
    , (401, '128x256', 'png')
    , (501, '512x1024', 'jpg')
  ),
  selection_images (id, promotion_id, type_id, original_id, uri, name) AS (
    SELECT
      CASE
        WHEN f.type_id = 1 THEN a.id
        ELSE gen_random_uuid()::uuid
      END,
      p.id::uuid,
      f.type_id,
      CASE
        WHEN f.type_id = 1 THEN NULL
        ELSE a.id
      END,
      CONCAT('https://placehold.co/', f.image_size, '/', a.color, '/white.', f.image_format),
      'Stub from placehold.co'
    FROM "public"."promotion" p
    INNER JOIN selection_assets a ON a.promotion_id = p.id
    CROSS JOIN selection_formats f
  )
INSERT INTO "public"."promotion_image" (id, promotion_id, type_id, original_id, uri, name)
SELECT id, promotion_id, type_id, original_id, uri, name
FROM selection_images
