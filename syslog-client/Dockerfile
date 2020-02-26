FROM node:10.9-alpine

WORKDIR /app/syslog-client

COPY ./dist/* /app/syslog-client/

RUN cd /app/syslog-client/

RUN npm install express -g
RUN ls -la /usr/local/lib/node_modules/
ENV NODE_PATH=/usr/local/lib/node_modules/
CMD ["node", "server.js"]
# CMD ["sleep", "100000"]
EXPOSE 3000
