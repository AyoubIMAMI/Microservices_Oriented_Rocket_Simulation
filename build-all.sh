#!/bin/bash

function compile_dir()  # $1 is the dir to get it
{
    cd $1
    ./build.sh
    cd ..
}

echo "** Building all"

compile_dir "weather-service"

compile_dir "mission-service"

compile_dir "rocket-department-service"

compile_dir "telemetry-service"

compile_dir "rocket-hardware-mock-service"

compile_dir "payload-service"

# remove # to add pause in script execution
read -p "** Press any key to continue... "

echo "** Done all"
