FROM glassfish:latest

MAINTAINER Sachin "sachin9675@gmail.com"

COPY target/JavaMicroservice.war /
COPY start.sh /

EXPOSE 4848 8080

RUN ["chmod", "+x", "/start.sh"]

ENTRYPOINT ["/start.sh"]
