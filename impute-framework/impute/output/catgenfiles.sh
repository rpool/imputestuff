#!/bin/bash
# Usage: catgenfiles.sh <file prefix> <numbins> <outname>
# 24/5/2009 vm.kattenberg - vm.kattenberg@psy.vu.nl
FPREFIX=$1
NUMBINS=$2
OUTFILEG=$3
OUTFILEI=$4
COUNTER=0
touch $OUTFILEG
touch $OUTFILEI
while [ $COUNTER -lt $NUMBINS ] ; do
        cat $FPREFIX$COUNTER.gen >> $OUTFILEG
	cat $FPREFIX$COUNTER.info >> $OUTFILEI
	COUNTER=$(( $COUNTER + 1 ))
done 
