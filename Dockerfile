# Use Eclipse Temurin (Adoptium) OpenJDK 17 – slim and secure
FROM eclipse-temurin:17-jdk-alpine

# Copy the JAR built by Maven
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "/app.jar"]