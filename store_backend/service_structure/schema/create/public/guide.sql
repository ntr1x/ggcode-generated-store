create table "public"."guide" (
  "id" uuid not null,
  "subject_id" uuid,
  "name" text,
  "link" text,
  "position" int4,
  "created_at" timestamp(6),
  "updated_at" timestamp(6),
  primary key (id)
);
