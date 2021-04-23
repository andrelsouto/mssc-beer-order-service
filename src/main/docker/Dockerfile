FROM openjdk:11-jre-slim as builder
WORKDIR application
ADD target/mssc-beer-order-service-0.0.1-SNAPSHOT.jar mssc-beer-order-service-0.0.1-SNAPSHOT.jar
RUN java -Djarmode=layertools -jar mssc-beer-order-service-0.0.1-SNAPSHOT.jar extract

FROM openjdk:11-jre-slim
LABEL PROJECT_NAME=teste \
      PROJECT=reste

EXPOSE 8080

WORKDIR application
COPY --from=builder application/dependencies ./
COPY --from=builder application/spring-boot-loader ./
COPY --from=builder application/snapshot-dependencies ./
COPY --from=builder application/application ./
ENTRYPOINT ["java", "-Djava.security.egd=file:dev/./urandom", "org.springframework.boot.loader.JarLauncher"]
