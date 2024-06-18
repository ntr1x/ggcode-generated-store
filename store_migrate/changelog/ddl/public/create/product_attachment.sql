create table "public"."product_attachment" (
  "id" uuid not null,
  "uri" text,
  "size" int4,
  "page_count" int4,
  "name" text,
  "type_id" int4,
  "product_id" uuid,
  primary key (id)
);
