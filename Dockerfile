FROM openjdk:11
LABEL maintainer="bookmanagement"
ADD build/libs/bookmanagement-0.0.1-SNAPSHOT.jar bookmanagement.jar
ENTRYPOINT ["java","-jar","bookmanagement.jar"]