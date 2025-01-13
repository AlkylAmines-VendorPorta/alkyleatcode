package com.novelerp.core.service.impl;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.exception.InvalidDataException;
import com.novelerp.core.exception.SecurityException;
import com.novelerp.core.service.SecurityService;
/**
 * 
 * @author Vivek Birdi
 * MD5 Security Service
 */
@Service("MD5_SEC")
public class MD5SecurityServiceImpl implements SecurityService{

/*	@Autowired
	private AppPropertyUtil propertyUtil;
*/	
	private static final  Logger log = LoggerFactory.getLogger(MD5SecurityServiceImpl.class);
	
	@Override
	public byte[] encrypt(String data , String secKey) throws InvalidDataException{
		//String iv = propertyUtil.getProperty("eat.payment.gateway.iv");
		//String key = propertyUtil.getProperty("eat.payment.gateway.key");
		String iv = "487301RSJTUV436B";
		String key = "GT2947312T7061B2";
		
		return encrypt(data, key, iv);
	}
	
	@Override
	public byte[] encrypt(String data, String secKey, String iv) throws InvalidDataException{
        byte[] clean = data.getBytes();

        // Generating IV.
        int ivSize = 16;
		byte ivArray[] = iv.getBytes();
		if(CommonUtil.isStringEmpty(data)){
			throw new InvalidDataException("No data to be encrypted.");
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
        	log.error("Error", e);
		}	
        new String(encryptedIVAndText);
        return encrypted;
	}

	@Override
	public String decrypt(byte[] encryptedData, String secKey) throws SecurityException, InvalidDataException{

		if(encryptedData == null){
			throw new InvalidDataException("No data to be decypted");
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
        	log.error("Error", e);
        	throw new SecurityException(e.getMessage());
        }

        return new String(decrypted);
	}

	public static void main(String[] args) throws Exception{
		//String data = "request_id=1234567890|txn_amt=2.00|email=abc@xyz.com|mob=9820098200|udf=TENDER01_VENDOR05|hash=b5a8af03a1e13f95a843d6ead0223e28";
		String data = "request_id=2000001729|txn_amt=1|email=akshaygore@gmail.com|mob=5234523452|udf=TENDER01/625_ACME MFG_VEND101_11-06-2018 06:51_27AABCD1991A1ZV_RG|date_of_txn=11-06-2018 18:51:45|status=Success|remarks=|hash=732d150a97b3b9bfb90b72b276c39adf";
		String iv = "487301RSJTUV436B";
		String secKey = "GT2947312T7061B2";
		
		MD5SecurityServiceImpl service =  new MD5SecurityServiceImpl();
		String str = service.secureData(data, secKey, iv);
		System.out.println(">>>>> "+str);
		String str2 = "EzmS98gqLluvRxXVRExpzV/nmYn2glqoeKJJwZONGrQv2Hpmw pSyKkcgSFM W0GKZiRsk8TDgA2y43DH7emvnCk8G8 oLXzHuChNwr21S6usH5Kxv62Zldr1mF6n 21c4nolx1xxlDEVjlOydhIRq8YrYQePUk8TNghdcpjoC8D5225EQ8TmwjGnlhrR3EX3TA/iOv5orf2I39kTNOvsxIY2 0eghgjJkOHGJz4P7eZvwkOUE75A24hAuxzKN3lXa5R9IFo1AJobS1n6t1ay0adxIdAN6gG4mFsfLPijEXZjqjpprNst8zT8QnIAAs89NsYM8pi7kW9 AuAW8sM6cexuldaiRP xZF23tgwdXJ6qNMr99vIAQmVkiqJXSueloSGYvsQE4X6oO2mIQm605SCIRigVILg 5fkmaBPDbTEqSScNmL CNV9JsfNvmcda55/6XyjqWNPuskzDFtwU55VbgQzxTz/uLbtnAlDbCYfemA3oe/rN6k1knAzRzcf59WuHaPQY1VY0/TM7N2 Dw==";
//		String str2 = "X0pWmH12bMj/qKTZoqQBZOBabkUc0TsdGUVAaCqxNw2M2GqsqhwN5rT5f87yaNsgRiaJCKy1CeVrTVwOqet9X513ChiWIBAYUwaHzsHCNRZRAzflBPwDZD7U1zPDlFc98niYi5V3I4gnIA5WHf6BG81aVetlwHEubxYC7O2Gj+1SlA++04qzMIFHT5sO4w/sod8RNBgEzGbQjLVu7anrbBHeLmRu72fHXG+5S3352idSsVCSY2hIm5kvZzojGRPshIqekvauNqsQxJHToB6sPLLm3vQbiyxEaco+zV61Iycs4x1QQFpkkGZxJf1jEHDa";
		String decrypted = service.decryptData(str2, secKey,iv);
			decrypted = decrypted.replaceAll("\\|", "\\\",\\\"");
			decrypted = decrypted.replaceAll("=", "\\\":\\\"");
			decrypted = "{\""+decrypted+"\"}";
		
		System.out.println(decrypted);
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject) parser.parse(decrypted);	
		String requestID = (String) jObj.get("status");
		System.out.println(requestID);
		
	}

	@Override
	public String secureData(String data, String secKey) throws InvalidDataException {
		byte [] encryptedData = encrypt(data, secKey);
		return Base64.getEncoder().encodeToString(encryptedData); 
	}

	@Override
	public String secureData(String data, String secKey, String iv) throws InvalidDataException {
		byte [] encryptedData = encryptData(data, secKey,iv);
		return Base64.getEncoder().encodeToString(encryptedData); 
	}
	
	@Override
	public String decryptData(String data, String secKey) throws InvalidDataException,SecurityException {
		if(CommonUtil.isStringEmpty(data)){
			throw new InvalidDataException("No data to be encoded.");
		}
		byte[] encodedData =  null;
		try{
			encodedData = data.getBytes(StandardCharsets.UTF_8);
		}catch (Exception e) {
			log.error("Error", e);
		}
		byte[] decodedData = Base64.getDecoder().decode(encodedData);		
		return decrypt(decodedData, secKey);
	}
	
	@Override
	public String decryptData(String data, String secKey, String iv) throws InvalidDataException,SecurityException {
		if(CommonUtil.isStringEmpty(data)){
			throw new InvalidDataException("No data to be encoded.");
		}
		byte[] decodedData = Base64.getDecoder().decode(data);		
		return decrypt(decodedData, secKey, iv);
	}

	@Override
	public String decryptDataNoPadding(String data, String secKey, String iv) throws InvalidDataException,SecurityException {
		if(CommonUtil.isStringEmpty(data)){
			throw new InvalidDataException("No data to be encoded.");
		}
		byte[] decodedData = Base64.getDecoder().decode(data);		
		return decryptNoPadding(decodedData, secKey, iv);
	}

	@Override
	public String getCheckusm(String messege) throws NoSuchAlgorithmException{
		MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(messege.getBytes(),0,messege.length());
        return new BigInteger(1,m.digest()).toString(16);
		} 
	
	public byte[] encryptData(String plainText, String key, String iv) {
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
	
	public String decrypt(byte[] data, String key, String iv) {
		
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
	
	public String decryptNoPadding(byte[] data, String key, String iv) {
		
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
