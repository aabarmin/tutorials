#!/bin/bash

props_file="/opt/props.properties"

# Check if property file exists, it shouldn't
if [[ ! -f "$props_file" ]]; then
  touch "$props_file"
fi

# Add parameters based on the environment variables
echo "repos.folder=/opt/data/repos" >> "$props_file"
echo "repos.list=$REPOS_LIST_FILE" >> "$props_file"

if [[ "$CREDENTIALS_LOGIN" == "none" ]]; then
  echo "credentials.login=" >> "$props_file"
else
  echo "credentials.login=$CREDENTIALS_LOGIN" >> "$props_file"
fi

if [[ "$CREDENTIALS_PASSWORD" == "none" ]]; then
  echo "credentials.password=" >> "$props_file"
else
  echo "credentials.password=$CREDENTIALS_PASSWORD" >> "$props_file"
fi

echo "repo.checkout.branch=$CHECKOUT_BRANCH" >> "$props_file"
echo "" >> "$props_file"

# Run the script
cd /opt/code/clone-repos
./run.sh /opt/props.properties
