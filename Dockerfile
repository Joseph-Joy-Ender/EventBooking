FROM maven:3.8.7 as build
COPY . .
RUN mvn -B clean package -DskipTests
FROM openjdk:17-jdk-slim
COPY --from=build /target/*.jar Events.jar
EXPOSE 9092
ENTRYPOINT ["java", "-jar", "Events.jar"]
