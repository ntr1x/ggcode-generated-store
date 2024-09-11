# Build scripts
build/admin_ui:
	docker build \
	  --target prod \
		--tag store/admin_ui:1.0-SNAPSHOT \
		--build-arg="BUILD_MODE=$${BUILD_MODE:=development}" \
		--build-arg="MODULE_NAME=$${MODULE_NAME:=admin_ui}" \
		--build-arg="MODULE_VERSION=$${MODULE_VERSION:=1.0-SNAPSHOT}" \
		--file Dockerfile \
		.
