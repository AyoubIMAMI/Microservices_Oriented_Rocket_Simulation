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

compile_dir "payload-hardware-mock-service"

compile_dir "payload-service"

compile_dir "executive-service"

compile_dir "stage-hardware-mock-service"

compile_dir "logs-service"

compile_dir "webcaster-service"

compile_dir "robot-department-service"

compile_dir "robot-hardware-mock-service"

compile_dir "scientific-department-service"

compile_dir "telemetry-reader-service"

# remove # to add pause in script execution
#read -p "** Press any key to continue... "

echo "** Done all"
