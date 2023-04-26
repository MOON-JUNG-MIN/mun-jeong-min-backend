FROM openjdk:17.0.1-jdk-slim
ENV TZ=Asia/Seoul
COPY ./build/libs/*.jar ./app.jar
ENTRYPOINT ["java","-jar","./app.jar"]