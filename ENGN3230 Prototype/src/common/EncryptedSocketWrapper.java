/**************************************
 * EncryptedSocketWrapper.java
 * @author 	Brendan Leeper
 * @uid 	U5034424
 * @date 	12/10/2014
 *************************************
 *
 *************************************/
package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


/**
 * EncryptedSocketWrapper
 * 
 * Used for encrypted communication between client/mobile app and server
 * Uses Java cryptography classes to encrypt/decrypt stuff
 *
 */
public class EncryptedSocketWrapper implements Runnable {
	
	Socket s;
	boolean server = true;
	
	public EncryptedSocketWrapper(Socket s) {
		this.s = s;
	}
	
	public EncryptedSocketWrapper(Socket s, boolean server) {
		this.s = s;
		this.server = server;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		PrintWriter out = null;
		BufferedReader in = null;
		int stage = 0;
		
		/* SERVER stages: 	0 - hello not yet sent
							1 - awaiting first data/command
							2 - awaiting additional data or 
		*/
		
		/* CLIENT stages: 	0 - awaiting first hello
							1 - sent data/command, awaiting confirmation
							2 - sent bye, awaiting reply
		*/
		
		String line;
		try {
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			if(server) {
				// we are the server, send initial hello
				out.println("hello");
				stage = 1;
			}
			
			// TODO: Package data into blob and send
			// RSA public key can encrypt up to 245 bytes
			// Always outputs 256 bytes
			// Make a file format like:
			// 2 byte command code
			// x bytes for username
			// 1 byte for rsa or aes encrypted data
			// 256 bytes for rsa encrypted data or big blob for aes

			while ((line = in.readLine()) != null) {
				if(server) {
					if(stage == 1) {
						// we expect a command
					} else if(stage == 2) {
						
					}
					
				} else {
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Communication completed");
	}
	
	public static byte[] encrypt(String input, String username) {
		byte[] data = null;
		try {
			data = input.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(data.length > 245) {
			throw new RuntimeException("Input data too long");
		}
		
		System.out.println("Length of data: " + data.length);
		
		PublicKey publicKey = Keystore.getPublicKey(username);
		
		Cipher rsa;
		
		try {
			rsa = Cipher.getInstance("RSA");
			rsa.init(Cipher.ENCRYPT_MODE, publicKey);
			return rsa.doFinal(input.getBytes());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String decrypt(byte[] input, String username) {
		PrivateKey privateKey = Keystore.getPrivateKey(username);
		
		Cipher rsa;
		
		try {
			rsa = Cipher.getInstance("RSA");
			rsa.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decrypted = rsa.doFinal(input);
			return new String(decrypted, "UTF8");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
