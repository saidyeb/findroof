FROM openjdk:11
ADD target/findroof-0.0.1-SNAPSHOT.jar findroof-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "findroof-0.0.1-SNAPSHOT.jar"]
