#-platform=linux/amd64
FROM openjdk:17
VOLUME /tmp
COPY target/Cst438Project2-0.0.1-SNAPSHOT.jar  Cst438Project2-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","Cst438Project2-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080:8080

#intense-springs-54966
#https://intense-springs-54966.herokuapp.com/ | https://git.heroku.com/intense-springs-54966.git

