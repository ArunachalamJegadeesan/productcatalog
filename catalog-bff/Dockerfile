FROM openjdk:8-jdk-alpine
LABEL app.name="BFF" app.type="BFF-Springboot" app.port="8080"
MAINTAINER Arunachalam.J
RUN mkdir -p /deploy
WORKDIR /deploy
EXPOSE 8080/tcp
ARG DEPENDENCY=target/bff-1.0.jar
COPY ${DEPENDENCY} .
ENTRYPOINT ["java", "-jar","bff-1.0.jar"]
