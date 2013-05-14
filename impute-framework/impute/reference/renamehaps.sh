#!/bin/bash
# pattern is hapmap_r24_b36_fwd.consensus.qc.poly.chrX_ceu.phased
COUNTER=1
while [ $COUNTER -lt 23 ] ; do
	mv hapmap_r24_b36_fwd.consensus.qc.poly.chr"$COUNTER"_ceu.phased chr$COUNTER.haps
        COUNTER=$(( $COUNTER + 1 ))
done
