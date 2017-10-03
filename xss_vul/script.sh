#!/bin/bash

for f in *.dot;
do
	dot -Tpng $f > xss_pics/$f.png
done
