#!/bin/sh
FROMCHR=$1
TOCHR=$2
SET=$3
OUTDIR=$4

# Source the parameters
source impute.parameters
TOCHR=$(( $TOCHR + 1 ))
while [ $FROMCHR -le $TOCHR ] ; do
	# Split and align input
	plink --bfile $SET --chr $FROMCHR --make-bed --out $SET$FROMCHR
	plink --bfile $SET"$FROMCHR" --update-map chr"$FROMCHR".alignment --make-bed --out "$SET"_chr"$FROMCHR"
	rm $SET$FROMCHR.*
	
	# Calculate bin sizes for chr
	INDEX=$(( $FROMCHR - 1 ))
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
		qsub impute_job -v CHROMOSOME=$FROMCHR,SET=$SET,COUNTER=$COUNTER,OUTDIR=/archive/victormk/impute_out,FROM=$FROM,TO=$TO > chr$1.job_id
		COUNTER=$(( $COUNTER + 1))
	done 
	FROMCHR=$(( $FROMCHR + 1 ))
done


