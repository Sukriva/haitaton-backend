FROM registry.redhat.io/openjdk/openjdk-11-rhel7
EXPOSE 8080 8081
VOLUME /tmp
ADD "louhi-service-*.jar" louhi-service.jar
CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar louhi-service.jar" ]
