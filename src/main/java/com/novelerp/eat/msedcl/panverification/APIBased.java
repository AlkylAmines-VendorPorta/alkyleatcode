package com.novelerp.eat.msedcl.panverification;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.core.util.AppPropertyUtil;

@Component
public class APIBased {
	
	private Logger log = LoggerFactory.getLogger(APIBased.class);

	
	@Autowired
	private AppPropertyUtil propertyUtil;
	
	public static class DummyTrustManager implements X509TrustManager {

		public DummyTrustManager() {
		}

		public boolean isClientTrusted(X509Certificate cert[]) {
			return true;
		}

		public boolean isServerTrusted(X509Certificate cert[]) {
			return true;
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

		}
	}

	public static class DummyHostnameVerifier implements HostnameVerifier {

		public boolean verify(String urlHostname, String certHostname) {
			return true;
		}

		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
	}


	public String verifyPan(String userId, String panNo, byte[] singData) throws Exception {

		if (userId == null || panNo == null || userId.equals("") || panNo.equals("")) {
			throw new InvalidParameterException();
		}
		String output = null;

		String urlOfNsdl = "https://14.140.81.154/TIN/PanInquiryBackEnd";
		//String urlOfNsdl = propertyUtil.getProperty("eat.pan.verification.url");

		String data = null;
		data = userId + "^" + panNo; // "V0139201^AMXPP6546K";

		Date startTime = null;
		Calendar c1 = Calendar.getInstance();
		startTime = c1.getTime();

		Date connectionStartTime = null;
		String logMsg = "\n-";
		Calendar c = Calendar.getInstance();
		long nonce = c.getTimeInMillis();
		InputStream inputStream = null;
		String signature = "";

		try {
			BufferedReader br = null;
			inputStream = new ByteArrayInputStream(singData);
			br = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = br.readLine()) != null) {
				signature += line;
			}
			br.close();
		} catch (Exception e) {
			logMsg += "::Exception: " + e.getMessage() + " ::Program Start Time:" + startTime + "::nonce= " + nonce;
		}

		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContext.getInstance("SSL");
			sslcontext.init(new KeyManager[0], new TrustManager[] { new DummyTrustManager() }, new SecureRandom());
		} catch (NoSuchAlgorithmException e) {
			logMsg += "::Exception: " + e.getMessage() + " ::Program Start Time:" + startTime + "::nonce= " + nonce;
		} catch (KeyManagementException e) {
			logMsg += "::Exception: " + e.getMessage() + " ::Program Start Time:" + startTime + "::nonce= " + nonce;
		}

		SSLSocketFactory factory = sslcontext.getSocketFactory();

		String urlParameters = "data=";
		try {
			urlParameters = urlParameters + URLEncoder.encode(data, "UTF-8") + "&signature="
					+ URLEncoder.encode(signature, "UTF-8");
		} catch (Exception e) {
			logMsg += "::Exception: " + e.getMessage() + " ::Program Start Time:" + startTime + "::nonce= " + nonce;
		}

		try {
			URL url;
			HttpsURLConnection connection;
			InputStream is = null;

			String ip = urlOfNsdl;
			url = new URL(ip);
			System.out.println("URL " + ip);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setSSLSocketFactory(factory);
			connection.setHostnameVerifier(new DummyHostnameVerifier());
			OutputStream os = connection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			osw.write(urlParameters);
			osw.flush();
			connectionStartTime = new Date();
			logMsg += "::Request Sent At: " + connectionStartTime;
			logMsg += "::Request Data: " + data;
			osw.close();
			is = connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String line = null;
			line = in.readLine();
			output = line; 
			System.out.println("Output: " + line);
			is.close();
			in.close();
		} catch (ConnectException e) {
			logMsg += "::Exception: " + e.getMessage() + "::Program Start Time:" + startTime + "::nonce= " + nonce;
		} catch (Exception e) {
			logMsg += "::Exception: " + e.getMessage() + "::Program Start Time:" + startTime + "::nonce= " + nonce;
		}
		log.info(logMsg);
		return output;
	}

}
