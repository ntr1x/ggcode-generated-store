create table "public"."item" (
  "id" uuid not null,
  "product_id" uuid,
  "shop_id" uuid,
  "ean_13" text,
  "code_128" text,
  primary key (id)
);
