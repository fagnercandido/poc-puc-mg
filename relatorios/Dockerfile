FROM openjdk

COPY ./target/relatorios-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch relatorios-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","relatorios-0.0.1-SNAPSHOT.jar"]  