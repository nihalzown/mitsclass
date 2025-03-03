#!/bin/bash
allowed_users=("user1" "user2" "user3")
current_user=$(whoami)
if [[ ! " ${allowed_users[@]} " =~ " ${current_user} " ]]; then
    echo "Access denied!"
    exit 1
fi
echo "Access granted!"