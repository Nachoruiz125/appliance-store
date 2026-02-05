FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/sales-service-0.1.jar
COPY ${JAR_FILE} sales-service.jar
ENTRYPOINT ["java", "-jar", "sales-service.jar"]