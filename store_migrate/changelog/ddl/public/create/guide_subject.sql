create table "public"."guide_subject" (
  "id" uuid not null,
  "name" text,
  "position" int4,
  "created_at" timestamp(6),
  "updated_at" timestamp(6),
  primary key (id)
);
