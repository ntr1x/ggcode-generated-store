.PHONY: build up down

up:
	docker compose \
	  --profile 'app' \
	  --profile 'env' \
	  up -d

down:
	docker compose --profile '*' down

build:
	@make --no-print-directory build/app/worker_product_image
	@make --no-print-directory build/app/worker_employee_table

build/app/worker_product_image:
	docker build \
	  --tag store/worker_product_image:1.0-SNAPSHOT \
	  build/app/app-worker-product-image

build/app/worker_employee_table:
	docker build \
	  --tag store/worker_employee_table:1.0-SNAPSHOT \
	  build/app/app-worker-employee-table
