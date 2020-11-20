# Dockerfile
# FROM docker.artifactory.kasikornbank.com:8443/maven:3.6.3-jdk-11-openj9 as builder
FROM common-docker.artifactory.kasikornbank.com:8443/builder-image/maven-jdk8-basebuilder:3.1.2 as builder

USER root
ARG http_proxy
ARG https_proxy
ARG no_proxy

ENV http_proxy=http://172.31.97.120:8080
ENV https_proxy=http://172.31.97.120:8080
ENV no_proxy=$no_proxy

WORKDIR /src
COPY pom.xml .
COPY settings.xml .

RUN mvn -s settings.xml dependency:go-offline -B -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true

WORKDIR /
COPY pom.xml /pom.xml
COPY src/main /src/main
COPY src/test /src/test
COPY settings.xml /settings.xml

# RUN mvn clean test
RUN mvn clean install -s settings.xml -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true

# Dockerfile
FROM docker.artifactory.kasikornbank.com:8443/openjdk:15-jdk
COPY --from=builder target/restservice-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "restservice-0.0.1-SNAPSHOT.jar"]