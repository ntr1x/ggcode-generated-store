migrate/changelog/liquibase_host:
	docker run --rm \
		-v ./changelog:/liquibase/changelog \
		--add-host=host.docker.internal:host-gateway \
		liquibase/liquibase \
		--defaults-file=changelog/liquibase_host.properties \
		update

rollback/changelog/liquibase_host:
	docker run --rm \
		-v ./changelog:/liquibase/changelog \
		--add-host=host.docker.internal:host-gateway \
		liquibase/liquibase \
		--defaults-file=changelog/liquibase_host.properties \
		rollback-count 1

reset/changelog/liquibase_host:
	docker run --rm \
		-v ./changelog:/liquibase/changelog \
		--add-host=host.docker.internal:host-gateway \
		liquibase/liquibase \
		--defaults-file=changelog/liquibase_host.properties \
		rollback-count 1000
