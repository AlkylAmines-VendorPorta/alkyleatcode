package com.novelerp.alkyl.component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.dto.GSTINAuthDto;
import com.novelerp.alkyl.dto.VehicleRegistrationDto;
import com.novelerp.appbase.util.AppBaseConstant;

import sun.misc.BASE64Decoder;

@Component
public class GetGSTINDetails
{
static String folderPath = "";
static byte[] appKey = null;
static String action = "ACCESSTOKEN";
static String userName = AppBaseConstant.ALKYL_USER_NAME;
static String password = AppBaseConstant.ALKYL_PASSWORD;
static String gstin = AppBaseConstant.ALKYL_GSTIN;
static String encPayload = "";
static String authtoken = "";
static String sek = "";
static ObjectMapper objectMapper;


public boolean getAuth(String gstin)
{
authtoken = "";
folderPath = getPath();
objectMapper = new ObjectMapper();
try {
	String appKey = Base64.getEncoder().encodeToString(createAESKey());
	boolean ForceRefreshAccessToken=false;
    String payload = "{\"username\":\"" + userName + "\",\"password\":\"" + password + "\",\"AppKey\":\"" + appKey + "\",\"ForceRefreshAccessToken\":"+ForceRefreshAccessToken+"}";
    System.out.println("Payload: Plain: " + payload);
    payload = Base64.getEncoder().encodeToString(payload.getBytes());
    payload = "{\"Data\":\""+encryptAsymmentricKey(payload)+"\"}";
    System.out.println("Payload: Encrypted: " + payload);
    HttpClient httpClient = HttpClientBuilder.create().build();
    /*DefaultHttpClient httpClient = new DefaultHttpClient();*/
    HttpPost postRequest = new HttpPost(AppBaseConstant.GSTIN_AUTH_URL);
    postRequest.setHeader("client-id", AppBaseConstant.ALKYL_CLIENT_ID);
    postRequest.setHeader("client-secret", AppBaseConstant.ALKYL_CLIENT_SECRET);
    postRequest.setHeader("gstin", AppBaseConstant.ALKYL_GSTIN);
    /*postRequest.addHeader("KeepAlive", "true");
    postRequest.addHeader("AllowAutoRedirect", "false");*/
    StringEntity input = new StringEntity(payload);
    input.setContentType("application/json");
    postRequest.setEntity(input);
   HttpResponse response = httpClient.execute(postRequest);
    if (response.getStatusLine().getStatusCode() != 200)
    {
    throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
    }
    BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
    String output;
    String responseText = "";
    while ((output = br.readLine()) != null) {
    responseText = output;
    }
    System.out.println("Responsessssss:" + responseText);
    String status = objectMapper.readTree(responseText).get("Status").asText();
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(responseText);
    JSONObject jsonData =(JSONObject) json.get("Data");
    System.out.println("authToken------: " + jsonData.get("AuthToken"));
    String Auth=(String) jsonData.get("AuthToken");
    System.out.println("sek------: " + jsonData.get("Sek"));
    sek =(String) jsonData.get("Sek");
    sek = decrptBySymmetricKeySEK(sek);
    System.out.println("decSEK------: " + sek);
   return  getGSTINStatus(gstin,Auth);
}catch (Exception e) {
	// TODO: handle exception
}
    /*String status = objectMapper.readTree(responseText).get("data.AuthToken").asText();
    System.out.println("status------: " + status);
    if (status.equals("0"))
     {
     String errorDesc = "";
     errorDesc = objectMapper.readTree(responseText).get("error").asText();
     //errorDesc = new String(Base64.getDecoder().decode(errorDesc), "utf-8");
     System.out.println("Error: " + errorDesc);
     return errorDesc;
     }

    if (status.equals("1"))
     {
    authtoken = objectMapper.readTree(responseText).get("AuthToken").asText();
    sek = objectMapper.readTree(responseText).get("sek").asText();
    System.out.println("Authtoken: " + authtoken);
    System.out.println("Encrypted SEK: " + sek);
    sek = decrptBySymmetricKeySEK(sek);
    System.out.println("Decrypted SEK: " + sek);
    return authtoken;
    }
    httpClient.getConnectionManager().shutdown();
    } catch (Exception ex) {
   System.out.println(ex.getMessage());
   return ex.getMessage();
}
return null;*/
return false;
}

public static PublicKey getPublicKey() throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException
     {
   FileInputStream in = new FileInputStream("D:/einv_sandbox.pem");
   byte[] keyBytes = new byte[in.available()];
   in.read(keyBytes);
   in.close();
   String pubKey = new String(keyBytes, "UTF-8");
   pubKey = pubKey.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
   BASE64Decoder decoder = new BASE64Decoder();
   keyBytes = decoder.decodeBuffer(pubKey);
   X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
   KeyFactory keyFactory = KeyFactory.getInstance("RSA");
   PublicKey publicKey = keyFactory.generatePublic(spec);
   return publicKey;
   }

