package dataToGraph;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;

/**
 * The GUI used to create a dataToGraph project
 * 
 *  * @author Kim Spasaro
 */
public class GUIRemoveGraphType extends javax.swing.JFrame {

	public int graphType;
	public File inFile;
	public String title;
	public String abs;
	public String dateFormat;
	public Path projFile; // path of project file, to be created
	public Path projPath; // path of project directory, to be created
	public String outString;
	public String search = "blah blah blah";
	private static final String selectDate = "Select";
	
	// for closing the gui
	public static final int APPROVE_OPTION = 1;
	public static final int CANCEL_OPTION = 0;
	public static final int DEFAULT = -1;
	public int closeOp = DEFAULT;


	/**
	 * Creates new RickshawGUI
	 */
	public GUIRemoveGraphType() {
		initComponents();

	}

	// -----------------------------------------
	// Accessors
	// -----------------------------------------

	// -----------------------------------------
	/**
	 * Gets selected input CSV file
	 * @return inFile CSV file to graph
	 */
	public File getInFile() {
		return inFile;
	}

	// -----------------------------------------
	/**
	 * Gets selected graph title
	 * @return title The graph title
	 */
	public String getTitle() {
		return title;
	}

	// -----------------------------------------
	/**
	 * Gets selected abstract
	 * @return title The graph abstract
	 */
	public String getAbs() {
		return abs;
	}

	// -----------------------------------------
	/**
	 * Gets selected date format
	 * @return dateFormat The format of date values in the CSV file
	 */
	public String getDate() {
		return dateFormat;
	}
	
	// -----------------------------------------
	/**
	 * Gets selected date format
	 * @return dateFormat The format of date values in the CSV file
	 */
	public String getSearch() {
		return "blah blah blah";
	}

	// -----------------------------------------
	/**
	 * Gets project output path, named after selected output file name
	 * @return outPath The path to create the project
	 */
	public Path getProjPath() {
		return projPath;
	}

	// -----------------------------------------
	/**
	 * Gets project output path, named after selected output file name
	 * @return outPath The path to create the project
	 */
	public Path getProjFile() {
		return projFile;
	}

