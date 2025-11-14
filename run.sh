#!/bin/bash

# AeroSuite Aviation System - Run Script

echo "Starting AeroSuite..."
echo "===================="

# Check if compiled
if [ ! -d "target/classes" ]; then
    echo "Project not compiled. Compiling now..."
    mvn clean compile
fi

# Run with JavaFX
echo "Launching application..."
mvn javafx:run
