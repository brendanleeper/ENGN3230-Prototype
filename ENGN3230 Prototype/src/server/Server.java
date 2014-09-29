/**************************************
 * Server.java
 * @author 	Brendan Leeper
 * @uid 	U5034424
 * @date 	27/09/2014
 *************************************
 *
 *************************************/
package server;

import sqlite.SQLiteManager;

/**
 * Server
 *
 */
public class Server {

	/**
	 * @author Brendan
	 * @param args
	 */
	public static void main(String[] args) {
		SQLiteManager sqlm = new SQLiteManager();
		sqlm.init();
		sqlm.dropTables();
		sqlm.makeTables();
		sqlm.insert();
		sqlm.query("SELECT * FROM data");
		
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
