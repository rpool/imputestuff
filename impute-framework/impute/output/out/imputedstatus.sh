#!/bin/bash
COUNTER=1
while [ $COUNTER -lt 23 ] ; do
	cut -f1,2 -d' ' chr"$COUNTER"/chr"$COUNTER"_out.info > chr"$COUNTER"/temp
	cat chr"$COUNTER"/temp | sed -e 's/'$COUNTER' /0 /' > chr"$COUNTER"/temp.1
	cat chr"$COUNTER"/temp.1 | sed 's/---/1/' > chr"$COUNTER"/chr"$COUNTER"_out.imputed
	rm chr"$COUNTER"/temp
	rm chr"$COUNTER"/temp.1
	cp chr"$COUNTER"/chr"$COUNTER"_out.imputed .
	COUNTER=$(( $COUNTER + 1 ))
done
