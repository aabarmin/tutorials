#!/bin/bash

value_read() {
  local prompt=$1
  local default_value=$2

  echo "$prompt" > /dev/stderr
  read value

  if [[ "$value" =~ ^\s*$ ]]; then
    echo "$default_value"
  else
    echo "$value"
  fi
}