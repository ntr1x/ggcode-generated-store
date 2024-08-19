#!/bin/bash

process_put () {
  local key=$1

  local object=$(echo $key | sed -e 's/.*\///g')

  mkdir -p "$(dirname "$SOURCE_DIR/$key")"

  rm -f "$SOURCE_DIR/${key}";
  mcli get "minio/${key}" "$SOURCE_DIR/${key}" >/dev/null;

  local data=$(
    csvq --format jsonl 'SELECT * FROM `'"$SOURCE_DIR/${key}"'`' \
      | jq '{
        username: .["ТабельныйНомер"],
        enabled: true,
        credentials: [
          {
            type: "password",
            value: "password"
          }
        ],
        attributes: {
          "name": .["СотрудникНаименование"],
          "employee_1c_ref": .["СотрудникИдентификатор"],
          "position_1c_ref": .["ДолжностьИдентификатор"],
          "position_title": .["ДолжностьНаименование"],
          "department_title": .["ПодразделениеКод"]
        }
      }' \
      | jq '{
        "ifResourceExists": "OVERWRITE",
        "users": [inputs]
      }'
  )

  local access_token=$(
    curl -s -X POST \
      --user $KEYCLOAK_CLIENT_CLIENTID:$KEYCLOAK_CLIENT_CLIENTSECRET \
      -H "Content-Type: application/x-www-form-urlencoded" \
      -d 'grant_type=client_credentials' \
      $KEYCLOAK_TOKEN_URL \
      | jq -r '.access_token'
  )

  local response=$(
    curl -s -X POST \
      -H "Authorization: Bearer $access_token" \
      -H "Content-Type: application/json" \
      -d "$data" \
      $KEYCLOAK_IMPORT_URL \
      | jq -c 'del(.results)'
  )

  echo -e "[RESPONSE]: $response" >&2;
}


if [[ "$EVENT_NAME" == "s3:ObjectCreated:Put" ]]; then
  echo "[PUT]: $OBJECT_KEY" >&2;
  process_put "$OBJECT_KEY"
else
  echo "[SKIP]: $OBJECT_KEY. Unsupported event: '$EVENT_NAME'" >&2
fi