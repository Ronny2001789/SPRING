ARG FINAL_NAME=kbe-rest-brewery-0.0.1-SNAPSHOT.jar
FROM eclipse-temurin:11-jre AS builder
ARG FINAL_NAME
WORKDIR /application
ADD target/${FINAL_NAME} ./
RUN java -Djarmode=layertools -jar ${FINAL_NAME} extract

FROM eclipse-temurin:11-jre

WORKDIR /application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]