#!/bin/bash

TARGET_BUCKET="product-image-variant"

process_put () {
  local key=$1

  local object=$(echo $key | sed -e 's/.*\///g')

  mkdir -p "$(dirname "$SOURCE_DIR/$key")"
  mkdir -p "$(dirname "$TARGET_DIR/rbt-cropped/${key}")"
  mkdir -p "$(dirname "$TARGET_DIR/rbt-190x/${key}")"
  mkdir -p "$(dirname "$TARGET_DIR/rbt-80x80/${key}")"
  mkdir -p "$(dirname "$TARGET_DIR/rbt-100x100/${key}")"
  mkdir -p "$(dirname "$TARGET_DIR/rbt-190x190/${key}")"
  mkdir -p "$(dirname "$TARGET_DIR/rbt-legacy-227x197/${key}")"
  mkdir -p "$(dirname "$TARGET_DIR/rbt-legacy-398x256/${key}")"
  mkdir -p "$(dirname "$TARGET_DIR/rbt-60x75/${key}")"
  mkdir -p "$(dirname "$TARGET_DIR/rbt-legacy-560x420/${key}")"
  mkdir -p "$(dirname "$TARGET_DIR/rbt-1400x900/${key}")"
  mkdir -p "$(dirname "$TARGET_DIR/rbt-x128/${key}")"

  rm -f "$SOURCE_DIR/${key}";
  mcli get "minio/${key}" "$SOURCE_DIR/${key}" >/dev/null;

  if test -f "$SOURCE_DIR/${key}"; then

    # Сохранение как картинки с обрезанными белыми рамками..
    convert "$SOURCE_DIR/$key" \
      -fuzz 5% \
      -trim +repage \
      -gravity center \
      -background white \
      "$TARGET_DIR/rbt-cropped/${key}";

    mcli put "$TARGET_DIR/rbt-cropped/${key}" "minio/$TARGET_BUCKET/rbt-cropped/${object}" >/dev/null;
    rm "$TARGET_DIR/rbt-cropped/${key}";

    # Сохранение как картинки фиксированной ширины 190px
    convert "$SOURCE_DIR/$key" \
      -fuzz 5% \
      -trim +repage \
      -gravity center \
      -resize 190x \
      -bordercolor white \
      -background white \
      "$TARGET_DIR/rbt-190x/$key";

    mcli put "$TARGET_DIR/rbt-190x/${key}" "minio/$TARGET_BUCKET/rbt-190x/${object}" >/dev/null;
    rm "$TARGET_DIR/rbt-190x/${key}";

    # Сохранение как картинки уменьшенного и фиксированного размера 80x80.
    convert "$SOURCE_DIR/$key" \
      -fuzz 5% \
      -trim +repage \
      -gravity center \
      -thumbnail 80x80 \
      -extent 80x80 \
      -bordercolor white \
      -background white \
      "$TARGET_DIR/rbt-80x80/$key";

    mcli put "$TARGET_DIR/rbt-80x80/${key}" "minio/$TARGET_BUCKET/rbt-80x80/${object}" >/dev/null;
    rm "$TARGET_DIR/rbt-80x80/${key}";

    # Сохранение как картинки уменьшенного и фиксированного размера 80x80.
    convert "$SOURCE_DIR/$key" \
      -fuzz 5% \
      -trim +repage \
      -gravity center \
      -thumbnail 100x100 \
      -extent 100x100 \
      -bordercolor white \
      -background white \
      "$TARGET_DIR/rbt-100x100/$key";

    mcli put "$TARGET_DIR/rbt-100x100/${key}" "minio/$TARGET_BUCKET/rbt-100x100/${object}" >/dev/null;
    rm "$TARGET_DIR/rbt-100x100/${key}";

    # Сохранение как картинки уменьшенного и фиксированного размера 190x190.
    convert "$SOURCE_DIR/$key" \
      -fuzz 5% \
      -trim +repage \
      -gravity center \
      -thumbnail 190x190 \
      -extent 190x190 \
      -bordercolor white \
      -background white \
      "$TARGET_DIR/rbt-190x190/$key";

    mcli put "$TARGET_DIR/rbt-190x190/${key}" "minio/$TARGET_BUCKET/rbt-190x190/${object}" >/dev/null;
    rm "$TARGET_DIR/rbt-190x190/${key}";

    # Сохранение как картинки уменьшенного и фиксированного размера 227x197 (для старого сайта).
    convert "$SOURCE_DIR/$key" \
      -fuzz 5% \
      -trim +repage \
      -gravity center \
      -thumbnail 227x197 \
      -extent 227x197 \
      -bordercolor white \
      -background white \
      "$TARGET_DIR/rbt-legacy-227x197/$key";

    mcli put "$TARGET_DIR/rbt-legacy-227x197/${key}" "minio/$TARGET_BUCKET/rbt-legacy-227x197/${object}" >/dev/null;
    rm "$TARGET_DIR/rbt-legacy-227x197/${key}";

    # Сохранение как картинки уменьшенного и фиксированного размера 398x256 (для старого сайта).
    convert "$SOURCE_DIR/$key" \
      -fuzz 5% \
      -trim +repage \
      -gravity center \
      -thumbnail 398x256 \
      -extent 398x256 \
      -bordercolor white \
      -background white \
      "$TARGET_DIR/rbt-legacy-398x256/$key";

    mcli put "$TARGET_DIR/rbt-legacy-398x256/${key}" "minio/$TARGET_BUCKET/rbt-legacy-398x256/${object}" >/dev/null;
    rm "$TARGET_DIR/rbt-legacy-398x256/${key}";

    # Сохранение как картинки уменьшенного и фиксированного размера 60x75.
    convert "$SOURCE_DIR/$key" \
      -fuzz 5% \
      -trim +repage \
      -gravity center \
      -thumbnail 60x75 \
      -extent 60x75 \
      -bordercolor white \
      -background white \
      "$TARGET_DIR/rbt-60x75/$key";

    mcli put "$TARGET_DIR/rbt-60x75/${key}" "minio/$TARGET_BUCKET/rbt-60x75/${object}" >/dev/null;
    rm "$TARGET_DIR/rbt-60x75/${key}";

    # Сохранение как картинки фиксированного размера 560x420 (если можно поменять размер c 530x400).
    convert "$SOURCE_DIR/$key" \
      -fuzz 5% \
      -trim +repage \
      -gravity center \
      -resize 560x420 \
      -extent 560x420 \
      -bordercolor white \
      -background white \
      "$TARGET_DIR/rbt-legacy-560x420/$key";

    mcli put "$TARGET_DIR/rbt-legacy-560x420/${key}" "minio/$TARGET_BUCKET/rbt-legacy-560x420/${object}" >/dev/null;
    rm "$TARGET_DIR/rbt-legacy-560x420/${key}";

    # Сохранение как картинки фиксированного размера 1400x900 (если можно поменять размер c 530x400).
    convert "$SOURCE_DIR/$key" \
      -fuzz 5% \
      -trim +repage \
      -gravity center \
      -resize 1400x900 \
      -extent 1400x900 \
      -bordercolor white \
      -background white \
      "$TARGET_DIR/rbt-1400x900/$key";

    mcli put "$TARGET_DIR/rbt-1400x900/${key}" "minio/$TARGET_BUCKET/rbt-1400x900/${object}" >/dev/null;
    rm "$TARGET_DIR/rbt-1400x900/${key}";

    # Сохранение как картинки фиксированной высоты размера 128 (для превьюшек под основной картинкой).
    convert "$SOURCE_DIR/$key" \
      -fuzz 5% \
      -trim +repage \
      -gravity center \
      -resize x128 \
      -bordercolor white \
      -background white \
      "$TARGET_DIR/rbt-x128/$key";

    mcli put "$TARGET_DIR/rbt-x128/${key}" "minio/$TARGET_BUCKET/rbt-x128/${object}" >/dev/null;
    rm "$TARGET_DIR/rbt-x128/${key}";
  else
    echo "No such file: $key. Skipping."
  fi
}

