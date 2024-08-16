#stage 1
#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as builder

# Add Maintainer Info
LABEL maintainer="Brice Monthe"

# The application's version file
ARG PROJECT_VERSION=0.0.1
ARG WKD=/home/app
ARG VERSION=0.37.1

RUN mkdir -p ${WKD}

WORKDIR ${WKD}

# Copy the project files to the container
COPY . .

# Build the application and run tests
RUN ./mvnw clean package -DskipTests -Dcheckstyle.skip -Dpmd.skip

# Add the application's jar to the container
RUN cp ./target/*.jar app.jar

#stage 2
#Same Java runtime
FROM openjdk:17-jdk-slim

#Add volume pointing to /tmp
VOLUME /tmp

#Copy unpackaged application to new container
ARG WORKDIR=/home/app
ARG SPRING_PROFILES_ACTIVE=dev

COPY --from=builder ${WORKDIR}/ /app

#execute the application
# ENTRYPOINT ["java","-cp","app:app/lib/*","com.emarketshop.payment_service.PaymentServiceApplication"]

EXPOSE 8085

CMD ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "/app/app.jar"]

