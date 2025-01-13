package com.novelerp.core.service;

import java.security.NoSuchAlgorithmException;

import com.novelerp.core.exception.InvalidDataException;
import com.novelerp.core.exception.SecurityException;

/**
 * Encryption/ Decryption 
 * @author Vivek Birdi
 *
 */
public interface SecurityService {

	
	public byte[] encrypt(String data, String secKey) throws InvalidDataException;

	public String decrypt(byte []encryptedDatam, String secKey) throws SecurityException,InvalidDataException;
		
	public String secureData(String data, String secKey) throws InvalidDataException;

	public String decryptData(String data, String secKey) throws InvalidDataException, SecurityException;
	
	/**
	 * 
	 * @param data
	 * @param secKey
	 * @param iv -  initialization vector
	 * @return
	 * @throws InvalidDataException in case of data in empty or null;
	 */
	public byte[] encrypt(String data, String secKey, String iv) throws InvalidDataException;
	
	public String secureData(String data, String secKey, String iv) throws InvalidDataException ;
	
	public String getCheckusm(String messege) throws NoSuchAlgorithmException;
	
	public String decryptData(String data, String secKey, String iv) throws InvalidDataException,SecurityException;
	
	public String decryptDataNoPadding(String data, String secKey, String iv) throws InvalidDataException,SecurityException;
}
