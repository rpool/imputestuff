#!/bin/bash
# $1 from chr
# $2 to chr
COUNTER=$1
while [ $COUNTER -lt $2 ] ; do
	mkdir chr"$COUNTER"
	mv chr"$COUNTER".* chr"$COUNTER"/
	COUNTER=$(( $COUNTER + 1 ))
done
