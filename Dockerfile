FROM openjdk:21-jdk-slim as builder
WORKDIR /app

COPY gradlew .
COPY gradle/ ./gradle/
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x ./gradlew

RUN ./gradlew clean build -x test --no-daemon


FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=builder /app/build/libs/*jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]