#!/bin/bash

source "./log.sh"
source "./input.sh"
source "./properties.sh"
source "./clone.sh"

prompt="Input path to the properties file [props.properties]: "
default="props.properties"
prop_file="$(value_read "$prompt" "$default")"

log "Cloning using the $prop_file"

clone_all "$prop_file"
