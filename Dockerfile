FROM alpine:3.14
RUN apk update && add openjdk11-jre
WORKDIR /opt
COPY target/dbo-app.jar .
EXPOSE 8080
ENTRYPOINT java - jar dbo-app.jar