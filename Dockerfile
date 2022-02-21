FROM openjdk:8-jdk-alpine

MAINTAINER Ramazan Sakin <ramazansakin63@gmail.com>
EXPOSE 8085
ADD target/airport-reservatin-system-0.0.1-SNAPSHOT.jar airport-reservation-system.jar

ENTRYPOINT ["java","-jar","airport-reservation-system.jar"]

## Dockerizing the app
#
# Create a Spring Boot Application
# Create Dockerfile
# Build executable jar file - mvn clean package
# Build Docker image - docker build -t airport-reservation-app:v1 .
# Run Docker container using the image built - docker run --name airport-reservation-system -p 8080:8080 airport-reservation-applicaiton:v1
# Test