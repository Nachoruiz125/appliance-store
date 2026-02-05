FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/eureka-server-0.1.jar
COPY ${JAR_FILE} app_eureka.jar
ENTRYPOINT ["java", "-jar", "app_eureka.jar"]