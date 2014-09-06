package dataToGraph;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.nio.file.Path;
import dataToGraph.HTMLReader;

public class DataToGraph {

	/***************************************
	 *  Run GUI and Build Project
	 ***************************************/

	private int graphType;
	private File inFile;
	private Path projFile;
	public RickshawGUI gui;

	/*
	 * Build and display the graphical user interface
	 * @return void
	 */
	public RickshawGUI buildGUI() {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
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

		/* Create and display the form */
		/*java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				//gui = new RickshawGUI();
				System.out.println(gui);
				//gui.setVisible(true);
			}
		});*/

		gui = new RickshawGUI();
		gui.setVisible(true);
		return gui;

	}	


	/*
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

		gui.ending(projFile.toString());
		System.out.println("Project directory complete");

		return true;

	}

	// -----------------------------------------
	// Read CSV data file
	// -----------------------------------------
	private ArrayList<String> base;

	public void readCSV() throws Exception{

		base = HTMLReader.parseBaseHtml();

		BufferedReader CSV = new BufferedReader(new FileReader(inFile));
		Map<ArrayList<String>, double[]> data =  CSVParser.parseCSV(CSV,
				gui.getDate());

		CSV.close();
		System.out.println("CSV read");
	}

	// -----------------------------------------
	// Write to output file
	// -----------------------------------------
	public void writeDelims() throws IOException {

		BufferedWriter out = new BufferedWriter(
				new FileWriter(projFile.toString()));
				
		//for (int n = 0; n < base.size(); n++){
		//	System.out.println("" + n + "  " + base.get(n));
		//}
				
				

		if (graphType == 0) {
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

		else if (graphType == 1) {
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
		}
		// Do nothing if user cancels
		else
			System.out.println("Graph not created.");

	}
}
