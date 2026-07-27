# Bookstore REST API

A Spring Boot RESTful API for managing a digital bookstore catalog, including books, authors, and categories with PostgreSQL persistence, input validation, role-based security, and Swagger UI documentation.

---

## Tech Stack

* Java 17
* Spring Boot 3
* Spring Security
* Spring Data JPA / Hibernate
* PostgreSQL
* Swagger UI (springdoc-openapi)
* Maven

---

## Key Features

* Full CRUD operations for Books, Authors, and Categories
* Relational database mappings (@ManyToOne, @OneToMany)
* Field validation on requests (@NotBlank, @Min, @Size)
* Global exception handling with custom JSON error responses
* Role-based access control (Public GET requests; ADMIN role required for POST, PUT, DELETE)
* Interactive Swagger API documentation

---

## Getting Started

### Prerequisites

* JDK 17 or higher
* PostgreSQL installed and running locally
* Maven (or the included wrapper)


To Run
1. Clone the repository:
  git clone [https://github.com/alazar1270/bookstore_springboot_api.git](https://github.com/alazar1270/bookstore_springboot_api.git)
2. Navigate into the project folder:
  cd bookstore_springboot_api
3. Start the application:
  ./mvnw spring-boot:run

Basic Endpoints

  GET /api/books - List all books
  
  GET /api/books/{id} - Get book details
  
  POST /api/books - Add a new book (Admin only)
  
  PUT /api/books/{id} - Update a book (Admin only)
  
  DELETE /api/books/{id} - Delete a book (Admin only)
  
  GET /api/authors - List all authors
  
  GET /api/categories - List all categories
  
### Database Setup

Create a local database named `bookstore_db`:
  
```sql
CREATE DATABASE bookstore_db;

