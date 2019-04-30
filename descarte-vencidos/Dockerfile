FROM openjdk

COPY ./target/descarte-vencidos-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch descarte-vencidos-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","descarte-vencidos-0.0.1-SNAPSHOT.jar"]  