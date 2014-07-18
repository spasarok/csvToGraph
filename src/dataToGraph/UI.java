package dataToGraph;

import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.awt.Choice;
import java.awt.Color;
import dataToGraph.Copy;

public class UI {

	// -----------------------------------------
	// Check if user wishes to continue
	// -----------------------------------------
	public void greeting() {
		JDialog.setDefaultLookAndFeelDecorated(true);

		int response = JOptionPane.showConfirmDialog(null,
				"Welcome to DASIL Data visualizer\nDo you want to continue?",
				"Welcome", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if ((response == JOptionPane.NO_OPTION)
				|| (response == JOptionPane.CLOSED_OPTION))
			System.exit(0);
		else
			;
	}

	// -----------------------------------------
	// Get the input file
	// -----------------------------------------
	public File inFile;

	public File openFile() {
		JFileChooser fileopen = new JFileChooser();
		int ret = fileopen.showDialog(null, "Input File");
		if (ret == JFileChooser.APPROVE_OPTION) {
			inFile = fileopen.getSelectedFile();
			System.out.println(inFile);
		}
		return inFile;
	}

	// -----------------------------------------
	// Accessors
	// -----------------------------------------

	public int getGraphType() {
		return graphType;
	}
	
	public File getInFile() {
		return inFile;
	}

	public String getTitle() {
		return title;
	}

	public String getAbs() {
		return abs;
	}

	public String getDate() {
		return dateFormat;
	}

	public Path getOutPath() {
		return outPath;
	}
	
	// -----------------------------------------
	// Collect graph details
	// -----------------------------------------

	public JTextField inputField = new JTextField(10);
	public JTextField titleField = new JTextField(10);
	public JTextField abstractField = new JTextField(10);
	public JTextField dateField = new JTextField(10);
	public JTextField outputField = new JTextField(10);

	public int graphType;
	public String title;
	public String abs;
	public String dateFormat;
	public String outName;
	public String outString;
	public Path outDir;
	public Path outPath;

	public void collectFields() {

		Choice graphOpts = new Choice();
		graphOpts.setBackground(Color.WHITE);
		graphOpts.add("Rickshaw Graph");
		graphOpts.add("Econ Graph");
		
		JPanel myPanel = new JPanel();
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Graph Type:"));
		myPanel.add(graphOpts);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Title:"));
		myPanel.add(titleField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Abstract:"));
		myPanel.add(abstractField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Date Format:"));
		myPanel.add(dateField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Output File:"));
		myPanel.add(outputField);

		int result = JOptionPane.showConfirmDialog(null, myPanel,
				"Please Enter Graph Details", JOptionPane.OK_CANCEL_OPTION);

		// If the user is finished, collect their data
		if (result == JOptionPane.OK_OPTION) {

			graphType = graphOpts.getSelectedIndex();
			title = titleField.getText();
			abs = abstractField.getText();
			dateFormat = dateField.getText();
			outName = outputField.getText();

			// Create output file in same directory as input file
			outString = inFile.getParent() + File.separator + outName;
			outDir = Paths.get(outString);
			outPath = Paths.get(outString + "/" + outName + ".html");
			System.out.println(outDir);

			// Print to terminal
			System.out.println("Input File: " + inFile);
			System.out.println("Title: " + title);
			System.out.println("Abstract: " + abs);
			System.out.println("Date Format: " + dateFormat);
			System.out.println("Output Path: " + outString + "/" + outName + ".html");
		}
	}

	// -----------------------------------------
	// Make project directories
	// -----------------------------------------
	public boolean makeDirs() {
		boolean result = false;

		System.out.println("Creating directory: " + outDir.toString());
		
		// build the project directory
		if (!Files.exists(outDir)) {
			try {
				// make the project directory
				File projDir = new File(outString);
				projDir.mkdir();

				// make the local ss directory for project dependencies
				File ssBuilder = new File(outString + "/ss");
				ssBuilder.mkdir();

				// make the ss/examples directory
				File exampBuilder = new File(outString + "/ss/examples");
				exampBuilder.mkdir();

				// hopefully we haven't thrown an error
				
				result = true;
			} catch (Exception e) {
				System.err.format(
						"Unable to create one or more directories: %s%n", e);
				return result = false;
			}
		}
		
		if(result)
			return true;
		else {
			System.out.println("Directory " + outString
					+ " already exists");
			return false;
		}
	}
	
	// -----------------------------------------
	// Copy dependencies to local project directory
	// -----------------------------------------
	public void copyDepends() throws IOException {

		// copying dependencies to local project folder
		
	}

	// -----------------------------------------
	// Build working project directory
	// -----------------------------------------
	public void buildDir() throws IOException {
	
		// make the project directory
		System.out.println("Making project directory");
		boolean result = false;
		
		// if the project doesn't already exist, make it
		if (!Files.exists(outDir)) {
			try {
				// make the project directory
				File projDir = new File(outString);
				projDir.mkdir();
				result = true;
			} catch (Exception e) {
				System.err.format(
						"Unable to create one or more directories: %s%n", e);
				return;
			}
		
		}
		
		// abort if project exists
		if(!result) {
			System.out.println("Directory " + outString + " already exists");
			return;
		}
		
		// copy project dependencies
		System.out.println("Copying project dependencies");
		
		Copy copier = new Copy();
		copier.copyDir(Paths.get("ss"), Paths.get(outString));
		
		System.out.println("Dependencies copied");
		System.out.println("Project directory built");
	}

	// -----------------------------------------
	// Indicate file creation successful
	// -----------------------------------------
	public void ending(String outputFile) {
		JOptionPane.showMessageDialog(null, "Your file is being created at "
				+ outString + ".html");
	}
}