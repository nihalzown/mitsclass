#!/bin/bash
if pgrep -x "bash" > /dev/null; then
    echo "a shell is running"
else
    echo "no shell is running"
fi
