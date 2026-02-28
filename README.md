<div align="center">

# Transaction Service API

**A secure, containerised Spring Boot REST API for simulating financial transactions**

[![Java 17](https://img.shields.io/badge/Java-17-blue?logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot 4.0](https://img.shields.io/badge/Spring%20Boot-4.0-green?logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Docker](https://img.shields.io/badge/Docker-ready-blue?logo=docker&logoColor=white)](https://hub.docker.com/r/laurabailie/transaction-service)
[![Swagger Docs](https://img.shields.io/badge/Swagger-OpenAPI%203.0-brightgreen?logo=swagger)](http://localhost:8080/swagger-ui.html)

</div>

Secure backend API demonstrating **Spring Boot**, **JWT authentication**, **JPA/Hibernate**, **Swagger documentation**, **unit testing**, and **Docker containerisation** — built as a FinTech-style transaction processor.

## Features

- RESTful endpoints for transaction CRUD
- JWT-based authentication (HS256 symmetric)
- Input validation & global exception handling
- In-memory H2 database with auto-schema
- Layered architecture (Controller → Service → Repository)
- Interactive Swagger UI (springdoc-openapi)
- Unit tests (JUnit 5 + Mockito)
- Dockerised for easy deployment & portability

## Tech Stack

| Category              | Tools & Technologies                                 |
|-----------------------|------------------------------------------------------|
| Language              | Java 17                                              |
| Framework             | Spring Boot 4.0.3                                    |
| Security              | Spring Security + OAuth2 Resource Server (JWT)       |
| Persistence           | Spring Data JPA + H2 (in-memory)                     |
| Documentation         | springdoc-openapi 3.0+ (Swagger UI)                  |
| Token Handling        | jjwt 0.12                                            |
| Testing               | JUnit 5, Mockito                                     |
| Build & Container     | Maven, Docker                                        |
| IDE/Tools             | VS Code, Git                                         |

## Quick Start

### Prerequisites

- Java 17 (JDK)
- Maven (or use bundled `./mvnw`)
- Docker (optional)

### Local Development (Maven)

```bash
# Clone repo
git clone https://github.com/LauraBailie/transaction-service.git
cd transaction-service

# Build & run
mvn clean package
java -jar target/transaction-service-0.0.1-SNAPSHOT.jar
Docker (Recommended)
Bash# Build image
mvn clean package
docker build -t transaction-service .

# Run container
docker run -p 8080:8080 transaction-service

# Endpoints Overview
MethodEndpointDescriptionAuth?POST/api/auth/loginObtain JWT tokenNoPOST/api/transactionsCreate a transactionYesGET/api/transactionsList all transactionsYesGET/api/transactions/{id}Get transaction by IDYes

#Authentication Example
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser"}'

→ Returns {"token": "eyJhbGciOiJIUzI1NiIs..."}

# Create Transaction (with Bearer token)
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <your-token>" \
  -d '{"amount": 1500.00, "currency": "ZAR", "description": "Salary"}'

# Interactive API Docs
Open in browser:
http://localhost:8080/swagger-ui.html

Health Check
http://localhost:8080/actuator/health → {"status":"UP"}

#Project Structure
transaction-service/
├── src/
│   ├── main/
│   │   ├── java/.../controller/     REST endpoints
│   │   ├── java/.../service/        Business logic
│   │   ├── java/.../repository/     JPA interfaces
│   │   ├── java/.../model/          Entities
│   │   ├── java/.../security/       JWT configuration
│   │   └── resources/
│   │       └── application.yaml     Config (jwt.secret, etc.)
│   └── test/                        Unit & integration tests
├── Dockerfile                       Docker configuration
├── pom.xml                          Maven dependencies
└── README.md

# Security Notes

JWT secret configured via jwt.secret (never commit real value!)
Short-lived tokens (~15 min expiry)
Stateless (no sessions)
CSRF disabled (REST API)

# Future Enhancements

Refresh token support
Role-based access control (ADMIN/USER)
PostgreSQL + Flyway migrations
Testcontainers for integration tests
CI/CD with GitHub Actions
Deploy to Render / Railway

# License
MIT License

Made by Laura Bailie
Cape Town, South Africa
GitHub | Email