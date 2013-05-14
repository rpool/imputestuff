#!/bin/bash
# preprocessess and imputes a chunk
# $1 -> chromosome number
# $2 -> from pos
# $3 -> to pos
# $4 -> src directory
# $5 -> dest directory

# === Copy files to local ===
cp -rv $4/* .

# === Prepare input ===

# Set myplink to executable
chmod +x myplink

# Extract chromosome and make ASCII
./myplink --bfile allin --chr $1 --recode --out allin --noweb

# Set gtool to executable
chmod +x mygtool

# Gtool input data
./mygtool -P --ped allin.ped --map allin.map --og allin.gen --os allin.sample --log allin.gtool.log

# Finally make a strand file
cut -f3 -d' ' allin.gen | sed 's/$/ +/' > allin.strand

# === Run impute2 ===

# Set myimpute2 to executable
chmod +x myimpute2

# Run impute2 for desired chunk
./myimpute2 -m chr13.rmap -h chr13_eur.impute.hap -l chr13_eur.impute.legend -g allin.gen -strand_g allin.strand -Ne 11418 -o all_vs_eurCHR"$1"_"$2"_"$3".gen -phase -int "$2" "$3"
 
# === Copy files to output
mkdir out
cp -v all_vs_eur* out
cp -v *.log out
tar -cvzf out_CHR"$1"_"$2"_"$3".tar.gz out/*
cp -v out_CHR"$1"_"$2"_"$3".tar.gz $5
        
