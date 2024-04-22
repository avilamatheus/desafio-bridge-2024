# https://thedkpatel.medium.com/dockerizing-react-application-built-with-vite-a-simple-guide-4c41eb09defa

FROM node:18-alpine

ARG VITE_BACK_END_URL
ENV VITE_BACK_END_URL=$VITE_BACK_END_URL

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

RUN npm run build

EXPOSE 5173

CMD [ "npm", "run", "preview" ]