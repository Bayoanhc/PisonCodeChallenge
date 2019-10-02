package main;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.google.gson.*;

//Pison TCP Classifier Coding Challenge
public class mainCodeChallenge {
	
	static Socket clientServer;
	static int PORT = 0;
	
	//This is the CLIENT side
	public static void main(String[] args) throws InterruptedException {
			
		try {
			//Input port by user
			@SuppressWarnings("resource")
			Scanner myInputPort = new Scanner(System.in);
		    System.out.println("Enter port number");
		    PORT = myInputPort.nextInt();
		    
		    //Connection
		    InetAddress address = InetAddress.getByName("localhost");
		    clientServer = new Socket(address, PORT);
			System.out.println("Connected");
			
			//Retrieving data from server
            InputStreamReader isr = new InputStreamReader(clientServer.getInputStream());
            String message  = new BufferedReader(isr).readLine();
            
            while(message != null) {
            	System.out.println("Message received from the server : " + message);
            	
            	//Parsing the JSON to get "activation"
                JsonParser parser = new JsonParser();
                JsonObject jsonObj = (JsonObject) parser.parse(message);
                JsonElement label = jsonObj.get("label");
                	
                if(label.toString().equals("\"ACTIVATION\"")) {
                	
                	//Sending the message to the server
                	OutputStreamWriter osw = new OutputStreamWriter(clientServer.getOutputStream());
                	BufferedWriter bw = new BufferedWriter(osw);    
                       
                	bw.write("Activation Classified" + "\n");
                	bw.flush();
                }
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
