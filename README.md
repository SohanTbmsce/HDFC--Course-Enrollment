# Course & Employee Enrollment Management System

A Spring Boot REST API application for managing courses and employee
enrollments, including validation, business rules, custom exception
handling, DTO mapping with MapStruct, and analytics using Java Streams.

## Features

-   Course creation, retrieval, update, deletion and search
-   Employee enrollment management
-   Enrollment cancellation and completion
-   Duplicate enrollment and capacity validation
-   Custom exception handling
-   Bean validation
-   MapStruct DTO/entity mapping
-   Java Stream-based analytics

## Technologies

-   Java
-   Spring Boot
-   Spring Web
-   Jakarta Bean Validation
-   Maven
-   MapStruct
-   Lombok
-   Java Streams
-   REST APIs

## API Endpoints

Base URL: `http://localhost:8080`

### Course APIs

  ----------------------------------------------------------------------------------
  Method                  Endpoint                           Description
  ----------------------- ---------------------------------- -----------------------
  POST                    `/courses`                         Create a course

  GET                     `/courses`                         Get all courses

  GET                     `/courses/{id}`                    Get course by ID

  PUT                     `/courses/{id}`                    Update a course

  DELETE                  `/courses/{id}`                    Delete a course

  GET                     `/courses/trainer/{trainerName}`   Search courses by
                                                             trainer

  GET                     `/courses/fees/{amount}`           Get courses with fees
                                                             below the specified
                                                             amount
  ----------------------------------------------------------------------------------

### Enrollment APIs

  --------------------------------------------------------------------------------------
  Method                  Endpoint                               Description
  ----------------------- -------------------------------------- -----------------------
  POST                    `/enrollments`                         Enroll an employee

  GET                     `/enrollments`                         Get all enrollments

  GET                     `/enrollments/{id}`                    Get enrollment by ID

  PUT                     `/enrollments/{id}/cancel`             Cancel an enrollment

  PUT                     `/enrollments/{id}/complete`           Complete an enrollment

  GET                     `/enrollments/status/{status}`         Search enrollments by
                                                                 status

  GET                     `/enrollments/employee/{employeeId}`   Search enrollments by
                                                                 employee ID
  --------------------------------------------------------------------------------------

### Analytics APIs

  ----------------------------------------------------------------------------------
  Method                  Endpoint                           Description
  ----------------------- ---------------------------------- -----------------------
  GET                     `/analytics/course-count`          Get total course count

  GET                     `/analytics/enrollment-count`      Get total enrollment
                                                             count

  GET                     `/analytics/most-popular-course`   Get the course with the
                                                             highest enrollment
                                                             count
  ----------------------------------------------------------------------------------

## Sample Course Request

`POST /courses`

``` json
{
  "courseName": "Java Full Stack",
  "trainerName": "Rahul Sharma",
  "durationInDays": 30,
  "maxCapacity": 25,
  "fees": 15000
}
```

## Sample Enrollment Request

`POST /enrollments`

``` json
{
  "employeeId": 101,
  "employeeName": "Ratan",
  "courseId": 1
}
```

## Business Rules

1.  A course must exist before an employee can enroll.
2.  An employee cannot enroll twice in the same active course.
3.  Course capacity cannot be exceeded.
4.  Cancelled enrollments do not occupy course capacity.
5.  An employee can enroll again after cancelling a previous enrollment.
6.  Enrollment statuses are `ENROLLED`, `CANCELLED`, and `COMPLETED`.

## Exception Handling

The application uses `@RestControllerAdvice` for global exception
handling.

  Exception                        HTTP Status
  -------------------------------- ---------------
  `CourseNotFoundException`        404 NOT FOUND
  `EnrollmentNotFoundException`    404 NOT FOUND
  `DuplicateEnrollmentException`   409 CONFLICT
  `CourseCapacityFullException`    409 CONFLICT

## Validation

Request DTOs use Jakarta Bean Validation annotations such as:

-   `@NotBlank`
-   `@NotNull`
-   `@Positive`

Invalid input results in `400 BAD REQUEST`.

## Project Structure

``` text
src/main/java/com/hdfc
├── controller
│   ├── CourseController.java
│   ├── EnrollmentController.java
│   └── AnalyticsController.java
├── dto
├── entity
├── exception
│   ├── CourseNotFoundException.java
│   ├── EnrollmentNotFoundException.java
│   ├── DuplicateEnrollmentException.java
│   ├── CourseCapacityFullException.java
│   └── GlobalExceptionHandler.java
├── mapper
│   ├── CourseMapper.java
│   └── EnrollmentMapper.java
├── repository
│   ├── CourseRepository.java
│   └── EnrollmentRepository.java
└── service
    ├── CourseService.java
    ├── CourseServiceImpl.java
    ├── EnrollmentService.java
    ├── EnrollmentServiceImpl.java
    ├── AnalyticsService.java
    └── AnalyticsServiceImpl.java
```

## Architecture

``` text
Client
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Data
```

MapStruct handles DTO/entity conversion between the API and service
layers.

## Analytics

The analytics module uses Java Streams to calculate:

-   Total number of courses
-   Total number of enrollments
-   Most popular course based on enrollment count

## How to Run

### Clone the repository

``` bash
git clone <your-github-repository-url>
```

### Build the project

``` bash
mvn clean install
```

### Run the Spring Boot application

Run the main Spring Boot application class.

The application will be available at:

`http://localhost:8080`

## Testing

The APIs can be tested using Postman, cURL, or a browser for GET
requests.

Example:

``` bash
curl http://localhost:8080/courses
```

## HTTP Status Codes

    Status Meaning
  -------- -------------------------------
       200 Request successful
       201 Resource created
       204 Resource deleted successfully
       400 Validation / bad request
       404 Resource not found
       409 Business rule conflict

## Author

**Sohan T Sanjeev**

## Project Information

This project was developed as part of a Spring Boot backend development
assignment demonstrating REST API development, layered architecture, DTO
mapping, validation, exception handling, business rules, and Java
Stream-based analytics.
