migrate/changelog/liquibase:
	docker run --rm \
		-v ./changelog:/liquibase/changelog \
		--add-host=host.docker.internal:host-gateway \
		liquibase/liquibase \
		--defaults-file=changelog/liquibase.properties \
		update

rollback/changelog/liquibase:
	docker run --rm \
		-v ./changelog:/liquibase/changelog \
		--add-host=host.docker.internal:host-gateway \
		liquibase/liquibase \
		--defaults-file=changelog/liquibase.properties \
		rollback-count 1

reset/changelog/liquibase:
	docker run --rm \
		-v ./changelog:/liquibase/changelog \
		--add-host=host.docker.internal:host-gateway \
		liquibase/liquibase \
		--defaults-file=changelog/liquibase.properties \
		rollback-count 1000
