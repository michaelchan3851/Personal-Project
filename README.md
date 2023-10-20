# Personal-Project
# User Management System with Docker

## Overview

This User Management System is containerized using Docker, allowing for easy deployment and management. This README provides instructions on setting up and running the server using Docker.

## Prerequisites

- [Docker](https://www.docker.com/get-started) installed on your machine.

## How to Run with Docker

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/michaelchan3851/Personal-Project/tree/main/demo-user-management-system
   cd user-management-system
   ```

2. **Build the Docker Image:**
   ```bash
   docker build -t user-management-system .
   ```

3. **Run the Docker Container:**
   ```bash
   docker run -p 8185:8085 -d user-management-system
   ```

4. **Access the Application:**
   - Open a web browser and go to `http://localhost:8085` to access the application.

## API Endpoints

### 1. User Registration

- **Endpoint:** `/api/v1/signup`
- **Method:** POST
- **Request Body:**
  ```json
  {
    "email": "user@example.com",
    "username": "user123",
    "password": "StrongPassword123"
  }
  ```
- **Response:**
  - Success: HTTP 201 Created
  - Error: HTTP 400 Bad Request with error message

### 2. User Login

- **Endpoint:** `/api/v1/signin`
- **Method:** POST
- **Request Body:**
  ```json
  {
    "usernameOrEmail": "user123",
    "password": "StrongPassword123"
  }
  ```
- **Response:**
  - Success: HTTP 200 OK with user information
  - Error: HTTP 401 Unauthorized with error message

### 3. Password Reset

- **Endpoint:** `/api/v1/reset/password`
- **Method:** POST
- **Request Body:**
  ```json
  {
    "usernameOrEmail": "user123",
    "oldPassword": "StrongPassword123",
    "newPassword": "NewStrongPassword456"
  }
  ```
- **Response:**
  - Success: HTTP 200 OK with success message
  - Error: HTTP 400 Bad Request with error message

### 4. User Profile

- **Endpoint:** `/api/v1/profile`
- **Method:** GET
- **Request Param:** `username` (e.g., `user123`)
- **Response:**
  - Success: HTTP 200 OK with user information
  - Error: HTTP 404 Not Found with error message

### 5. List All Users (Admin Only)

- **Endpoint:** `/api/v1/users`
- **Method:** GET
- **Response:**
  - Success: HTTP 200 OK with a list of users
  - Error: HTTP 401 Unauthorized with error message (if not admin)

## Core Functionality Comments

- `UserController.java`: Handles HTTP requests related to user management.
- `UserDTO.java`: Data Transfer Object for user-related information.
- `UserService.java`: Service layer interface defining user-related operations.
- `UserServiceImpl.java`: Service layer implementation for user-related operations.
- `UserRepository.java`: JPA repository for database operations related to users.
- `UserEntity.java`: JPA entity representing the User table in the database.

Feel free to explore the codebase for detailed comments explaining each functionality and component.

