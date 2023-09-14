#!/bin/bash

function run_test() {
  printf "Starting the full docker-compose in background\n"
  ./start-all.sh
  sleep 2

  printf "## Asking Mission Commander to start the Rocket \n" 

  curl --write-out '%{http_code}' --silent --show-error --output /dev/null --location --request POST http://localhost:8080/api/mission/poll
  ret_code=$?
  echo "Stopping the docker-compose gracefully"
  ./stop-all.sh
  return $ret_code
}


echo "Running concurrency test WITH NO PRIOR BUILD"
echo "through the gateway"
run_test
ret_code=$?
exit $ret_code
