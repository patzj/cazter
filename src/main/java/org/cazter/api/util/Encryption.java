package org.cazter.api.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class that converts raw data to encrypted data using simple 
 * encryption algorithms.
 * @author patzj
 */
public class Encryption {

	/**
	 * 
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
