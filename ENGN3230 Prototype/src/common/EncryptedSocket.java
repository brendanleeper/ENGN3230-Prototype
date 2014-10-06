/**************************************
 * EncryptedSocket.java
 * @author 	Brendan Leeper
 * @uid 	U5034424
 * @date 	27/09/2014
 *************************************
 *
 *************************************/
package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;


/**
 * EncryptedSocket
 * 
 * Used for encrypted communication between client/mobile app and server
 * Use Java cryptography classes to make it ezpz
 *
 */
public class EncryptedSocket {

	public void test() throws NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, KeyStoreException {
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		keystore.load(new FileInputStream("E:/Dropbox/git/ENGN3230 Prototype/keystore.jks"), "security".toCharArray());
		PrivateKey key = (PrivateKey)keystore.getKey("testuser", "security".toCharArray());
		
		System.out.println(key);
	}
}
