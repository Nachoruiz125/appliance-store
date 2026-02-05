FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/config-server-0.1.jar
COPY ${JAR_FILE} config-server.jar
ENTRYPOINT ["java", "-jar", "config-server.jar"]