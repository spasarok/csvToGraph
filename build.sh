#!/bin/bash   
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
