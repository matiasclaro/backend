FROM amazoncorretto:11-alpine-jdk
MAINTAINER JMC
COPY target/jmc-0.0.1-SNAPSHOT.jar jmc-app.jar
ENTRYPOINT ["java","-jar","/jmc-app.jar"]