#!/bin/bash

text="\n
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

  printf "\n\n"

printf "\033[1;34m========================================\033[0m

Scenario 1: Mission successful with passage through Max Q, payload delivery, first stage return to Earth, robot lands on the moon and soil analysis.
\033[1;33mUS covered: 1 / 2 / 3 / 4 / 5 / 6 /  7 / 9 / 10 / 11 / 12 / 13 / 14 / 15 / 19 / 20 / 21\033[0m

Scenario 2: Mission failed due to a severe anomaly and rocket department order the destruction of the rocket to prevent potential damage on the ground.
\033[1;33mUS covered: 1 / 2 / 3 / 4 / 5 / 6 /  7 / 8 / 13 / 14 / 15 / 16\033[0m

Scenario 3: Mission failed due to the rocket auto-destruction after the detection of a critical anomaly.
\033[1;33mUS covered: 1 / 2 / 3 / 4 / 5 / 6 /  7 / 13 / 14 / 15 / 16 / 18\033[0m

\033[0;35m========================================\033[0m\n\n"

sleep 7

function wait_telemetry_service() {
    printf "\033[0;33m## Waiting for all services to be ready \033[0;31m(can take several minutes)\n\n\033[0m"

    ret_code=1
    while [ $ret_code -ne 0 ]; do
        response=$(curl --write-out '%{http_code}' --silent --show-error --output /dev/null --location --request POST http://localhost:3003/api/telemetry/service-status)
        ret_code=$?

        # Check the return code
        if [ $ret_code -eq 0 ]; then
          # Print the response body
          printf "\033[1;32m\nAll services ready!\033[0m\n"
        else
          # Handle the error here
          printf "\033[1;36mAll services not ready yet\033[0m\n"
        fi
        sleep 2
    done
}

function scenario1() {
  printf "\n\033[1;34m========================================\033[0m

Start of scenario 1: Mission successful with passage through Max Q, payload delivery, first stage return to Earth, robot lands on the moon and soil analysis.
\033[1;33mUS covered: 1 / 2 / 3 / 4 / 5 / 6 /  7 / 9 / 10 / 11 / 12 / 13 / 14 / 15 / 19 / 20 / 21\033[0m

\033[0;35m========================================\033[0m\n\n"
  sleep 5

  curl --silent --show-error --output /dev/null --location --request POST http://localhost:3001/api/mission/start -H "Content-Type: application/text" --data "Appolo 11"
}

function scenario2() {
  printf "\n\033[0m========================================\033[0m\n"
  printf "\n\033[0;33m## Ordering the launch of a second rocket\n\033[0m\n\n"

  printf "\n\033[0;34m========================================\033[0m

Start of scenario 2: Mission failed due to a severe anomaly and rocket department order the destruction of the rocket to prevent potential damage on the ground.
\033[1;33mUS covered: 1 / 2 / 3 / 4 / 5 / 6 /  7 / 8 / 13 / 14 / 15 / 16\033[0m

\033[0;35m========================================\033[0m\n\n"
  sleep 5

  curl --silent --show-error --output /dev/null --location --request POST http://localhost:3001/api/mission/start -H "Content-Type: application/text" --data "Falcon 10"
}

function scenario3() {
  printf "\n\033[0m========================================\033[0m\n"
  printf "\n\033[0;33m## Ordering the launch of a third rocket\n\033[0m\n\n"

  printf "\n\033[0;34m========================================\033[0m

Start of scenario 3: Mission failed due to the rocket auto-destruction after the detection of a critical anomaly.
\033[1;33mUS covered: 1 / 2 / 3 / 4 / 5 / 6 /  7 / 13 / 14 / 15 / 16 / 18\033[0m

\033[0;35m========================================\033[0m\n\n"
  sleep 5

  curl --silent --show-error --output /dev/null --location --request POST http://localhost:3001/api/mission/start -H "Content-Type: application/text" --data "Starship"
}

function sabotageRocket() {
  printf "\n\033[1;33m## Rocket sabotage\n\033[0m\n"
  curl --silent --show-error --output /dev/null --location --request POST http://localhost:3005/api/rocket-hardware/sabotaging
}

function criticalAnomaly() {
  printf "\n\033[1;33m## Critical anomaly occurs\n\033[0m\n"
  curl --silent --show-error --output /dev/null --location --request POST http://localhost:3005/api/rocket-hardware/dangerous-sabotaging
}

wait_telemetry_service
(sleep 4 && scenario1) &
(sleep 122 && scenario2) &
(sleep 137 && sabotageRocket) &
(sleep 142 && scenario3) &
(sleep 157 && criticalAnomaly) &
docker compose logs --follow --since 0m
read -p "."