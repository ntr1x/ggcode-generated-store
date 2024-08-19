FROM ubuntu:22.04

ARG INSTANTCLIENT_BASICLITE_URL=https://download.oracle.com/otn_software/linux/instantclient/instantclient-basic-linuxx64.zip
# ARG INSTANTCLIENT_BASICLITE_URL=https://download.oracle.com/otn_software/linux/instantclient/2115000/instantclient-basic-linux.x64-21.15.0.0.0dbru.zip
ARG INSTANTCLIENT_SQLPLUS_URL=https://download.oracle.com/otn_software/linux/instantclient/instantclient-sqlplus-linuxx64.zip
# ARG INSTANTCLIENT_SQLPLUS_URL=https://download.oracle.com/otn_software/linux/instantclient/2115000/instantclient-sqlplus-linux.x64-21.15.0.0.0dbru.zip
ARG MINIO_URL=https://dl.min.io/client/mc/release/linux-amd64/mc

RUN apt-get update \
 && apt-get install -y software-properties-common \
 && add-apt-repository ppa:mithrandie/csvq \
 && apt-get update \
 && apt-get install -y libaio1 curl unzip jq csvq

RUN mkdir -p /usr/lib/instantclient \
 && mkdir -p /tmp/instantclient \
 && cd /tmp/instantclient \
 && curl -o instantclient-basiclite.zip ${INSTANTCLIENT_BASICLITE_URL} \
 && curl -o instantclient-sqlplus.zip ${INSTANTCLIENT_SQLPLUS_URL} \
 && unzip instantclient-basiclite.zip -d ./basiclite \
 && unzip instantclient-sqlplus.zip -d ./sqlplus

RUN cd /tmp/instantclient \
 && mv ./basiclite/instantclient*/* /usr/lib/instantclient/ \
 && mv ./sqlplus/instantclient*/* /usr/lib/instantclient/ \
 && ln -s /usr/lib/instantclient/libclntsh.so.23.1 /usr/lib/libclntsh.so \
 && ln -s /usr/lib/instantclient/libocci.so.23.1 /usr/lib/libocci.so \
 && ln -s /usr/lib/instantclient/libociicus.so /usr/lib/libociicus.so \
 && ln -s /usr/lib/instantclient/libnnz23.so /usr/lib/libnnz23.so \
 && ln -s /usr/lib/libnsl.so.2 /usr/lib/libnsl.so.1 \
 && ln -s /lib/libc.so.6 /usr/lib/libresolv.so.2 \
 && rm -rf /tmp/instantclient

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

ARG NLS_LANG=RUSSIAN_RUSSIA.UTF8
ENV NLS_LANG=$NLS_LANG

WORKDIR /app

COPY ./bin /app/bin
COPY ./lib /app/lib

VOLUME [ "/app/bin", "/app/lib", "/app/work" ]

ENTRYPOINT ["bash", "/app/bin/import.sh"]
