create table "public"."dispatch" (
  "id" uuid not null,
  "customer_id" uuid,
  "session_id" text,
  "subscription_id" uuid,
  "type_id" int4,
  "status_id" int4,
  "template_type_id" int4,
  "created_at" timestamp(6),
  "updated_at" timestamp(6),
  "payload" jsonb,
  primary key (id)
);
