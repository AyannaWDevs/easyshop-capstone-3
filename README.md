# EasyShop Capstone
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

### An Interesting Snippet of Code
          @GetMapping("") // maps the method to a GET request to thebase/root path (/products) of the controller
    @PreAuthorize("permitAll()") 
    public List<Product> search(@RequestParam(name="cat", required = false) Integer categoryId,        // maps query parameters to the corresponding variable
                                @RequestParam(name="minPrice", required = false) BigDecimal minPrice,        //false indicates that the param is optional and defaults to null if empty
                                @RequestParam(name="maxPrice", required = false) BigDecimal maxPrice,
                                @RequestParam(name="color", required = false) String color
                                )
    {
        try //to perform product search and throws exception if encountered
        {
            return productDao.search(categoryId, minPrice, maxPrice, color);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");        //returns http error response 
        }
    }


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

## The Front End: 
### Homescreen
![Screenshot 2024-12-20 083354](https://github.com/user-attachments/assets/068e3d51-a2f7-4d72-ab6a-b3700eef5a19)

### Authentication/Log-in Feature
![Screenshot 2024-12-20 083416](https://github.com/user-attachments/assets/61fb6467-773b-44cd-9913-6c03b7c21c60)

### Search by Minimum and Maximum Price
![Screenshot 2024-12-20 085543](https://github.com/user-attachments/assets/f53c2c9b-2344-46be-9f17-b7d4474af058)

### Group by Category
![Screenshot 2024-12-20 085805](https://github.com/user-attachments/assets/d06f4279-1d12-4723-8727-ac04ddd0d749)

### Search by Color
![Screenshot 2024-12-20 091021](https://github.com/user-attachments/assets/242fe476-9710-4045-adcf-cab744be38ba)





