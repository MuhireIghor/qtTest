# Blogify

Blogify is a blog management application built with React.js (using TypeScript) and Spring Boot. It provides a simple interface for creating, editing, and managing blog posts.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Setup](#setup)
  - [Frontend](#frontend)
  - [Backend](#backend)
- [Running the Application](#running-the-application)

## Features

- Create, read, update, and delete blog posts and make comments on created blog posts.
- User authentication and authorization 
- Responsive design for various devices

## Technologies

- **Frontend:**
  - React.js
  - TypeScript
  - Vite

- **Backend:**
  - Java
  - Spring Boot
  - Spring security
  - Spring Data JPA
  -Hibernate
  - PostgreSQL


  


## Setup


1. **Clone the repository:**
   ```bash
   git clone https://github.com/MuhireIghor/qtTest.git
### Frontend
``` shell
   cd qtTest/blogify
   yarn

```
   
  
### Backend
``` shell
   cd qtTest/server/Blogiffy
   .mvnw clean package
   ```
   The Backend will be accessible via http://localhost:9090/ap1/v1<br />
   [The swagger documentation is at http://localhost:9090/swagger-ui/index.html#](http://localhost:9090/swagger-ui/index.html#)<br />
   
 
   

## Running the application
### Frontend
 ```shell
   yarn dev
   ```
### Backend
```java
   ./mvnw spring-boot:run
   ```
### API Documentation
### Authentication


- `GET /api/v1/auth/login`: Perform user authentication.
- `GET /api/v1/users/current-user`: Get currently logged in user
- `POST /api/v1/users/register/as-reader`: Register a new user as a reader
- `POST /api/v1/users/register/as-publisher`:
 Register a new user as a publisher




 ### Blogs

- `GET /api/v1/blogs/all-blogs`:Retrieve all blogs published
- `GET /api/v1/blogs/read-blog/{blogId}`:
Retrieve a single blog by its blogID

- `POST /api/v1/blogs/create`:Create a new blog



- `PUT /api/v1/blogs/update/{blogId}`: Update a blog by blogID.
- `DELETE /api/v1/blogs/{id}`: Delete a single blog by blogID.


 ### Comments

- `GET /api/v1/comments/all-comments/by-blog/{blogId}`:Retrieve all comments associated to a blog with blogId that has been published


- `POST /api/v1/comments/create-comment`:Create a new comment



- `PUT /api/v1/comments/update-comment/{commentId}`: Update a single comment by its commentID.
- `DELETE /api/v1/comments/delete-comment/{commentId}`: Delete a single comment by its commentID.
