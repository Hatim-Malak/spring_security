# Spring Security Demo

A Spring Boot application demonstrating Spring Security features including user authentication, authorization, and basic CRUD operations.

## Prerequisites

- Java 25
- Maven 3.6+
- PostgreSQL database (configured via Supabase)

## Setup

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd spring_security
   ```

2. Configure the database:
   - Update `src/main/resources/application.properties` with your PostgreSQL connection details.
   - The application uses JPA with Hibernate for database operations.

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

   Or on Windows:
   ```cmd
   mvnw.cmd spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## Default Credentials

- Username: `navin`
- Password: `hatim00`

## API Endpoints

### Public Endpoints
- `GET /` - Greeting with session ID
- `GET /csrf` - Get CSRF token (though CSRF is disabled in config)
- `POST /register` - Register a new user

### Protected Endpoints (Require Authentication)
- `GET /students` - Get list of students
- `POST /student` - Add a new student

All requests to protected endpoints require HTTP Basic Authentication.

## Features

- User registration with password encryption (BCrypt)
- HTTP Basic Authentication
- Stateless session management
- JPA integration with PostgreSQL
- H2 Console for development (if configured)
- CSRF protection disabled for simplicity

## Technologies Used

- Spring Boot 4.0.3
- Spring Security
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- BCrypt for password encoding

## Configuration

The application is configured in `src/main/java/spring_security/config/Config.java` with:
- Disabled CSRF
- HTTP Basic authentication
- Stateless sessions
- Custom UserDetailsService

## Database

The application uses PostgreSQL hosted on Supabase. Update the connection details in `application.properties` if needed.

## Testing

Run tests with:
```bash
./mvnw test
```