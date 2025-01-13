package com.novelerp.process.util;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessParamBuilder {

	private static final Logger log =  LoggerFactory.getLogger(ProcessParamBuilder.class);
	
	public static Map<String, Object> paramBuilder(Object object){
	
		Map<String, Object> params=  new HashMap<>();
		PropertyDescriptor [] descriptors = PropertyUtils.getPropertyDescriptors(object);  
		for (PropertyDescriptor property : descriptors) {
			try {
				String name = property.getName();
				if (!"owner".equals(name) && property.getWriteMethod() != null) {
					Object value = PropertyUtils.getProperty(object, name);
					params.put(name, value);
				}
			}catch (Exception e) {
				log.error("Error while reading object properties", e);
			}

		}
		return params;
	}
}
