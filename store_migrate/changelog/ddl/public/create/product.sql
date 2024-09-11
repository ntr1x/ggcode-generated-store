create table "public"."product" (
  "id" uuid not null,
  "name" text,
  "description" text,
  "price" numeric(38,2),
  "ean_13" text,
  "category_id" uuid,
  primary key (id)
);
