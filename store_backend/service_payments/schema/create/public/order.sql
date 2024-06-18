create table "public"."order" (
  "id" uuid not null,
  "source_type_id" int4,
  "customer_id" uuid,
  "region_id" uuid,
  "shop_id" uuid,
  "basket_id" uuid,
  "agent_id" uuid,
  "order_type_id" int4,
  "order_status_id" int4,
  "price" numeric(38,2),
  "spend" numeric(38,2),
  "bonus" numeric(38,2),
  "discount" numeric(38,2),
  "created_at" timestamp(6),
  "updated_at" timestamp(6),
  primary key (id)
);
