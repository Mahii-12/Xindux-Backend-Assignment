# Xindux-Backend-Assignment
I've developed a backend solution for wishlist management in e-commerce. It's built with Spring Boot, Spring Security (JWT authentication), and Spring Data JPA, ensuring secure and efficient wishlist operations.
# System Features
> User Authentication:
* Implemented secure signup and login functionality using Spring Security with JWT authentication.
> Wishlist Management:
* Designed RESTful API endpoints to manage user wishlists, enabling creation and deletion of wishlist items.
> Databse Integration
* Integrated MySQL in the system, enabling seamless data storage and retrieval for user information, wishlists, and products via Spring Data JPA repositories.
> Unit Testing
* Unit tests have been successfully implemented for wishlist components, covering controllers, services, and repositories, ensuring comprehensive test coverage to validate 
  functionality and enhance application reliability.
  
## Prerequisites
* Java Development Kit (JDK) 8 or above
* Maven
* Spring Tool Suite(STS)
* MySQL Workbench
 
# Application Setup Instructions:

> 1. Clone Repository:
  Clone the repository to your local machine:
 * git clone https://github.com/Mahii-12/Xindux-Backend-Assignment.git
> 2. Navigate to the Project Directory:
 * cd eclipse-workspace
> 3. Build the project using Maven:
 * mvn clean install

# Configurations Setup:
> Database Configurations and setup configurations:
  * Open the src/main/resources/application.properties file.
    > server.port=8090
    > 
    > spring.jpa.show-sql=true
    >   
    > spring.jpa.properties.hibernate.format_sql=true
    > 
    > spring.datasource.url=jdbc:mysql://localhost:3306/xindusdb
    > 
    > spring.datasource.username=root
    > 
    > spring.datasource.password=Xindus@2024
    > 
    > spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    > 
    > spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    > 
    > spring.jpa.hibernate.ddl-auto=update

*  Security Configuration:
   > Enhanced SecurityConfig.java with customized settings for JWT token expiration and secret key. Implemented security filter chain method for permitAll access to specific 
     endpoints while enforcing authentication for others, enhancing application security.

* Entity Mapping and Authorization:
  > Ensure proper mapping between the User and Wishlist entities to enforce access control.
  > Implement authorization logic to permit only authenticated users to access their wishlists.

* Run with  Maven:
  > Start the application using Maven:
    > mvn spring-boot:run
* The application will start on the port 8090.

# API Utilization
* Signup Functionality:
  > Open Postman or any HTTP client application.
  > Send a POST request to http://localhost:8090/AuthUser/registration with JSON body containing user details:
  > 
 ```json
{
     "username":"Kohli",
     "email":"Virat@gmail.com",
     "password":"123"
}
  ```
<img width="960" alt="Screenshot 2024-02-19 004919" src="https://github.com/Mahii-12/Xindux-Backend-Assignment/assets/112060996/7117a577-4749-4eea-acd7-ecd73e447305">

* Login Functionality:
  > Send a POST request to http://localhost:8090/AuthUser/login with JSON body for generating a token with user credentials.
 ```json
{
    "username": "user_username",
    "password": "user_passowrd"
}
```
* Wishlist APIs:
> To authenticate API requests, include the JWT token in the Authorization header.
> 
> In Postman, configure the Authorization type as "Bearer Token" and insert the JWT token obtained upon login. If the Authorization header starts with "Bearer ", extract > the token and retrieve the associated username.
> 
> To extract the token, exclude the initial seven characters from the authorization header using the code snippet:
>
```java
' token = authorizationHeader.substring(7); '
```
> 
> To create a wishlistItem:
>
* Endpoint to create wishlist is: https://www.localhost:8090/api/wishlists/create 
>
 > Logic to create wishlist:
>
* It retrieves the currently authenticated user.
>
* It checks if the user and the product specified by the wishlistId exist.
>
* If either the user or the product is not found, it throws a UserException.
>
* It retrieves the wishlist(s) associated with the user. If no wishlist exists, it creates a new one.
>
* It adds the product to each wishlist.
>
* It saves the user entity, which cascades the save operation to the associated wishlists.
>
* It constructs and returns a response DTO containing the wishlist ID and the details of the added product(s)

> To retrieve a wishlistitem:
>
* Endpoint to retrieve a wishlistitem is: https://www.localhost:8090/api/wishlists/Getwishlists 
>
 > Logic to retrieve a wishlistitem:
>
* Retrieve the username of the authenticated user from the security context.
>
* Use the username to find the corresponding user entity from the repository.
>
* Check if the user entity is present (not empty). If not found, throw a UserException with a message indicating that the user was not found.
>
* If the user entity is found, extract the list of wishlists associated with the user.
>
* Return the list of wishlists.

>  To delete a wishlistitem by id:
>
* Endpoint to delete a wishlistitem: https://www.localhost:8090/api/wishlists//wishlists/{Id}
>
  > Logic to delete a wishlistitem:
>
* Get the authenticated user's username.
>
* Find the user by the provided wishlist ID.
>
* If the user doesn't exist, throw an exception.
>
* Ensure that the authenticated user owns the wishlist.
>
* Find the product by the provided wishlist ID.
>
* If the product doesn't exist, throw an exception.
>
* Get the wishlist containing the product.
>
* Remove the product from the wishlist.
>
* Save the changes to the wishlist.
>
* Return the updated wishlist.

> I have developed a product entity, allowing users to manage their wishlists. 
>
*  Endpoint for product functionality: http://localhost:8090/api/products
> Each wishlist can contain multiple products. Users can perform the following operations:
>  
* Create: Users can create new products and add them to their wishlists.
>
* Retrieve: Users can retrieve information about individual products within their wishlists.
>
* Update: Users can update the details of existing products in their wishlists.
>
* Delete: Users can delete individual products from their wishlists.
>
* View All: Users can view all products in their wishlist at once.
>
* This system provides users with flexibility and control over their wishlists, enhancing their overall experience.














