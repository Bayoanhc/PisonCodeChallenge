package main;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import com.google.gson.*;

//Pison TCP Classifier Coding Challenge
public class mainCodeChallenge {
	
	static classiffierCodeChallenge classifierCode = new classiffierCodeChallenge();
	static Socket clientServer;
	static int PORT = 0;
	
	//This is the CLIENT side
	public static void main(String[] args) throws InterruptedException {
			
		try {
			//Input port by user
			Scanner myInputPort = new Scanner(System.in);
		    System.out.println("Enter port number");
		    PORT = myInputPort.nextInt();
		    
		    //Connection
		    InetAddress address = InetAddress.getByName("localhost");
		    clientServer = new Socket(address, PORT);
			System.out.println("Connected");
			
			//Retrieving data from server
			InputStream is = clientServer.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            //Sending the message to the server
        	OutputStream os = clientServer.getOutputStream();
        	OutputStreamWriter osw = new OutputStreamWriter(os);
        	BufferedWriter bw = new BufferedWriter(osw); 
			
			int timeStamp = 0;
        	int data = 0;
        	String label = "";
        	
        	//int[] arrayTimeData = new int[2];
            
            while(true) {
            	//Retrieving data from server
            	String message = br.readLine();
            	System.out.println("Message received from the server : " + message);
            	
            	//JSON Parser for all fields
            	JsonParser parser = new JsonParser();
                Object objParser = parser.parse(message);
                JsonObject jsonParser = (JsonObject) objParser;
                
            	//ClassiffierCodeChallenge Class method
            	classifierCode.classifier(message, /*arrayTimeData,*/ timeStamp, data, label, jsonParser, bw);
            }
	
		}
		catch(UnknownHostException e) {
			System.out.println("Host not found: " + e.getMessage());
		}
		catch(IOException ioe) {
			System.out.println("I/O Error: " + ioe.getMessage());
		}
		finally {
			try {
				clientServer.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
