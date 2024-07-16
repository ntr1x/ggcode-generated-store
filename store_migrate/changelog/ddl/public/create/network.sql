create table "public"."network" (
  "id" uuid not null,
  "name" text,
  "password" text,
  "hidden" boolean,
  "shop_id" uuid,
  "created_at" timestamp(6),
  "updated_at" timestamp(6),
  primary key (id)
);
