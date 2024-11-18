#!/bin/bash

# Clean previous compilation
rm -rf build
mkdir -p build

# Compile
javac -d build src/main/java/com/mrandrej/rpg/*.java src/main/java/com/mrandrej/rpg/entity/*.java

# Run
java -cp build main.java.com.mrandrej.rpg.Main