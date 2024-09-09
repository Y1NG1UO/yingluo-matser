FROM openjdk:17.0.2-jdk
COPY yingluo-service/target/yingluo-service.jar /app/yingluo-service.jar
# copy arthas
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas
WORKDIR /app
CMD ["java", "-jar","-Dspring.profiles.active=docker","-Duser.timezone=GMT+08", "yingluo-service.jar"]

