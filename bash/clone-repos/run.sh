#!/bin/bash

source "../common/log.sh"
source "./input.sh"
source "./properties.sh"
source "./clone.sh"

prop_file=$1

if [[ -z "$prop_file" ]]; then
  prompt="Input path to the properties file [props.properties]: "
  default="props.properties"
  prop_file="$(value_read "$prompt" "$default")"
fi

log "Cloning using the $prop_file"

clone_all "$prop_file"
