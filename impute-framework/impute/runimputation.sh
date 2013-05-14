#!/bin/bash
#
# Part of the VU imputation script pack
#
# Script for calculating bins
#
# Parameters (7):
# $1 name for this run; used in output filenames
# $2 the chromosome number
# $3 the input .gen file
# $4 the input .strand file
# $5 the haplotype file
# $6 the legend file
# $7 the recombination rate map file
#
# Created by vm.kattenberg - vm.kattenberg@psy.vu.nl
RUNNAME=$1                                                            #Name for this run; used in output filenames
CHROMOSOME=$2
GFILE=$3                                                              #Set .gen file
SFILE=$4                                                              #Set strand alignment file
HAPS=$5
LEG=$6
MAP=$7

# Source the impute parameters
source impute.parameters

INDEX=$(( $CHROMOSOME - 1 ))
CHRSIZE=${CHRS["$INDEX"]}
BINSIZE=${BINSIZES["$INDEX"]}
NUMBINS=$(( $CHRSIZE / $BINSIZE ))                                      #Number of bins (CHRSIZE / BINSIZE) 
RNUMBINS=$(( $CHRSIZE % $BINSIZE ))
if [ $RNUMBINS -gt 0 ] ; then
    NUMBINS=$(( $NUMBINS + 1))
fi 

# Loop over all the bins and start impute via a script
COUNTER=0
FIRSTRUN=1
while [ $COUNTER -lt $NUMBINS ] ; do
	if [ $FIRSTRUN -eq 1 ] ; then
		FROM=$(( $COUNTER * $BINSIZE ))
		TO=$(( $FROM + $BINSIZE ))
		FIRSTRUN=$(( $FIRSTUN - 1 ))
	else
		FROM=$(( $COUNTER * $BINSIZE ))
		TO=$(( $FROM + $BINSIZE ))
		FROM=$(( $FROM + 1 ))
	fi
	if [ $TO -gt $CHRSIZE ] ; then
 		TO=$CHRSIZE
	fi
	echo "RUNNING: $RUNNAME $COUNTER $GFILE $SFILE $FROM $TO"
	./runimpute.sh $RUNNAME $COUNTER $GFILE $SFILE $FROM $TO $HAPS $LEG $MAP $CHROMOSOME
	COUNTER=$(( $COUNTER + 1))
done 
