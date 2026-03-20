FROM maven:3.9.9-eclipse-temurin-17 AS dev
WORKDIR /app

COPY pom.xml .
RUN mvn --batch-mode dependency:go-offline
COPY src ./src

EXPOSE 8080

CMD ["mvn", "--batch-mode", "spring-boot:run"]

FROM dev AS build

RUN mvn --batch-mode clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/springboot-demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
