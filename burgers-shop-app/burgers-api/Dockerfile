FROM openjdk:17-jdk-slim

#Add volume pointing to /tmp
VOLUME /tmp

#Copy unpackaged application to new container
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app/app.jar

ENV SPRING_PROFILES_ACTIVE dev

#execute the application
# ENTRYPOINT ["java","-cp","app:app/lib/*","com.emarketshop.payment_service.PaymentServiceApplication"]

EXPOSE 8500

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "/app/app.jar"]