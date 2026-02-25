FROM openjdk:17-jdk-slim
ARG VERSION

COPY build/libs/task-tracker-${VERSION}.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]