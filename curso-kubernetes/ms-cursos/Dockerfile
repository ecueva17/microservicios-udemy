FROM amazoncorretto:21-alpine

WORKDIR /app

COPY ./target/ms-cursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "ms-cursos-0.0.1-SNAPSHOT.jar"]