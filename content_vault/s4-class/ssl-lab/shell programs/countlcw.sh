#!/bin/bash
lines=0
words=0
chars=0

while read line; do
    lines=$((lines+1))
    chars=$((chars+${#line}))
    for word in $line; do
        words=$((words+1))
    done
done

printf "Lines: %d\nWords: %d\nChars: %d\n" $lines $words $chars
