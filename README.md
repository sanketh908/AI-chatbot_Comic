# AI Chatbot Comic

AI Chatbot Comic is a Spring Boot–based backend application that lets users chat with an AI model and generate comic‑style content from their prompts.  
It includes user authentication, password reset via email, and JWT‑based security.

---

## Features

- **User management**
  - User registration and login
  - JWT‑based authentication and authorization
  - Password reset flow using email and reset tokens

- **AI chat & comic generation**
  - Text‑based chatbot interactions
  - Integration with LLaMA and/or LLaVA style services (`LLamaService`, `LlavaService`)
  - Prompt management and “thinking” flow (`PromptService`, `ThinkingService`)

- **Admin & management**
  - Admin endpoints for managing users and prompts (via `AdminController`)
  - Storage of user prompts and metadata in a relational database (MySQL)

- **Developer friendly**
  - REST API built with Spring Boot
  - API documentation via Springdoc OpenAPI (Swagger UI)
  - Layered architecture (Controller → Service → Repository → Entity)

---

## Tech Stack

- **Backend:** Java 17, Spring Boot 3.2.x
- **Frameworks/Libs:**
  - Spring Web (REST endpoints)
  - Spring Data JPA (database access)
  - Spring Security (authentication and authorization)
  - Spring Validation
  - Spring Mail (email sending)
  - JSON Web Tokens (`io.jsonwebtoken:jjwt-*`)
  - Lombok (to reduce boilerplate)
  - Springdoc OpenAPI (Swagger UI)
- **Database:** MySQL
- **Build Tool:** Maven

---

## Project Structure

Main packages under `com.sanketh.AIChatBot`:

- `controller` – REST controllers:
  - `AdminController`
  - `ChatbotController`
  - `HomeController`
  - `UserController`
- `service` – business logic:
  - `UserService`, `UserDetailsStorage`
  - `LLamaService`, `LlavaService`
  - `PromptService`, `ThinkingService`
  - `MailService`, `ResetTokenService`
- `entity` – JPA entities:
  - `User`, `Prompt`, `PasswordResetToken`
- `repository` – Spring Data JPA repositories
- `security` – Spring Security configuration, filters, JWT utilities
- `exception`, `enums`, `utils` – shared types and helpers
- `config` – additional configuration classes

> Note: Package names may still be capitalized in code (`Controller`, `Service`, `Entity`, etc.).  
> For best Java practice you can rename them to lowercase (`controller`, `service`, `entity`, …).

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- MySQL server running and reachable
- An SMTP configuration (for password reset emails), or a test mail account

### Clone the Repository

```bash
git clone https://github.com/sanketh908/AI-chatbot_Comic.git
cd AI-chatbot_Comic
```

### Configure the Application

Edit `src/main/resources/application.properties` and set at least:

```properties
# Application
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/aichatbot_db
spring.datasource.username=YOUR_DB_USER
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update

# JWT
app.jwt.secret=YOUR_SUPER_SECRET_KEY
app.jwt.expiration-ms=3600000

# Mail
spring.mail.host=YOUR_SMTP_HOST
spring.mail.port=587
spring.mail.username=YOUR_EMAIL
spring.mail.password=YOUR_EMAIL_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

> Replace all `YOUR_...` values with your actual configuration.

### Build and Run

Using Maven:

```bash
mvn clean package
```

Run the application:

```bash
mvn spring-boot:run
```

Or run the built JAR:

```bash
java -jar target/AIChatBot-0.0.1-SNAPSHOT.jar
```

The backend will start on `http://localhost:8080` by default.

---

## API Overview

> Exact paths may differ depending on your controller mappings.  
> These are typical examples; adjust them to match your actual code.

### Authentication & Users

- `POST /api/auth/register`  
  Register a new user.

- `POST /api/auth/login`  
  Authenticate and receive a JWT token.

- `POST /api/auth/request-reset`  
  Request a password reset email.

- `POST /api/auth/reset-password`  
  Reset password using a reset token.

### Chatbot & Comic

- `POST /api/chat`  
  Send a text prompt to the chatbot and receive a response.

- `POST /api/chat/comic`  
  Send a prompt to generate comic‑style content (LLaVA‑based).

### Admin

- `GET /api/admin/users`  
  List users (admin‑secured endpoint).

- `GET /api/admin/prompts`  
  View saved prompts / history.

(See Swagger UI for full and accurate list of endpoints.)

---

## API Documentation (Swagger / OpenAPI)

With Springdoc OpenAPI configured, Swagger UI is typically available at:

- `http://localhost:8080/swagger-ui/index.html`

From there you can explore all endpoints, request/response formats, and test calls directly from your browser.

---

## Security

- JWT‑based authentication for protected endpoints
- Spring Security configuration in the `security` package:
  - Configures which endpoints are public (e.g. registration, login, reset‑password)
  - Protects admin and chatbot endpoints with JWT filters
- Passwords should be stored using a password encoder (e.g. BCrypt)

Make sure:

- You **never** commit real JWT secrets, database passwords, or email credentials to Git.
- You use environment variables or an external config for production secrets.

---

## Testing

Basic guidelines for adding tests:

- Place tests under `src/test/java`
- Use `spring-boot-starter-test` (already in `pom.xml`)
- Add:
  - Unit tests for `UserService`, `PromptService`, `ResetTokenService`, etc.
  - At least one integration test for a controller (e.g. `ChatbotController`)

Example test command:

```bash
mvn test
```

---





## Author

- **Sanketh** – [GitHub Profile](https://github.com/sanketh908)
