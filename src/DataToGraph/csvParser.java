package DataToGraph;

import java.io.BufferedReader;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class csvParser {


    //////////////////////////////////////////
    /////reading
    //////////////////////////////////////////
    //// first line
	
    public static int labelLen;
    public static long[] xValz;
    public static Map<ArrayList<String>,double[]> data;
    public static String[] firstRowData;
    public static ArrayList<ArrayList<String>> labelSet;
    
	public static String blueRadio() {
    	String out = new String();
    	
        for (int j=0; j<labelLen; j++) {
        	out += "<div class=\"" + firstRowData[j] + "\">\n";
        	
            for (int i=0; i<labelSet.get(j).size(); i++) {
            	if((j==0) && (i==1))
            		out += "<input onclick=\"blueFunc()\" name=\"blue_" + firstRowData[j] + "\" type=\"radio\" value=\"" + i + "\" checked>\n";
            	else if((i==0) && (j>0))
            		out += "<input onclick=\"blueFunc()\" name=\"blue_" + firstRowData[j] + "\" type=\"radio\" value=\"" + i + "\" checked>\n";
            	else
            		out += "<input onclick=\"blueFunc()\" name=\"blue_" + firstRowData[j] + "\" type=\"radio\" value=\"" + i + "\" >\n";
            	out += "<label>" + labelSet.get(j).get(i) + "</label><br>\n";
            }
        	out += "</div>\n";
        }
        
        //System.out.println(out);
		return out;
    }

    public static String jsarrays() {
    	String out = new String();
    	
    	int mult = 1;
        for (int j=0; j<labelSet.size(); j++) {
        	mult *= labelSet.get(j).size();
        }

    	out += "var numOpts = new Array(" + (mult/=labelSet.get(0).size());
        for (int j=1; j<labelSet.size(); j++) {
        	out += ", " + (mult/=labelSet.get(j).size());
        }
        out += ");\n";

    	out += "var blueOpts = new Array(\"blue_" + firstRowData[0] + "\"";
        for (int j=1; j<labelSet.size(); j++) {
        	out += ", \"blue_" + firstRowData[j] + "\"";
        }
        out += ");\n";

    	out += "var redOpts = new Array(\"red_" + firstRowData[0] + "\"";
        for (int j=1; j<labelSet.size(); j++) {
        	out += ", \"red_" + firstRowData[j] + "\"";
        }
        out += ");\n";
        
        //System.out.println(out);
        return out;
    }

    public static String categoryCSS() {
    	int width = 100/labelLen;
    	String out = new String();
        for (int j=0; j<labelLen; j++) {
        	out +="." + firstRowData[j] + "{\n";
        	out +="float: left;\n";
        	out +="width: "+width+"%;\n";
        	out +="border:none;\n";
        	out +="}\n\n";
        }

        //System.out.println(out);
        return out;
    }

    public static Map<ArrayList<String>,double[]> parseCSV(BufferedReader CSV, String dateType) throws Exception {	
	    String curRow = CSV.readLine(); 
	    String[] rowData = curRow.split(",");
	    SimpleDateFormat df = new SimpleDateFormat(dateType);
	    int len;
	    labelLen=0;
	    boolean labelCheck=true;
	    labelSet = new ArrayList<ArrayList<String>>();
	
	    while(labelCheck) {
	    	try { 
	    		df.parse(rowData[labelLen]);
	    		labelCheck=false;
	    	} catch (ParseException e) {
	        	labelLen++;
	        	continue;
	    	}         	
	    }
        for (int i=0; i<labelLen; i++){
    		labelSet.add(new ArrayList<String>());        	
        }
	    //System.out.println(labelLen);
	    firstRowData = rowData;
        
        xValz = new long[rowData.length-labelLen];
        
        for (int i=labelLen; i<rowData.length; i++){
            //year = Integer.getInteger(dateData[1]);
            //month = Integer.getInteger(dateData[0]);

			Date dateToEpoch = (Date) df.parse(rowData[i]);
            
            long epoch = (dateToEpoch).getTime();
            xValz[i-labelLen] = (int) (epoch/1000);
            //xValz[i-1] = year + ((month-1)/12);
            //System.out.print(keys[i-1] + "\t");
        }
        
        data=new LinkedHashMap<ArrayList<String>,double[]>();
        curRow = CSV.readLine(); 
        
        while (curRow != null){
        	
           rowData = curRow.split(",");
           //System.out.print(rowData[0] + "\t");
           len = rowData.length;
           double[] val = new double[len-labelLen];
           ArrayList<String> label = new ArrayList<String>();

           for (int i=0; i<labelLen; i++){  
        	   	if(!labelSet.get(i).contains(rowData[i])) {
        	   		labelSet.get(i).add(rowData[i]);
        	   	}
        	   	//labelSet.get(i).add(rowData[i]);
            	label.add(rowData[i]);
           }
           
           for (int i=labelLen; i<len; i++){
            	val[i-labelLen] = Double.parseDouble(rowData[i]);
                //System.out.print(val[i-1] + "\t");
           }
           
           data.put(label, val); 

           //System.out.println();
           curRow = CSV.readLine(); 
        }
        //sortData();
		return data;
    }

    
    public static ArrayList<ArrayList<String>> permute(ArrayList<String> ext, ArrayList<ArrayList<String>> orj) {
    	ArrayList<ArrayList<String>> big = new ArrayList<ArrayList<String>>();
    	
	    for (int j=0; j<orj.size(); j++) {
	        for (int i=0; i<ext.size(); i++) {
	    		@SuppressWarnings("unchecked")
				ArrayList<String> combo = (ArrayList<String>) orj.get(j).clone();
	    		combo.add(ext.get(i));
	    		big.add(combo);
	        }
    	}
    	
    	if(orj.size()==0) 
        	for (int i=0; i<ext.size(); i++) {
        		ArrayList<String> start = new ArrayList<String>();
	    		start.add(ext.get(i));
        		big.add(start);    
        	}
    	
        //System.out.println(big.size());
    	return big;
    }
    
    public static String redRadio() {
    	String out = new String();
    	
        for (int j=0; j<labelLen; j++) {
        	out += "<div class=\"" + firstRowData[j] + "\">\n";
        	
            for (int i=0; i<labelSet.get(j).size(); i++) {
            	if((j==0) && (i==0))
                	out += "<input onclick=\"redFunc()\" name=\"red_" + firstRowData[j] + "\" type=\"radio\" value=\"" + i + "\" checked>\n";
            	else if((i==0) && (j>0))
                    out += "<input onclick=\"redFunc()\" name=\"red_" + firstRowData[j] + "\" type=\"radio\" value=\"" + i + "\" checked>\n";
                else
                	out += "<input onclick=\"redFunc()\" name=\"red_" + firstRowData[j] + "\" type=\"radio\" value=\"" + i + "\" >\n";
            	out += "<label>" + labelSet.get(j).get(i) + "</label><br>\n";
            }
        	out += "</div>\n";
        }
        
        //System.out.println(out);
		return out;
    }

    public static void sortData() {
        for (int j=0; j<labelLen; j++) {
        	System.out.println(labelSet.get(j));
        }
    }
    
    public static String tofullJSON() {
    	ArrayList<ArrayList<String>> full=new ArrayList<ArrayList<String>>();
    	
        for (int i=0; i<labelSet.size(); i++) {
        	full = permute(labelSet.get(i), full);
        	//permute 
        }
        System.out.println(full.size());
    	
    	
        String out = new String();
        ArrayList<String> curLine;
        double[] valz;

        for (int i= full.size()-1; i>=0; i--) {
          curLine = full.get(i);
          valz = data.get(curLine);

          out +="{\nname: \"";
          for (int j=0; j<labelLen; j++) {
            out += curLine.get(j) + " ";
          }
          out += "\",\ndata: [ ";
          for (int j=0; j<xValz.length; j++){
	      	if (valz == null) 
	      		out += "{ x: " + xValz[j] + ", y: " + 0 + "}";
	      	else
	      		out += "{ x: " + xValz[j] + ", y: " + valz[j] + "}";
	      	if(j < xValz.length-1)
	      		out += ", ";            	  
	      }
          
          if(i>0)
          	out += " ], \ncolor: palette.color()\n},\n";
          else
            out += " ], \ncolor: palette.color()\n}\n";
        	  
        }
        System.out.println("json done");
        return out;
    }
    
    public static String toJSON() {
        String out = new String();
        ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>(data.keySet());
        ArrayList<String> curLine;
        double[] valz;

        for (int i=lines.size()-1; i>=0; i--) {
          curLine = lines.get(i);
          valz = data.get(curLine);

          out +="{\nname: \"";
          for (int j=0; j<labelLen; j++) {
              out += curLine.get(j) + " ";	
          }
          out += "\",\ndata: [ ";
          for (int j=0; j<valz.length; j++){
              out += "{ x: " + xValz[j] + ", y: " + valz[j] + "}";
              if(j < valz.length-1)
                  out += ", ";            	  
          }
          if(i>0)
          	out += " ], \ncolor: palette.color()\n},\n";
          else
            out += " ], \ncolor: palette.color()\n}\n";
        	  
        }
        //System.out.println(out);
        return out;
    }
    
}
