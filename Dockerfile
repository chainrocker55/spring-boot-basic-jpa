# Dockerfile
FROM docker.artifactory.kasikornbank.com:8443/maven:3.6.1-jdk-8-slim as builder

USER root
ARG http_proxy
ARG https_proxy
ARG no_proxy

ENV http_proxy=http://172.31.97.120:8080
ENV https_proxy=http://172.31.97.120:8080
ENV no_proxy=$no_proxy

WORKDIR /src
COPY pom.xml .

RUN mvn dependency:go-offline -B

WORKDIR /
COPY pom.xml /pom.xml
COPY src/main /src/main
COPY src/test /src/test

RUN mvn clean test
RUN mvn clean install

# Dockerfile
FROM common-docker.artifactory.kasikornbank.com:8443/openjdk:1.0.0
COPY --from=builder target/restservice-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "restservice-0.0.1-SNAPSHOT.jar"]