FROM eclipse-temurin:17-jre-jammy

ENV TZ=Asia/Seoul

WORKDIR /app

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]