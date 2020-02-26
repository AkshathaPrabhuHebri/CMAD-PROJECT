FROM node:10.9-alpine

WORKDIR /app/syslog-client

COPY ./dist/* /app/syslog-client/

RUN cd /app/syslog-client/

RUN npm install express -g
RUN export NODE_PATH=/usr/local/lib/node_modules/
CMD ["node", "server.js"]
EXPOSE 3000
