#!/bin/bash

for dir in */; do
  echo "directory: $dir"
  cd "$dir"
  for subdir in */; do
    cd "$subdir"
    for file in *.yaml; do
      echo "creating $file"
      kubectl apply -f "$file"
    done
    cd ..
  done
  cd ..
done
