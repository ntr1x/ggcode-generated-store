create table "public"."subscription" (
  "id" uuid not null,
  "customer_id" uuid,
  "type_id" int4,
  "session_id" text,
  "created_at" timestamp(6),
  "payload" jsonb,
  primary key (id)
);
