#!/bin/bash

source "$PROJECT_ROOT/lib/util.sh"

query_bounds() {
	trace "[TRACE] Query bounds";

	MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $MYSQL_HOSTNAME -P $MYSQL_PORT -u$MYSQL_USERNAME -D $MYSQL_DATABASE --default_character_set=utf8 <<- EOF > "$WORK_DIR/bounds.tsv"
		SELECT
			COALESCE(min(wi.attribute_id), 0) AS MINRECID,
			COALESCE(max(wi.attribute_id), 0) AS MAXRECID
		FROM web.attribute wi;

	EOF

	cat "$WORK_DIR/bounds.tsv" | sed "s/'/\'/;s/\t/,/g;s/\n//g" > "$WORK_DIR/bounds.csv"
}

query_updates() {

	local cur_rec_id=$1
	local max_rec_id=$2

	trace "[TRACE] Query updates: ($cur_rec_id, $max_rec_id], max: $FETCHER_BATCH_SIZE";

	MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $MYSQL_HOSTNAME -P $MYSQL_PORT -u$MYSQL_USERNAME -D $MYSQL_DATABASE --default_character_set=utf8 <<- EOF > "$WORK_DIR/updates.tsv"
		SELECT
			wi.attribute_id AS RECID,
			wi.attribute_id ,
			concat('"', replace(wi.name,'"', '""'), '"') AS name ,
			concat('"', replace(wi.name_url,'"', '""'), '"') AS name_url ,
			wi.type ,
			wi.unit ,
			wi.status ,
			concat('"', replace(wi.onec_id,'"', '""'), '"') AS onec_id ,
			wi.display_type ,
			wi.data_type ,
			wi.enter_values_type
		FROM web.attribute wi
		WHERE wi.attribute_id > $cur_rec_id
			AND wi.attribute_id <= $max_rec_id
		ORDER BY wi.attribute_id
		LIMIT ${FETCHER_BATCH_SIZE:-1000};

	EOF

	cat "$WORK_DIR/updates.tsv" | sed "s/'/\'/;s/\t/,/g;s/\n//g" > "$WORK_DIR/updates.csv"
}
