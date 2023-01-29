FROM amazoncorretto:8-alpine-jdk
MAINTAINER jmc
COPY target/jmc-0.0.1-SNAPSHOT jmc-app.jar
ENTRYPOINT ["java","-jar","/jmc-app.jar"]