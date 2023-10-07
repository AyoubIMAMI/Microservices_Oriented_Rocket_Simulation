#!/bin/bash

text="
     \033[1;34m __  ___              __  __
     /  |/  /___ __________\ \/ /
    / /|_/ / __ \`/ ___/ ___/\  /
   \033[0;35m/ /  / / /_/ / /  (__  ) / /
  /_/  /_/\__,_/_/  /____/ /_/
    \033[0mTO THE SPACE AND BEYOND
  "

  IFS=$'\n' read -r -d '' -a lines <<< "$text"
  for line in "${lines[@]}"; do
      printf "$line\n"
      sleep 0.01
  done

  printf "\n\n\n"

printf "\033[1;34m========================================\033[0m

Scenario 1: Mission successful with passage through Max Q, payload delivery and stage return to Earth.

Scenario 2: Mission failed due to a severe anomaly and the destruction of the rocket to prevent potential damage on the ground.

\033[0;35m========================================\033[0m\n\n"

sleep 5

function wait_telemetry_service() {
    printf "\033[0;33m## Waiting for all services to be ready \n\033[0m"

    ret_code=1
    while [ $ret_code -ne 0 ]; do
        response=$(curl --write-out '%{http_code}' --silent --show-error --output /dev/null --location --request POST http://localhost:3003/api/telemetry/service-status)
        ret_code=$?

        # Check the return code
        if [ $ret_code -eq 0 ]; then
          # Print the response body
          printf "\033[1;32mAll services ready!\033[0m\n"
        else
          # Handle the error here
          printf "\033[1;31mError: All services not ready yet (HTTP status code: $ret_code)\033[0m\n"
        fi
        sleep 1
    done
}

function scenario1() {
  printf "\n\033[1;34m========================================\033[0m

Start of scenario 1: Mission successful with passage through Max Q, payload delivery and stage return to Earth.

\033[0;35m========================================\033[0m\n\n"
  sleep 5

  curl --silent --show-error --output /dev/null --location --request POST http://localhost:3001/api/mission/start
}

function scenario2() {
  printf "\n\033[0m========================================\033[0m\n"
  printf "\n\033[0;33m## Reset databases and mock hardware for the next scenario\n\033[0m\n"
  curl --silent --show-error --output /dev/null --location --request POST http://localhost:3003/api/telemetry/reset-db
  curl --silent --show-error --output /dev/null --location --request POST http://localhost:3004/api/payload/reset-db
  curl --silent --show-error --output /dev/null --location --request POST http://localhost:3011/api/logs/reset-db

  printf "\n\033[0;34m========================================\033[0m

Start of scenario 2: Mission failed due to a severe anomaly and the destruction of the rocket to prevent potential damage on the ground.

\033[0;35m========================================\033[0m\n\n"
  sleep 5

  curl --silent --show-error --output /dev/null --location --request POST http://localhost:3001/api/mission/start
}

function sabotageRocket() {
  printf "\n\033[1;33m## Sabotage the rocket\n\033[0m\n"
  curl --silent --show-error --output /dev/null --location --request POST http://localhost:3005/api/rocket-hardware/sabotaging
}

wait_telemetry_service
(sleep 2 && scenario1) &
(sleep 70 && scenario2) &
(sleep 80 && sabotageRocket) &
docker compose logs --follow --since 0m
read -p "."