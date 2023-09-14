#!/bin/bash

echo "stopping all"
docker-compose --file menu-service/docker-compose-menu.yml down

echo "all services stopped "
