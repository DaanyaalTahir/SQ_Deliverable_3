FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ENTRYPOINT ["mvn","spring-boot:run"]