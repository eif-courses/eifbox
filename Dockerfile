FROM gradle:8.4.0-jdk17 as builder
LABEL authors="Marius"
WORKDIR /app
COPY . .
RUN ./gradlew build --stacktrace

FROM openjdk
WORKDIR /app
EXPOSE 8080
COPY --from=builder /app/build/libs/eifbox-0.0.1-SNAPSHOT.jar .
CMD java -jar eifbox-0.0.1-SNAPSHOT.jar
