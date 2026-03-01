<div align="center">

# Transaction Service API

Secure RESTful backend for personal finance tracking  
User authentication (JWT), transaction CRUD (income/expense/transfer), PostgreSQL + Flyway, clean architecture

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16+-336791?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org)
[![JWT](https://img.shields.io/badge/JWT-Auth-black?style=for-the-badge&logo=json-web-tokens)](https://jwt.io)
[![Render Deployed](https://img.shields.io/badge/Deployed%20on-Render-46E3B7?style=for-the-badge&logo=render)](https://render.com)

**Live Demo**: https://transaction-service-29ts.onrender.com  
**Swagger UI (local)**: http://localhost:8080/swagger-ui.html

</div>

## ✨ Features

- **JWT Authentication** — secure registration, login, role-based claims
- **Transaction Management** — create, list income/expense/transfer entries
- **Input Validation** — DTOs + Jakarta Bean Validation + clean error responses
- **Database** — Flyway migrations, PostgreSQL (production), H2 (dev/tests)
- **Clean Architecture** — layered structure (controllers → services → repositories)
- **Testing** — unit tests (Mockito), context loading with Flyway integration
- **Deployment Ready** — Render-friendly with environment variables

## 🛠️ Tech Stack

| Category      | Technology                        |
| ------------- | --------------------------------- |
| Language      | Java 17                           |
| Framework     | Spring Boot 3                     |
| Security      | Spring Security + JWT             |
| Persistence   | Spring Data JPA + Hibernate       |
| Database      | PostgreSQL (prod) / H2 (dev/test) |
| Migrations    | Flyway                            |
| Validation    | Jakarta Bean Validation           |
| Testing       | JUnit 5 + Mockito                 |
| Deployment    | Render                            |
| Documentation | Springdoc OpenAPI / Swagger UI    |

## 🚀 Quick Start (Local)

1. Clone the repository

   ```bash
   git clone https://github.com/LauraBaillie/transaction-service.git
   cd transaction-service

   ```

2. Set environment variables (or create .env file)

  ```
    export DB_URL=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    export DB_USERNAME=sa
    export DB_PASSWORD=""
    export JWT_SECRET=super-secret-local-test-key-do-not-use-in-production-abcdefghijklmnopqrstuvwxyz123456
    export JWT_EXPIRATION=86400000   # 24 hours in ms
  ```

3. Build & run

  ```
    mvn clean spring-boot:run
    Test endpoints (Postman / curl)
    POST /api/auth/register → { "username": "testuser", "password": "test123" }
    POST /api/auth/login → get JWT token
    POST /api/transactions (with Authorization: Bearer <token>) → create transaction
    GET /api/transactions → list user's transactions
  ```

🧪 Testing
All tests pass with clean separation:
Bashmvn clean test

- Unit tests — service layer (Mockito)
- Integration-style — context loading + Flyway migrations in H2
- Coverage — focused on business logic & security mocking

🌐 Deployment (Render)

1. Connect GitHub repo to Render
2. Build command: mvn clean package
3. Start command: java -jar target/transaction-service-\*.jar
4. Set environment variables in Render dashboard:
   - DB_URL, DB_USERNAME, DB_PASSWORD (use Render PostgreSQL add-on)
   - JWT_SECRET, JWT_EXPIRATION
