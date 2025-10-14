FROM amazoncorretto:21.0.8-al2023 AS builder

WORKDIR /workspace

COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./
RUN chmod +x mvnw

RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw -B -q -DskipTests dependency:go-offline

COPY src ./src
RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw -B -DskipTests package

RUN set -eux; \
    JAR_PATH="$(ls target/*.jar | head -n1)"; \
    cp "$JAR_PATH" /workspace/app.jar

FROM amazoncorretto:21.0.8-al2023 AS runtime
WORKDIR /app

ENV TZ=Asia/Tokyo \
    SPRING_PROFILES_ACTIVE=prod \
    JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75 -XX:+ExitOnOutOfMemoryError"

# 非rootユーザー（セキュリティ基本）
RUN addgroup --system app && adduser --system --ingroup app app
# ↑ 注: Amazon Linux 2023 では adduser/addgroup が無い場合がある。その場合は:
# RUN groupadd -r app && useradd -r -g app app
USER app:app                                      # 以降の実行ユーザーを app に固定

# 実行Jarのみを持ち込む（攻撃面積とサイズを最小化）
COPY --from=builder /workspace/app.jar ./app.jar

EXPOSE 8080                                       # ドキュメント上の公開ポート（実際の公開は docker run -p で行う）
ENTRYPOINT ["java","-jar","/app/app.jar"]         # exec 形式で PID1=java。シグナルが正しく届く


