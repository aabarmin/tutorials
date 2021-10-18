#!/bin/bash

rm -rf ./client/src/main/java/dev/abarmin/grpc/protos

protoc --java_out=client/src/main/java types.proto