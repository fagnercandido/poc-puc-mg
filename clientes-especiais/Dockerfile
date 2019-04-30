FROM openjdk

COPY ./target/clientes-especiais-0.0.1.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch clientes-especiais-0.0.1.jar'

ENTRYPOINT ["java","-jar","clientes-especiais-0.0.1.jar"]  