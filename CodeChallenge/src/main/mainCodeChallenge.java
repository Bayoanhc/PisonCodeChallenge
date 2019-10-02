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
	
	//This is the CLIENT side
	public static void main(String[] args) throws UnknownHostException {
			
		try {

			InetAddress address = InetAddress.getByName(host);
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		    System.out.println("Enter port number");
		    PORT = myObj.nextInt();
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
                	System.out.print("lable: " + label.toString() + "\n");
                	
                	if(label.toString().equals(activation)) {
                		//Send the message to the server
                        OutputStream os = clientServer.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        BufferedWriter bw = new BufferedWriter(osw);    
                        
                        //String sendMessage = br.readLine();
                        bw.write(message + "\n");
                        bw.flush();
                        //System.out.println("Message sent to the server : "+ message);
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