create table "public"."basket_entry" (
  "id" uuid not null,
  "basket_id" uuid,
  "product_id" uuid,
  "quantity" int4,
  primary key (id)
);
