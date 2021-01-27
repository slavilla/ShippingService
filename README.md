# ShippingService
A sample project that calculates the shipping cost of a parcel. This uses Spring Boot, Spring Validation, Spring Data JPA, and in-memory H2 Database.

## Setup
1. Install Gradle https://gradle.org/install/
2. Install Git https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
3. Git clone this repository.
4. Go to the cloned repository and run `gradlew bootRun` to start the server.

## Usage
To calculate shipping cost, send a GET request to the application in below format.
```
GET http://localhost:8080/api/shipping/cost?weight=5&height=30&width=10&length=10
Accept: application/json
```
Response:
```
{
  "cost": 150.00,
  "discount": 0,
  "message": ""
}
```
Initial pricing rules are imported from https://github.com/slavilla/ShippingService/blob/master/src/main/resources/data.sql.

Pricing rules can be updated in the H2 console http://localhost:8080/h2-console.
```
JDBC URL: jdbc:h2:mem:shipping
User Name: sa
Password:
```
