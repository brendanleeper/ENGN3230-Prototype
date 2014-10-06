/**************************************
 * Server.java
 * @author 	Brendan Leeper
 * @uid 	U5034424
 * @date 	27/09/2014
 *************************************
 *
 *************************************/
package server;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import common.EncryptedSocket;

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
		//SQLiteManager sqlm = new SQLiteManager();
		//sqlm.init();
		//sqlm.dropTables();
		//sqlm.makeTables();
		//sqlm.insert();
		//sqlm.query("SELECT * FROM data");
		
		EncryptedSocket es = new EncryptedSocket();
		try {
			es.test();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
