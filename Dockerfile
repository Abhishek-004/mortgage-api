FROM tomcat

#RUN mkdir /usr/local/tomcat/webapps/studentapp

COPY ./target/MortgageLoan-API-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/mortgage-api.war