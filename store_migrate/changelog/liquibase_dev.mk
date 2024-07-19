migrate/changelog/liquibase:
	docker run --rm \
		-v ./changelog:/liquibase/changelog \
		liquibase/liquibase \
		--defaults-file=changelog/liquibase_dev.properties \
		update

rollback/changelog/liquibase:
	docker run --rm \
		-v ./changelog:/liquibase/changelog \
		liquibase/liquibase \
		--defaults-file=changelog/liquibase_dev.properties \
		rollback-count 1

reset/changelog/liquibase:
	docker run --rm \
		-v ./changelog:/liquibase/changelog \
		liquibase/liquibase \
		--defaults-file=changelog/liquibase_dev.properties \
		rollback-count 1000
