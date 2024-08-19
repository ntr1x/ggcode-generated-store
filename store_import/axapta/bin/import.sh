#!/bin/bash

PROJECT_ROOT=$(pwd)

source $PROJECT_ROOT/lib/util.sh
source $PROJECT_ROOT/lib/minio.sh

[[ -z "$ORACLE_CONNECTION_STRING" ]] && fail "[ERROR] ORACLE_CONNECTION_STRING environment variable should be set"
[[ -z "$FETCHER_GRID" ]] && fail "[ERROR] FETCHER_GRID environment variable should be set"
[[ -z "$MINIO_BUCKET" ]] && fail "[ERROR] MINIO_BUCKET environment variable should be set"

APP_ACTION_SCRIPT="$PROJECT_ROOT/lib/grid/${FETCHER_GRID}.sh"

[[ ! -e "$APP_ACTION_SCRIPT" ]] && fail "[ERROR] No action script for grid '"$FETCHER_GRID"'"

source $APP_ACTION_SCRIPT

WORK_DIR="/app/work/${FETCHER_GRID}"

mkdir -p "$WORK_DIR"

cleanup () {
	exit 130
}

trap cleanup SIGINT

minio_setup;

if [[ $? != 0 ]]; then
	echo "[ERROR] Invalid config" >&2;
	exit 1;
fi

download_progress;

if [[ ! -e "$WORK_DIR/progress.info" ]]; then
	echo "[INFO] Starting from very beginning" >&2;

	query_bounds;

	min_rec_id=$(csvq --format jsonl 'SELECT * FROM `'"$WORK_DIR/bounds.csv"'`' | jq '.MINRECID|tonumber');
	max_rec_id=$(csvq --format jsonl 'SELECT * FROM `'"$WORK_DIR/bounds.csv"'`' | jq '.MAXRECID|tonumber');

	save_progress $min_rec_id $max_rec_id $min_rec_id;

	upload_progress;
fi

while true; do

	trace "[TRACE] Step in a loop";

	rm -f "$WORK_DIR/progress.info";
	rm -f "$WORK_DIR/bounds.csv";
	rm -f "$WORK_DIR/updates.csv";

	download_progress;

	min_rec_id=$(cat "$WORK_DIR/progress.info" | jq '.min_rec_id|tonumber');
	max_rec_id=$(cat "$WORK_DIR/progress.info" | jq '.max_rec_id|tonumber');
	cur_rec_id=$(cat "$WORK_DIR/progress.info" | jq '.cur_rec_id|tonumber');

	echo "[INFO] Processing (${min_rec_id:-NIL}, ${max_rec_id:-NIL}] for value ${cur_rec_id:-NIL}" >&2;

	query_bounds;

	min_rec_id=$(csvq --format jsonl 'SELECT * FROM `'"$WORK_DIR/bounds.csv"'`' | jq '.MINRECID|tonumber');
	max_rec_id=$(csvq --format jsonl 'SELECT * FROM `'"$WORK_DIR/bounds.csv"'`' | jq '.MAXRECID|tonumber');

	query_updates $cur_rec_id $max_rec_id;

	if [[ ! -s $WORK_DIR/updates.csv ]]; then
		trace "[TRACE] No results in range";

		save_progress $min_rec_id $max_rec_id $max_rec_id;

		echo "[INFO] Done" >&2;
		break;
	fi

	cur_rec_id=$(csvq --format jsonl 'SELECT COALESCE(max(RECID), '"$max_rec_id"') AS RECID FROM `'"$WORK_DIR/updates.csv"'`' | jq '.RECID|tonumber');

	save_progress $min_rec_id $max_rec_id $cur_rec_id;

	upload_updates;

	upload_progress;

	if [[ $cur_rec_id -ge $max_rec_id ]]; then
		echo "[INFO] Done" >&2;
		break;
	fi

	test $? -gt 128 && break;
done