process_delete () {
  local key=$1

  local object=$(echo $key | sed -e 's/.*\///g')

  mcli rm "minio/$TARGET_BUCKET/rbt-cropped/${object}" >/dev/null;
  mcli rm "minio/$TARGET_BUCKET/rbt-190x/${object}" >/dev/null;
  mcli rm "minio/$TARGET_BUCKET/rbt-80x80/${object}" >/dev/null;
  mcli rm "minio/$TARGET_BUCKET/rbt-100x100/${object}" >/dev/null;
  mcli rm "minio/$TARGET_BUCKET/rbt-190x190/${object}" >/dev/null;
  mcli rm "minio/$TARGET_BUCKET/rbt-legacy-227x197/${object}" >/dev/null;
  mcli rm "minio/$TARGET_BUCKET/rbt-legacy-398x256/${object}" >/dev/null;
  mcli rm "minio/$TARGET_BUCKET/rbt-60x75/${object}" >/dev/null;
  mcli rm "minio/$TARGET_BUCKET/rbt-legacy-560x420/${object}" >/dev/null;
  mcli rm "minio/$TARGET_BUCKET/rbt-1400x900/${object}" >/dev/null;
  mcli rm "minio/$TARGET_BUCKET/rbt-x128/${object}" >/dev/null;
}

if [[ "$EVENT_NAME" == "s3:ObjectCreated:Put" ]]; then
  echo "[PUT]: $OBJECT_KEY" >&2;
  process_put "$OBJECT_KEY"
elif [[ "$EVENT_NAME" == "s3:ObjectRemoved:Delete" ]]; then
  echo "[DELETE]: $OBJECT_KEY" >&2;
  process_delete "$OBJECT_KEY"
else
  echo "[SKIP]: $OBJECT_KEY. Unsupported event: '$EVENT_NAME'" >&2
fi