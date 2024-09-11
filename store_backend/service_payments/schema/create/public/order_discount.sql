create table "public"."order_discount" (
  "id" uuid not null,
  "order_id" uuid,
  "product_id" uuid,
  "discount" numeric(38,2),
  "bonus" numeric(38,2),
  primary key (id)
);
