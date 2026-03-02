# Ocean View Resort Hotel Reservation Management System

## 📌 Overview

The Hotel Reservation Management System is a Java EE web application developed using a layered architecture approach.  
The system manages reservations, billing, reporting, user roles, and notifications in a structured and scalable way.

This project demonstrates:

- Object-Oriented Design (OOD)
- SOLID Principles
- Design Patterns
- Test-Driven Development (TDD)
- CI/CD with GitHub Actions
- Semantic Versioning
- Multi-branch Git Workflow

---

##  Architecture

The system follows **Layered Architecture**:

Presentation Layer (JSP / Servlets)  
Service Layer (Business Logic)  
Repository Layer (Data Access)  
Infrastructure Layer (DB, Email, Utilities)

---

##  Features

###  Authentication & User Management
- Secure login & registration
- Role-based access control (Admin / Staff)
- Update profile
- Password reset
- Username validation

---

###  Reservation Management
- Create reservations
- Room availability validation
- Cancel reservations
- Update reservations
- Search reservations
- Monthly filtering

---

###  Room Management
- Add rooms (Admin only)
- View all rooms
- Availability status by date range
- Date validation logic

---

###  Billing System
- Bill generation per reservation
- Total calculation:
    - Price per night
    - Number of nights
    - Tax percentage
- Printable invoice support

---

###  Reporting & Dashboard
- Revenue report (date range)
- Monthly revenue
- Reservation statistics
- Room occupancy report
- Dashboard analytics

---

###  Notification System
- Email notification on:
    - Reservation creation
    - Reservation cancellation
- Observer Design Pattern used
- Notification logging

---

### ❓ Help Guide System
- Role-based help guide
- Strategy Pattern implementation
- Professional accordion UI layout

---

##  Test-Driven Development (TDD)

Unit tests implemented for:

- UserService
- RoomService
- BillingService
- ReportService
- NotificationObserver

Fake repositories were used to isolate database dependencies.

Maven Surefire is used for test execution.

---

##  Branch Strategy

The project follows a professional multi-branch workflow:

feature/* → develop → QA → regression → master

### Branch Roles

- feature/* → Individual feature development
- develop → Integration branch
- QA → Quality assurance testing
- regression → Stability verification
- master → Production-ready code

Branch protection rules are enforced on master.

---

##  CI/CD

GitHub Actions is configured to:

- Build project automatically
- Run all unit tests
- Block merge if tests fail

CI status check is required before merging to protected branches.

---

##  Versioning

This project follows **Semantic Versioning (MAJOR.MINOR.PATCH)**:

| Version | Description |
|---------|-------------|
| v0.1.0 | Core reservation & billing implementation |
| v0.2.0 | Reporting & notification features |
| v0.3.0 | UI modernization |
| v0.3.1 | Bug fix (icon encoding issue) |
| v1.0.0 | First stable production release |

---

##  Technologies Used

- Java 8
- Java EE (Servlet & JSP)
- Maven
- MySQL
- JUnit 5
- Git & GitHub
- GitHub Actions (CI)

---

##  Design Patterns Used

- Builder Pattern (User creation)
- Observer Pattern (Email notification)
- Strategy Pattern (Help system)
- Layered Architecture

---

## ▶ How to Run the Project

1. Clone repository:
   git clone <repository-url>

2. Configure MySQL database

3. Update DB credentials in DBConnection class

4. Run using Tomcat server

5. Access application via browser

---

## 📌 Author

Lavanya Nethmini  
Final Year Software Engineering Project