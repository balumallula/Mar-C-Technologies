# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container (ensure you're in the right directory)
COPY . .

# Run Maven build to create the JAR file, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Run the application (use a lighter image)
FROM eclipse-temurin:17-jdk-alpine

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar /app.jar

# Expose the port the app will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
