#!/bin/bash

function wait_mission_service() {
    printf "## Waiting for mission service to be ready \n"

    ret_code=0
    while [ $ret_code -ne 200 ]; do
        response=$(curl --write-out '%{http_code}' --silent --show-error --output /dev/null --location --request POST http://localhost:3001/api/mission/status)
        ret_code=$?

        # Check the return code
        if [ $ret_code -eq 200 ]; then
          # Print the response body
          echo "$response"
        else
          # Handle the error here
          echo "Error: Mission service not ready yet (HTTP status code: $ret_code)"
        fi
    done
}

function run_test() {

  printf "## Asking Mission Commander to start the Rocket \n"

  curl --write-out '%{http_code}' --silent --show-error --output /dev/null --location --request POST http://localhost:3001/api/mission/start | ./follow-logs.sh
  ret_code=$?
   # Check the return code
  if [ $ret_code -eq 0 ]; then
    # Print the response body
    echo "$response"
  else
    # Handle the error here
    echo "Error: The request did not succeed (HTTP status code: $ret_code)"
  fi
}


# echo "Running concurrency test WITH NO PRIOR BUILD"
# echo "through the gateway"
wait_mission_service
run_test


#read -p "** Press any key to continue... "
