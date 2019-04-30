FROM openjdk

COPY ./target/vendas-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch vendas-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","vendas-0.0.1-SNAPSHOT.jar"]  