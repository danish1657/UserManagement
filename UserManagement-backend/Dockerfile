# Step 1: Build the JAR file using Maven
FROM maven:3.8.5-openjdk-22-slim AS build

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package

# Step 2: Create the actual Docker image with the JAR file
FROM openjdk:22-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar usermanagement.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "usermanagement.jar"]
