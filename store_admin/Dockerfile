FROM node:latest AS builder

WORKDIR /usr/src/app
ENV PATH=/usr/src/node_modules/.bin:$PATH

ARG VITE_BACKEND_URL
ENV VITE_BACKEND_URL=$VITE_BACKEND_URL

COPY package.json ./

RUN yarn install

COPY . ./

FROM builder AS dev
CMD ["yarn", "dev"]

FROM builder AS prod-builder
RUN yarn build

FROM nginx:latest AS prod

COPY --from=prod-builder /usr/src/app/dist /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]
