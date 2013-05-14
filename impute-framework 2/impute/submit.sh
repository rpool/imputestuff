#!/bin/bash
#
# Part of the VU imputation script pack
#
# Script for submitting an imputation. This script sets the environment for QSUB
# Parameters (4):
# $1 chromosome number
# $2 the base directory the imputation is run from
#
# Created by vm.kattenberg - vm.kattenberg@psy.vu.nl
PREFIX=$2
qsub impute_job -v CHROMOSOME=$1,BASEDIR=$2,BINDIR=$PREFIX/bin,OUTDIR=$PREFIX/output/chr$1,INDIR=$PREFIX/input/chr$1,REFDIR=$PREFIX/reference/chr$1,RUNNAME=chr$1,HAPS=chr$1.haps,LEG=chr$1.legend,MAP=chr$1.genetic.map > chr$1.job_id

