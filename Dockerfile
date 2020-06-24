FROM openjdk:8-jre-alpine
RUN apk update \
    && apk add  unzip \
    && apk add curl \
    && adduser -u 1001 -h /home/sunbird/ -D sunbird \
    && mkdir -p /home/sunbird/ 
RUN chown -R sunbird:sunbird /home/sunbird
USER sunbird
COPY ./play-service-1.0.0-dist.zip /home/sunbird/
RUN unzip /home/sunbird/play-service-1.0.0-dist.zip -d /home/sunbird/ 
EXPOSE 9000
WORKDIR /home/sunbird/
CMD java -XX:+PrintFlagsFinal $JAVA_OPTIONS -cp '/home/sunbird/play-service-1.0.0/lib/*' play.core.server.ProdServerStart  /home/sunbird/play-service-1.0.0