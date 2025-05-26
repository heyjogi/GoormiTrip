# 1. Base image
FROM eclipse-temurin:21-jdk-alpine

# 2. Timezone 설정
RUN apk add --no-cache tzdata \
    && cp /usr/share/zoneinfo/Asia/Seoul /etc/localtime \
    && echo "Asia/Seoul" > /etc/timezone \
    && apk del tzdata

# 3. 작업 디렉토리 생성
WORKDIR /app

# 4. JAR 파일 복사
COPY ./build/libs/*.jar app.jar

# 5. 포트 오픈
EXPOSE 8080

# 6. 실행 명령
ENTRYPOINT ["java", "-jar", "app.jar"]
