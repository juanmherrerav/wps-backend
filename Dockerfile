FROM openjdk:8-jdk-alpine

VOLUME /tmp
COPY target/wps-backend-1.0.0.jar /target/wps-backend-1.0.0.jar
CMD ["java","-jar","/target/wps-backend-1.0.0.jar"]
