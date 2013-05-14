#!/bin/bash
BFILE=$1 #.bed/.bim/.fam file prefix
COUNTER=1

while [ $COUNTER -lt 23 ] ; do
	plink --bfile $BFILE --chr $COUNTER --make-bed --out $BFILE$COUNTER
	plink --bfile $BFILE"$COUNTER" --update-map ../reference/chr"$COUNTER"/chr"$COUNTER".alignment --make-bed --out chr"$COUNTER"
	mv chr"$COUNTER".* chr$COUNTER/
	rm $BFILE$COUNTER.*
	COUNTER=$(( $COUNTER + 1 ))
done
