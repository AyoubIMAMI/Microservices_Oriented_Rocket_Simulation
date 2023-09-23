#!/bin/bash

APP="${PWD##*/}"

# Compiling and buildpacking docker image
echo "** Compiling $APP"
./stop.sh
docker image rm marsy/mission-service
mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName="marsy/$APP"
echo "** Done"
