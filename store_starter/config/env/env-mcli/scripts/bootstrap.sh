#!/bin/bash

mc alias set minio $MINIO_TARGET_SERVER $MINIO_ACCESS_KEY $MINIO_SECRET_KEY
mc admin info minio

mc admin user add minio api Qwerty123

mc admin policy attach minio readwrite --user api

mc admin user svcacct add \
  --access-key "y5LeRZ84J4SsenpaIxMl" \
  --secret-key "AZitoDkffcEYFYUmV4CGv1jr2fiXWIw4uSvdFlDu" \
  minio api

mc mb minio/app-product-image
mc mb minio/app-product-image-variant
mc mb minio/app-public-image
mc mb minio/app-public-document
mc mb minio/onec-employee-table

mc anonymous set download minio/app-product-image
mc anonymous set download minio/app-product-image-variant
mc anonymous set download minio/app-public-image
mc anonymous set download minio/app-public-document

mc admin config set minio/ notify_kafka:app_product_image \
   brokers="env-kafka:29092" \
   topic="app_product_image"

mc admin config set minio/ notify_kafka:onec_employee_table \
   brokers="env-kafka:29092" \
   topic="onec_employee_table"

mc admin service restart minio

mc event add minio/app-product-image arn:minio:sqs::app_product_image:kafka --event put,delete
mc event add minio/onec-employee-table arn:minio:sqs::onec_employee_table:kafka --event put
