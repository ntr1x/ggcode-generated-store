create table "public"."shop" (
  "id" uuid not null,
  "name" text,
  "description" text,
  "address" text,
  "lon" numeric(38,2),
  "lat" numeric(38,2),
  "region_id" uuid,
  primary key (id)
);
