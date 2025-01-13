/*package com.novelerp.eat.msedcl.paymentposting;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Configuration
public class PaymentPostingClientConfig extends WsConfigurerAdapter {

	
	private String documentPostingUri = "http://sapeccmock.mahadiscom.in:8000/sap/bc/srt/rfc/sap/zfi_etender/800/zfi_etender/zfi_etender";
	private String vendorCreationUri = "http://sapeccmock.mahadiscom.in:8000/sap/bc/srt/rfc/sap/zvendor_create_0718/800/zvendor_create_0718/zvendor_create_0718";

	private String userName = "WEBSERVICE";
	private String userPassword = "123456";
	
	@Bean
	Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath("com.novelerp.eat.msedcl.paymentposting");
		return jaxb2Marshaller;
	}
	@Bean
	Jaxb2Marshaller vcjaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath("com.novelerp.eat.msedcl.sap");
		return jaxb2Marshaller;
	}
	
	@Bean(name = "webServiceTemplate")
	public WebServiceTemplate webServiceTemplate() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setDefaultUri(documentPostingUri);
	    webServiceTemplate.setMessageSender(httpComponentsMessageSender());
		return webServiceTemplate;
	}
	@Bean(name = "vcWebServiceTemplate")
	public WebServiceTemplate vcWebServiceTemplate() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(vcjaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(vcjaxb2Marshaller());
		webServiceTemplate.setDefaultUri(vendorCreationUri);
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