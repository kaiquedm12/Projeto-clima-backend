# Use uma imagem oficial do Maven para construir o projeto
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

# Use uma imagem oficial do OpenJDK para rodar o projeto
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/projeto-intermediario-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
