FROM java:8-jdk-alpine

COPY ./target/entrega-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch entrega-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","entrega-0.0.1-SNAPSHOT.jar"]  