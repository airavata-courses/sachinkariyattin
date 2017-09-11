FROM glassfish:latest

MAINTAINER Sachin "sachin9675@gmail.com"

COPY target/JavaMicroservice.war /
COPY start.sh /

EXPOSE 8080

RUN ["chmod", "+x", "/start.sh"]

ENTRYPOINT ["/start.sh"]
