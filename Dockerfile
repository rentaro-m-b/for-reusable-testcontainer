FROM amazoncorretto:21.0.8-al2023 AS builder

WORKDIR /workspace

COPY mvnw ./
RUN chmod +x mvnw
COPY .mvn .mvn
COPY pom.xml ./
COPY src ./src
RUN dnf install -y tar gzip && dnf clean all
RUN ./mvnw -B -DskipTests -DfinalName=app package

FROM amazoncorretto:21.0.8-al2023 AS runtime

WORKDIR /app

COPY --from=builder /workspace/target/app.jar ./app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