	// -----------------------------------------
	/**
	 * Gets selected close operation
	 * @return closeOp Either save or cancel
	 */
	public int getCloseOp(){
		return closeOp;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	private void initComponents() {

		System.out.println("Initializing gui");

		graphProps = new javax.swing.JPanel();
		titleLabel = new javax.swing.JLabel();
		titleField = new javax.swing.JTextField();
		scrollPane = new javax.swing.JScrollPane();
		abstractLabel = new javax.swing.JLabel();
		abstractTextArea = new javax.swing.JTextArea();
		dateLabel = new javax.swing.JLabel();
		dateCombo = new javax.swing.JComboBox<String>();
		fileProps = new javax.swing.JPanel();
		inputLabel = new javax.swing.JLabel();
		inputField = new javax.swing.JTextField();
		outputLabel = new javax.swing.JLabel();
		outputField = new javax.swing.JTextField();
		okButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		graphProps.setBorder(javax.swing.BorderFactory.createTitledBorder("Graph Properties"));

		// -----------------------------------------
		// Title
		// -----------------------------------------
		titleLabel.setText("Title");
		titleField.setText("Title");
		titleField.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				textFieldMouseClicked(evt, titleField);
			}
		});

		// -----------------------------------------
		// Abstract
		// -----------------------------------------
		abstractLabel.setText("Abstract");
		abstractTextArea.setColumns(20);
		abstractTextArea.setRows(5);
		abstractTextArea.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(150, 150, 150), 1, true));
		scrollPane.setViewportView(abstractTextArea);

		// -----------------------------------------
		// Date format
		// -----------------------------------------
		dateLabel.setText("Date Format");
		dateCombo.setMaximumRowCount(20);
		dateCombo.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { selectDate, "dd-MMMM-yy", "dd/MMMM/yy", "dd-MMMM-yyyy", "dd/MMMM/yyyy", "MMM-yy", "MMM/yy", "MMM-yyyy", "MMM/yyyy", "MM-yy", "MM/yy", "MM-yyyy", "MM/yyyy", "mmyy", "mmyyyy", "yy", "yyyy" }));

		// -----------------------------------------
		// Input and output fields
		// -----------------------------------------
		fileProps.setBorder(javax.swing.BorderFactory.createTitledBorder("File Properties"));

		inputLabel.setText("Choose File");
		inputField.setText("CSV File");
		inputField.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				inputFieldMouseClicked(evt);
			}
		});

		outputLabel.setText("Save As");
		outputField.setText("Project Name");
		outputField.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				textFieldMouseClicked(evt, outputField);
			}
		});

		// -----------------------------------------
		// OK and cancel buttons
		// -----------------------------------------
		okButton.setText("OK");
		okButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				okButtonActionPerformed(evt);
			}
		});

		cancelButton.setText("Cancel");
		cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {		
				cancelButtonActionPerformed(evt);
			}
		});

		// -----------------------------------------
		// Gross layout stuff
		// -----------------------------------------
		System.out.println("Building gui");

		javax.swing.GroupLayout graphPropsLayout = new javax.swing.GroupLayout(graphProps);
		graphProps.setLayout(graphPropsLayout);
		graphPropsLayout.setHorizontalGroup(
				graphPropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(graphPropsLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(graphPropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(abstractLabel)
								.addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(graphPropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(graphPropsLayout.createSequentialGroup()
												.addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(31, 31, 31)
												.addGroup(graphPropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(graphPropsLayout.createSequentialGroup()
																.addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(dateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGroup(graphPropsLayout.createSequentialGroup()
																		.addComponent(dateLabel)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(dateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
																		.addComponent(scrollPane))
																		.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		graphPropsLayout.setVerticalGroup(
				graphPropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(graphPropsLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(graphPropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(dateLabel)
								.addComponent(dateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
							
								.addGroup(graphPropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(dateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(dateLabel))
										.addGroup(graphPropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(graphPropsLayout.createSequentialGroup()
														
														.addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(graphPropsLayout.createSequentialGroup()
																
																.addComponent(abstractLabel)))
																.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		javax.swing.GroupLayout filePropsLayout = new javax.swing.GroupLayout(fileProps);
		fileProps.setLayout(filePropsLayout);
		filePropsLayout.setHorizontalGroup(
				filePropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(filePropsLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(inputLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(55, 55, 55)
						.addComponent(outputLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(outputField, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		filePropsLayout.setVerticalGroup(
				filePropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(filePropsLayout.createSequentialGroup()
						.addGap(18, 18, 18)
						.addGroup(filePropsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(inputLabel)
								.addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(outputLabel)
								.addComponent(outputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(graphProps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(fileProps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(graphProps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(fileProps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(okButton)
								.addComponent(cancelButton))
								.addContainerGap())
				);

		pack();

	}// </editor-fold> 


	// -----------------------------------------
	// Pop-up window for selecting input file
	// -----------------------------------------
	private class FileSelector extends javax.swing.JFrame {

		// create a FileSelector
		private FileSelector() {
			init();
		}

		// to store close operation
		private int closeOpp = CANCEL_OPTION;

		// accessor
		private File getSelectedFile(){
			return fileChooser.getSelectedFile();
		}

		// initialize the fileselector
		private void init() {

			System.out.println("Initializing filechooser");
			filePanel = new javax.swing.JPanel();
			fileChooser = new javax.swing.JFileChooser();
			build();

			closeOpp = fileChooser.showOpenDialog(FileSelector.this);

			// if the user clicks Open
			if (closeOpp == JFileChooser.APPROVE_OPTION){
				inFile = fileChooser.getSelectedFile();
				inputField.setText(inFile.toString());
				filePanel.getParent().setVisible(false);
			}
			// if the user clicks Cancel
			else {
				System.out.println("No input file chosen");
				inputField.selectAll();
			}
		}

		// -----------------------------------------
		// gross layout stuff
		// -----------------------------------------

		private void build() {

			System.out.println("Building filechooser");

			setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

			filePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Select File"));

			javax.swing.GroupLayout filePanelLayout = new javax.swing.GroupLayout(filePanel);
			filePanel.setLayout(filePanelLayout);
			filePanelLayout.setHorizontalGroup(
					filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(filePanelLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					);
			filePanelLayout.setVerticalGroup(
					filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(filePanelLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					);

			javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
			getContentPane().setLayout(layout);
			layout.setHorizontalGroup(
					layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(filePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addContainerGap())
					);
			layout.setVerticalGroup(
					layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(filePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addContainerGap())
					);

			pack();
		}
	} // </editor-fold>


	// -----------------------------------------
	// Event Handlers
	// -----------------------------------------

	/**
	 * Highlight text when text field is clicked
	 * @return void
	 */
	private void textFieldMouseClicked(java.awt.event.MouseEvent evt, JTextField field) {                                        
		field.selectAll();
	} 

	// -----------------------------------------
	/**
	 * Create FileChooser pop-up when input field is selected
	 * @return void
	 */
	private void inputFieldMouseClicked(java.awt.event.MouseEvent evt) {                                        
		FileSelector fileChooser = new FileSelector();
	} 

	// -----------------------------------------
	/**
	 * Collect fields and close main window when user OKs
	 * @return void
	 */
	private void okButtonActionPerformed(java.awt.event.MouseEvent evt) {

		// make sure user remembered to select the date format before collecting input
		if (dateCombo.getSelectedItem() == selectDate)
			dateWarning();
		else{
			closeOp = APPROVE_OPTION;
			this.setVisible(false);
			collect();
		}
	}   

	// -----------------------------------------
	/**
	 * Close main window when user cancels
	 * @return void
	 */
	private void cancelButtonActionPerformed(java.awt.event.MouseEvent evt) {
		closeOp = CANCEL_OPTION;                                          
		System.exit(0);
	} 

	public void close(){
		System.exit(0);
	}

	// -----------------------------------------
	// Input Collection
	// -----------------------------------------
	/**
	 * Collect user input
	 * @return void
	 */
	public void collect () {

		synchronized(this) {
			title = titleField.getText();
			abs = abstractTextArea.getText();
			dateFormat = (String) dateCombo.getSelectedItem();
			inFile = new File (inputField.getText());

			String outName = outputField.getText();

			// Create output file in same directory as input f
			String outString = inFile.getParent() + File.separator + outName;
			projPath = Paths.get(outString);
			projFile = Paths.get(outString + "/" + outName + ".html");

			// Print to terminal
			System.out.println("Input File: " + inFile);
			System.out.println("Title: " + title);
			System.out.println("Abstract: " + abs);
			System.out.println("Date Format: " + dateFormat);
			System.out.println("Project Path: " + projPath);
			System.out.println("Project File: " + projFile);

			notify();

		}
	}


	// -----------------------------------------
	// Dialogs
	// -----------------------------------------
	/**
	 * Indicate date format needs to be selected
	 * @return void
	 */
	public void dateWarning() {
		JOptionPane.showMessageDialog(null, "Please select a date format.");
	}

	// -----------------------------------------
	/**
	 * Indicate file creation successful
	 * @return void
	 */
	public void ending() {
		JOptionPane.showMessageDialog(null, "Your file has been created at "
				+ projPath.toString() + ".html");
	}

	public static void main(String args[]) {
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
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GUI().setVisible(true);
			}
		});
	}


	// Variables declaration - do not modify

	private javax.swing.JLabel abstractLabel;
	private javax.swing.JTextArea abstractTextArea;
	private javax.swing.JComboBox<String> dateCombo;
	private javax.swing.JLabel dateLabel;
	private javax.swing.JPanel fileProps;
	private javax.swing.JPanel graphProps;
	private javax.swing.JTextField inputField;
	private javax.swing.JLabel inputLabel;
	private javax.swing.JButton cancelButton;
	private javax.swing.JButton okButton;
	private javax.swing.JTextField outputField;
	private javax.swing.JLabel outputLabel;
	private javax.swing.JScrollPane scrollPane;
	private javax.swing.JTextField titleField;
	private javax.swing.JLabel titleLabel;
	// Variables declaration - do not modify                     
	private javax.swing.JFileChooser fileChooser;
	private javax.swing.JPanel filePanel;
	// End of variables declaration  
}
