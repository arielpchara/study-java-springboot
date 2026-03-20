# I know Spring Boot

![I know kung fu](assets/i-know-kung-fu.jpg)

There's that scene in The Matrix where Neo wakes up from the training chair and just says it: *"I know kung fu."*
Not "I read about kung fu." Not "I watched some videos." He *knows* it. Wired in. Ready.

I'm not Neo. But I've been chasing that feeling in 2026.

## The challenge I made for myself

At the end of 2025 I made a deal with myself: learn at least three new languages in 2026. Not the hello world kind. Not the "I followed a tutorial" kind. The real kind, where you hit something complex, sit with it, and come out the other side actually understanding why it works.

Java was first on the list.

I'd already seen a lot of it. The syntax wasn't alien. The JVM wasn't a mystery. But *knowing about* something and *knowing* something are different things. I wanted to close that gap. So I built this repository as a living notebook. Every problem I face here is a problem I chose to solve, not copy-paste my way through.

## The tools I'm using and the ones that fight back

Maybe you'd expect me to say "I'm using AI to learn faster." That's partly true. I use multiple AI tools to argue with, to clarify my thinking, to challenge assumptions I didn't know I had. But the honest answer is that **AI alone isn't enough**.

The other half is other developers. Real people I provoke with obstacles: "here's what I built, what's wrong with it?" or "why would this break?" That friction is the point. Learning without resistance is just reading.

## The Spring Boot journey begins here

This repository starts with Spring Boot. Not because it's the most exciting thing in the Java ecosystem, but because it's the right place to build muscle memory. REST endpoints, dependency injection, JPA, Docker. These are the foundations that everything else sits on.

The complexity will come. I'm saving it for when the foundations feel solid.

## What going deep actually means to me

I could have generated this project in thirty seconds. The AI tools would have done it happily.

But I didn't. Every file here was written with a question in mind. Why does this annotation exist? What does this config actually do to my data over time? Why does this thing need to start before that other thing? Each small question leads to a real answer, and real answers stick.

Small things compound. Answering them yourself, even when the answer is one message away, is the difference between knowing *about* something and knowing it.

## To wrap up

This is a study repository, not a production system. It's a place where I'm allowed to be wrong, to ask obvious questions, to rebuild something three times because the third version finally makes sense to me.

The 2026 challenge isn't about finishing three languages. It's about the quality of the understanding I carry out the other side.

Neo didn't just load the program. He used it in a fight.

I'm still in the chair, but the upload is going well.

---

## Project reference

## Quick start



The recommended way to run the project (no local Java or Maven needed):

```bash
docker compose up --watch
```

This starts both the app and a PostgreSQL database. The app restarts automatically when you edit files under `src/`, and rebuilds the image when `pom.xml` changes.

Once running, open the API docs:

```
http://localhost:8080/docs
```

## Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (includes Docker Compose)
- Java 17+ and Maven — **only** needed for local runs outside Docker

## What the project does

The application exposes these REST endpoints:

| Method | Path | Description |
|---|---|---|
| `GET` | `/api/hello` | Health/welcome message |
| `GET` | `/api/greetings` | List all saved greetings |
| `POST` | `/api/greetings` | Save a new greeting |

Example — `GET /api/hello`:

```json
{
  "message": "Welcome to the Spring Boot demo project",
  "status": "ok"
}
```

Example — `POST /api/greetings` with body `{"message": "Hello"}`:

```json
{
  "id": 1,
  "message": "Hello",
  "createdAt": "2026-03-20T13:00:00Z"
}
```

## Project structure

```text
src/
  main/
    java/com/pchara/study/springbootdemo/
      SpringbootDemoApplication.java
      controller/WelcomeController.java
      model/Greeting.java
      repository/GreetingRepository.java
      config/OpenApiConfig.java
    resources/
      static/docs/index.html     ← Stoplight Elements UI
      application.properties
  test/
    java/com/pchara/study/springbootdemo/
      SpringbootDemoApplicationTests.java
pom.xml
Dockerfile
compose.yaml
```

## Tech stack

- Java 17
- Spring Boot 3.3
- Spring Data JPA + PostgreSQL 17 (Alpine)
- springdoc-openapi 2.6
- Stoplight Elements (CDN)
- Maven / JUnit 5

## Database

The app connects to **PostgreSQL 17 Alpine** started by `compose.yaml`.

Connection is configured via environment variables (defaults match the Compose setup):

| Variable | Default |
|---|---|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://localhost:5432/springbootdemo` |
| `SPRING_DATASOURCE_USERNAME` | `demo` |
| `SPRING_DATASOURCE_PASSWORD` | `demo` |

Hibernate manages the schema automatically on startup (`ddl-auto=update`).
Database data is persisted in a named Docker volume (`db_data`) and survives container restarts.

## How to run with Docker (production image)

> **Note:** this starts only the app container — you need a separate PostgreSQL instance.
> For a complete local setup, use `docker compose up` instead.

```bash
docker build -t springboot-demo .
docker run --rm -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://<host>:5432/springbootdemo \
  -e SPRING_DATASOURCE_USERNAME=demo \
  -e SPRING_DATASOURCE_PASSWORD=demo \
  springboot-demo
```

The `Dockerfile` uses a multi-stage build:

- `dev` — runs the app with `mvn spring-boot:run` for development (watch mode target)
- `build` — packages the JAR
- Final stage — runs the JAR on a minimal Java 17 JRE image

## How to run locally (without Docker)

Make sure Java 17+ and Maven are installed and a PostgreSQL instance is accessible, then:

```bash
mvn spring-boot:run
```

The app will start on `http://localhost:8080`.

## How to test

```bash
mvn test
```

## API documentation

When the application is running:

| URL | Description |
|---|---|
| `http://localhost:8080/docs` | **Stoplight Elements** — rich sidebar UI (recommended) |
| `http://localhost:8080/swagger-ui.html` | Swagger UI |
| `http://localhost:8080/v3/api-docs` | Raw OpenAPI JSON |

Stoplight Elements is served as a static page from `src/main/resources/static/docs/index.html`, loaded from CDN, and reads the OpenAPI spec generated by springdoc at `/v3/api-docs`.

## Suggested next steps

- Add validation (`spring-boot-starter-validation`) and proper error responses
- Introduce a service layer between controller and repository
- Add authentication (Spring Security + JWT)
- Add integration tests with Testcontainers
