# Restaurant Management System

## Language and Framework
![Java](https://img.shields.io/badge/Language-Java-green)
![Spring Boot](https://img.shields.io/badge/Framework-Spring%20Boot-brightgreen)

## Summary
The Restaurant Management System is a project that presents a restaurant management system with two user roles: Normal Person Class (NPC) and Admin. The Admin role has higher-level access, while NPC has lower-level access. The project includes features for signup, signin, signout, and session management using tokens.

## Models
### User
- **Attributes:**
  - `id (Integer)`: Unique identifier for each user.
  - `name (String)`: Name of the user.
  - `email (String)`: Email of the user.
  - `password (String)`: Password (stored securely) of the user.
  - `role (Role)`: User role (Admin or NPC).
  - `localDateTime (LocalDateTime)`: Timestamp of user creation.

### FoodOrder
- **Attributes:**
  - `id (Integer)`: Unique identifier for each food order.
  - `foodItemList (List<FoodItem>)`: List of food items in the order.
  - `quantity (Integer)`: Total quantity of food items.
  - `user (User)`: User associated with the order.
  - `orderStatus (OrderStatus)`: Order status (e.g., CREATED, CANCEL).
  - `creationTime (LocalDateTime)`: Timestamp of order creation.

### FoodItem
- **Attributes:**
  - `id (Integer)`: Unique identifier for each food item.
  - `title (String)`: Title of the food item.
  - `description (String)`: Description of the food item.
  - `creatingDateTime (LocalDateTime)`: Timestamp of food item creation.
  - `imageUrl (String)`: URL of the food item's image.
  - `price (Double)`: Price of the food item.

## Controllers
### FoodItemController
- **Endpoints:**
  - `POST /food/admin`: Add a new food item (admin).
  - `GET /food/all`: Retrieve a list of all food items.
  - `PUT /food/id/{id}/admin`: Update food item details (admin).
  - `DELETE /food/id/{id}/admin`: Delete a food item (admin).

### OrderController
- **Endpoints:**
  - `POST /order`: Place an order (registered user).
  - `PUT /order/{orderId}/status/admin`: Update order status (admin).
  - `PUT /order/id/{orderId}`: Cancel an order (user or admin).
  - `GET /orders`: Get all orders for a particular user.
  - `GET /order/id/{orderId}`: Get order details (admin or user).
  - `DELETE /order/id/{orderId}/admin`: Delete an order (admin).

### UserController
- **Endpoints:**
  - `POST /signup/npc/user`: Add a new NPC user.
  - `POST /signup/admin`: Add a new Admin user.
  - `POST /signin`: Sign in a user.
  - `DELETE /user/signout`: Sign out a user.
  - `GET /user`: Get user details.
  - `GET /users`: Get a list of all users (admin).
  - `DELETE /user/id/{id}/admin`: Delete a user (admin).

## Repositories
### IFoodItemRepo
- Interface extending JpaRepository for the FoodItem entity.

### IOrderRepo
- Interface extending JpaRepository for the FoodOrder entity.
- Includes a custom query to retrieve orders by user.

### IUserAuthTokenRepo
- Interface extending JpaRepository for the UserAuthToken entity.
- Includes a custom query to find a UserAuthToken by token value.

### IUserRepo
- Interface extending JpaRepository for the User entity.
- Includes custom queries to find a user by email or password.

## Services
### FoodItemService
- Manages food item-related operations, including adding, updating, retrieving, and deleting food items.
- Validates user privileges for admin actions.

### OrderService
- Manages order-related operations, including placing orders, updating order status, canceling orders, and retrieving orders.
- Validates user privileges for admin actions.

### UserAuthTokenService
- Manages user authentication tokens.
- Creates tokens, retrieves tokens by value, and removes tokens from records.

### UserService
- Manages user-related operations, including user registration, login, logout, and user management.
- Validates user roles and passwords.

## PasswordEncryptor
- A utility class to securely encrypt passwords using the MD5 algorithm.

## Database Schema (MySQL)
The system uses a MySQL database. Below are the database schemas for the "User," "Order," and "foodItems "entities:

  ## Prerequisites
Make sure you have the following prerequisites installed on your system:

- Java Development Kit (JDK)
- Maven
- all the dependencies from poem.xml or active internet connection

## Installation
1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/Abdul12527/Restaurant-Managements-Services.git
   
2. Navigate to the project directory

    ```bash
    cd Restaurant-Managements-Services

  
## Usage
You can use the NewMonster Jobs application by making HTTP requests to the provided API endpoints using your preferred API testing tool or framework.

## Swagger UI
For a more interactive experience and to explore the available APIs, you can use the Swagger UI at http://localhost:8080/swagger-ui/index.html when running the application on your local system.
