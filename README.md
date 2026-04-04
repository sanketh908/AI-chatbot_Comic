# AI Chatbot Comic

AI Chatbot Comic is a Spring Boot–based backend application that lets users chat with an AI model and generate content from their prompts.  
It includes user authentication, password reset via email, and JWT‑based security,Role based autentication and aturaization too.it is a very simple project to learn the new going trand <b> Ai </b> this is just a try to get batter in spring and java 

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

## API Overview

Below is a high‑level summary of the main REST endpoints.  
For full details (request/response models, auth, examples), open Swagger UI:

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

---

### HomeController

Public endpoints for signup, login, password reset, health checks, and basic pages.

- `POST /home/signup`  
  Register a new user account.

- `POST /home/reset-password`  
  Reset password using a valid reset token.

- `POST /home/login`  
  Authenticate user and return JWT token.

- `POST /home/forgot-password`  
  Request a password reset link via email.

- `GET /home/ping`  
  Health‑check / ping endpoint.

- `GET /home/chat`  
  Public/home chat page endpoint (view / basic info).

- `GET /home/about`  
  “About” page endpoint.

- `GET /home/`  
  Home page/root endpoint.

---

### ChatbotController

Chatbot endpoints for getting responses, history, and managing chat history.  
These endpoints require authentication.

- `GET /chat/response/stateless`  
  Get a chatbot response without storing conversation state.

- `GET /chat/response/statefull`  
  Get a chatbot response using stored conversation context.

- `GET /chat/ping`  
  Chatbot‑specific health‑check / ping.

- `GET /chat/history`  
  Get the authenticated user’s chat history.

- `DELETE /chat/deletehistory/{id}`  
  Delete a specific chat history entry by ID.

- `DELETE /chat/clearAllhistory`  
  Delete **all** chat history for the authenticated user.

---

### AdminController

Admin operations. Only users with the `ROLE_ADMIN` authority can access these.

- `POST /admin/deleteAccount`  
  Delete a user account (admin action).

- `POST /admin/addAdmin`  
  Promote a user to admin / add an admin.

- `GET /admin/getAllUsers`  
  Get a list of all users.

- `DELETE /admin/deleteUser/{id}`  
  Delete a specific user by ID.

---

### UserController

Authenticated user account management.

- `PUT /user/Editusername`  
  Edit the username of the currently authenticated user.

- `POST /user/deleteAccount`  
  Delete the currently authenticated user’s own account.

---

### DTO Schemas

The main request/response models shown in Swagger:

- `SignupDTO` – used for signup requests  
- `LoginDTO` – used for login requests  
- `PromptDTO` – used for chatbot prompt requests  
- `Response` – standard AI/chatbot response wrapper  
- `PromptResponse` – response containing prompt + AI output details
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
## Screenshots:
<img width="1917" height="868" alt="image" src="https://github.com/user-attachments/assets/8d7c6652-81ca-46d4-858f-f8417831914b" />
<img width="1917" height="872" alt="image" src="https://github.com/user-attachments/assets/54c3d62f-b4c2-4cfe-9170-42a7db677ba6" />
<img width="1918" height="873" alt="image" src="https://github.com/user-attachments/assets/63caed76-dbe1-4375-928e-00d3c80558d0" />
<img width="1919" height="867" alt="image" src="https://github.com/user-attachments/assets/fa82aec7-a1a4-4b83-a7f0-f663506f1881" />
<img width="1919" height="865" alt="image" src="https://github.com/user-attachments/assets/34589cb4-5a99-47a4-a6aa-62859592eea7" />





<hr>


## Author

- **Sanketh** – [GitHub Profile](https://github.com/sanketh908)
