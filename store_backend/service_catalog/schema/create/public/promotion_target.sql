create table "public"."promotion_target" (
  "id" uuid not null,
  "source_type_id" int4,
  "promotion_id" uuid,
  "category_id" uuid,
  "product_id" uuid,
  "region_id" uuid,
  "shop_id" uuid,
  primary key (id)
);
