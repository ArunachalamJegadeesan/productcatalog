FROM openjdk:8-jdk-alpine
LABEL app.name="BFF" app.type="Microservice-BFF-springboot" app.port="6060"
MAINTAINER Arunachalam.J
RUN mkdir -p /deploy
WORKDIR /deploy
EXPOSE 6060/tcp
ARG DEPENDENCY=target/bff-0.0.1-SNAPSHOT.jar
COPY ${DEPENDENCY} .
ENTRYPOINT ["java", "-jar","bff-0.0.1-SNAPSHOT.jar"]
