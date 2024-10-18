# Hexagonal Architecture Spring Boot Project

### ARM-API Documentation - Ports & Adapters architecture

### Overview

![arm-backend-dashboard.png](https://raw.githubusercontent.com/ARM-Capstone-Project/arm-api/main/arm-backend-dashboard.png)

The ARM-API provides various endpoints for managing users, roles, zones, and devices. Below is a detailed list of available endpoints, methods, and request/response details.

---

### Base URL

http://localhost:8081/api

---

### Endpoints

| **Endpoint**                   | **Method** | **Description**                         |
| ------------------------------ | ---------- | --------------------------------------- |
| `/auth/register`               | POST       | Register a new user.                    |
| `/auth/login`                  | POST       | Authenticate a user and return a token. |
| `/users`                       | GET        | Retrieve a list of users.               |
| `/users/{userId}`              | GET        | Retrieve details of a specific user.    |
| `/users/{userId}`              | PUT        | Update details of a specific user.      |
| `/users/{userId}`              | DELETE     | Delete a specific user.                 |
| `/admin/assign_role`           | POST       | Assign a role to a user.                |
| `/admin/remove_role`           | POST       | Remove a role from a user.              |
| `/zones`                       | POST       | Save a new zone.                        |
| `/zones`                       | GET        | Retrieve a list of zones.               |
| `/zones/{zoneId}`              | GET        | Retrieve details of a specific zone.    |
| `/zones/{zoneId}`              | PUT        | Update details of a specific zone.      |
| `/zones/{zoneId}`              | DELETE     | Delete a specific zone.                 |
| `/admin/assign_zone`           | POST       | Assign a zone to a user.                |
| `/admin/assign_device`         | POST       | Assign a device to a zone.              |
| `/devices`                     | GET        | Retrieve list all device                |
| `/devices/{deviceId}`          | GET        | Retrieve details of a device            |
| `/devices/{deviceId}`          | PUT        | Updae details of a device               |
| `/devices/{deviceId}`          | DELETE     | Delete a device                         |
| `/devices`                     | POST       | Create a device and related sensors     |
| `/devices/status/{status}`     | GET        | Retrieve list of device by status       |
| `/devices/location/{location}` | GET        | Retrieve list of device by location     |
| `/devices/type/{type}`         | GET        | Retrieve list of device by type         |
| `/devices/zone/{zoneId}`       | GET        | Retrieve list of device by a zone       |

---

### Authentication

Most endpoints require a Bearer token for authorization. Ensure to include the `Authorization` header with the format `Bearer <token>` in your requests where applicable.

---

### Technologies

- JDK 22
- Spring Boot 3.3.2
- Maven 3.8.6
- Postgres
- Spring JPA

---

### Module Dependency Diagram

![in-out-archi.png](https://raw.githubusercontent.com/ARM-Capstone-Project/arm-api/main/in-out-archi.png)

![moduleDependency.png](https://raw.githubusercontent.com/ARM-Capstone-Project/arm-api/main/moduleDependency.png)
