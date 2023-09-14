#!/bin/bash

function compile_dir()  # $1 is the dir to get it
{
    cd $1
    ./build.sh
    cd ..
}

echo "** Building all"

docker image rm marsy/weather-service
compile_dir "weather-service"

docker image rm marsy/mission-service
compile_dir "mission-service"

docker image rm marsy/rocket-service
compile_dir "rocket-service"

# remove # to add pause in script execution
# read -p "Press any key to continue... "

echo "** Done all"
