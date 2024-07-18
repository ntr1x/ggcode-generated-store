create table "public"."network" (
  "id" uuid not null,
  "name" text,
  "password" text,
  "hidden" boolean,
  "type_id" int4,
  "shop_id" uuid,
  "created_at" timestamp(6),
  "updated_at" timestamp(6),
  primary key (id)
);
