create table "public"."promotion_image" (
  "id" uuid not null,
  "uri" text,
  "name" text,
  "type_id" int4,
  "promotion_id" uuid,
  "original_id" uuid,
  primary key (id)
);
