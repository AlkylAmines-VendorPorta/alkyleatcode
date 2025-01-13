package com.novelerp.core.util;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendSMS implements Runnable {
	private static Logger log = LoggerFactory.getLogger(SendSMS.class);
	private String requestURL;
	private boolean isSetProxy;

	@Override
	public void run() {
		triggredSMS();
	}
	public SendSMS(String requestURL, boolean isSetProxy) {
		this.requestURL = requestURL;
		this.isSetProxy = isSetProxy;
	}
	Boolean triggredSMS() {
		
		
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.3.111", 8080));

		boolean isSMSSent = false;
		try {
			URL url = new URL(requestURL.toString());
			HttpURLConnection uc ;
			if(isSetProxy){
				uc = (HttpURLConnection) url.openConnection(proxy);
			}else{
				uc = (HttpURLConnection) url.openConnection();
			}
			log.info(uc.getResponseMessage());
			uc.disconnect();
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return isSMSSent;
	}
}
