FROM openjdk:8
EXPOSE 8089
ADD target/kaddem-0.0.2-SNAPSHOT.jar kaddem-test.jar
ENTRYPOINT ["java","-jar","/kaddem-test.jar "]
