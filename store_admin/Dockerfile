FROM node:latest AS builder

WORKDIR /usr/src/app
ENV PATH=/usr/src/node_modules/.bin:$PATH

ARG BUILD_MODE=development
ENV BUILD_MODE=$BUILD_MODE

COPY package.json ./

RUN yarn install

COPY . ./

FROM builder AS dev
CMD ["yarn", "dev"]

FROM builder AS prod-builder
RUN yarn vue-tsc && yarn vite build --mode $BUILD_MODE

FROM nginx:latest AS prod

COPY --from=prod-builder /usr/src/app/dist /usr/share/nginx/html

ARG BUILD_MODE=development
ENV BUILD_MODE=$BUILD_MODE

RUN if [${BUILD_MODE} != development ]; then rm /usr/share/nginx/html/config.js; fi
COPY ./spa-nginx.conf /etc/nginx/conf.d/default.conf

CMD ["nginx", "-g", "daemon off;"]
