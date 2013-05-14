#!/bin/bash
#
# Part of the VU imputation script pack
#
# Script for submitting an imputation for each chromosome
#
# Parameters (3):
# $1 the chromosome number to start with (1 to 22)
# $2 the chromosome number to end with (1 to 22)
# $3 base directory the directory which will contain the input, output and scripts
# 
# Created by vm.kattenberg - vm.kattenberg@psy.vu.nl
FROMCHR=$1
TOCHR=$2
INDEX=0

while [ $FROMCHR -le $TOCHR ] ; do
    ./submit.sh $FROMCHR $3
	FROMCHR=$(( $FROMCHR + 1 ))
done
