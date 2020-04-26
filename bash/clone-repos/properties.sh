#!/bin/bash

property_read() {
  local filename=$1
  local property=$2

  if [[ ! -f "$filename" ]]; then
    log_error "File $filename doesn't exist"
    exit 1
  fi

  while IFS= read -r line
  do
    local name="$(get_property_name $line)"
    if [[ "$name" == "$property" ]]; then
      local value="$(get_property_value $line)"
      echo "$value"
      exit 0
    fi
  done < "$filename"

  exit 1
}

get_property_name() {
  local line=$1
  local pattern='(.+)=(.+)'

  [[ "$line" =~ $pattern ]]
  echo "${BASH_REMATCH[1]}"
}

get_property_value() {
local line=$1
  local pattern='(.+)=(.+)'

  [[ "$line" =~ $pattern ]]
  echo "${BASH_REMATCH[2]}"
}