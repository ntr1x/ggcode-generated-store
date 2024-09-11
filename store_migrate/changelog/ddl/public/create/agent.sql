create table "public"."agent" (
  "id" uuid not null,
  "customer_id" uuid,
  "kind" text,
  "title" text,
  "inn" text,
  "kpp" text,
  "bic" text,
  "bank" text,
  "okved" text,
  "address" text,
  "phone" text,
  "email" text,
  "registration_address" text,
  primary key (id)
);
