#!/bin/bash

API_NAME=$1

# Check for a provided environment
if [ -z "$API_NAME" ]; then
    echo "Please provide an environment (development, staging, or production)."
    exit 1
fi

echo "$API_NAME"

case "$API_NAME" in
CAAPIs)
    echo "CA API test running..."
    newman run NovaAPIs.postman_collection.json --environment nova.postman_environment.json --folder "CAAPIs"
    ;;
PASAPIs)
    echo "PAS API test running..."
    newman run NovaAPIs.postman_collection.json --environment nova.postman_environment.json --folder "PASAPIs"
    ;;
*)
    echo "Invalid API specified. Please use right API name."
    exit 1
    ;;
esac
