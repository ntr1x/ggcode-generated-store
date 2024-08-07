create table "public"."customer" (
  "id" uuid not null,
  "email" text,
  "phone" text,
  "name" text,
  "surname" text,
  "patronymic" text,
  primary key (id)
);
