FROM ubuntu:22.04

ARG MINIO_URL=https://dl.min.io/client/mc/release/linux-amd64/mc

# RUN apt-get update \
#  && apt-get install -y apt-transport-https ca-certificates dirmngr \
#  && apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 8919F6BD2B48D754 \
#  && echo "deb https://packages.clickhouse.com/deb stable main" | tee /etc/apt/sources.list.d/clickhouse.list \
#  && apt-get update \
#  && apt-get install -y clickhouse-client

RUN apt-get update \
 && apt-get install -y software-properties-common \
 && add-apt-repository ppa:mithrandie/csvq \
 && apt-get update \
 && apt-get install -y libaio1 curl unzip jq csvq mysql-client

RUN curl https://dl.min.io/client/mc/release/linux-amd64/mc --create-dirs -o /usr/lib/minio-binaries/mcli \
 && chmod +x /usr/lib/minio-binaries/mcli

ENV LD_LIBRARY_PATH=/usr/lib/instantclient
ENV MINIO_BINARIES_PATH=/usr/lib/minio-binaries
ENV PATH="$LD_LIBRARY_PATH:$MINIO_BINARIES_PATH:$PATH"

ARG MINIO_TARGET_SERVER=minio:9000
ENV MINIO_TARGET_SERVER=$MINIO_TARGET_SERVER

ARG MINIO_ACCESS_KEY=minioadmin
ENV MINIO_ACCESS_KEY=$MINIO_ACCESS_KEY

ARG MINIO_SECRET_KEY=minioadmin
ENV MINIO_SECRET_KEY=$MINIO_ACCESS_KEY

ARG MINIO_THROTTLE_INTERVAL=5
ENV MINIO_THROTTLE_INTERVAL=$MINIO_THROTTLE_INTERVAL

ARG FETCHER_BUCKET
ENV FETCHER_BUCKET=$FETCHER_BUCKET

ARG FETCHER_BATCH_SIZE=5000
ENV FETCHER_BATCH_SIZE=$FETCHER_BATCH_SIZE

ARG FETCHER_TRACE=false
ENV FETCHER_TRACE=$FETCHER_TRACE

WORKDIR /app

COPY ./bin /app/bin
COPY ./lib /app/lib

VOLUME [ "/app/bin", "/app/lib", "/app/work" ]

ENTRYPOINT ["bash", "/app/bin/import.sh"]
