FROM openjdk:8-jdk-alpine
LABEL app.name="ProductCatalogWebApp" app.type="web- springboot" app.port="8080"
MAINTAINER Arunachalam.J
RUN mkdir -p /deploy
WORKDIR /deploy
EXPOSE 8080/tcp
ARG DEPENDENCY=target/productcatalog-1.0.jar
COPY ${DEPENDENCY} .
ENTRYPOINT ["java", "-jar","productcatalog-1.0.jar"]




