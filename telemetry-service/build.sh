#!/bin/bash

APP="${PWD##*/}"

# Compiling and buildpacking docker image
echo "** Compiling $APP"
#docker image rm marsy/telemetry-service
mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName="marsy/$APP"
echo "** Done"
