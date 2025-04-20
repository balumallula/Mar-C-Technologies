# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
COPY . .                         # Copies all source code
RUN mvn clean package -DskipTests # This creates the JAR file in target/

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build target/*.jar app.jar  # Copies the generated JAR
# ... rest of your Dockerfile
