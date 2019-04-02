# base image
FROM stackaero/aero-tomcat:1.0.4

# set working directory
WORKDIR /opt/bitnami/tomcat


ENV GROUPID com.acme.mortgage
ENV ARTIFACT mortgage-api
ENV REPOSITORY https://artifactory-standalone-repodev5.cloud.stacklynx.com/artifactory/aero-repo
ENV SETUP_SCRIPT $TOMCAT_HOME/bin/bootstrap-app.sh
RUN curl -o $SETUP_SCRIPT https://raw.githubusercontent.com/stacklynx/mortgage-api/master/bootstrap-app.sh &&  chmod +x $SETUP_SCRIPT 

RUN  /home/bitnami/download-artifact.sh 

ENV REPOSITORY ""
ENV GROUPID ""

