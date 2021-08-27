FROM openjdk:16-slim
RUN addgroup --system  spring && adduser --system spring --ingroup spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY ./logs /logs/
ENTRYPOINT ["java", "-jar", "/app.jar"]