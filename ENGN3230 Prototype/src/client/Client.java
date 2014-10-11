/**************************************
 * Client.java
 * @author 	Brendan Leeper
 * @uid 	U5034424
 * @date 	27/09/2014
 *************************************
 *
 *************************************/
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client
 *
 */
public class Client {
	
	private boolean firstConnect = true;
	private static int port = 4444;
	private static boolean local = false;
	private static String host = "127.0.0.1";
	
	/**
	 * @author Brendan
	 * @param args
	 */
	public static void main(String[] args) {
		/*if(args.length == 2) {
			port = Integer.parseInt(args[0]);
			
			if(args[1].equals("local")) {
				local = true;
				host = "127.0.0.1";
			}
		} else {
			System.out.println("java PrototypeClient <portNumber> <local/remote>");
			return;
		}*/
		
	    try {
			Socket s = new Socket(host, port);
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			String line;
			while ((line = in.readLine()) != null) {
			    System.out.println("Server: " + line);
			    if(line.equals("Hello")) {
			    	out.println("Is it me you're looking for?");
			    }
			    if (line.equals("Bye"))
			    	out.println("Bye");
			        break;
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// main loop waits for data
		// every x hours sends to server
		
		// first login, request public key from server
		// generate session encryption key
		// encrypt key via rsa public key
		// encrypt data with session key
		// send data to server
		// send encrypted session key to server
		// server decrypts session key with private key
		// server decrypts data with session key
		// server sends confirmation to client
		// connection closes
	}
}
