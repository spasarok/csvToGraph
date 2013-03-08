package DataToGraph;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

import DataToGraph.ui;
import DataToGraph.htmlReader;

public class DataToGraph{
	
		public static int option;
		public static String[] options = {"Rickshaw Graph Tool", "Econ Graph Tool"};

	  
    public static void main(String[] args) throws Exception {
    	
        ui.greeting();
        
    	option = ui.getOption(options);
            
        File inputFile = ui.openFile();

    	String title =  ui.askTitle();
    	
    	String abs =  ui.askAbstract();
    	
    	String dateType = ui.askDateType();

        String outputFile = ui.askOutputFile(inputFile);

        //////////////////////////////////////////
        /////reading
        ////////////////////////////////////////// 
    	
    	//String outName =  JOptionPane.showInputDialog(null,"Name of the output file:");
    	//out.write(read);          
        
        ArrayList<String> base = htmlReader.parseBaseHtml();  
        
        BufferedReader CSV = new BufferedReader(new FileReader(inputFile)); 
        
        Map<ArrayList<String>, double[]> data = csvParser.parseCSV(CSV, dateType);
        
        CSV.close();

        System.out.println("csv read");
        //////////////////////////////////////////
        /////////////////// start writing
        //////////////////////////////////////////

        BufferedWriter out = new BufferedWriter(new FileWriter((String) outputFile));

        if (option == 0) {
            String json = csvParser.toJSON();
            System.out.println("toJSON done");
            
	        out.write(base.get(0)); //css
	        out.write(base.get(1)); out.write(title);//title
	        out.write(base.get(2)); out.write(abs);//abstract
	        out.write(base.get(3)); //red
	        out.write(base.get(4)); //blue
	        out.write(base.get(5)); out.write(json);//json
	        out.write(base.get(6)); 
	        out.write(base.get(7)); 
        } else if (option == 1) {
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
            
            out.write(base.get(0)); out.write(css);//css
            out.write(base.get(1)); out.write(title);//title
            out.write(base.get(2)); out.write(abs);//abstract
            out.write(base.get(3)); out.write(red);//red
            out.write(base.get(4)); out.write(blue);//blue
            out.write(base.get(5)); out.write(json);//json
            out.write(base.get(6)); out.write(js);//javascript
            out.write(base.get(7)); 
        } 
        
        out.close();

        ui.ending(outputFile);
    } 
} 
