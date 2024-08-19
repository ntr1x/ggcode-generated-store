#!/bin/bash

source "$PROJECT_ROOT/lib/util.sh"

query_bounds() {
	trace "[TRACE] Query bounds";

	MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $MYSQL_HOSTNAME -P $MYSQL_PORT -u$MYSQL_USERNAME -D $MYSQL_DATABASE --default_character_set=utf8 <<- EOF > "$WORK_DIR/bounds.tsv"
		SELECT
			COALESCE(min(wi.item_attr_value_id), 0) AS MINRECID,
			COALESCE(max(wi.item_attr_value_id), 0) AS MAXRECID
		FROM web.item_attr_string wi;

	EOF

	cat "$WORK_DIR/bounds.tsv" | sed "s/'/\'/;s/\t/,/g;s/\n//g" > "$WORK_DIR/bounds.csv"
}

query_updates() {

	local cur_rec_id=$1
	local max_rec_id=$2

	trace "[TRACE] Query updates: ($cur_rec_id, $max_rec_id], max: $FETCHER_BATCH_SIZE";

	MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $MYSQL_HOSTNAME -P $MYSQL_PORT -u$MYSQL_USERNAME -D $MYSQL_DATABASE --default_character_set=utf8 <<- EOF > "$WORK_DIR/updates.tsv"
		SELECT
			wi.item_attr_value_id AS RECID,
			wi.item_attr_value_id ,
			wi.item_id ,
			wi.attribute_id ,
			concat('"', replace(wi.value,'"', '""'), '"') AS value ,
			concat('"', replace(wi.raw_value,'"', '""'), '"') AS raw_value ,
			concat('"', replace(wi.translit_value,'"', '""'), '"') AS translit_value
		FROM web.item_attr_string wi
		WHERE wi.item_attr_value_id > $cur_rec_id
			AND wi.item_attr_value_id <= $max_rec_id
		ORDER BY wi.item_attr_value_id
		LIMIT ${FETCHER_BATCH_SIZE:-1000};

	EOF

	cat "$WORK_DIR/updates.tsv" | sed "s/'/\'/;s/\t/,/g;s/\n//g" > "$WORK_DIR/updates.csv"
}
