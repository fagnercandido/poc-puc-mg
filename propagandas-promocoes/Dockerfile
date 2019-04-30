FROM openjdk

COPY ./target/propagandas-promocoes-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch propagandas-promocoes-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","propagandas-promocoes-0.0.1-SNAPSHOT.jar"]  