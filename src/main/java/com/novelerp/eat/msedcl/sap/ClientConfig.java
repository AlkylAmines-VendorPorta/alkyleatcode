/*package com.novelerp.eat.msedcl.sap;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Configuration
public class ClientConfig extends WsConfigurerAdapter {

	
	private String defaultUri = "http://sapeccmock.mahadiscom.in:8000/sap/bc/srt/rfc/sap/zvendor_create_0718/800/zvendor_create_0718/zvendor_create_0718";
	//private String defaultUri = "http://sapeccdev.mahadiscom.in:8000/sap/bc/srt/wsdl/flv_10002A111AD1/srvc_url/sap/bc/srt/rfc/sap/zvendor_create/200/zvendor_create/zvendor_create?sap-client=200";
	//	private String userName = propertyUtil.getProperty("sap.service.username");
//	private String userPassword = propertyUtil.getProperty("sap.service.password");

	private String userName = "WEBSERVICE";
	private String userPassword = "123456";
	
	@Bean
	Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath("com.novelerp.eat.msedcl.sap");
		return jaxb2Marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setDefaultUri(defaultUri);
		 // set a HttpComponentsMessageSender which provides support for basic authentication
	    webServiceTemplate.setMessageSender(httpComponentsMessageSender());

		return webServiceTemplate;
	}

	@Bean
	public HttpComponentsMessageSender httpComponentsMessageSender() {
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
		// set the basic authorization credentials
		httpComponentsMessageSender.setCredentials(usernamePasswordCredentials());

		return httpComponentsMessageSender;
	}

	@Bean
	public UsernamePasswordCredentials usernamePasswordCredentials() {
		// pass the user name and password to be used
		return new UsernamePasswordCredentials(userName, userPassword);
	}

}
*/