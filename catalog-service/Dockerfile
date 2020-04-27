FROM openjdk:8-jdk-alpine
LABEL app.name="catalogservoce" app.type=" microservice-springboot" app.port="8080"
MAINTAINER Arunachalam.J
RUN mkdir -p /deploy
WORKDIR /deploy
EXPOSE 8080/tcp
ARG DEPENDENCY=target/catalogservice-1.0.jar
COPY ${DEPENDENCY} .
ENTRYPOINT ["java", "-jar","catalogservice-1.0.jar"]
