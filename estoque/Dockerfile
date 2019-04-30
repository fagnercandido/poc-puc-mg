FROM openjdk

COPY ./target/estoque-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch estoque-0.0.1-SNAPSHOT.jar'

EXPOSE 8080
EXPOSE 5432

ENTRYPOINT ["java","-jar","estoque-0.0.1-SNAPSHOT.jar"]  