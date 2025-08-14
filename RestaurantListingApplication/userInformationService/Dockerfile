FROM eclipse-temurin:21-jdk AS build
WORKDIR /opt
COPY target/*.jar /opt/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]