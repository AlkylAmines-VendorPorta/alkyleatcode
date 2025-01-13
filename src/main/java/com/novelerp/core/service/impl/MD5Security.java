package com.novelerp.core.service.impl;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MD5Security {

	
	public static byte[] encrypt(String data , String secKey) throws Exception{
		//String iv = propertyUtil.getProperty("eat.payment.gateway.iv");
		//String key = propertyUtil.getProperty("eat.payment.gateway.key");
		String iv = "487301RSJTUV436B";
		String key = "GT2947312T7061B2";
		
		return encrypt(data, key, iv);
	}
	
	
	public static byte[] encrypt(String data, String secKey, String iv) throws Exception{
        byte[] clean = data.getBytes();

        // Generating IV.
        int ivSize = 16;
		byte ivArray[] = iv.getBytes();
		if(data.equals("")||data.equals(null)){
			throw new NullPointerException("No data to be encrypted.");
		}
		
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivArray);

        byte[] encryptedIVAndText = null;
        byte[] encrypted = null;
        try{
	        // Hashing key.
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        digest.update(secKey.getBytes("UTF-8"));
	        byte[] keyBytes = new byte[16];
	        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
	        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
	
	        // Encrypt.
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
	       encrypted = cipher.doFinal(clean);
	
	        // Combine IV and encrypted part.
	        encryptedIVAndText = new byte[ivSize + encrypted.length];
	        System.arraycopy(ivArray, 0, encryptedIVAndText, 0, ivSize);
	        System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);
        }catch (Exception e) {
        	e.printStackTrace();
		}	
        new String(encryptedIVAndText);
        return encrypted;
	}
	
	
	public static byte[] encrypt(byte[] clean, String secKey, String iv) throws Exception{
		
        // Generating IV.
        int ivSize = 16;
		byte ivArray[] = iv.getBytes();
		if(null==clean){
			throw new NullPointerException("No data to be encrypted.");
		}
		
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivArray);

        byte[] encryptedIVAndText = null;
        byte[] encrypted = null;
        try{
	        // Hashing key.
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        digest.update(secKey.getBytes("UTF-8"));
	        byte[] keyBytes = new byte[16];
	        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
	        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
	
	        // Encrypt.
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
	       encrypted = cipher.doFinal(clean);
	
	        // Combine IV and encrypted part.
	        encryptedIVAndText = new byte[ivSize + encrypted.length];
	        System.arraycopy(ivArray, 0, encryptedIVAndText, 0, ivSize);
	        System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);
        }catch (Exception e) {
        	e.printStackTrace();
		}	
        new String(encryptedIVAndText);
        return encrypted;
	}
	/*public static void fileProcessor(int cipherMode,String key,File inputFile,File outputFile){
		 try {
		       Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
		       Cipher cipher = Cipher.getInstance("AES");
		       cipher.init(cipherMode, secretKey);

		       FileInputStream inputStream = new FileInputStream(inputFile);
		       byte[] inputBytes = new byte[(int) inputFile.length()];
		       inputStream.read(inputBytes);

		       byte[] outputBytes = cipher.doFinal(inputBytes);

		       FileOutputStream outputStream = new FileOutputStream(outputFile);
		       outputStream.write(outputBytes);

		       inputStream.close();
		       outputStream.close();

		    } catch (NoSuchPaddingException | NoSuchAlgorithmException 
	                     | InvalidKeyException | BadPaddingException
		             | IllegalBlockSizeException | IOException e) {
			e.printStackTrace();
	            }
	     }*/
	
	public static String decrypt(byte[] encryptedData, String secKey) throws SecurityException, Exception{

		if(encryptedData == null){
			throw new NullPointerException("No data to be decypted");
		}
        int ivSize = 16;
        int keySize = 16;

        // Extract IV.
        byte[] iv = new byte[ivSize];

        System.arraycopy(encryptedData, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Extract encrypted part.
        int encryptedSize = encryptedData.length - ivSize;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(encryptedData, ivSize, encryptedBytes, 0, encryptedSize);

        // Hash key.
        byte[] keyBytes = new byte[keySize];
        byte[] decrypted = null;
        try{
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(secKey.getBytes());
	        System.arraycopy(md.digest(), 0, keyBytes, 0, keyBytes.length);
	        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
	
	        // Decrypt.
	        Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
	        decrypted = cipherDecrypt.doFinal(encryptedBytes);
        }catch (Exception e) {
        	throw new SecurityException(e.getMessage());
        }

        return new String(decrypted);
	}

	public static void main(String[] args) throws Exception{
		//String data = "request_id=1234567890|txn_amt=2.00|email=abc@xyz.com|mob=9820098200|udf=TENDER01_VENDOR05|hash=b5a8af03a1e13f95a843d6ead0223e28";
		String data = "request_id=2000001729|txn_amt=1|email=akshaygore@gmail.com|mob=5234523452|udf=TENDER01/625_ACME MFG_VEND101_11-06-2018 06:51_27AABCD1991A1ZV_RG|date_of_txn=11-06-2018 18:51:45|status=Success|remarks=|hash=732d150a97b3b9bfb90b72b276c39adf";
		String iv = "487301RSJTUV436B";
		String secKey = "GT2947312T7061B2";
		
		String str = MD5Security.secureData(data, secKey, iv);
		//byte[] str =MD5Security.encrypt(data, secKey, iv);
		System.out.println(">>>>> "+str);
	//	String str2 = "EzmS98gqLluvRxXVRExpzV/nmYn2glqoeKJJwZONGrQv2Hpmw pSyKkcgSFM W0GKZiRsk8TDgA2y43DH7emvnCk8G8 oLXzHuChNwr21S6usH5Kxv62Zldr1mF6n 21c4nolx1xxlDEVjlOydhIRq8YrYQePUk8TNghdcpjoC8D5225EQ8TmwjGnlhrR3EX3TA/iOv5orf2I39kTNOvsxIY2 0eghgjJkOHGJz4P7eZvwkOUE75A24hAuxzKN3lXa5R9IFo1AJobS1n6t1ay0adxIdAN6gG4mFsfLPijEXZjqjpprNst8zT8QnIAAs89NsYM8pi7kW9 AuAW8sM6cexuldaiRP xZF23tgwdXJ6qNMr99vIAQmVkiqJXSueloSGYvsQE4X6oO2mIQm605SCIRigVILg 5fkmaBPDbTEqSScNmL CNV9JsfNvmcda55/6XyjqWNPuskzDFtwU55VbgQzxTz/uLbtnAlDbCYfemA3oe/rN6k1knAzRzcf59WuHaPQY1VY0/TM7N2 Dw==";
	//	String str2 = "X0pWmH12bMj/qKTZoqQBZOBabkUc0TsdGUVAaCqxNw2M2GqsqhwN5rT5f87yaNsgRiaJCKy1CeVrTVwOqet9X513ChiWIBAYUwaHzsHCNRZRAzflBPwDZD7U1zPDlFc98niYi5V3I4gnIA5WHf6BG81aVetlwHEubxYC7O2Gj+1SlA++04qzMIFHT5sO4w/sod8RNBgEzGbQjLVu7anrbBHeLmRu72fHXG+5S3352idSsVCSY2hIm5kvZzojGRPshIqekvauNqsQxJHToB6sPLLm3vQbiyxEaco+zV61Iycs4x1QQFpkkGZxJf1jEHDa";
		String str2="X0pWmH12bMj/qKTZoqQBZOBabkUc0TsdGUVAaCqxNw2M2GqsqhwN5rT5f87yaNsgRiaJCKy1CeVrTVwOqet9X513ChiWIBAYUwaHzsHCNRZRAzflBPwDZD7U1zPDlFc98niYi5V3I4gnIA5WHf6BG81aVetlwHEubxYC7O2Gj+1SlA++04qzMIFHT5sO4w/sod8RNBgEzGbQjLVu7anrbBHeLmRu72fHXG+5S3352idSsVCSY2hIm5kvZzojGRPshIqekvauNqsQxJHToB6sPLLm3vQbiyxEaco+zV61IydNcC/EnmEYdnEIUTsofXp0";
		String decrypted = MD5Security.decryptData(str2, secKey,iv);
		//String decrypted = MD5Security.decrypt(str, secKey);
			decrypted = decrypted.replaceAll("\\|", "\\\",\\\"");
			decrypted = decrypted.replaceAll("=", "\\\":\\\"");
			decrypted = "{\""+decrypted+"\"}";
		
		System.out.println(decrypted);
		/*JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject) parser.parse(decrypted);	
		String requestID = (String) jObj.get("status");
		System.out.println(requestID);*/
		
	}

	
	public static String secureData(String data, String secKey) throws Exception {
		byte [] encryptedData = encrypt(data, secKey);
		return Base64.getEncoder().encodeToString(encryptedData); 
	}

	
	public static String secureData(String data, String secKey, String iv) throws Exception {
		byte [] encryptedData = encryptData(data, secKey,iv);
		return Base64.getEncoder().encodeToString(encryptedData); 
	}
	
	
	public static String decryptData(String data, String secKey) throws SecurityException,Exception {
		if(data.equals("")||data.equals(null)){
			throw new NullPointerException("No data to be encoded.");
		}
		byte[] encodedData =  null;
		try{
			encodedData = data.getBytes(StandardCharsets.UTF_8);
		}catch (Exception e) {
			
		}
		byte[] decodedData = Base64.getDecoder().decode(encodedData);		
		return decrypt(decodedData, secKey);
	}
	
	
	public static String decryptData(String data, String secKey, String iv) throws SecurityException,Exception {
		if(data.equals("")||data.equals(null)){
			throw new NullPointerException("No data to be encoded.");
		}
		byte[] decodedData = Base64.getDecoder().decode(data);		
		return decrypt(decodedData, secKey, iv);
	}

	
	public static String decryptDataNoPadding(String data, String secKey, String iv) throws SecurityException,Exception {
		if(data.equals("")||data.equals(null)){
			throw new NullPointerException("No data to be encoded.");
		}
		byte[] decodedData = Base64.getDecoder().decode(data);		
		return decryptNoPadding(decodedData, secKey, iv);
	}

	
	public static String getCheckusm(String messege) throws NoSuchAlgorithmException{
		MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(messege.getBytes(),0,messege.length());
        return new BigInteger(1,m.digest()).toString(16);
		} 
	
	public static byte[] encryptData(String plainText, String key, String iv) {
			byte[] clean = plainText.getBytes();
			
			try {
				IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
				SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	
				// Encrypt.
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
				byte[] encrypted = cipher.doFinal(clean);
				return encrypted;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}
	
	public static String decrypt(byte[] data, String key, String iv) {
		
		try {
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
			// Decrypt.
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
			byte[] decrypted = cipher.doFinal(data);

			return new String(decrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}	
	
public static byte[] decryptDoc(byte[] data, String secKey, String iv) {
	 
		byte ivArray[] = iv.getBytes();
		if(null==data){
			throw new NullPointerException("No data to be encrypted.");
		}
		
     IvParameterSpec ivParameterSpec = new IvParameterSpec(ivArray);

		try {
			 MessageDigest digest = MessageDigest.getInstance("SHA-256");
		        digest.update(secKey.getBytes("UTF-8"));
		        byte[] keyBytes = new byte[16];
		        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
		        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
			// Decrypt.
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			byte[] decrypted = cipher.doFinal(data);

			return decrypted;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String decryptNoPadding(byte[] data, String key, String iv) {
		
		try {
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
			// Decrypt.
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
			byte[] decrypted = cipher.doFinal(data);

			return new String(decrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}	
}
