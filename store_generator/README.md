# Online Store Example

This example demonstrates how to use GGCode Spring, Compose, and Core repositories together. Use it to bootstrap a local environment with Postgres, PGAdmin, Kafka, KafkaUI, and HaProxy, along with a multi-module Spring Boot project featuring Product Catalog, Customer Database, Customer Basket, and specialized Payments microservices.

## Quick Start

### Prerequisites

#### Step 1

You need Java 17+ and Maven 3.8.8+ to build and run the application. Make sure that your Maven points to Java 17+.

#### Step 2

Upload your SSH Key to a GitHub. It is usually located in `~/.ssh/id_rsa.pub` on Linux.

#### Step 3

Add this routes to your hosts file:

```
127.0.0.1   api.local.example.com
127.0.0.1   swagger-ui.local.example.com
127.0.0.1   kafka-ui.local.example.com
127.0.0.1   pgadmin.local.example.com
127.0.0.1   api.local.example.com
```

It is usually located in `/etc/hosts` on Linux.

#### Step 4

Make sure that ports `80`, `5432` and `9092` are free on your system.

### Launching

#### Step 1

Export PROJECT_ROOT environment variable:

```bash
# Just to simplify further instructions:
$ export PROJECT_ROOT=$(pwd)
```

#### Step 2

Generate code:

```bash
$ cd $PROJECT_ROOT/store_generator

# Go to the project root directory
$ ggcode install

# Generate compose manifests
$ ggcode run @/generate-compose

# Generate spring boot applicaiton
$ ggcode run @/generate-spring

# Generate liquibase migrations
$ ggcode run @/generate-migrate

# Generate admin application
$ ggcode run @/generate-admin
```

#### Step 3

Build back-end application:

```bash
$ cd $PROJECT_ROOT/store_backend
$ make build/assembly_web
```

#### Step 4

Build Admin UI:

```bash
$ cd $PROJECT_ROOT/store_admin
$ make build/admin_ui
```

#### Step 5

Launch containers:

```bash
$ cd $PROJECT_ROOT/store_starter
$ docker compose --profile env --profile app up -d
```

#### Step 6

Migrate database:

```bash
$ cd $PROJECT_ROOT/store_migrate
$ make migrate/changelog/liquibase_host
```

#### Step 7

Initialize keycloak installation:

```bash
# Launch one-off container to initialize keycloak
$ cd $PROJECT_ROOT/store_starter
$ docker compose run env_kcadm
```

#### Step 8

Open in your browser:

- http://admin-ui.local.example.com/
- http://swagger-ui.local.example.com/
- http://kafka-ui.local.example.com/
- http://pgadmin.local.example.com/


## Futher Development

This project is a greate template for your real-world adoptions. If you want to develop your own application:

1. Move this project to your own Git repository.
1. Optionally remove the contents of the `app/` directory from `.gitignore` file.
1. Make some modifications in action scripts (they are located in `./run` directory)
1. Overwrite generated content using `ggcode run` command.
1. Commit & Push it to your Git repository.
