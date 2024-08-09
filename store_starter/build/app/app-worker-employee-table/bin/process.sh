#!/bin/bash

process_put () {
  local key=$1

  local object=$(echo $key | sed -e 's/.*\///g')

  mkdir -p "$(dirname "$SOURCE_DIR/$key")"

  rm -f $SOURCE_DIR/${key};
  mcli get "minio/${key}" "$SOURCE_DIR/${key}" >/dev/null;

  csvq --format jsonl 'SELECT * FROM `'"$SOURCE_DIR/${key}"'`' | jq
}


if [[ "$EVENT_NAME" == "s3:ObjectCreated:Put" ]]; then
  echo "[PUT]: $OBJECT_KEY" >&2;
  process_put "$OBJECT_KEY"
else
  echo "[SKIP]: $OBJECT_KEY. Unsupported event: '$EVENT_NAME'" >&2
fi