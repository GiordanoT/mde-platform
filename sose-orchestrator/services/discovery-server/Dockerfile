FROM maven:3.9.0-eclipse-temurin-17-alpine

WORKDIR /app
COPY  . .
EXPOSE 8761
RUN mvn clean install
CMD mvn spring-boot:run