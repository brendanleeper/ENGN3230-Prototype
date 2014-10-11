/**************************************
 * Server.java
 * @author 	Brendan Leeper
 * @uid 	U5034424
 * @date 	27/09/2014
 *************************************
 *
 *************************************/
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import common.EncryptedSocketWrapper;


/**
 * Server
 *
 */
public class Server implements Runnable {
	
	private static int port = 4444;
	private static boolean stopped = false;
	private ServerSocket ss;
	
	public Server(int port) {
		this.port = port;
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(!stopped()){
	        Socket listenSocket;
	        try {
	            listenSocket = ss.accept();
	        } catch (IOException e) {
	            if(stopped()) {
	                System.out.println("Server stopped") ;
	                return;
	            }
	            throw new RuntimeException("Client connection failed", e);
	        }
	        
	        // Once a client connects create a new encryptedsocketwrapper to deal with it
	        new Thread(new EncryptedSocketWrapper(listenSocket)).start();
	    }
	}
	
	public boolean stopped() {
		return stopped;
	}
	
	/**
	 * @author Brendan
	 * @param args
	 */
	public static void main(String[] args) {
		/*if(args.length == 1) {
			port = Integer.parseInt(args[0]);
		} else {
			System.out.println("java PrototypeServer <portNumber>");
			return;
		}*/
		
		
		
		//SQLiteManager sqlm = new SQLiteManager();
		//sqlm.init();
		//sqlm.dropTables();
		//sqlm.makeTables();
		//sqlm.insert();
		//sqlm.query("SELECT * FROM data");
		
		// listen on socket
		// accept connection
		// decrypt data
		// push into db
		
		// listen on socket
		// accept connection
		// encrypt data
		// push to mobile app
		
		// php accesses db directly
		// no need to interface with server
	}

	
}
