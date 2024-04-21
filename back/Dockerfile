FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean install -Dmaven.test.skip=true


FROM eclipse-temurin:17-jre-alpine
COPY --from=build /app/target/*.jar /app/app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
