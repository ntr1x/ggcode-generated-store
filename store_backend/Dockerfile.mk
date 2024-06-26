# Build scripts
build/assembly_web:
	docker build \
		--tag store/assembly_web:1.0-SNAPSHOT \
		--build-arg="MODULE_NAME=$${MODULE_NAME:=assembly_web}" \
		--build-arg="MODULE_VERSION=$${MODULE_VERSION:=1.0-SNAPSHOT}" \
		--file Dockerfile \
		.
