#!/bin/bash   
echo cleaning Rickshaw files
cd ss
make clean
echo making Rickshaw files
make
cd ..
echo compiling class files
javac src/dataToGraph/*java
echo moving to bin
mv src/dataToGraph/*class bin/dataToGraph
cd bin
echo jarring dataToGraph
jar cvfm graph.jar META-INF/* dataToGraph/*
mv graph.jar ..
cd ..
echo done
