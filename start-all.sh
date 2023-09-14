#!/bin/bash

echo "starting all"
docker-compose --file weather-service/docker-compose-weather.yml \
               --file mission-service/docker-compose-mission.yml up -d

# read -p "Press any key to continue... "

echo "all services started"
