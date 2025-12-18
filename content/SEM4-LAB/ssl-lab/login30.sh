#!/bin/bash

username="admin"
while ! who | grep -q "^$username "; do
    echo "User $username not logged in , retrying in 30 seconds..."
    sleep 30
done
echo "User $username has logged in."