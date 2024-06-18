#!/bin/bash
docker build \
  --tag example/assembly_web:1.0-SNAPSHOT \
  --build-arg="MODULE_NAME=assembly_web" \
  --build-arg="MODULE_VERSION=1.0-SNAPSHOT" \
  --file Dockerfile \
  .
