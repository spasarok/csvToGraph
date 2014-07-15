package DataToGraph;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;

import DataToGraph.ui;
import DataToGraph.htmlReader;

public class DataToGraph {

	public static int option;
	public static String[] options = { "Rickshaw Graph Tool", "Econ Graph Tool" };

	public static void main(String[] args) throws Exception {

		ui Collector = new ui();
		Collector.greeting(); // Check if user wishes to continue
		option = Collector.getOption(options); // Choose graph tool
		File inFile = Collector.openFile(); // Get input file
		Collector.collectFields(); // Collect graph details
		String outFile = Collector.getOutFile(); // Get output file

		// -----------------------------------------
		// ///reading
		// -----------------------------------------

		ArrayList<String> base = htmlReader.parseBaseHtml();

		BufferedReader CSV = new BufferedReader(new FileReader(inFile));

		Map<ArrayList<String>, double[]> data = csvParser.parseCSV(CSV,
				Collector.getDate());

		CSV.close();

		System.out.println("csv read");
		// ////////////////////////////////////////
		// ///////////////// start writing
		// ////////////////////////////////////////

		BufferedWriter out = new BufferedWriter(new FileWriter(
				(String) outFile));

		if (option == 0) {
			String json = csvParser.toJSON();
			System.out.println("toJSON done");

			out.write(base.get(0)); // css
			out.write(base.get(1));
			out.write(Collector.getTitle());// title
			out.write(base.get(2));
			out.write(Collector.getAbs());// abstract
			out.write(base.get(3)); // red
			out.write(base.get(4)); // blue
			out.write(base.get(5));
			out.write(json);// json
			out.write(base.get(6));
			out.write(base.get(7));
		}

		else if (option == 1) {
			String css = csvParser.categoryCSS();
			System.out.println("categoryCSS done");
			String json = csvParser.tofullJSON();
			System.out.println("tofullJSON done");
			String red = csvParser.redRadio();
			System.out.println("redRadio done");
			String blue = csvParser.blueRadio();
			System.out.println("blueRadio done");
			String js = csvParser.jsarrays();
			System.out.println("jsarrays done");

			out.write(base.get(0));
			out.write(css);// css
			out.write(base.get(1));
			out.write(Collector.getTitle());// title
			out.write(base.get(2));
			out.write(Collector.getAbs());// abstract
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

		Collector.ending(outFile);

	}
}
