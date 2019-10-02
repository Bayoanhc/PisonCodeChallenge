package main;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.google.gson.*;

//Pison TCP Classifier Coding Challenge
public class mainCodeChallenge {
	
	static Socket clientServer;
	static String host = "localhost";
	static String activation = "\"ACTIVATION\"";
	static int PORT = 0;
	//static int activationcounter = 1;
	
	//This is the CLIENT side
	public static void main(String[] args) {
			
		try {

			InetAddress address = InetAddress.getByName(host);
			Scanner myInputPort = new Scanner(System.in);
		    System.out.println("Enter port number");
		    PORT = myInputPort.nextInt();
			clientServer = new Socket(address, PORT);
			System.out.println("Connected");
			
			InputStream is = clientServer.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            while(br.readLine() != null) {
            	
            	message = br.readLine();
            	System.out.println("Message received from the server : " + message);
            	
            	JsonParser parser = new JsonParser();
                try {
                	Object obj = parser.parse(message);
                	JsonObject jsonObj = (JsonObject) obj;
                	JsonElement label = jsonObj.get("label");
                	
                	if(label.toString().equals(activation)) {
                		//Send the message to the server
                        OutputStream os = clientServer.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        BufferedWriter bw = new BufferedWriter(osw);    
                        
                        bw.write("Activation Classified" /*+ message */+ "\n");
                        bw.flush();
                        //System.out.println("Message sent to the server : "+ message);
                        //activationcounter++;
                	}
                	
                } catch(Exception e) {
                	e.printStackTrace();
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
