FROM amazoncorretto:21-alpine-jdk

ARG JAR_FILE=target/inventory.jar

COPY ${JAR_FILE} inventory.jar

ENTRYPOINT ["java","-jar","/inventory.jar"]