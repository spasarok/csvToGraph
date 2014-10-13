# csvToGraph

csvToGraph is a data visualizing tool for graphing CSV data. It improves Shutterstock's Rickshaw* tool by integrating a backend GUI and CSV parser.


*a JavaScript toolkit for creating interactive time series graphs, developed at [Shutterstock](http://www.shutterstock.com))

## File Structure

* `bin` contains the compiled Java class files packaged in the DataToGraph executable jar.

* `src` containst the raw java files in the DataToGraph package. These files construct the DataToGraph gui and read the CSV input file.

* `ss` contains the Rickshaw tool used to visualize the CSV data.

## To Do List

- [x] Consolidate up user interface
- [x] Make user interface look snazzy
- [x] Dropdown menu for various date formats
- [x] Allow to choose input file from location other than project directory
- [ ] Allow negatives on y-axis
