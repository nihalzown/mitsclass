#!/bin/bash
echo 'To test the integrity of the file in a directory at '$PWD
read -p "Enter the name of the file : " file
calculate_hash() {
    sha256sum $1 | awk '{print $1}'
}
if [ ! -f $file ]; then
    echo "File not found!"
    exit 1
fi

oghash=$(calculate_hash $file)
echo "Original hash: $oghash"

# Wait for user to modify the file
read -p "Press enter when you have modified the file..."

newhash=$(calculate_hash $file)
echo "New hash: $newhash"

if [ "$oghash" == "$newhash" ]; then
    echo "File integrity is intact."
else
    echo "File has been modified."
fi