#!/bin/bash
echo "enter an executable to protect :"
read file
if [ ! -f $file ]; then
    echo "file not found"
    exit 1
fi
chmod -x $file
echo "file is no longer executable"