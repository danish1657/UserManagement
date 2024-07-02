# User Management API

This project provides a set of RESTful APIs for managing users, units, roles, and user roles. The API is built using Spring Boot and for frontend, angular framework is used to communicate it with APIs and frontend randering.
![swagger picture](https://github.com/danish1657/UserManagement/assets/64883381/90930f63-2b9b-47e4-8312-e362bcd4d66f)

## Table of Contents
- [User Controller](#user-controller)
  - [Get All Users](#get-all-users)
  - [Create User](#create-user)
  - [Update User](#update-user)
  - [Delete User](#delete-user)
  - [Get User Roles for User and Unit](#get-user-roles-for-user-and-unit)
- [Unit Controller](#unit-controller)
  - [Get All Units](#get-all-units)
  - [Update Unit](#update-unit)
  - [Get Users with User Roles at Unit](#get-users-with-user-roles-at-unit)
- [Role Controller](#role-controller)
  - [Get All Roles](#get-all-roles)
  - [Update Role](#update-role)
- [UserRole Controller](#userrole-controller)
  - [Get All UserRoles](#get-all-userroles)
  - [Create UserRole](#create-userrole)
  - [Update UserRole](#update-userrole)
  - [Delete UserRole](#delete-userrole)
  - [Fetch Valid User Roles](#fetch-valid-user-roles)
- [cURL Commands](#curl-commands)

Swagger Link to the API: http://localhost:8080/swagger-ui.html

## User Controller

### Get All Users
Retrieve all users.
- **URL**: `/users`
- **Method**: `GET`
- **Response**:
  ```json
  [
    {
      "id": 1,
      "name": "Alice",
      "version": 1
    },
    {
      "id": 2,
      "name": "Bob",
      "version": 2
    },
    {
      "id": 3,
      "name": "Eve",
      "version": 1
    }
  ]
