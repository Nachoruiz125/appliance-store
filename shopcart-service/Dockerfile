FROM eclipse-temurin:17-jdk
ARG JAR_FILE=target/shopcart-service-0.1.jar
COPY ${JAR_FILE} shopcart-service.jar
ENTRYPOINT ["java", "-jar", "shopcart-service.jar"]