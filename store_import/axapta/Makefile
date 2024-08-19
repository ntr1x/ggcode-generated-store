build/app/importer_axapta:
	docker build \
	  --tag store/importer_axapta:1.0-SNAPSHOT \
	  .

bash/app/importer_axapta:
	docker run --rm -it \
	  store/importer_axapta:1.0-SNAPSHOT

import/app/importer_axapta:
ifndef bucket
	@echo "Property 'bucket' is not defined"
else ifndef grid
	@echo "Property 'grid' is not defined"
else
	@docker run --rm -it \
		--env ORACLE_CONNECTION_STRING="axapta/axapta@//192.168.98.250/out" \
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
	  store/importer_axapta:1.0-SNAPSHOT
endif

import/app/importer_axapta/product:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-product" \
		grid="product"

import/app/importer_axapta/product_attribute:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-product-attribute" \
		grid="product_attribute"

import/app/importer_axapta/brand:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-brand" \
		grid="brand"

import/app/importer_axapta/catalogue:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-catalogue" \
		grid="catalogue"

import/app/importer_axapta/catalogue_basic:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-catalogue-basic" \
		grid="catalogue_basic"

import/app/importer_axapta/catalogue_label:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-catalogue-label" \
		grid="catalogue_label"

import/app/importer_axapta/catalogue_attribute:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-catalogue-attribute" \
		grid="catalogue_attribute"

import/app/importer_axapta/catalogue_basic_link:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-catalogue-basic-link" \
		grid="catalogue_basic_link"

import/app/importer_axapta/attribute:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-attribute" \
		grid="attribute"

import/app/importer_axapta/attribute_type:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-attribute-type" \
		grid="attribute_type"

import/app/importer_axapta/attribute_option:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-attribute-option" \
		grid="attribute_option"

import/app/importer_axapta/unit_rate:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-unit-rate" \
		grid="unit_rate"

import/app/importer_axapta/auxiliary:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-auxiliary" \
		grid="auxiliary"

import/app/importer_axapta/upload:
	@make --no-print-directory import/app/importer_axapta \
		bucket="axapta-upload" \
		grid="upload"
