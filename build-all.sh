#!/bin/bash

function compile_dir()  # $1 is the dir to get it
{
    cd $1
    ./build.sh
    cd ..
}

echo "** Compiling all"

compile_dir "weather-service"

compile_dir "mission-service"

compile_dir "rocket-service"

# read -p "Press any key to continue... "

echo "** Done all"
