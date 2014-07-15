package DataToGraph;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;

public class ui {

	public File inFile = null;
	public String title;
	public String abs;
	public String dateFormat;
	public String outName;
	public String outFile = null;
	
	// -----------------------------------------
	// Accessors
	// -----------------------------------------
	
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

	public String getOutFile() {
		return outFile;
	}
	
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
	// Select Rickshaw or econ graph tool
	// -----------------------------------------
	public int getOption(String[] options) {
		JList list = new JList(options);
		JOptionPane.showMessageDialog(null, list, "Select tool",
				JOptionPane.PLAIN_MESSAGE);
		// System.out.println(list.getSelectedIndices()[0]);
		return list.getSelectedIndex();
	}

	// -----------------------------------------
	// Get the input file
	// -----------------------------------------
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
	// Collect graph details
	// -----------------------------------------
	
	public JTextField inputField = new JTextField(10);
	public JTextField titleField = new JTextField(10);
	public JTextField abstractField = new JTextField(10);
	public JTextField dateField = new JTextField(10);
	public JTextField outputField = new JTextField(10);

	public void collectFields() {
		
		JPanel myPanel = new JPanel();
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

			title = titleField.getText();
			abs = abstractField.getText();
			dateFormat = dateField.getText();
			outName = outputField.getText();
			
			// Create output file in same directory as input file
			outFile = inFile.getParent() + File.separator + outName + ".html";
			
			// Print to terminal
			System.out.println("Input File: " + inFile);
			System.out.println("Title: " + title);
			System.out.println("Abstract: " + abs);
			System.out.println("Date Format: " + dateFormat);
			System.out.println("Output File: " + outFile);
		}
	}

	// -----------------------------------------
	// Indicate file creation successful
	// -----------------------------------------
	public void ending(String outputFile) {
		JOptionPane.showMessageDialog(null,
				"Your file is being created at " + outFile);
	}
}
