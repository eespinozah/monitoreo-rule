FROM maven:3.6.3-jdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace

COPY ./pom.xml .
RUN mvn dependency:go-offline

COPY ./ .
RUN mvn -B -f pom.xml clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /workspace/target/*.jar monitoreo-rule.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","monitoreo-rule.jar"]