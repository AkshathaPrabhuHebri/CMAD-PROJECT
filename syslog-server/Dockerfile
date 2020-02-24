from maven:3.6.1-jdk-8-slim
RUN mkdir -p /app
WORKDIR /app
COPY . .
RUN mvn package && \
	  pwd && \
	  ls target/ && \
      mv /app/target/*.jar /run/syslog-server.jar && \
      rm -rf /app/*
CMD java -jar /run/syslog-server.jar
