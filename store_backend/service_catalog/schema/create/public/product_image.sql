create table "public"."product_image" (
  "id" uuid not null,
  "uri" text,
  "name" text,
  "type_id" int4,
  "product_id" uuid,
  "original_id" uuid,
  primary key (id)
);
