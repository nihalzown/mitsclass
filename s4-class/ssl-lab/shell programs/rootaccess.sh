#!/bin/bash
if [[ $(id -u) -eq 0 ]]; then
    echo "This script cannot be run as root. Aborting..."
    exit 1
fi
echo "This script can only be run by non-root user."