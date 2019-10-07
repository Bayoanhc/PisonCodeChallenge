package main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class classiffierCodeChallenge {
	
	public void classifier(String message, /*int[] arrayTimeData,*/ int timeStamp, int data, String label, JsonObject jsonParser, BufferedWriter bw) {
		
		//Parsing the JSON to get "timeStamp"
        JsonElement timeStampElement = jsonParser.get("timeStamp");
        timeStamp = Integer.parseInt(timeStampElement.toString());

        //Parsing the JSON to get "data"
        JsonElement dataElement = jsonParser.get("data");
        data = Integer.parseInt(dataElement.toString());
        
      	//Parsing the JSON to get "label"
        JsonElement labelElement = jsonParser.get("label");
        label = labelElement.toString();
        
        List<Integer> listOfRealDataValues = new ArrayList<Integer>();

        listOfRealDataValues.add(-14131);
        listOfRealDataValues.add(-14120); 
        listOfRealDataValues.add(-13769); //appear in both 'rest/active'
        
        listOfRealDataValues.add(-12346); //appear in both 'rest/active'
        listOfRealDataValues.add(-12054); //appear in both 'rest/active'
        listOfRealDataValues.add(-11420);
        	
        listOfRealDataValues.add(1337); //appear in both 'rest/active'
        listOfRealDataValues.add(2000);
        listOfRealDataValues.add(11453); //appear in both 'rest/active'
        
        listOfRealDataValues.add(12300); //appear in both 'rest/active'
        listOfRealDataValues.add(12332); //appear in both 'rest/active'
        listOfRealDataValues.add(13427);
        
        listOfRealDataValues.add(14000); //appear in both 'rest/active'
        listOfRealDataValues.add(14783);
        listOfRealDataValues.add(15687);
        
        listOfRealDataValues.add(15789);
        listOfRealDataValues.add(56782); //appear in both 'rest/active'
        listOfRealDataValues.add(80000); //appear in both 'rest/active'
        
        //if(arrayTimeData == null) {
	     	//do nothing
		//} else {
        
	        if(listOfRealDataValues.contains(data)) {
	        
	        	switch(data) {
	        	
	        		case -13769: case -12346: case -12054:
	        		case 1337:   case 11453:  case 12300:
	        		case 12332:  case 14000:  case 56782:
	        		case 80000:
	        			try {
	        				bw.write("Activation Classified" + "\n");
	        				bw.flush();
	        			} catch (IOException e) {
	        				e.printStackTrace();
	        			}
			  		  	break;
	        		
	        		case -14131: case -14120: case -11420:
	        		case 2000:   case 13427:  case 14783:
	        		case 15687:  case 15789:
	        			try {
	        				bw.write("Activation Classified" + "\n");
	        				bw.flush();
	        			} catch (IOException e) {
	        				e.printStackTrace();
	        			}
			  		  	break;
	        		
	        		default:
	        			break;
	        	}
			}
	        
	        //arrayTimeData[0] = timeStamp;
	        //arrayTimeData[1] = data;
		}
        
	//}

}
