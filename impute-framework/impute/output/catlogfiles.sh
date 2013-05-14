#!/bin/sh
# ./catlogfiles.sh "$COUNTER" $NUMBINS
INDEX=$1
NUMBINS=$2
COUNTER=0
touch impute_chr"$INDEX".log
while [ $COUNTER -lt $NUMBINS ] ; do
	cat imputechr"$INDEX"$COUNTER.out >> impute_chr"$INDEX".log
	COUNTER=$(( $COUNTER + 1 ))
done