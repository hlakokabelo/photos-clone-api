# Photos Clone API

A simple REST API built with Java and Spring Boot for uploading, retrieving, downloading, and deleting photos.

This project was created to practise Spring Boot development, REST APIs, dependency injection, and database operations.

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Data JDBC
- H2 Database
- Gradle
- Docker

## Features

- Upload photos
- Retrieve photo information
- Download photos
- Delete photos
- Validate incoming requests

## Getting Started

**1. Clone the repository**

```bash
git clone <repository-url>
cd photos-clone
```

**2. Run the application**

On Windows:

```bash
.\gradlew.bat bootRun
```

On Linux/macOS:

```bash
./gradlew bootRun
```

The API will be available at:

`http://localhost:8080`

## Running with Docker

Build the Docker image:

```bash
docker build -t photos-clone .
```

Run the container:

```bash
docker run -p 8080:8080 photos-clone
```

## Database

The project currently uses H2 for development and testing. Data stored in an in-memory H2 database is lost when the application restarts.

## CI/CD

GitHub Actions is configured to build the project using Gradle on pushes and pull requests to the `main` branch.