 public static byte[] createAESKey() {
    try {
    KeyGenerator gen = KeyGenerator.getInstance("AES");
    gen.init(256);
    /* 128-bit AES */
    SecretKey secret = gen.generateKey();
    appKey = secret.getEncoded();
    } catch (NoSuchAlgorithmException ex) {
   /*Logger.getLogger(EWayBillAuth.class.getName()).log(Level.SEVERE, null, ex);*/
   }
   return appKey;
   }

private static String encryptAsymmentricKey(String clearText) throws Exception
 {
   PublicKey publicKeys = getPublicKey();
   Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
   cipher.init(Cipher.ENCRYPT_MODE, publicKeys);
   byte[] encryptedText = cipher.doFinal(clearText.getBytes());
   String encryptedPassword = Base64.getEncoder().encodeToString(encryptedText);
   return encryptedPassword;
 }

public static String getPath()
 {
   String folderPath = "";
   try {
   File tempFile = new File(GetGSTINDetails.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
   folderPath = tempFile.getParentFile().getPath() + "\\";
   return folderPath;
   } catch (URISyntaxException ex) {
   Logger.getLogger(GetGSTINDetails.class.getName()).log(Level.SEVERE, null, ex);
  }
  return folderPath;
 }

public static String decrptBySymmetricKeySEK(String encryptedSek)
 {
  Key aesKey = new SecretKeySpec(appKey, "AES");
  try {      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
  cipher.init(Cipher.DECRYPT_MODE, aesKey);
  byte[] encryptedSekBytes = Base64.getDecoder().decode(encryptedSek);
  byte[] decryptedSekBytes = cipher.doFinal(encryptedSekBytes);
  byte[] sekBytes = decryptedSekBytes;
  String decryptedSek = Base64.getEncoder().encodeToString(decryptedSekBytes);
  return decryptedSek;
  } catch (Exception e) {
  return "Exception; " + e;
  }
  }
public boolean getGSTINStatus(String gstin,String auth) throws ClientProtocolException, IOException, ParseException{
	 HttpClient httpClient = HttpClientBuilder.create().build();
	    /*DefaultHttpClient httpClient = new DefaultHttpClient();*/
	    HttpGet postRequest = new HttpGet(AppBaseConstant.GSTIN_STATUS_URL+gstin);
	    postRequest.setHeader("client-id", AppBaseConstant.ALKYL_CLIENT_ID);
	    postRequest.setHeader("client-secret", AppBaseConstant.ALKYL_CLIENT_SECRET);
	    postRequest.setHeader("Gstin", AppBaseConstant.ALKYL_GSTIN);
	    postRequest.setHeader("user_name", AppBaseConstant.ALKYL_USER_NAME);
	    postRequest.setHeader("AuthToken", auth);
	    postRequest.setHeader("Content-Type", "application/json");
	    /*postRequest.addHeader("KeepAlive", "true");
	    postRequest.addHeader("AllowAutoRedirect", "false");*/
	   /* StringEntity input = new StringEntity(payload);
	    input.setContentType("application/json");
	    postRequest.setEntity(input);*/
	   HttpResponse response = httpClient.execute(postRequest);
	   if (response.getStatusLine().getStatusCode() != 200)
	    {
	    throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
	    }
	   BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
	    String output;
	    String responseText = "";
	    while ((output = br.readLine()) != null) {
	    responseText = output;
	    }
	    System.out.println("Response:" + responseText);
	    JSONParser parser = new JSONParser();
	    JSONObject json = (JSONObject) parser.parse(responseText);
	    String data = (String) json.get("Data");
	    System.out.println("data------: " + data);
	    String decData=decryptBySymmentricKey(data,sek);
	    System.out.println("decJason:" + decData);
	    String gstinStatus = objectMapper.readTree(decData).get("Status").asText();
	    System.out.println("gstinStatus------: " + gstinStatus);
	    if(gstinStatus.equals("ACT")){
	    	return true;
	    }
	return false;
	
}
public static String decryptBySymmentricKey(String data, String decryptedSek) {
	 {
	  byte[] sekByte = Base64.getDecoder().decode(decryptedSek);
	  Key aesKey = new SecretKeySpec(sekByte, "AES");
	  try {
	  Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	  cipher.init(Cipher.DECRYPT_MODE, aesKey);
	  byte[] decordedValue = new BASE64Decoder().decodeBuffer(data);
	  byte[] decValue = cipher.doFinal(decordedValue);
	  return new String(decValue);
	  } catch (Exception e) {
	  return "Exception " + e;
	  }
	  }
	}
 }
