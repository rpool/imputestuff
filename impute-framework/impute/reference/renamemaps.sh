#!/bin/bash
# pattern is genetic_map_chrX_CEU_b36.txt
COUNTER=1
while [ $COUNTER -lt 23 ] ; do
	mv genetic_map_chr"$COUNTER"_CEU_b36.txt chr$COUNTER.genetic.map
        COUNTER=$(( $COUNTER + 1 ))
done
