package com.novelerp.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AppPropertyUtil {

	private static Logger log = LoggerFactory.getLogger(AppPropertyUtil.class); 
	private Properties property = null;

	@PostConstruct
	public void init(){
		read();
	}
	
	public synchronized void read(){
		property = new Properties();
		InputStream input = null;
		try {
			input = AppPropertyUtil.class.getClassLoader().getResourceAsStream("app.properties");
			property.load(input);
		} catch (IOException e) {
			log.error("Exception", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.error("Exception", e);
				}
			}
		}
	}
	
	public String getProperty(String key){
		return property.getProperty(key);
	}

	public static void main(String[] args) {
		new AppPropertyUtil().read();
	}
	
	public String getAppTempDir(){
		if(OSValidator.isWindows()){
			return getProperty("eat.tmp.location.windows");
		}
		return getProperty("eat.tmp.location.linux");
	}
	
	public String getAppDocDir(){
		if(OSValidator.isWindows()){
			return getProperty("eat.file.location.windows");
		}
		return getProperty("eat.file.location.linux");
	}
}
