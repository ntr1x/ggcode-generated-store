#!/bin/bash

mc alias set minio $MINIO_TARGET_SERVER $MINIO_ACCESS_KEY $MINIO_SECRET_KEY
mc admin info minio

mc admin user add minio api Qwerty123

mc admin policy attach minio readwrite --user api

mc admin user svcacct add \
  --access-key "y5LeRZ84J4SsenpaIxMl" \
  --secret-key "AZitoDkffcEYFYUmV4CGv1jr2fiXWIw4uSvdFlDu" \
  minio api

mc mb minio/employee-document
mc mb minio/product-image
mc mb minio/product-image-variant
mc mb minio/employee-table
mc mb minio/product-table
mc mb minio/price-table

mc anonymous set download minio/employee-document
mc anonymous set download minio/product-image
mc anonymous set download minio/product-image-variant

mc admin config set minio/ notify_kafka:product_image \
   brokers="env-kafka:29092" \
   topic="minio_product_image"

mc admin config set minio/ notify_kafka:employee_table \
   brokers="env-kafka:29092" \
   topic="minio_employee_table"

mc admin config set minio/ notify_kafka:product_table \
   brokers="env-kafka:29092" \
   topic="minio_product_table"

mc admin config set minio/ notify_kafka:price_table \
   brokers="env-kafka:29092" \
   topic="minio_price_table"

mc admin service restart minio

mc event add minio/product-image arn:minio:sqs::product_image:kafka --event put,delete
mc event add minio/employee-table arn:minio:sqs::employee_table:kafka --event put
mc event add minio/product-table arn:minio:sqs::product_table:kafka --event put
mc event add minio/price-table arn:minio:sqs::price_table:kafka --event put



