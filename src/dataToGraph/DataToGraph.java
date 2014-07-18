package dataToGraph;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.nio.file.Path;
import dataToGraph.UI;
import dataToGraph.HTMLReader;

public class DataToGraph {

	public static int option;
	public static String[] options = { "Rickshaw Graph Tool", "Econ Graph Tool" };

	public static void main(String[] args) throws Exception {

		UI gui = new UI();
		gui.greeting(); // Check if user wishes to continue
		option = gui.getOption(options); // Choose graph tool
		File inFile = gui.openFile(); // Get input file
		gui.collectFields(); // Collect graph details
		Path outPath = gui.getOutPath(); // Get output location
		gui.buildDir(); // Build the project

		// -----------------------------------------
		// Read the CSV
		// -----------------------------------------

		ArrayList<String> base = HTMLReader.parseBaseHtml();

		BufferedReader CSV = new BufferedReader(new FileReader(inFile));

		Map<ArrayList<String>, double[]> data =  CSVParser.parseCSV(CSV,
				gui.getDate());

		CSV.close();

		System.out.println("csv read");
		// ////////////////////////////////////////
		// ///////////////// start writing
		// ////////////////////////////////////////

		BufferedWriter out = new BufferedWriter(
				new FileWriter(outPath.toString()));

		if (option == 0) {
			String json =  CSVParser.toJSON();
			System.out.println("toJSON done");

			out.write(base.get(0)); // css
			out.write(base.get(1));
			out.write(gui.getTitle());// title
			out.write(base.get(2));
			out.write(gui.getAbs());// abstract
			out.write(base.get(3)); // red
			out.write(base.get(4)); // blue
			out.write(base.get(5));
			out.write(json);// json
			out.write(base.get(6));
			out.write(base.get(7));
		}

		else if (option == 1) {
			String css =  CSVParser.categoryCSS();
			System.out.println("categoryCSS done");
			String json =  CSVParser.tofullJSON();
			System.out.println("tofullJSON done");
			String red =  CSVParser.redRadio();
			System.out.println("redRadio done");
			String blue =  CSVParser.blueRadio();
			System.out.println("blueRadio done");
			String js =  CSVParser.jsarrays();
			System.out.println("jsarrays done");

			out.write(base.get(0));
			out.write(css);// css
			out.write(base.get(1));
			out.write(gui.getTitle());// title
			out.write(base.get(2));
			out.write(gui.getAbs());// abstract
			out.write(base.get(3));
			out.write(red);// red
			out.write(base.get(4));
			out.write(blue);// blue
			out.write(base.get(5));
			out.write(json);// json
			out.write(base.get(6));
			out.write(js);// javascript
			out.write(base.get(7));
		}

		out.close();

		gui.ending(outPath.toString());

	}
}