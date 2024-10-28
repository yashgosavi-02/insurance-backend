# Insurance and Investment Website

## Project Overview
This Insurance and Investment Website is a digital platform developed for a local insurance and investment company. The platform aims to modernize traditional workflows, facilitate customer acquisition, and offer an enhanced, user-friendly interface. It allows customers to explore insurance policies, calculate finances, and manage policies efficiently.

## Features
- **Financial Calculators**: Tools for users to calculate insurance premiums, investment returns, and other financial parameters.
- **Policy Filtering**: Allows customers to filter and compare insurance policies based on their specific requirements and preferences.
- **Policy and User Management**: Admin capabilities for managing policies, tracking customer accounts, and handling policy updates.

## Technology Stack
### Backend
- **Java Spring Boot** for backend logic
- **MySQL** as the relational database
- **Hibernate** as the ORM framework
- Additional Libraries:
  - **Spring Security** for authentication and authorization
  - **Spring Data JPA** for database interactions
  - **Thymeleaf** for server-side templating
  - **Spring Mail** for sending email notifications

## Installation and Setup

### Prerequisites
- [Java Development Kit](https://www.oracle.com/java/technologies/javase-downloads.html) (JDK 21 or later)
- MySQL server

### Backend Setup
1. In the backend directory, set up the database configuration in application.properties
``` 
spring.datasource.url=jdbc:mysql://localhost:3306/insurance_investment
spring.datasource.username=<your_mysql_username>
spring.datasource.password=<your_mysql_password>
```

2. Build and Run the Backend Server
```
mvn spring-boot:run
```


## Usage
- The backend API runs on a different port (e.g., http://localhost:8080), handling requests for financial calculations, user management, and policy filtering.

## Future Enhancements
- Adding advanced analytics for users to visualize their policy performance
- Integration of payment gateways for premium payments
- Multi-language support for a wider audience

## Contribution
- Feel free to fork the repository, make changes, and submit a pull request. Ensure - that all code follows the coding standards and passes linting checks.

## Contact
For questions or further information, please contact https://www.github.com/pushkar132
