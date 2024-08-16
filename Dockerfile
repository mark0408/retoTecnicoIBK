FROM adoptopenjdk:8-jre-hotspot
MAINTAINER marko_espejo
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} demo-2.0.jar
ENTRYPOINT ["java","-jar","/demo-2.0.jar"]