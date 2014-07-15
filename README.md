# csvToGraph

csvToGraph is a data visualizing tool for graphing CSV data. It improves Rickshaw (a JavaScript toolkit for creating interactive time series graphs, developed at [Shutterstock](http://www.shutterstock.com)) by offering a simple gui and by interpreting CSV data files.

## File Structure

* bin contains the compiled Java class files packaged in the DataToGraph executable jar.

* src containst the raw java files in the DataToGraph package. These files construct the DataToGraph gui and read the CSV input file.

* ss contains the Rickshaw tool used to visualize the CSV data.

## To Do List

- [x] Consolidate up user interface
- [ ] Make user interface look snazzy?
- [ ] Dropdown menu for various date formats
- [ ] Allow to choose input file from location other than project directory
- [ ] Allow negatives on y-axis
