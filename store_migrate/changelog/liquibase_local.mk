migrate/changelog/liquibase_local:
	docker run --rm \
		-v ./changelog:/liquibase/changelog \
		--add-host=host.docker.internal:host-gateway \
		liquibase/liquibase \
		--defaults-file=changelog/liquibase_local.properties \
		update

rollback/changelog/liquibase_local:
	docker run --rm \
		-v ./changelog:/liquibase/changelog \
		--add-host=host.docker.internal:host-gateway \
		liquibase/liquibase \
		--defaults-file=changelog/liquibase_local.properties \
		rollback-count 1

reset/changelog/liquibase_local:
	docker run --rm \
		-v ./changelog:/liquibase/changelog \
		--add-host=host.docker.internal:host-gateway \
		liquibase/liquibase \
		--defaults-file=changelog/liquibase_local.properties \
		rollback-count 1000
