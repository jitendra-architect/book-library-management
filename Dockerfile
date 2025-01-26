# Use an official JDK as a base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/book-library-management-0.0.1-SNAPSHOT.jar application.jar

# Expose the port on which the Spring Boot application runs
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "application.jar"]