# User Management System REST API

A Spring Boot RESTful backend application that handles user onboarding with dependent cascading dropdowns (Country < State< City), automated temporary credential generation via SMTP email, first-time login password resets, user authentication, and third-party dashboard quote integration.

---

## 📌 Features

* **Dependent Dropdowns**: Dynamic cascading fetch for Countries, States (by Country ID), and Cities (by State ID).
* **Email Uniqueness Check**: Real-time validation endpoint to verify duplicate email entries before registration.
* **Automated User Onboarding**:
  * Auto-generates a secure temporary password.
  * Sends an HTML/text onboarding email to the user with their login credentials via SMTP.
  * Flags account password state as `NO` until the user performs a mandatory reset.
* **Account Security & Reset**: First-time password update workflow ensuring security.
* **Authentication**: Login endpoint validating credentials and returning user profile details.
* **Third-Party API Integration**: Fetches random motivational quotes from `https://dummyjson.com/quotes/random` for the user dashboard.
* **Interactive API Docs**: Built-in Swagger UI & OpenAPI 3 for testing.

---

## 🛠️ Tech Stack

* **Java**: 17+
* **Framework**: Spring Boot (Web, Data JPA, Mail, DevTools)
* **Database**: MySQL
* **Documentation**: SpringDoc OpenAPI (Swagger UI)
* **Build Tool**: Maven
* **Utilities**: Project Lombok

---

## 🗄️ Database Design

```text
+------------------+         +------------------+         +------------------+
|  COUNTRY_MASTER  |         |   STATE_MASTER   |         |   CITY_MASTER    |
+------------------+         +------------------+         +------------------+
| COUNTRY_ID (PK)  |<---+    | STATE_ID (PK)    |<---+    | CITY_ID (PK)     |
| COUNTRY_NAME     |    +----| COUNTRY_ID (FK)  |    +----| STATE_ID (FK)    |
+------------------+         | STATE_NAME       |         | CITY_NAME        |
                             +------------------+         +------------------+
                                      ^                            ^
                                      |                            |
                             +--------+----------------------------+
                             |
+----------------------------+---------------------+
|                     USER_MASTER                  |
+--------------------------------------------------+
| USER_ID (PK, AUTO_INCREMENT)                     |
| UNAME, EMAIL, PWD, PWD_UPDATED, PHNO             |
| COUNTRY_ID (FK), STATE_ID (FK), CITY_ID (FK)     |
| CREATED_AT, UPDATED_AT                           |
+--------------------------------------------------+

🚀 Setup & Execution
1. Database Configuration
Create the database and configure src/main/resources/application.properties:

Properties
spring.datasource.url=jdbc:mysql://localhost:3306/jrtp?useSSL=false&serverTimezone=UTC
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Spring Mail Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=YOUR_EMAIL@gmail.com
spring.mail.password=YOUR_GMAIL_APP_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
2. Static Master Data Initialization
Execute the following script to load cascading dropdown master data:

SQL
USE jrtp;

INSERT INTO country_master (country_id, country_name) VALUES (1, 'India'), (2, 'USA');

INSERT INTO state_master (state_id, state_name, country_id) VALUES 
(1, 'AP', 1), (2, 'TG', 1), (3, 'RI', 2), (4, 'NJ', 2);

INSERT INTO city_master (city_id, city_name, state_id) VALUES 
(1, 'Guntur', 1), (2, 'Ongole', 1), 
(3, 'Hyderabad', 2), (4, 'Warangal', 2), 
(5, 'Providence', 3), (6, 'New Port', 3), 
(7, 'Trenton', 4), (8, 'Newark', 4);
3. Build and Run
Bash
git clone [https://github.com/YOUR_USERNAME/REPOSITORY_NAME.git](https://github.com/YOUR_USERNAME/REPOSITORY_NAME.git)
cd REPOSITORY_NAME
mvn spring-boot:run
📖 API Documentation & Endpoints
Interactive Swagger UI: http://localhost:8080/swagger-ui.html

1. Get Countries
Endpoint: GET /countries

Response:

JSON
{
  "status": 200,
  "message": "Fetched Countries Successfully",
  "data": [
    { "countryId": 1, "countryName": "India" },
    { "countryId": 2, "countryName": "USA" }
  ]
}
2. Get States by Country ID
Endpoint: GET /states/{countryId}

Response:

JSON
{
  "status": 200,
  "message": "States fetched Successfully",
  "data": [
    { "stateId": 1, "stateName": "AP" },
    { "stateId": 2, "stateName": "TG" }
  ]
}
3. Get Cities by State ID
Endpoint: GET /cities/{stateId}

Response:

JSON
{
  "status": 200,
  "message": "Cities fetched Successfully",
  "data": [
    { "cityId": 1, "cityName": "Guntur" },
    { "cityId": 2, "cityName": "Ongole" }
  ]
}
4. Check Unique Email
Endpoint: GET /unique/{email}

Response:

JSON
{
  "status": 200,
  "message": "Duplicate Email Found",
  "data": "DUPLICATE"
}
5. Register User
Endpoint: POST /user

Request Body:

JSON
{
  "name": "Ashok",
  "email": "user@example.com",
  "phno": 9876543210,
  "countryId": 1,
  "stateId": 1,
  "cityId": 1
}
Response:

JSON
{
  "status": 200,
  "message": "Registration successfull",
  "data": "SUCCESS"
}
6. User Login
Endpoint: POST /login

Request Body:

JSON
{
  "email": "user@example.com",
  "pwd": "TEMPORARY_PASSWORD"
}
Response (Success):

JSON
{
  "status": 200,
  "message": "Login Success",
  "data": {
    "userId": 1,
    "name": "Ashok",
    "email": "user@example.com",
    "pwd": "TEMPORARY_PASSWORD",
    "pwdUpdated": "NO",
    "phno": 9876543210,
    "countryId": 1,
    "stateId": 1,
    "cityId": 1
  }
}
7. Reset Password
Endpoint: POST /reset-pwd

Request Body:

JSON
{
  "email": "user@example.com",
  "newPwd": "NewPassword@123",
  "confirmPwd": "NewPassword@123"
}
Response:

JSON
{
  "status": 200,
  "message": "Pwd updated successfully",
  "data": "SUCCESS"
}
8. Fetch Daily Dashboard Quote (External API)
Endpoint: GET /quote

Response:

JSON
{
  "status": 200,
  "message": "Quote Fetched successfully",
  "data": {
    "id": 1133,
    "quote": "Many Marriages Would Be Better If The Husband And The Wife Clearly Understood That They Are On The Same Side.",
    "author": "Zig Ziglar"
  }
}
