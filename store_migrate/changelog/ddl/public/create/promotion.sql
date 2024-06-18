create table "public"."promotion" (
  "id" uuid not null,
  "name" text,
  "description" text,
  "type_id" int4,
  "config" jsonb,
  primary key (id)
);
