FROM node:boron

# Create app directory
WORKDIR /app

ADD . /app

RUN npm install express-gateway

EXPOSE 80

CMD ["npm", "start"]
