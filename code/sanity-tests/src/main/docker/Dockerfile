FROM eclipse-temurin:17-jdk

VOLUME /tmp
COPY sanity-tests-*-all.jar app.jar

CMD ["java","-jar","app.jar"]

EXPOSE 8080:8080