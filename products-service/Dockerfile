FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/products-service-0.1.jar
COPY ${JAR_FILE} products-service.jar
ENTRYPOINT ["java", "-jar", "products-service.jar"]