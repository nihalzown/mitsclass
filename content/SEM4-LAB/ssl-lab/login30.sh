#!/bin/bash
# Aim: Program to perform login check (Login Check).
username="admin"
while ! who | grep -q "^$username "; do
    echo "User $username not logged in , retrying in 30 seconds..."
    sleep 30
done
echo "User $username has logged in."