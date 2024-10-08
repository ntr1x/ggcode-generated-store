FROM maven:3.9.5-eclipse-temurin-17-alpine AS loader
WORKDIR /app

ADD pom.xml .
ADD assembly_web/pom.xml ./assembly_web/pom.xml
ADD common_api/pom.xml ./common_api/pom.xml
ADD common_events/pom.xml ./common_events/pom.xml
ADD common_jpa/pom.xml ./common_jpa/pom.xml
ADD common_minio/pom.xml ./common_minio/pom.xml
ADD common_security/pom.xml ./common_security/pom.xml
ADD common_web/pom.xml ./common_web/pom.xml
ADD common_ws/pom.xml ./common_ws/pom.xml
ADD service_catalog/pom.xml ./service_catalog/pom.xml
ADD service_events/pom.xml ./service_events/pom.xml
ADD service_payments/pom.xml ./service_payments/pom.xml
ADD service_profile/pom.xml ./service_profile/pom.xml
ADD service_security/pom.xml ./service_security/pom.xml
ADD service_structure/pom.xml ./service_structure/pom.xml
ADD service_uploads/pom.xml ./service_uploads/pom.xml
ADD gateway_events/pom.xml ./gateway_events/pom.xml
ADD gateway_payments/pom.xml ./gateway_payments/pom.xml
ADD gateway_profile/pom.xml ./gateway_profile/pom.xml
ADD shared_api/pom.xml ./shared_api/pom.xml
RUN mvn dependency:go-offline

FROM loader AS builder
ARG MODULE_NAME
ARG MODULE_VERSION=1.0-SNAPSHOT
ADD assembly_web/src ./assembly_web/src
ADD common_api/src ./common_api/src
ADD common_events/src ./common_events/src
ADD common_jpa/src ./common_jpa/src
ADD common_minio/src ./common_minio/src
ADD common_security/src ./common_security/src
ADD common_web/src ./common_web/src
ADD common_ws/src ./common_ws/src
ADD service_catalog/src ./service_catalog/src
ADD service_events/src ./service_events/src
ADD service_payments/src ./service_payments/src
ADD service_profile/src ./service_profile/src
ADD service_security/src ./service_security/src
ADD service_structure/src ./service_structure/src
ADD service_uploads/src ./service_uploads/src
ADD gateway_events/src ./gateway_events/src
ADD gateway_payments/src ./gateway_payments/src
ADD gateway_profile/src ./gateway_profile/src
ADD shared_api/src ./shared_api/src
RUN mvn -Drevision=$MODULE_VERSION clean package --projects $MODULE_NAME --also-make

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
VOLUME /tmp
ARG MODULE_NAME
ARG MODULE_VERSION=1.0-SNAPSHOT
ENV JAR_FILE="/app/${MODULE_NAME}/target/${MODULE_NAME}-${MODULE_VERSION}-spring-boot.jar"
COPY --from=builder ${JAR_FILE} /app/app.jar
RUN ls -la .
ENTRYPOINT java $JAVA_OPTS -jar /app/app.jar
