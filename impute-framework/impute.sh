#!/bin/bash
#
# Part of the VU imputation script pack
#
# Convenience script for starting an imputation. A preconfigured zip will be downloaded to the 
# specified directory, extracted and run.
#
# Parameters (4):
# $1 the chromosome number to start with (1 to 22)
# $2 the chromosome number to end with (1 to 22)
# $3 base directory the directory which will contain the input, output and scripts
# $4 the name of the set
# 
# Created by vm.kattenberg - vm.kattenberg@psy.vu.nl

# set the remote host where the set is hosted (the script will look in the imputations directory)
REMOTEHOST=vm.kattenberg@clio.psy.vu.nl

# make directory
mkdir $3
cd $3

# scp from imputedir
scp $REMOTEHOST:imputations/$4.zip .

# unzip
unzip $4.zip

# make all scripts executable
chmod -R 700 *

# run spawn script
./spawn.sh $1 $2 $3

