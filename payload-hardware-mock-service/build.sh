#!/bin/bash

APP="${PWD##*/}"

# Compiling and buildpacking docker image
echo "** Compiling $APP"
docker build -t "marsy/$APP" .
echo "** Done"
