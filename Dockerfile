FROM sa-saopaulo-1.ocir.io/gruxacaxnw3m/devops/gradle:jre11

USER root

RUN mkdir /apm-agent
RUN mkdir /api && apt-get update && apt-get install vim telnet wget -y
COPY build/libs/*.jar /api
COPY devops/elastic-apm-agent-1.31.0.jar /apm-agent

WORKDIR /api
ENV TZ=America/Sao_Paulo

CMD $JAVA_HOME/bin/java -Duser.timezone=America/Sao_Paulo -Dspring.profiles.active=$PROFILE -Dhostname=$HOSTNAME -Dtoken=$TOKEN -server -Xms4g -Xmx4g -javaagent:/apm-agent/elastic-apm-agent-1.31.0.jar -Delastic.apm.service_name=ms-cost-center -Delastic.apm.server_urls=http://logstash-node4.valecard.com.br:8200 -Delastic.apm.application_packages=br.com.valecard -Delastic.apm.environment=$PROFILE -XX:+UseConcMarkSweepGC -XX:-UseGCOverheadLimit -jar *.jar
