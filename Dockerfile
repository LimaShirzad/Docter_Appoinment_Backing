# Start from an OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file (build should produce this)
COPY target/*.jar doctor.jar

# Run the jar file
ENTRYPOINT ["java", "-jar","doctor.jar"]