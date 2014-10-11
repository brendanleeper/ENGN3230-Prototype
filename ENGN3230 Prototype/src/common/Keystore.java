/**************************************
 * Keystore.java
 * @author 	Brendan Leeper
 * @uid 	U5034424
 * @date 	12/10/2014
 *************************************
 *
 *************************************/
package common;

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
}
