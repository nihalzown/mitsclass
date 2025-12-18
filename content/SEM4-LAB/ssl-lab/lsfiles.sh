#!/bin/bash
# Aim: Program to perform file listing (File Listing).
count=1
for file in *; do
    if [ -f $file ]; then
        created=$(date -r "$file" +%Y-%m-%d)
        printf "%d\t%s\t%s\n" "$count" "$created" "$file"
        count=$((count+1))
    fi
done