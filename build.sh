#!/bin/bash

echo "Docker build script"

# Build script
set -eo pipefail

echo $1
echo $2

build_tag=$1
name=groups_service
node=$2
org=$3

docker build -f ./Dockerfile --label commitHash=$(git rev-parse --short HEAD) -t ${org}/${name}:${build_tag} .

echo {\"image_name\" : \"${name}\", \"image_tag\" : \"${build_tag}\", \"node_name\" : \"$node\"} > metadata.json
