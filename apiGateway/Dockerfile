# Start with a base image containing java runtime
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Metadata (optional; MAINTAINER is deprecated)
LABEL maintainer="brainstorm.com"

#Add the application's jar to the conatiner
COPY target/apiGateway-0.0.1-SNAPSHOT.jar apiGateway-0.0.1-SNAPSHOT.jar

#exacute the application
ENTRYPOINT ["java", "-jar", "/app/apiGateway-0.0.1-SNAPSHOT.jar"]