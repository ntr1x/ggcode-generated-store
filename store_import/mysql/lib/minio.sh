save_progress() {
	trace "[TRACE] Save progress"

	local min_rec_id=$1
	local max_rec_id=$2
	local cur_rec_id=$3

	[[ $cur_rec_id -le $max_rec_id ]] || fail "[ERROR] Invalid bounds (${min_rec_id:-NIL}, ${max_rec_id:-NIL}] for value ${cur_rec_id:-NIL}'"
	[[ $cur_rec_id -ge $min_rec_id ]] || fail "[ERROR] Invalid bounds (${min_rec_id:-NIL}, ${max_rec_id:-NIL}] for value ${cur_rec_id:-NIL}'"

	cat > "$WORK_DIR/progress.info" <<- EOF
	{
		"min_rec_id": $min_rec_id,
		"max_rec_id": $max_rec_id,
		"cur_rec_id": $cur_rec_id,
		"updated_at": "`date -u +"%Y-%m-%dT%TZ"`"
	}

	EOF
}

download_progress() {
	trace "[TRACE] Download progress";

	while true; do
		response=$(mcli --json get -q "minio/$MINIO_BUCKET/progress.info" "$WORK_DIR/progress.info" 2> /dev/null)
		if [[ $? == 0 ]]; then
			break;
		else
			status=$(echo $response | jq -r '.status')
			if [[ "$status" == "success" ]]; then
				echo "[INFO] No progress.info file present" >&2;
				break;
			fi
			echo "[INFO] Minio is down. Sleep for $MINIO_THROTTLE_INTERVAL second(s)" >&2;
			sleep $MINIO_THROTTLE_INTERVAL;
		fi
		test $? -gt 128 && break;
	done
}

upload_progress() {
	trace "[TRACE] Upload progress";

	[[ -s "$WORK_DIR/progress.info" ]] || fail "[ERROR] Progress file should not be empty"

	while true; do
		mcli cp "$WORK_DIR/progress.info" --attr="content-type=application/json" "minio/$MINIO_BUCKET/progress.info" > /dev/null;
		if [[ $? == 0 ]]; then
			break
		else
			echo "[INFO] Minio is down. Sleep for $MINIO_THROTTLE_INTERVAL second(s)" >&2;
			sleep $MINIO_THROTTLE_INTERVAL;
		fi
		test $? -gt 128 && break;
	done
}

upload_updates() {
	trace "[TRACE] Upload updates";

	[[ -s "$WORK_DIR/updates.csv" ]] || fail "[ERROR] Data file should not be empty"

	local datetime=`date -u +"%Y-%m-%dT%TZ"`

	while true; do
		mcli cp "$WORK_DIR/updates.csv" --attr="content-type=text/csv" "minio/$MINIO_BUCKET/updates-$datetime.csv" > /dev/null;
		if [[ $? == 0 ]]; then
			break
		else
			echo "[INFO] Minio is down. Sleep for $MINIO_THROTTLE_INTERVAL second(s)" >&2;
			sleep $MINIO_THROTTLE_INTERVAL;
		fi
		test $? -gt 128 && break;
	done
}

minio_setup() {
	trace "[TRACE] Setup minio connection";

	while true; do
		mcli alias set minio $MINIO_TARGET_SERVER $MINIO_ACCESS_KEY $MINIO_SECRET_KEY > /dev/null;
		if [[ $? == 0 ]]; then
			break
		else
			echo "[INFO] Minio is down. Sleep for $MINIO_THROTTLE_INTERVAL second(s)" >&2;
			sleep $MINIO_THROTTLE_INTERVAL;
		fi
		test $? -gt 128 && break;
	done
}
