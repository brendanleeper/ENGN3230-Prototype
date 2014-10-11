/**************************************
 * EncryptedSocketWrapper.java
 * @author 	Brendan Leeper
 * @uid 	U5034424
 * @date 	12/10/2014
 *************************************
 *
 *************************************/
package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import server.Server;


/**
 * EncryptedSocketWrapper
 * 
 * Used for encrypted communication between client/mobile app and server
 * Use Java cryptography classes to make it ezpz
 *
 */
public class EncryptedSocketWrapper implements Runnable {
	
	Socket s;
	
	public EncryptedSocketWrapper(Socket s) {
		this.s = s;
	}

	public void test() throws NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, KeyStoreException {
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		keystore.load(new FileInputStream(Keystore.getKeystorePath()), Keystore.getKeystorePass().toCharArray());
		PrivateKey key = (PrivateKey)keystore.getKey("testuser", Keystore.getKeystorePass().toCharArray());
		
		System.out.println(key);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
	}
}
