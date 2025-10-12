# Stage 1: Build
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Копируем pom.xml и сборочные файлы
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Собираем с production profile
RUN ./mvnw dependency:go-offline -Pproduction
COPY src src
RUN ./mvnw clean package -Pproduction -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre

WORKDIR /app

# Копируем JAR из build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]