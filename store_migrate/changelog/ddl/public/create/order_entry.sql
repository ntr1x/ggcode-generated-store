create table "public"."order_entry" (
  "id" uuid not null,
  "order_id" uuid,
  "product_id" uuid,
  "quantity" int4,
  primary key (id)
);
