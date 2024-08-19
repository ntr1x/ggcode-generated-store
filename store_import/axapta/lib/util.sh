#!/bin/bash

fail () {
	echo "$1" >&2
	exit 1
}

trace () {
	[[ "$FETCHER_TRACE" == true ]] && echo "$1" >&2
}
