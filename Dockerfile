FROM openjdk:13

EXPOSE 8081

RUN mkdir -p /opt/application/jar
WORKDIR /opt/application/jar
COPY ./target/voteService-0.0.1.jar /opt/application/jar/vote-service-0.0.1.jar

ENTRYPOINT [ "java", \ 
    "-jar", \ 
    "/opt/application/jar/vote-service-0.0.1.jar" ]