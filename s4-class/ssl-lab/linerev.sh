#!/bin/bash
num=10
mapfile -t lines < <(tail -n "$num" input.txt)
for ((i=${#lines[@]}-1; i>=0; i--)); do
    echo "${lines[i]}"
done