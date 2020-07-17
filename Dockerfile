FROM maven:3-openjdk-11 AS builder
RUN mkdir multithreaded-echo-server
WORKDIR multithreaded-echo-server
COPY . .
RUN mvn clean package


FROM dabal/jre11-alpine:latest
EXPOSE 6666
COPY --from=builder /multithreaded-echo-server/target/multithreaded-echo-server-1.0-SNAPSHOT-jar-with-dependencies.jar .
CMD ["java","-jar","multithreaded-echo-server-1.0-SNAPSHOT-jar-with-dependencies.jar"]