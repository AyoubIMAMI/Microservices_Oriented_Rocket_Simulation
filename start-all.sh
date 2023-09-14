#!/bin/bash

echo "starting all"
docker-compose --file weather-service/docker-compose-weather.yml up -d

echo "all services started"
