
FROM node:14 AS builder

WORKDIR /app

COPY package*.json ./


RUN npm install


COPY . .

RUN npm run build

FROM nginx:alpine

COPY --from=builder /app/dist/contrat /usr/share/nginx/html

EXPOSE 4100

CMD ["nginx", "-g", "daemon off;"]
