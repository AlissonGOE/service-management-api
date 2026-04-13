# Service Management API

A RESTful API built with Spring Boot for managing service orders in a service management system.

This project simulates a real-world system used to manage clients, employees, and service orders, including business rules, status tracking, and inventory control.

---

## 🎯 Objective

The main goal of this project is to practice backend development using Spring Boot, applying clean architecture principles, DTO pattern, layered architecture, and business rule validation.

It is also designed as a portfolio project to demonstrate backend development skills.

---

## ⚙️ Technologies

- Java 17
- Spring Boot 3.5.13
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database (development)
- Flyway (database migration)
- Lombok
- Springdoc OpenAPI (Swagger)

---

## 🏗️ Architecture

The project follows a layered architecture:

- Controller → Handles HTTP requests (API layer)
- Service → Business logic layer
- Repository → Database access layer
- DTO → Data transfer between layers
- Mapper → Entity ↔ DTO conversion
- Model (Entity) → Database structure
- Exception Handling → Centralized error handling

---

## 📁 Project Structure

src/main/java/com/alisson/servicemanagement/
├── controller
├── service
├── repository
├── model
├── dto
├── mapper
├── enums
├── exception
└── config


---

## 📌 Features

- Client registration and management
- Employee registration
- Service order creation and tracking
- Service status management
- Business rules validation
- RESTful API design
- Swagger API documentation

---

## 📜 Business Rules

- A service starts with status IN_PROGRESS
- A service can only be completed after payment confirmation
- A client can have multiple services
- A service can contain multiple items
- Service status must follow defined workflow

---

## 🗄️ Database

The project uses H2 Database for development purposes.

Flyway is used for database versioning and migration control.

---

## 🌐 API Documentation

After running the project, access Swagger UI:

http://localhost:8080/swagger-ui.html

---

## 🚀 How to Run

### Clone the repository

git clone https://github.com/AlissonGOE/service-management-api.git

### Run the project

./mvnw spring-boot:run

Or run directly from your IDE (IntelliJ / VSCode).

---

## 📊 Future Improvements

- Spring Security (authentication & authorization)
- JWT-based login system
- PostgreSQL integration
- Docker support
- Unit and integration tests expansion
- Frontend integration

---

## 👨‍💻 Author

Alisson Giovane  
GitHub: https://github.com/AlissonGOE

---

## 📌 Notes

This project is continuously evolving as part of a backend development learning journey. New features and improvements will be added over time following best practices.
