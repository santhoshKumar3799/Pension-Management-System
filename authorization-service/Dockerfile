FROM openjdk:8
EXPOSE 8000
ADD target/authorization-service.jar authorization-service.jar
ENTRYPOINT ["java","-jar","/authorization-service.jar"]