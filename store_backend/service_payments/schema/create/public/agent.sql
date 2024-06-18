create table "public"."agent" (
  "id" uuid not null,
  "customer_id" uuid,
  "kind" text,
  primary key (id)
);
