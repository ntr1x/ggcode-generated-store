INSERT
  INTO "public"."image_type" (id, name, description, format, width, height)
	VALUES
    (1, 'ORIGINAL', 'Original image', NULL, NULL, NULL)
  , (2, 'COMPRESSED', 'Compressed image', 'jpg', NULL, NULL)

  , (101, 'THUMB_FIXED_256X256', 'Thumbnail 256 x 256', 'png', 256, 256)
  , (102, 'THUMB_FIXED_192X256', 'Thumbnail 192 x 256', 'png', 192, 256)
  , (103, 'THUMB_FIXED_256X192', 'Thumbnail 256 x 192', 'png', 256, 192)
  , (104, 'THUMB_FIXED_144X256', 'Thumbnail 144 x 256', 'png', 144, 256)
  , (105, 'THUMB_FIXED_256X144', 'Thumbnail 256 x 144', 'png', 256, 144)

  , (201, 'THUMB_FIXED_512X512', 'Thumbnail 512 x 512', 'png', 512, 512)
  , (202, 'THUMB_FIXED_384X512', 'Thumbnail 384 x 512', 'png', 384, 512)
  , (203, 'THUMB_FIXED_512X384', 'Thumbnail 256 x 384', 'png', 512, 384)
  , (204, 'THUMB_FIXED_288X512', 'Thumbnail 288 x 512', 'png', 288, 512)
  , (205, 'THUMB_FIXED_512X288', 'Thumbnail 512 x 288', 'png', 512, 288)

  , (301, 'THUMB_WIDTH_256', 'Thumbnail 256 x Auto', 'png', 256, NULL)
  , (302, 'THUMB_WIDTH_512', 'Thumbnail 512 x Auto', 'png', 512, NULL)

  , (401, 'THUMB_HEIGHT_256', 'Thumbnail Auto x 256', 'png', NULL, 256)
  , (402, 'THUMB_HEIGHT_512', 'Thumbnail Auto x 512', 'png', NULL, 512)

  , (501, 'WIDTH_512', 'Image 512 x Auto', 'jpg', 512, NULL)
  , (502, 'WIDTH_1024', 'Image 1024 x Auto', 'jpg', 1024, NULL)
  , (503, 'WIDTH_1920', 'Image 1024 x Auto', 'jpg', 1920, NULL)
;
