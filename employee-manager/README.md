# Employee Registration System

A Spring Boot web application to register and manage employees.

## Features
- Add, view, edit and delete employees (CRUD)
- Search employees by full name
- Form validation (email, 10-digit phone, age, salary)
- Duplicate email check
- REST API at `/api/employees`

## Tech Stack
Java, Spring Boot, Spring Data JPA, Thymeleaf, MySQL, Lombok, Maven

## How to Run
1. Create MySQL database `employee_db`
2. Copy `secrets.properties.example` to `secrets.properties` and enter your MySQL username and password
3. Run `.\mvnw spring-boot:run`
4. Open `http://localhost:8080/employees`