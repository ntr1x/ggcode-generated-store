create table "public"."template" (
  "id" uuid not null,
  "type_id" int4,
  "shape_id" int4,
  "name" text,
  "payload" jsonb,
  primary key (id)
);
