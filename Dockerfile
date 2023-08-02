FROM openjdk:11
ADD target/postapp.jar postapp.jar
ENTRYPOINT ["java", "-jar", "postapp.jar"]