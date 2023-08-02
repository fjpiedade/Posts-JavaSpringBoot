FROM maven:3.8.5-openjdk-11 AS build
COPY . .
RUN mvn clean package

FROM openjdk:11.0-jdk
COPY --from=build /target/postapp.jar postapp.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "postapp.jar"]