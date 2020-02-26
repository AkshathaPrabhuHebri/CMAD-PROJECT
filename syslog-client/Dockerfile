FROM node:10.9-alpine

WORKDIR /app/syslog-client

COPY ./dist/* /app/syslog-client/

RUN cd /app/syslog-client/

ENV PORT 3000c
EXPOSE 3000

CMD ["node", "server.js"]
