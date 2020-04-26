#!/bin/bash

generate_single_report() {
  local git_folder=$1
  local report_folder=$2
  local since_days=$3

  local pattern='.*/(.+)'
  [[ "$git_folder" =~ $pattern ]]
  local folder_name="${BASH_REMATCH[1]}"

  log "Generate a report for $folder_name"

  local current_folder="$(pwd)"
  cd "$git_folder"

  ## Generate a report for the single repository
  git log --since="$since_days".days \
    --pretty=format:"%Cblue%h%Creset - %Cred%an:%Creset %Cgreen%s%Creset" \
    -p \
    --no-merges \
    > "$report_folder/$folder_name.txt"

  cd "$current_folder"
}