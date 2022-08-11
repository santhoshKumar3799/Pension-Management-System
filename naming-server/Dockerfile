FROM openjdk:8
EXPOSE 8761
ADD target/naming-server.jar naming-server.jar
ENTRYPOINT ["java","-jar","/naming-server.jar"]