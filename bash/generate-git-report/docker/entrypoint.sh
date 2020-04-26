#!/bin/bash

folder_repos="/opt/data/repos"
folder_reports="/opt/data/reports"
days="$REPORT_DAYS"

cd /opt/code/generate-git-report
./run.sh "$folder_repos" "$days" "$folder_reports"