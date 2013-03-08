package DataToGraph;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

public class ui {

    public static int getOption(String[] options) {
		JList list = new JList(options);
		JOptionPane.showMessageDialog(
		  null, list, "Select tool", JOptionPane.PLAIN_MESSAGE);
		//System.out.println(list.getSelectedIndices()[0]);
		return list.getSelectedIndex();
    }
	
    public static void greeting() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        
	    int response = JOptionPane.showConfirmDialog(null, "Welcome to DASIL Data visualizer\nDo you want to continue?", "Welcome",
	            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    
	    if ((response == JOptionPane.NO_OPTION) || (response == JOptionPane.CLOSED_OPTION))
	        System.exit(0);
	    else;
    }

    public static void ending(String outputFile) {
        JOptionPane.showMessageDialog(null,"Your file is successfully created at " + outputFile);
    }

    public static File openFile() {
    	JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Input File");
        File inputFile = null;        
        if (ret == JFileChooser.APPROVE_OPTION) {
        	inputFile = fileopen.getSelectedFile();
        	System.out.println(inputFile);
        }
        return inputFile;
    }

    public static String askTitle() {
    	String title =  JOptionPane.showInputDialog(null,"Title of the Graph:");
    	return title;
    }

    public static String askAbstract() {
    	String title =  JOptionPane.showInputDialog(null,"An Abstract of the Graph:");
    	return title;
    }
    
    public static String askDateType() {
        String dateType =  JOptionPane.showInputDialog(null,"Define the date type according to Java conventions:", "MM/yyyy");
        return dateType;    	
    }
    
    public static String askOutputFile(File inputFile) throws Exception {
        
        String outputFileName =  JOptionPane.showInputDialog(null,"Output File name:");
         
        String outputFile = inputFile.getParent() + File.separator + outputFileName+ ".html";    
        return outputFile;
    }
}
