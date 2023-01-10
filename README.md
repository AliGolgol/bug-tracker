## Introduction
Implement a simple bug tracker as a REST service.


- Set up the project and add needed dependencies
- Implement a REST API with CRUD functionalities
- Add/use a database of your choice (in-memory data store is allowed)
- Test your application

You can use every resource you want (web, other code, etc.)

## Technical Details

The service is written in Kotlin and uses Spring Boot as an underlying framework. 

### Code Structure

The service strives to follow the clean architecture approach

![clean architecture diagram](docs/clean_architecture.png)

All API endpoints are located in `com.agg.bugtrackers.application.ui` package. The OpenAPI documentation for the API is available in `localhost:8080/documentations`.

Interfacing with external systems,which are in memory data persistence, is limited to `com.agg.bugtrackers.infrastructure` package.
Use cases contain the application logic and depend only on domain entities.

## Requirement
- Java 11
- Gradle

### Running from docker
You can also run both services from docker, by running the following command:
```
sudo docker build -t bugtrackers-service .
docker run -p 8080:8080 bugtrackers-service
The address of rest api is: http://loclahost:8080
```

## Interacting with the API

### BugTrackers endpoints & Documentation

Run the following url to query over RestApi.

```
localhost:808/documentations
```