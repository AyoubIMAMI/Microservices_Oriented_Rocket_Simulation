#!/bin/bash

echo "** Stopping all"
docker-compose --file menu-service/docker-compose-menu.yml \
               --file mission-service/docker-compose-mission.yml \
               --file rocket-service/docker-compose-rocket.yml down

echo "** All services stopped"
