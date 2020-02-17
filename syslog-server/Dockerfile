from maven:3.6.1-jdk-8-slim
WORKDIR /app
COPY . .
RUN mvn package && \
      mv target/syslog-server.jar /run/syslog-server.jar && \
      rm -rf /app/*
CMD java  -jar /run/syslog-server.jar
