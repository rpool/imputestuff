#!/bin/bash
# pattern is chrX.ceu.r24.legend
COUNTER=1
while [ $COUNTER -lt 23 ] ; do
	mv chr"$COUNTER".ceu.r24.legend chr$COUNTER.legend
        COUNTER=$(( $COUNTER + 1 ))
done
