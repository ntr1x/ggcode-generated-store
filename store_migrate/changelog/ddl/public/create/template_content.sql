create table "public"."template_content" (
  "id" uuid not null,
  "template_id" uuid,
  "type_id" int4,
  "name" text,
  "payload" jsonb,
  primary key (id)
);
