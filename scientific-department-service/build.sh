#!/bin/bash

APP="${PWD##*/}"

# Compiling and buildpacking docker image
echo "** Compiling $APP"
docker image rm marsy/$APP
docker build -t "marsy/$APP" .
echo "** Done"