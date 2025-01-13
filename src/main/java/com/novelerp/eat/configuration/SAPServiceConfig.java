package com.novelerp.eat.configuration;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Configuration
public class SAPServiceConfig extends WsConfigurerAdapter {

	/*@Autowired
	private AppPropertyUtil propertyUtil;
	
	@Autowired
	private SystemConfiguratorService sysConfigService;*/

	private String documentPostingUri ="http://sapeccmock.mahadiscom.in:8000/sap/bc/srt/rfc/sap/zfi_e_tender/800/zfi_e_tender/zfi_e_tender";//propertyUtil.getProperty("eat.sap.service.documentPostingUri");
	private String vendorCreationUri = "http://SAPDEV:8000/sap/bc/srt/rfc/sap/zvendor_portal/009/zvendor_portal/zvendorportal";
	private String contractCreationUri = "http://sapeccmock.mahadiscom.in:8000/sap/bc/srt/rfc/sap/zmm_create_contract/800/zmm_create_contract/zmm_create_contract";//propertyUtil.getProperty("eat.sap.service.vendorCreationUri");
	private String userName = "basis";//propertyUtil.getProperty("eat.sap.service.username");
	private String userPassword = "basis1917";//propertyUtil.getProperty("eat.sap.service.password");

	
	/*private String documentPostingUri =sysConfigService.getPropertyConfigurator(AppBaseConstant.EAT_SAP_SERVICE_PAYMENT_POSTING);
	private String vendorCreationUri = sysConfigService.getPropertyConfigurator(AppBaseConstant.EAT_SAP_SERVICE_VENDOR_POSTING);
	private String contractCreationUri = sysConfigService.getPropertyConfigurator(AppBaseConstant.EAT_SAP_SERVICE_CONTRACT_POSTING);
	private String userName = sysConfigService.getPropertyConfigurator(AppBaseConstant.EAT_SAP_SERVICE_USER_NAME);
	private String userPassword = sysConfigService.getPropertyConfigurator(AppBaseConstant.EAT_SAP_SERVICE_PASSWORD);*/
	
	@Bean
	Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath("com.novelerp.eat.msedcl.paymentposting");
		return jaxb2Marshaller;
	}
	@Bean
	Jaxb2Marshaller vcjaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath("com.sap.document.sap.rfc.functions");
		return jaxb2Marshaller;
	}
	@Bean
	Jaxb2Marshaller contractjaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath("com.novelerp.eat.msedcl.createContract");
		return jaxb2Marshaller;
	}
	
	
	@Bean(name = "documentPostingWebServiceTemplate")
	public WebServiceTemplate webServiceTemplate() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setDefaultUri(documentPostingUri);
	    webServiceTemplate.setMessageSender(httpComponentsMessageSender());
		return webServiceTemplate;
	}
	@Bean(name = "vendorCreationWebServiceTemplate")
	public WebServiceTemplate vcWebServiceTemplate() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(vcjaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(vcjaxb2Marshaller());
		webServiceTemplate.setDefaultUri(vendorCreationUri);
		 // set a HttpComponentsMessageSender which provides support for basic authentication
	    webServiceTemplate.setMessageSender(httpComponentsMessageSender());
		return webServiceTemplate;
	}
	@Bean(name = "contractCreationWebServiceTemplate")
	public WebServiceTemplate ccWebServiceTemplate() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(contractjaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(contractjaxb2Marshaller());
		webServiceTemplate.setDefaultUri(contractCreationUri);
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

	/*public static HttpComponentsMessageSender getHttpComponentsMessageSender(String userName,String password) {
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
		// set the basic authorization credentials
		httpComponentsMessageSender.setCredentials(usernamePasswordCredentials(userName,password));
		return httpComponentsMessageSender;
	}
	
	private static UsernamePasswordCredentials usernamePasswordCredentials(String user_name,String password) {
		// pass the user name and password to be used
		return new UsernamePasswordCredentials(user_name, password);
	}*/
	
}

