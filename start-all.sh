#!/bin/bash

echo "** Starting all"
docker-compose --file weather-service/docker-compose-weather.yml \
               --file mission-service/docker-compose-mission.yml \
               --file telemetry-service/docker-compose-telemetry.yml \
               --file rocket-hardware-mock-service/docker-compose-rocket-hardware.yml \
               --file payload-service/docker-compose-payload.yml \
               --file rocket-service/docker-compose-rocket.yml up

# remove # to add pause in script execution
read -p "Press any key to continue... "

echo "** All services started"
