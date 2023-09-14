#!/bin/bash

echo "stopping all"
docker-compose --file menu-service/docker-compose-menu.yml \
               --file mission-service/docker-compose-mission.yml down

echo "all services stopped "
