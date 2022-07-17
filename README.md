# SpringSecurityProject
This is a Spring Boot application with the use of:
  1. Thymeleaf
  2. Web
  3. Data
  4. Security
  5. Lombok
  6. PostgreSql
  7. Docker

You can run the application using Docker
```
docker build -t spring-project .
```
```
docker-compose up
```

### -------------------------------------
The application is located on port 8080.

It has players api, you can access it on http://localhost:8080/api/players
To use it, you have got to log in.
There are 2 users in database: admin and user. User is allowed to only read, admin is allowed to read and write.
Admin Log: admin Pass: admin
User Log: user Pass: user.

After that you can CRUD players.
