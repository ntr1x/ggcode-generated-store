create table "public"."customer_bonus" (
  "id" uuid not null,
  "customer_id" uuid,
  "order_discount_id" uuid,
  "bonus" numeric(38,2),
  "created_at" timestamp(6),
  "updated_at" timestamp(6),
  primary key (id)
);
