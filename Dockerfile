FROM openjdk:16-slim
RUN addgroup --system  spring && adduser --system spring --ingroup spring
USER spring:spring
COPY ./logs /logs/
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]