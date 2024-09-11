create table "public"."image_type" (
  "id" int4 not null,
  "name" text,
  "description" text,
  "format" text,
  "width" int4,
  "height" int4,
  primary key (id)
);
