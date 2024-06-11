FROM maven:3.8.7 as build
COPY . .
RUN mvn -B clean package -DskipTests
FROM openjdk:20
COPY --from=build /target/*.jar Events.jar
EXPOSE 9092
ENTRYPOINT ["Java", "-jar", "Events.jar"]
