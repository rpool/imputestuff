#!/bin/bash
source ../impute.parameters

COUNTER=1
INDEX=0
while [ $COUNTER -lt 23 ] ; do
	echo "$COUNTER"
	INDEX=$(( COUNTER - 1 ))
	BSIZE=${BINSIZES["$INDEX"]}
	NUMBINS=$(( ${CHRS["$INDEX"]} / $BSIZE ))  
	RNUMBINS=$(( ${CHRS["$INDEX"]} % $BSIZE ))
	if [ $RNUMBINS -gt 0 ] ; then
   		 NUMBINS=$(( $NUMBINS + 1))
	fi 
	cd chr"$COUNTER"
	rm chr"$COUNTER"_out.*
	cp ../catgenfiles.sh .
	cp ../catlogfiles.sh .
	./catgenfiles.sh chr"$COUNTER" $NUMBINS chr"$COUNTER"_out.gen chr"$COUNTER"_out.info
	./catlogfiles.sh "$COUNTER" $NUMBINS
	#cat chr"$COUNTER"_out.gen | sed s/---/"$COUNTER"/ > temp
	#mv temp chr"$COUNTER"_out.gen
    cp input.sample chr"$COUNTER"_out.sample
	cd ..
	cd out
	mkdir chr"$COUNTER"
	cd chr"$COUNTER"
	cp ../../chr"$COUNTER"/impute_chr"$COUNTER".log .
	cp ../../chr"$COUNTER"/chr"$COUNTER"_out.gen .
	cp ../../chr"$COUNTER"/chr"$COUNTER"_out.info .
	cp ../../chr"$COUNTER"/chr"$COUNTER"_out.sample . 
	COUNTER=$(( $COUNTER + 1 ))
	cd ..
	cd ..
	echo "------------"
done
