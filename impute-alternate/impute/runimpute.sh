#!/bin/bash
#
# Part of the VU imputation script pack
#
# Script for calculating bins
#
# Parameters (7):
# $1 name for this run; used in output filenames
# $2 the bin number
# $3 the input .gen file
# $4 the input .strand file
# $5 from base pair
# $6 to base pair
# $7 the haplotype file
# $8 the legend file
# $9 the recombination rate map file
# $10 the chromosome number
#
# Created by vm.kattenberg - vm.kattenberg@psy.vu.nl

HFILE=$7   #Hapmap phased genotype file
LFILE=$8   #Hapmap legend file
MFILE=$9   #Hapmap map file
GFILE=$3                                                              #Set .gen file
SFILE=$4                                                              #Set strand alignment file
FROMBP=$5                                                             #From bp (for bin size)
TOBP=$6                                                               #To bp (for bin size)

OFILE=$1$2.gen                                                        #Output file
IFILE=$1$2.info                                                       #Info file
RFILE=$1$2.out                                                        #Log file

CHROMOSOME=${10}

# Source the parameters
source impute.parameters

INDEX=$(( $CHROMOSOME - 1 ))
BUFFER=${BUFFERS["$INDEX"]}
NE=${NES["$INDEX"]}

# Start impute in the background and store it's PID
./myimpute -h $HFILE -l $LFILE -m $MFILE -g $GFILE -s $SFILE -Ne $NE -int $FROMBP- $TOBP -buffer $BUFFER -o $OFILE -i $IFILE -r $RFILE -fix_strand > impute$1$2.out & 
PID=$!
echo $PID > $1$2.pid

# Wait for impute to finish
wait $PID
