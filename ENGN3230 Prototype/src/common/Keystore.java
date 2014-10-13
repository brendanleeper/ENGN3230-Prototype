/**************************************
 * Keystore.java
 * @author 	Brendan Leeper
 * @uid 	U5034424
 * @date 	12/10/2014
 *************************************
 *
 *************************************/
package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Keystore
 *
 */
public class Keystore {
	
	private static final String KEYSTORE_PATH = "E:/Dropbox/git/ENGN3230 Prototype/keystore.jks";
	private static final String KEYSTORE_PASS = "security";
	
	public static String getKeystorePath() {
		return KEYSTORE_PATH;
	}

	public static String getKeystorePass() {
		return KEYSTORE_PASS;
	}
	
	public static KeyPair getKeyPair(String username) {		
		PrivateKey priv = null;
		PublicKey pub = null;
		try {
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(new FileInputStream(Keystore.getKeystorePath()), Keystore.getKeystorePass().toCharArray());
			
			if(!keystore.containsAlias(username)) {
				throw new RuntimeException("Alias supplied to keystore not found");
			}
			
			priv = (PrivateKey) keystore.getKey(username, Keystore.getKeystorePass().toCharArray());
			pub = keystore.getCertificate(username).getPublicKey();
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new KeyPair(pub, priv);
	}
	
	public static PrivateKey getPrivateKey(String username) {
		return getKeyPair(username).getPrivate();
	}
	
	public static PublicKey getPublicKey(String username) {
		return getKeyPair(username).getPublic();
	}
}
