#!/bin/bash
source impute.params
CHRC=$1
while [ $CHRC -lt $2 ] ; do
    INDEX=$(( CHRC - 1))
    CHRSIZE=${CHRS["$INDEX"]} 
    BINSIZE=5000000
    NUMBINS=$(( $CHRSIZE / $BINSIZE ))
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
        echo Submitting: impute_job -v CHR="$CHRC",FROM="$FROM",TO="$TO",SRC="$3",DEST="$4"
        qsub impute_job -v CHR="$CHRC",FROM="$FROM",TO="$TO",SRC="$3",DEST="$4" > chr"$CHRC"_"$FROM"_"$TO".job_id     
        
        COUNTER=$(( $COUNTER + 1))
    done 
    CHRC=$(( $CHRC + 2))
done



