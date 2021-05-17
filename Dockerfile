#### Stage 1: Build the application
FROM openjdk:8-jdk-alpine

#Install Maven
RUN apk add --no-cache curl tar bash
ARG MAVEN_VERSION=3.3.9
ARG USER_HOME_DIR="/root"
RUN mkdir -p /usr/share/maven && \
curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
ENTRYPOINT ["/usr/bin/mvn"]

# Set the current working directory inside the image
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Copy maven executable to the image
COPY mvnw /usr/src/app
COPY .mvn /usr/src/app/.mvn

# Copy the pom.xml file
COPY pom.xml /usr/src/app

# Build all the dependencies in preparation to go offline. 
# This is a separate step so the dependencies will be cached unless 
# the pom.xml file has changed.
RUN chmod +x ./mvnw
# RUN mvn install:install-file -Dfile="target/stocknote-bridge-java-1.0.1.jar" -DgroupId=io.samco -DartifactId=stocknote-bridge-java -Dversion=1.0.1 -Dpackaging=jar
RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY src /usr/src/app/src

# Package the application
RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

#### Stage 2: A minimal docker image with command to run the app 
FROM openjdk:8-jre-alpine

ARG DEPENDENCY=/usr/src/app/target/dependency

# Copy project dependencies from the build stage
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:usr/src/app/lib/*","com.axis.app.MyTradingAppSbApplication"]