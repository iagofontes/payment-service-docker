FROM openjdk:8-jdk-alpine
ADD target/payment-service-docker.jar payment-service-docker.jar
EXPOSE 8081
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java", "-jar", "payment-service-docker.jar"]
