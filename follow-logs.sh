#!/bin/bash

docker-compose --file weather-service/docker-compose-weather.yml \
               --file mission-service/docker-compose-mission.yml logs --follow
