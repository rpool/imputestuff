#!/bin/bash
COUNTER=1
while [ $COUNTER -lt 23 ] ; do
	#echo "--------------"
	#echo "$COUNTER"
	wc -l chr"$COUNTER"/chr"$COUNTER"_out.info
	COUNTER=$(( $COUNTER + 1 ))
done
