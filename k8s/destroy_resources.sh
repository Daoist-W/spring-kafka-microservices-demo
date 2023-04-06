#!/bin/bash

for dir in */; do
  echo "directory: $dir"
  cd "$dir"
  for subdir in */; do
    cd "$subdir"
    for file in *.yaml; do
      echo "deleting $file"
      kubectl delete -f "$file"
    done
    cd ..
  done
  cd ..
done
