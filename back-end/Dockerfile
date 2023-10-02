#Dockerfile for gradle using jdk17
FROM bellsoft/liberica-openjdk-alpine:17.0.5
#Copy data from the build output to docker
RUN mkdir /app
COPY build/libs/*.jar app.jar
#Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 9001