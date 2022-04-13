FROM maven:3.6.3-jdk-11-slim as build
WORKDIR /SpringSecurityProject
COPY src ./src
COPY pom.xml .
RUN mvn clean package -Dmaven.test.skip

FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
COPY --from=build /SpringSecurityProject/target/SpringSecurityProject-0.0.1-SNAPSHOT.jar spring-project.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar" ,"spring-project.jar" ]