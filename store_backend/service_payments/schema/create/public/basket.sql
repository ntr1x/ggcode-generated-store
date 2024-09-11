create table "public"."basket" (
  "id" uuid not null,
  "customer_id" uuid,
  "agent_id" uuid,
  "region_id" uuid,
  "shop_id" uuid,
  primary key (id)
);
