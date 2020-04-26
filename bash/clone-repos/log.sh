#!/bin/bash

debug_enabled=1

debug() {
	local message=$1
	if [[ "$debug_enabled" = "1" ]]; then
		log "DEBUG: $message"
	fi
}

log() {
	local message=$1
	echo "$message" > /dev/stdout
}

log_error() {
	local message=$1
	echo "ERROR: $message" > /dev/stderr
}
