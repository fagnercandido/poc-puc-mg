FROM openjdk

COPY ./target/clientes-especiais-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch clientes-especiais-0.0.1-SNAPSHOT.jar'

EXPOSE 9180
EXPOSE 5432

ENTRYPOINT ["java","-jar","clientes-especiais-0.0.1-SNAPSHOT.jar"]  
