#!/bin/bash

source "../common/log.sh"
source "./report.sh"

folder=$1
days=$2
reports_base=$3

## Check the script parameters
if [ -z "$folder" ] ||  [ -z "$days" ] || [ -z "$reports_base" ]; then
  log "No parameters were given, example:"
  log "./run.sh <folder with repos> <number of days> <reports base folder>"
  log "./run.sh ./repos 2 reports"
  exit 0
fi

report_folder="$reports_base/$(date +%F)"

## Create a report folder if not exist
if [[ -d "$report_folder" ]]; then
  rm -rf "$report_folder"
fi

if [[ ! -d "$report_folder" ]]; then
  mkdir -p "$report_folder"
fi

## Iterate over files in the given directory
files="$(ls "$folder")"
for file in $files; do
  if [[ -d "$folder/$file" ]]; then
    if [[ -d "$folder/$file/.git" ]]; then
      ## And generate a single repo report
      generate_single_report "$folder/$file" "$report_folder" "$days"
    fi
  fi
done

log "Generating a daily report"

## Generate a general report
### Remove an old report file
daily_report="$reports_base/$(date +%F).txt"
if [[ -f "$daily_report" ]]; then
  rm "$daily_report"
fi

### Create a new daily report file
touch "$daily_report"

### Concat report parts if they're not empty
files="$(ls "$report_folder")"
for file in $files; do
  if [[ -f "$report_folder/$file" ]]; then
    size="$(stat -f%z "$report_folder/$file")"
    if [[ "$size" != "0" ]]; then
      cat "$report_folder/$file" >> "$daily_report"
    fi
  fi
done

log "Done"
