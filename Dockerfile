FROM amazoncorretto:21.0.8-al2023 AS builder

WORKDIR /workspace

COPY mvnw ./
RUN chmod +x mvnw
COPY .mvn .mvn
COPY pom.xml ./
COPY src ./src
RUN dnf install -y tar gzip && dnf clean all
RUN ./mvnw -B -DskipTests package

FROM amazoncorretto:21.0.8-al2023 AS runtime

WORKDIR /app

COPY --from=builder /workspace/target/demo-for-showing-test-containers-0.0.1-SNAPSHOT.jar ./demo-for-showing-test-containers-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/demo-for-showing-test-containers-0.0.1-SNAPSHOT.jar"]
