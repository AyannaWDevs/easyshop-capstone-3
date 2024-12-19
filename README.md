## EasyShop Capstone
### Description
The E-Commerce API is a RESTful backend service that manages categories and products for an online shopping platform. It includes user authentication and role-based access control to ensure secure resource management.
### Key Features
**Category Management**

Allows managing product categories within the e-commerce platform.

**Product Management**

Enables interaction with products, such as browsing, searching, and managing product data for administrators.

**User Authentication**

Ensures secure access to the API with role-based permissions for users and administrators.

### Setup
1. Clone the repository
2. Configure the database:

        -- Run the SQL script to initialize the schema
   
        -- Update application.properties with your database details (database_name, username, password)

4. Build and run the application
5. Access the API at http://localhost:8080

### Applied Concepts

**Java Fundamentals**

- Understanding the application of concepts such as OOP, encapsulation, and polymorphism
- Utilizing try-catch, throw, and custom exception handling
- Knowledge of primitive and complex data types such as BigDecimal or String

**Spring Framework**

  - Dependency Injection
  - Controllers such as @RestController, @RequestMapping, and @GetMapping
  - @Component for DAO classes
 
 
**Database Management**

  - CRUD operations (GET, POST, PUT, DELETE)
  - Query filtering with WHERE clauses
  - Setting up and configuring a MySQL database for the application and establishing a connection
 
**RESTful API Design**
    
- REST Principles and use of HTTP methods (GET, POST, PUT, DELETE)
- Troubleshooting status codes

**Authentication/Authorization**
    
- Assigning roles (e.g. ADMIN, USER)
- Role-Based Access Control
   
**Testing and Debugging**
- Postman for manual testing of API endpoints
