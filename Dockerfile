FROM openjdk:8
EXPOSE 8083
ADD target/project0.jar app.jar
ENTRYPOINT [ "java" , "-jar" , "/app.jar"]