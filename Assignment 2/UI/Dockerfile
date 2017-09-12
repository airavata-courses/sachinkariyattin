FROM node:boron

# Create app directory
WORKDIR /app

ADD . /app

RUN npm install connect serve-static

EXPOSE 8090

CMD ["node", "server.js"]

