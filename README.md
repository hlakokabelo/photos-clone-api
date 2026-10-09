# Photos Clone API

A REST API built with **Java 21 and Spring Boot 4** for uploading, managing, searching, and downloading photos.

This project was developed to gain hands-on experience with Spring Boot, RESTful API development, Spring Security, JWT authentication, dependency injection, and database operations.

## Tech Stack

- **Backend:** Java 21, Spring Boot 4
- **Database:** H2, Spring Data JDBC
- **Security:** Spring Security, JWT, BCrypt
- **API Documentation:** Springdoc OpenAPI (Swagger UI)
- **Build Tool:** Gradle
- **Containerization:** Docker
- **CI/CD:** GitHub Actions
- **Frontend:** HTML, CSS, JavaScript (upload interface)

## Features

- User registration and login with JWT authentication
- Secure password hashing using BCrypt
- Upload photos with a maximum file size of 100 MB
- Upload multiple photos through a browser interface
- Associate uploaded photos with user accounts
- Retrieve individual photos or list all photos
- Search photos by filename and content type
- Limit the number of returned results
- Download photos in their original format
- Delete photos
- Request validation and custom HTTP error responses
- Interactive API documentation with Swagger UI

## API Endpoints

| Method | Endpoint             | Description                               |
| ------ | -------------------- | ----------------------------------------- |
| GET    | `/api`               | API documentation                         |
| POST   | `/api/auth/register` | Register a user                           |
| POST   | `/api/auth/login`    | Authenticate and receive a JWT            |
| GET    | `/api/photos`        | Retrieve photos with optional filters     |
| GET    | `/api/photo/{id}`    | Retrieve photo metadata                   |
| POST   | `/api/photo`         | Upload a photo (JWT required)             |
| POST   | `/api/photo/manager` | Upload a photo using the manager endpoint |
| DELETE | `/api/photo/{id}`    | Delete a photo                            |
| GET    | `/api/download/{id}` | Download a photo                          |

### Filtering Photos

The `/api/photos` endpoint supports optional query parameters:

| Parameter     | Description                                          |
| ------------- | ---------------------------------------------------- |
| `fileName`    | Filter by filename (case-insensitive, partial match) |
| `contentType` | Filter by MIME type                                  |
| `limit`       | Maximum number of results (default: 1000)            |

Example:

```http
GET /api/photos?fileName=holiday&contentType=image&limit=10
```

## Web Interface

A lightweight browser interface is available for uploading photos individually or in batches.

- **Upload Interface:** `/upload.html`
- **Swagger UI:** `/swagger-ui/index.html`
- **API Documentation:** `/api`

The upload interface sends a separate API request for each selected image and displays individual upload results.

## Getting Started

### 1. Clone the repository

```bash
git clone <repository-url>
cd photos-clone
```

### 2. Configure environment variables

Set the following environment variable before starting the application:

```env
JWT_SECRET=your-base64-encoded-secret
```

Generate a secure secret using:

```bash
openssl rand -base64 32
```

Do not commit real secrets to GitHub.

### 3. Run the application

**Windows:**

```bash
.\gradlew.bat bootRun
```

**Linux/macOS:**

```bash
./gradlew bootRun
```

The application will be available at:

`http://localhost:8080`

## Running with Docker

Build the Docker image:

```bash
docker build -t photos-clone .
```

Run the container with the JWT secret configured:

```bash
docker run -p 8080:8080 -e JWT_SECRET=<your-secret> photos-clone
```

## Database

The application uses **H2** with **Spring Data JDBC** for database operations.

The database stores photo metadata, image data, user accounts, and photo ownership information.

When using an in-memory H2 database, stored data is lost when the application restarts.

## CI/CD

GitHub Actions automatically builds the project using Gradle on pushes and pull requests to the `main` branch.

## Project Purpose

This project serves as a practical introduction to the Spring ecosystem, focusing on REST API design, authentication, persistence, validation, and backend application architecture.
