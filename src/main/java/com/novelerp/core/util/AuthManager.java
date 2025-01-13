package com.novelerp.core.util;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Component;
@Component
public class AuthManager {
   
	 // The higher the number of iterations the more 
    // expensive computing the hash is for us and
    // also for an attacker.
    private static final int iterations = 20*1000;
    private static final int desiredKeyLen = 256;
   
    /**
     * @param String password
     * @param String salt
     * @return String salted Hash password
     * */
    public String getSaltedHash(String password,String salt) {
        try {
        	return Base64.getEncoder().encodeToString(salt.getBytes()) + "$" + hash(password, salt.getBytes());
			/*return Base64.encodeBase64String(salt.getBytes()) + "$" + hash(password, salt.getBytes());*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
    }

    /**
     * @param String password
     * @param String stored password 
     * @return true if password and stored password are equals. 
     * */
    public boolean check(String password, String stored){
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException("The stored password have the form 'salt$hash'");
        }
        String hashOfInput = "";
		try {
			hashOfInput = hash(password, Base64.getDecoder().decode(saltAndPass[0]));			
/*			hashOfInput = hash(password, Base64.decodeBase64(saltAndPass[0]));*/
		} catch (Exception e) {
			e.printStackTrace();
		}
        return hashOfInput.equals(saltAndPass[1]);
    }
    
    public static boolean compare(String password, String stored){
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException("The stored password have the form 'salt$hash'");
        }
        String hashOfInput = "";
		try {
			hashOfInput = hash(password, Base64.getDecoder().decode(saltAndPass[0]));			
/*			hashOfInput = hash(password, Base64.decodeBase64(saltAndPass[0]));*/
		} catch (Exception e) {
			e.printStackTrace();
		}
        return hashOfInput.equals(saltAndPass[1]);
    }

    private static String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
            password.toCharArray(), salt, iterations, desiredKeyLen)
        );
        return Base64.getEncoder().encodeToString(key.getEncoded());
        /*return Base64.encodeBase64String(key.getEncoded());*/
    }
    
    /*public static void main(String[] a){
    	String  password = "pH9@0krH";
    	System.out.println("plain text = "+password);
    	try {
			System.out.println("Encrypted password = "+getSaltedHash(password,"abcde"));
			System.out.println("decrypted password = "+check("pH9@0krH", "YWFhYWExMTExaw==$xtCcENSLbAV5LMIzdfUj9CAJFDuP7N7BTR2hHk8jItg="));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }*/
}