FROM adoptopenjdk/openjdk17:jdk-17.0.2_8-alpine
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    WAIT_TIME=0 \
    JAVA_OPTS=""
VOLUME /room-booking-service
ARG JAR_FILE=target/roombooking-*.jar
COPY ${JAR_FILE} /room-booking-service/room-booking.jar
ENTRYPOINT ["java","-jar","/room-booking-service/room-booking.jar"]
EXPOSE 9090