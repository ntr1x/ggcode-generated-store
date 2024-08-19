build/importer/mysql:
	docker build \
	  --tag store/importer_mysql:1.0-SNAPSHOT \
	  .

bash/importer/mysql:
	docker run --rm -it \
		--entrypoint="/bin/bash" \
	  store/importer_mysql:1.0-SNAPSHOT

import/importer/mysql:
ifndef bucket
	@echo "Property 'bucket' is not defined"
else ifndef grid
	@echo "Property 'grid' is not defined"
else
	@docker run --rm -it \
		--env MYSQL_HOSTNAME="172.16.0.21" \
		--env MYSQL_PORT="14102" \
		--env MYSQL_DATABASE="web" \
		--env MYSQL_USERNAME="web" \
		--env MYSQL_PASSWORD="web" \
		--env MINIO_ACCESS_KEY="y5LeRZ84J4SsenpaIxMl" \
		--env MINIO_TARGET_SERVER="http://env-minio:9000" \
		--env MINIO_SECRET_KEY="AZitoDkffcEYFYUmV4CGv1jr2fiXWIw4uSvdFlDu" \
		--env MINIO_BUCKET="$(bucket)" \
		--env MINIO_THROTTLE_INTERVAL=5 \
		--env FETCHER_GRID="$(grid)" \
		--env FETCHER_BATCH_SIZE="5000" \
		--env FETCHER_TRACE=false \
		--volume "$$(pwd)/bin:/app/bin" \
		--volume "$$(pwd)/lib:/app/lib" \
		--network store_starter_default \
	  store/importer_mysql:1.0-SNAPSHOT
endif

import/importer/mysql/product:
	@make --no-print-directory import/importer/mysql \
		bucket="mysql-product" \
		grid="product"

import/importer/mysql/brand:
	@make --no-print-directory import/importer/mysql \
		bucket="mysql-brand" \
		grid="brand"

import/importer/mysql/attribute:
	@make --no-print-directory import/importer/mysql \
		bucket="mysql-attribute" \
		grid="attribute"

import/importer/mysql/attribute_type:
	@make --no-print-directory import/importer/mysql \
		bucket="mysql-attribute-type" \
		grid="attribute_type"

import/importer/mysql/attribute_option:
	@make --no-print-directory import/importer/mysql \
		bucket="mysql-attribute-option" \
		grid="attribute_option"

import/importer/mysql/auxiliary:
	@make --no-print-directory import/importer/mysql \
		bucket="mysql-auxiliary" \
		grid="auxiliary"
