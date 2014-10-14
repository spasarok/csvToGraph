/**
 * A program to create an interactive graph from a CSV file.
 * 
 * @author Kim Spasaro
 * @version 2.0
 * 
 */

package dataToGraph;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import dataToGraph.HTMLReader;

public class DataToGraph {

	/****************************************
	 *  Run GUI and Build Project
	 ***************************************/

	private int graphType;
	private File inFile;
	private Path projFile;
	private ArrayList<String> groups = new ArrayList<String>();
	public RickshawGUI gui;

	/**
	 * Build and display the graphical user interface
	 * @return void
	 */
	public RickshawGUI buildGUI() {
		/** Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/** If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(RickshawGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(RickshawGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(RickshawGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(RickshawGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		gui = new RickshawGUI();
		gui.setVisible(true);
		return gui;

	}	


	/**
	 * Collect gui input and build project directories
	 * @return boolean true if user OKs, false if user cancels
	 */
	public boolean run() throws IOException, InterruptedException{

		System.out.println("Input done, onto project build");

		// build project if user OKs
		if (gui.getCloseOp() != RickshawGUI.APPROVE_OPTION){
			System.out.println("Build canceled");
			return false;
		}

		System.out.println("Building project");

		// Set field choices
		graphType = gui.getGraphType(); // Set graph tool
		inFile = gui.getInFile(); 		// Set input file
		projFile = gui.getProjFile(); 	// Set output location

		// Build the project
		Builder projBuilder = new Builder(gui);
		projBuilder.buildDir();

		System.out.println("Project directory complete");

		return true;

	}

	// -----------------------------------------
	// Read CSV data file
	// -----------------------------------------
	private ArrayList<String> base;

	public void readCSV() throws Exception{

		base = HTMLReader.parseBaseHtml();

		// parse the csv file
		System.out.println("Parsing CSV file");
		BufferedReader CSV = new BufferedReader(new FileReader(inFile));
		Map<ArrayList<String>, double[]> data =  CSVParser.parseCSV(CSV,
				gui.getDate());
		CSV.close();
		
		// group the legend labels
		System.out.println("Gathering legend labels from CSV");
		CSV = new BufferedReader(new FileReader(inFile));
		groups = CSVParser.getLabels(CSV);
		CSV.close();
	}

	// -----------------------------------------
	// Write to output file
	// -----------------------------------------
	public void writeDelims() throws IOException {

		System.out.println("Writing input to base html file");
		
		// add the legend groups
		Charset charset = StandardCharsets.UTF_8;
		Path rickjs = Paths.get(gui.getProjPath().toString() + "/ss/rickshaw.js");
		
		// add legend groups to rickshaw.js
		String content = new String(Files.readAllBytes(rickjs), charset);
		content = content.replace("$GROUPS$", groups.toString());
		Files.write(rickjs, content.getBytes(charset));
		
		// add legend groups to Rickshaw.Graph.Legend.js
		Path rickgraph = Paths.get(gui.getProjPath().toString() + "/ss/src/js/Rickshaw.Graph.Legend.js");
		content = new String(Files.readAllBytes(rickgraph), charset);
		content = content.replace("$GROUPS$", groups.toString());
		Files.write(rickgraph, content.getBytes(charset));
		
		
		
		
		
		BufferedWriter out = new BufferedWriter(new FileWriter(projFile.toString()));

		// write input to delimiters in base html file
		if (graphType == 0) {
			System.out.println("Creating JSON");
			String json =  CSVParser.toJSON();
			
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
			//out.write(base.get(8));
		}

		else if (graphType == 1) {
			System.out.println("CSS");
			String css =  CSVParser.categoryCSS();
			System.out.println("Creating JSON");
			String json =  CSVParser.tofullJSON();
			System.out.println("Creating radio buttons");
			String red =  CSVParser.redRadio();
			String blue =  CSVParser.blueRadio();
			System.out.println("Creating js arrays");
			String js =  CSVParser.jsarrays();

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
		
		System.out.println("Output file complete");

	}

	// -----------------------------------------
	// Main method for creating graph project
	// -----------------------------------------
	public static void main(String[] args) throws Exception {

		DataToGraph project = new DataToGraph();

		RickshawGUI gui = project.buildGUI();

		// Make sure we don't build files until input has been collected
		synchronized(gui) {
			try {
				gui.wait();
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}


		if (project.run()) {
			project.readCSV();
			project.writeDelims();
			System.out.println("Finished");
			gui.ending();
			gui.close();
		}
		// Do nothing if user cancels
		else
			System.out.println("Graph not created");

	}
}
