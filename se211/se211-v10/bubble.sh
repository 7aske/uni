#!/usr/bin/env bash

echo "enter one number per line (^D to break)"

while read -p "> " line; do
    arr=("${arr[@]}" $line)
done

for ((i = 0; i<${#arr[@]}; i++)); do
      
    for((j = 0; j<${#arr[@]}-i-1; j++)); do

        if [ ${arr[j]} -gt ${arr[$((j+1))]} ]; then
            temp=${arr[j]} 
            arr[$j]=${arr[$((j+1))]}   
            arr[$((j+1))]=$temp 
        fi
    done
done

echo "${arr[*]}"
