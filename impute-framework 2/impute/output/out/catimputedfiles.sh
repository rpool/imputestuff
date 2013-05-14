#!/bin/bash
OUTFILE=$1
COUNTER=1
touch $OUTFILE
while [ $COUNTER -lt 23 ] ; do
    cat chr"$COUNTER"_out.imputed >> $OUTFILE
	COUNTER=$(( $COUNTER + 1 ))
done 
