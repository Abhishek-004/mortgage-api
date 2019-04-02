# base image
FROM stackaero/aero-tomcat:1.0.4

# set working directory
WORKDIR /opt/bitnami/tomcat


ENV GROUPID com.acme.mortgage
ENV ARTIFACT mortgage-api
ENV REPOSITORY https://artifactory-standalone-repodev5.cloud.stacklynx.com/artifactory/aero-repo
ENV SETUP_SCRIPT /home/bitnami/bootstrap-app.sh

COPY ./bootstrap-app.sh $SETUP_SCRIPT

RUN  /home/bitnami/download-artifact.sh 

ENV REPOSITORY ""
ENV GROUPID ""

