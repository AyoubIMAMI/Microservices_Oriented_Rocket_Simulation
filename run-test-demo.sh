#!/bin/bash

function run_test() {

  printf "## Asking Mission Commander to start the Rocket \n"

  curl --write-out '%{http_code}' --silent --show-error --output /dev/null --location --request POST http://localhost:3001/api/mission/start
  ret_code=$?
   # Check the return code
  if [ $ret_code -eq 0 ]; then
    # Print the response body
    echo "$response"
  else
    # Handle the error here
    echo "Error: The request did not succeed (HTTP status code: $ret_code)"
  fi
  read -p "** Press any key to continue... "
  echo "Stopping the docker-compose gracefully"
}


# echo "Running concurrency test WITH NO PRIOR BUILD"
# echo "through the gateway"
run_test


read -p "** Press any key to continue... "
