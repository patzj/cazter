package org.cazter.api.util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import org.apache.commons.codec.binary.Base64;

/**
 * Utility class that converts raw data to encrypted data using existing 
 * encryption algorithms.
 * @author patzj
 */
public class Encryption {

	private Key key;
	private String algorithm;
	
	public Encryption() { }
	
	public Encryption(String algorithm) {
		this.algorithm = algorithm;
	}
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
	
	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public Key generateKey() {
		KeyGenerator keyGenerator;
		
		try {
			keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			key = keyGenerator.generateKey();
		} catch(NoSuchAlgorithmException exception) {
			exception.printStackTrace();
		}
		
		return key;
	}
	
	public String AESEncrypt(String data) {
		byte[] dataBytes = data.getBytes();
		byte[] encryptedDataBytes;
		String encryptedData = null;
		
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encryptedDataBytes = cipher.doFinal(dataBytes);
			encryptedData = new Base64().encodeToString(encryptedDataBytes);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return encryptedData;
	}

	public String AESDecrypt(String encryptedData) {
		String decryptedData = null;
		byte[] decyptedBytes;
		
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, key);
			decyptedBytes = new Base64().decode(encryptedData);
			decryptedData = new String(cipher.doFinal(decyptedBytes));
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		
		return decryptedData;
	}
	
	/**
	 * The static method that encrypts a String object into MD5.
	 * @param String object to be encrypted to MD5.
	 * @return MD5 encrypted String value.
	 */
	public static String toMd5(String data) {
		MessageDigest messageDigest;
		StringBuilder encyptedData = new StringBuilder();
		byte[] dataBytes;
		
		try {
			// Digest String to byte[]
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(data.getBytes());
			dataBytes = messageDigest.digest();
			
			// Append hex-converted bytes
			for(byte dataByte : dataBytes) {
				encyptedData.append(String.format("%02x", 
						dataByte & 0xff));
			}
		} catch(NoSuchAlgorithmException exception) {
			throw new RuntimeException(exception);
		}
		
		return encyptedData.toString();
	}
}
