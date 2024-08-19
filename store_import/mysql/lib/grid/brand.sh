#!/bin/bash

source "$PROJECT_ROOT/lib/util.sh"

query_bounds() {
	trace "[TRACE] Query bounds";

	MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $MYSQL_HOSTNAME -P $MYSQL_PORT -u$MYSQL_USERNAME -D $MYSQL_DATABASE --default_character_set=utf8 <<- EOF > "$WORK_DIR/bounds.tsv"
		SELECT
			COALESCE(min(wi.brand_id), 0) AS MINRECID,
			COALESCE(max(wi.brand_id), 0) AS MAXRECID
		FROM web.brand wi;

	EOF

	cat "$WORK_DIR/bounds.tsv" | sed "s/'/\'/;s/\t/,/g;s/\n//g" > "$WORK_DIR/bounds.csv"
}

query_updates() {

	local cur_rec_id=$1
	local max_rec_id=$2

	trace "[TRACE] Query updates: ($cur_rec_id, $max_rec_id], max: $FETCHER_BATCH_SIZE";

	MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $MYSQL_HOSTNAME -P $MYSQL_PORT -u$MYSQL_USERNAME -D $MYSQL_DATABASE --default_character_set=utf8 <<- EOF > "$WORK_DIR/updates.tsv"
		SELECT
			wi.brand_id AS RECID,
			wi.brand_id ,
			wi.onec_id ,
			concat('"', replace(wi.name,'"', '""'), '"') AS name ,
			concat('"', replace(wi.name_ru,'"', '""'), '"') AS name_ru ,
			wi.logo ,
			concat('"', replace(wi.description,'"', '""'), '"') AS description ,
			wi.url ,
			wi.status ,
			wi.ordering ,
			wi.logo_act ,
			wi.catname ,
			wi.b_BrandId ,
			wi.onmain ,
			wi.own ,
			wi.kt ,
			wi.main_brand_id ,
			wi.in_footer ,
			wi.is_popular
		FROM web.brand wi
		WHERE wi.brand_id > $cur_rec_id
			AND wi.brand_id <= $max_rec_id
		ORDER BY wi.brand_id
		LIMIT ${FETCHER_BATCH_SIZE:-1000};

	EOF

	cat "$WORK_DIR/updates.tsv" | sed "s/'/\'/;s/\t/,/g;s/\n//g" > "$WORK_DIR/updates.csv"
}
