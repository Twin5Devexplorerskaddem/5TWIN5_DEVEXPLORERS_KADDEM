FROM openjdk:8
EXPOSE 8089
ADD target/kaddem-0.0.1-SNAPSHOT.jar kaddemtest.jar
ENTRYPOINT ["java","-jar","/kaddemtest.jar "]
