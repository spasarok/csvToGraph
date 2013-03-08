package DataToGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;;

public class htmlReader {

	public static ArrayList<String> parseBaseHtml() throws IOException {	    
	ArrayList<String> base = new ArrayList<String>();
    	
    BufferedReader file = new BufferedReader(new FileReader(System.getProperty("user.dir")
    		+ File.separator + "ss" + File.separator + "base.html"));	
	String delim = "$DELIM$";
    
    String read = file.readLine();
    String out = new String();
	Boolean found;  
    
    while (read != null){
    	found = Arrays.asList(read.split(" ")).contains(delim);
        if(found) {
            base.add(out);
            out = new String();
        	read = file.readLine(); 
        }
    	
    	out += read;
    	out += '\n';
    	read = file.readLine(); 
    }
    base.add(out);
    file.close();
    
    //System.out.println(base.get(4));
    
    return base;
    }

}
