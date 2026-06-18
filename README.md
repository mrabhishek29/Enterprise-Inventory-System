# Enterprise Inventory Management System

A scalable and maintainable backend application developed using **Spring Boot** that provides RESTful APIs for managing inventory operations, including product management, user management, and order processing.

## 🚀 Features

- Product Management (Create, Read, Update, Delete)
- User Management (Create, Read, Update, Delete)
- Order Management
- DTO-based Request/Response Handling
- Service and Repository Layer Separation
- Global Exception Handling
- Input Validation
- Swagger/OpenAPI Documentation
- Unit Testing with JUnit 5 and Mockito
- RESTful API Design
- **Multi-Container Orchestration**: Production-ready deployment using Docker Compose.
- **Internal DNS Resolution**: Isolated network communication between the app and database.

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|----------|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Data JPA | Data Persistence |
| Hibernate | ORM Framework |
| PostgreSQL | Database |
| **Docker** | Containerization |
| **Docker Compose** | Container Orchestration |
| Maven | Build Tool |
| JUnit 5 | Unit Testing |
| Mockito | Mocking Framework |
| Swagger/OpenAPI | API Documentation |

## 📦 Containerized Deployment Guide

The application is fully containerized and uses Docker Compose to manage both the Spring Boot service and the PostgreSQL database backend locally on an isolated virtual network.

### Prerequisites
- [Docker Desktop](https://docker.com) installed and running.
- Java 21 & Maven installed (if building artifacts natively).


The application will be accessible locally on port `8080`, and the database container will safely persist records inside the managed volume structure across deployments.

## 📂 Project Structure

```text
enterprise-inventory-system
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── src
    ├── main
    │   ├── java/.../controller
    │   │            ├── service
    │   │            │   └── impl
    │   │            ├── repository
    │   │            ├── entity
    │   │            ├── dto
    │   │            ├── exception
    │   │            └── config
    │   └── resources/application.properties
    └── test
```

## 🏗️ Architecture

```text
[ Client Request ]
       ↓ (Port 8080)
┌───────────────────────────────── inventory-network ─────────────────────────────────┐
│                                                                                     │
│  Controller Layer  ──>  Service Layer  ──>  Repository Layer                       │
│  [ inventory-service Container ]                                                    │
│                                                                                     │
│                                       ↓ (Internal DNS: inventory-db)                │
│                                                                                     │
│                                 [ inventory-db Container ] (PostgreSQL 17)          │
└─────────────────────────────────────────────────────────────────────────────────────┘
```

## ✨ Key Highlights

- Developed RESTful APIs for Product, User, and Order Management.
- Implemented layered architecture following Spring Boot best practices.
- Added Swagger UI for API documentation and testing.
- Created unit test cases using JUnit 5 and Mockito.
- Implemented DTO pattern for request and response handling.
- Added centralized exception handling for robust error management.
- Containerized the workflow using tiny-footprint `eclipse-temurin:21-jre` base runtimes.
  
## 🔮 Future Enhancements

- Spring Security with JWT Authentication
- Role-Based Access Control (RBAC)
- CI/CD Pipeline using GitHub Actions
- Integration Testing
- Redis Caching
- Logging and Monitoring

## 👨‍💻 Author

**Abhishek Sharma**

Java Backend Developer | Spring Boot | REST APIs | Microservices | SQL 
