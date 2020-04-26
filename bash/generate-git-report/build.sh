#!/bin/bash

# Create a temporary dir
if [[ -d "./tmp" ]]; then
  rm -rf ./tmp
fi
mkdir ./tmp

# Temporary copy the ../common here
cp -r ../common/ ./tmp/common

# Run the Docker build
docker build --tag generate-git-report .

# Remote temporary files
if [[ -d "./tmp" ]]; then
  rm -rf ./tmp
fi
