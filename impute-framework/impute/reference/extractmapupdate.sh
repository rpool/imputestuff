#!/bin/bash
# pattern is chrX.legend
COUNTER=1
while [ $COUNTER -lt 23 ] ; do
	cut -f1,2 -d' ' chr"$COUNTER".legend > chr"$COUNTER".alignment.temp
	sed 1d chr"$COUNTER".alignment.temp > chr"$COUNTER".alignment
	rm chr"$COUNTER".alignment.temp
        COUNTER=$(( $COUNTER + 1 ))
done
