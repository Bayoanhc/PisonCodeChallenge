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
	static String activation = "\"ACTIVATION\"";
	
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
            String message  = br.readLine();
            
            while(message != null) {
            	System.out.println("Message received from the server : " + message);
            	
            	//Parsing the JSON to get "activation"
                JsonParser parser = new JsonParser();
                Object obj = parser.parse(message);
                JsonObject jsonObj = (JsonObject) obj;
                JsonElement label = jsonObj.get("label");
                	
                if(label.toString().equals(activation)) {
                	
                	//Sending the message to the server
                	OutputStream os = clientServer.getOutputStream();
                	OutputStreamWriter osw = new OutputStreamWriter(os);
                	BufferedWriter bw = new BufferedWriter(osw);    
                       
                	bw.write("Activation Classified" /*+ message */+ "\n");
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
