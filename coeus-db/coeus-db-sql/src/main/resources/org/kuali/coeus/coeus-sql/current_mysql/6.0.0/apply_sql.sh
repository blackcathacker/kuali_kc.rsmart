#!/usr/bin/env bash

for f in `cat files`
do
	CMD="mysql < $f"
	echo "$CMD"
	eval $CMD
done
