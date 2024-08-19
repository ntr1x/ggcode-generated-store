#!/bin/bash

source "$PROJECT_ROOT/lib/util.sh"

query_bounds() {
	trace "[TRACE] Query bounds";

	MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $MYSQL_HOSTNAME -P $MYSQL_PORT -u$MYSQL_USERNAME -D $MYSQL_DATABASE --default_character_set=utf8 <<- EOF > "$WORK_DIR/bounds.tsv"
		SELECT
			COALESCE(min(wi.item_id), 0) AS MINRECID,
			COALESCE(max(wi.item_id), 0) AS MAXRECID
		FROM web.item wi;

	EOF

	cat "$WORK_DIR/bounds.tsv" | sed "s/'/\'/;s/\t/,/g;s/\n//g" > "$WORK_DIR/bounds.csv"
}

query_updates() {

	local cur_rec_id=$1
	local max_rec_id=$2

	trace "[TRACE] Query updates: ($cur_rec_id, $max_rec_id], max: $FETCHER_BATCH_SIZE";

	MYSQL_PWD="$MYSQL_PASSWORD" mysql -h $MYSQL_HOSTNAME -P $MYSQL_PORT -u$MYSQL_USERNAME -D $MYSQL_DATABASE --default_character_set=utf8 <<- EOF > "$WORK_DIR/updates.tsv"
		SELECT
			wi.item_id AS RECID,
			wi.item_id ,
			concat('"', replace(wi.typename,'"', '""'), '"') AS typename ,
			concat('"', replace(wi.name,'"', '""'), '"') AS name ,
			wi.brand_id ,
			wi.real_brand_id ,
			wi.article ,
			wi.price ,
			wi.currency_id ,
			wi.price_virtual ,
			wi.is_variable_price ,
			concat('"', replace(wi.description,'"', '""'), '"') AS description ,
			wi.image ,
			wi.status ,
			wi.realstatus ,
			wi.company_assortment ,
			wi.is_available ,
			wi.b_ItemId ,
			wi.views ,
			wi.guarantee ,
			wi.dimension ,
			wi.capacity ,
			wi.bottom_limit ,
			wi.top_limit ,
			concat('"', replace(wi.onec_id,'"', '""'), '"') AS onec_id ,
			concat('"', replace(wi.onec_parent_id,'"', '""'), '"') AS onec_parent_id ,
			wi.catalogue_basic_id ,
			wi.cost_amount ,
			wi.itemurl ,
			wi.is_rating ,
			wi.file_flash ,
			wi.file_manual ,
			wi.item_status_id ,
			wi.file_cert ,
			wi.item_sale ,
			wi.item_type ,
			wi.item_dot_started_onec_id ,
			wi.noindex_description ,
			wi.weight ,
			wi.width ,
			wi.height ,
			wi.depth
		FROM web.item wi
		WHERE wi.item_id > $cur_rec_id
			AND wi.item_id <= $max_rec_id
		ORDER BY wi.item_id
		LIMIT ${FETCHER_BATCH_SIZE:-1000};

	EOF

	cat "$WORK_DIR/updates.tsv" | sed "s/'/\'/;s/\t/,/g;s/\n//g" > "$WORK_DIR/updates.csv"
}
