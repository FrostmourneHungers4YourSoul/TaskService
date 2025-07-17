FROM maven:3.9.11-eclipse-temurin-17-alpine AS builder
COPY src /usr/app/src
COPY pom.xml /usr/app
RUN mvn -f /usr/app/pom.xml dependency:go-offline -B
RUN mvn -f /usr/app/pom.xml clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /usr/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]