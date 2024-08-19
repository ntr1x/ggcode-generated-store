#!/bin/bash

source "$PROJECT_ROOT/lib/util.sh"

query_bounds() {
	trace "[TRACE] Query bounds";

	sqlplus -s <<- EOF > "$WORK_DIR/bounds.csv"
		$ORACLE_CONNECTION_STRING

		set markup csv on;
		set feedback off;

		SELECT
			COALESCE(min(aii.RECID), 0) AS MINRECID,
			COALESCE(max(aii.RECID), 0) AS MAXRECID
		FROM AXAPTA.INTITEM aii;

	EOF
}

query_updates() {

	local cur_rec_id=$1
	local max_rec_id=$2

	trace "[TRACE] Query updates: ($cur_rec_id, $max_rec_id], max: $FETCHER_BATCH_SIZE";

	sqlplus -s <<- EOF > "$WORK_DIR/updates.csv"
		$ORACLE_CONNECTION_STRING

		set markup csv on;
		set feedback off;

		SELECT * FROM (
			SELECT
				aii.RECID ,
				aii.TYPEEVENT,
				TO_CHAR(aii.CREATEDDATE, 'YYYY-MM-DD') AS CREATEDDATE ,
				TO_CHAR(aii.CREATEDTIME) AS CREATEDTIME ,
				aii.ITEMID ,
				aii.GROUPID ,
				aii.ITEMNAME ,
				aii.BRANDID ,
				aii.TYPEGABARIT ,
				TO_CHAR(aii.UNITVOLUME, 'FM999999999999990.99999') AS UNITVOLUME ,
				aii.ITEMTYPE ,
				aii.ARTICLE ,
				aii.BLOCKED ,
				aii.DATAAREAID ,
				TO_CHAR(aii.MAINBRANDID) ,
				aii.COMPANYASSORTMENT ,
				aii.IDUNITREF ,
				aii.IDREF ,
				TO_CHAR(aii.WEIGHT, 'FM999999999999990.99999') AS WEIGHT ,
				TO_CHAR(aii.WIDTH, 'FM999999999999990.99999') AS WIDTH ,
				TO_CHAR(aii.HEIGHT, 'FM999999999999990.99999') AS HEIGHT ,
				TO_CHAR(aii.DEPTH, 'FM999999999999990.99999') AS DEPTH
			FROM AXAPTA.INTITEM aii
			WHERE aii.RECID > $cur_rec_id
				AND aii.RECID <= $max_rec_id
			ORDER BY aii.RECID
		) A
		WHERE rownum <= ${FETCHER_BATCH_SIZE:-1000};

	EOF
}
