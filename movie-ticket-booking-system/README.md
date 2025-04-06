Booking Movie Ticket APIs Spring Boot Project : 
This project is a Spring Boot implementation of the backend APIs for a ticket booking system similar to the popular platform "BookMyShow".
It provides a set of RESTful APIs that enable client applications to interact with the ticket booking system and perform various operations.

Features
User Registration -> Users can create an account.
Movie Management -> Admin/users can add movie.
Theater Management -> Admin/users can add, allocate seats
Show Management -> Admin/users can create show by combining movie and thearer add by adding seat and price details.
Ticket Booking -> Users can book tickets with show id and seat number.

Future plans : 
1. add user athentication and authorisation , only Admin should be able to manage theater, movie , shows
2. Handle concurrency
   
Technologies Used
Java 17+
Spring Boot 3.3.0
Spring Data JPA
MySQL (as the database)
Maven (for dependency management)
