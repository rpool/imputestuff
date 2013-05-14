#!/bin/bash
COUNTER=1
while [ $COUNTER -lt 23 ] ; do
	wc -l chr"$COUNTER"_out.imputed
	COUNTER=$(( $COUNTER + 1 ))
done
