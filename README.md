# Online Store

## Quick Start

### Prerequisites

#### Step 1

You need Java 17+ and Maven 3.8.8+ to build and run the application. Make sure that your Maven points to Java 17+.

#### Step 2

Add this routes to your hosts file:

```
127.0.0.1   api.local.example.com
127.0.0.1   admin-ui.local.example.com
127.0.0.1   swagger-ui.local.example.com
127.0.0.1   kafka-ui.local.example.com
127.0.0.1   pgadmin.local.example.com
127.0.0.1   auth.local.example.com
```

It is usually located in `/etc/hosts` on Linux.

#### Step 3

Make sure that ports `80`, `5432` and `9092` are free on your system.

### Launching

#### Step 1

Export PROJECT_ROOT environment variable:

```bash
# Just to simplify further instructions:
$ export PROJECT_ROOT=$(pwd)
```

#### Step 2

Build back-end application:

```bash
$ cd $PROJECT_ROOT/store_backend
$ make build/assembly_web
```

#### Step 3

Build Admin UI:

```bash
$ cd $PROJECT_ROOT/store_admin
$ make build/admin_ui
```

#### Step 4

Launch containers:

```bash
$ cd $PROJECT_ROOT/store_starter
$ docker compose --profile env --profile app up -d
```

#### Step 5

Migrate database:

```bash
$ cd $PROJECT_ROOT/store_migrate
$ make migrate/changelog/liquibase_host
```

#### Step 6

Initialize keycloak installation:

```bash
# Launch one-off container to initialize keycloak
$ cd $PROJECT_ROOT/store_starter
$ docker compose run env_kcadm
```

#### Step 7

Build and launch admin application:

```bash
$ cd $PROJECT_ROOT/store_admin
$ yarn
$ yarn dev
```

#### Step 8

Open in your browser:

- http://admin-ui.local.example.com/
- http://swagger-ui.local.example.com/
- http://kafka-ui.local.example.com/
- http://pgadmin.local.example.com/
- http://localhost:5173/
