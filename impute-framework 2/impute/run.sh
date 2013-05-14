#!/bin/bash
#
# Part of the VU imputation script pack
#
# Script for running an imputation for a single chromosome. 
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
./runimputation.sh "$RUNNAME" $CHROMOSOME $GFILE $SFILE $HAPS $LEG $MAP

