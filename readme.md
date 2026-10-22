# baseSpringBoot

A Spring Boot application using Java 21, Maven, and PostgreSQL. Docker Compose starts the application and its database together.

## Requirements

- Docker Engine or Docker Desktop
- Docker Compose (`docker-compose` command)

## Launch

From the project root, run:

```bash
docker-compose up
```

Compose builds the application image, starts PostgreSQL, waits for the database to become healthy, and then starts the application. On the first launch, building the image may take a few minutes.

Once the application has started, it is available at <http://localhost:8080>. PostgreSQL is exposed on `localhost:5433`.

To run the containers in the background instead, use `docker-compose up -d`. To stop them, run `docker-compose down` from the project root.

The Compose configuration uses a named volume (`postgres_data`) to retain database data when the containers are stopped.

Adminer is available at <http://localhost:8081/?pgsql=postgres&username=postgres&db=base_db> for managing the PostgreSQL database through a web interface.

## Reset the database

If the database is in a bad state and you want to start over, run:

```bash
docker compose down -v
docker compose up -d
```

The `-v` option deletes the database volume, so **all stored database data will be permanently removed**. The second command recreates the containers in the background.