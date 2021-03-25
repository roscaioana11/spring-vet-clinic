# Application Project Preview

- Project Name: vet-clinic
- Application Owner: Ioana Roșca
- Tools used: IntelliJ IDEA, Gradle, PostgreSQL on DBeaver, Postman, Hibernate, RabbitMQ
- Programming Language: Java
- Framework: Spring Boot
- Architecture Style: 3-tier Architecture

# Project Overview:
The vet-clinic application helps manage the database of a veterinary clinic by providing the following functionalities: managing owners and their pets, managing information about vet, providing a way for owners to create consultations with vets, allowing vets to create and manage diagnoses. The application allows users to access these functions by providing API endpoints that receive customized data in order to execute the required functionalities.

# Architecture:
The application uses a 3-layered architecture, with the following layers:
-	Data Access Layer (Repository), which provides a connection between the 	application and the PostgreSQL database, using an ORM (Hibernate)
-	Business Logic Layer (Service), which provides the logic for all the CRUD 	operations
-	Presentation Layer (Controller/API), which provides the endpoints that allow the 	Business Logic to be accessed with external requests
The application also makes use of a Messaging Framework (RabbitMQ) to notify a different application by the name of vet-notification whenever a new consultation or a new diagnosis is created. The Java Messaging Service (JMS) API allows these notifications to get sent to a RabbitMQ queue from which they will be sent asynchronously to the notification application. If the notification application is not running, these messages will be memorized in the queue waiting to get sent.

# Application Usage:
In order to use the vet-clinic application, the user must send a request to the API using the provided URLs, for example by using Postman. This request, which may contain a DTO, is received by a Controller and sent to its respective Service in order to be processed. Once the request is processed it will communicate with the Repository through an entity object and perform the necessary actions on the database. Once everything is executed the user will receive a message in the form of JSON object if the transaction was successful or an error code if something wrong occurred.
