# RESTful API that allows you to manage Users and User preferences

This project built using Java and the following tools:

-Spring Boot as server side framework

-Maven as build automation tool

-Hibernate as ORM / JPA implementation

-Postgres as database implementation

-Spring Data JPA as the top layer over Hibernate

###Application Structure

####Domain

Domain model is organized under the Domain package and it consists of entity class. Entities use various annotations that describe the relationships between each other. All these annotations are used by JPA in order to map entities to database tables.

####DTO

In order to decouple the model/domain layer from the client side. only the needed data can be populated using DTO, instead of populating the entire model/domain. For small projects (like this), it is common for a DTO to be identical with the corresponding model. in order to implement a more scalableapplication DTOs are better approch.

####Repository

Repositories are interfaces that are responsible for data persistence and retrieval. The repository layer is an abstraction that provides all CRUD functionality and keeps hidden the data related information specific database implmentation from the other layers. This layer should always persist entities.

####Service

Service layer depends on the repository layer and provides separation of concern, encapsulating all the business logic implementation. It is there to apply business rules on data sent to and from the repository layer. Service layer does not care about the specific database implementation and provides loose coupling. This technique makes the application flexible in a possible data source replacement. This layer should always receive and return DTOs.

####Controller

Controller layer depends on the service layer and is responsible for the incoming requests and the outgoing responses. A controller determines all theavailable endpoints that client side (or other api) is able to call. This layer should not apply logic on the receiving or returning data.

#### User and User Preferences

In this app, Users primary details like name, email, phone, city are stored in a USERS table and Optional User Preferences are stored in  separate table called USERPREFERENCES, and they have a one-to-one relationship between USERS and USERPREFERENCES table.

The table looks like below.
![stack Overflow](https://github.com/ShekharaMathur/ShekharaMathur/blob/main/Screen%20Shot%202021-12-26%20at%204.54.17%20PM.png)

#### Views for the domains/results 
Many times we may required to provide different views of the same domain to different requirements. this, we may end-up in creating many DTO's to present it.
We can solve this by using Jackson's (@JsonView) view. It is similar to database views, where we can build multiple virtual tables with different combinations of columns in the underlying table, we can define multiple views of the same Domain/DTOs with different combinations of fields.

##### Usuage
1. Define View as class or Interface.
2. Use @JsonView annotation in Domain/DTO to map fields to one or more views.

Jackson reads these @JsonView annotations on fields during serialization/ de-serialization of objects and serialize/deserialize only fields in view and will skip all other fields. 

@JsonView annotation directly can be leveraged to customize, control the serialization/deserialization of REST API response and request body data.

`@GetMapping("/api/v1/users")`
`@JsonView(value = @JsonView(UserView.BaseView.class))`

This will render only @JsonView marked fields to to api/v1/users api calls.

####Quick Start

#####Prerequisites

######Clone the application
`https://github.com/ShekharaMathur/ShekharaMathur.git`

Create a Postgres database

CREATE Table <name>;

`CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        name VARCHAR (50),
        email VARCHAR (50) NOT NULL UNIQUE,
        phone VARCHAR (50) NOT NULL UNIQUE,
	city  VARCHAR (50) NOT NULL
);`

######Change postgress username and password as per your installation

`open src/main/resources/application.properties`

change spring.datasource.username and spring.datasource.password as per your postgress installation

######Build and run the app using maven

Goto user folder then run the following

`./mvnw package`

`java -jar target/user-0.0.1-SNAPSHOT.jar`

######Alternatively, you can run the app without packaging it using -

`mvn spring-boot:run`

#### Java Faker

This library is a port of Ruby's faker gem (as well as Perl's Data::Faker library) that generates fake data. It's useful when you're developing a new project and need some pretty data for showcase.

References "https://github.com/DiUS/java-faker"

This is used in this project to populate the User and UserPrefernce model during app start/run.

The server will start running at `http://localhost:8080`.

#######Explore Rest APIs

The app defines following CRUD API's for User Creation

`GET /api/v1/users`

`POST /api/v1/users`

`GET /api/v1/users/{userId}`

`PUT /api/v1/users/{userId}`

`DELETE /api/v1/users/{userId}`

The following API's defines for User Preferences setting

`POST /api/v1/users/{id}/preferences`

`PUT /api/v1/users/{id}/update-preferences`

`GET /api/v1/users/{id}/get-preferences`



