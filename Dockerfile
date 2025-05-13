FROM jenkins/jenkins:lts

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

COPY casc.yaml /usr/share/jenkins/ref/casc.yaml
COPY seed-job.groovy /usr/share/jenkins/ref/init.groovy.d/seed-job.groovy
COPY jobs /usr/share/jenkins/ref/jobs/seed-job/workspace/jobs
