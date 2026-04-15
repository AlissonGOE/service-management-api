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

```
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
```

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

- A service starts with status RECEIVED
- A service can only be completed after payment confirmation
- A client can have multiple services
- A service can contain multiple items
- Service status must follow defined workflow

---

## 📚 Enums / Domain Constants

ServiceStatus

The service order follows a defined lifecycle to ensure proper tracking and business rule validation.

- RECEIVED → Service order has been created and the item was received from the client
- IN_PROGRESS → Service is currently being executed
- WAITING_PARTS → Service is temporarily paused waiting for parts or materials
- COMPLETED → Service execution has finished successfully
- DELIVERED → Item/service has been delivered back to the client
- CANCELLED → Service order was cancelled
- WAITING_EXECUTION -> The service is awaiting execution in the main queue

Notes:
- A service always starts with status RECEIVED
- COMPLETED does not mean the item was delivered yet
- DELIVERED represents the final step of the process

ServiceType

Defines the type of service being performed in a service order.

- REPAIR → Service related to fixing or correcting a defect
- QUOTE → Service performed for cost estimation or evaluation
- WARRANTY → Service covered under warranty conditions
- CUSTOMIZATION → Service focused on modifying or personalizing the item

Notes:
- Service type helps define the purpose of the service order
- It can influence business rules and workflow

ServicePriority

Defines the type of priority in the service order.

- LOW -> Low priority (can wait)
- NORMAL -> Standard priority
- HIGH -> Needs faster attention
- URGENT -> Immediate service

Notes:
- Priority helps define the order in which services should be handled
- URGENT services should be prioritized over all others
- LOW priority services can be scheduled for later execution
- Priority can be used to implement SLA (Service Level Agreement) rules

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
