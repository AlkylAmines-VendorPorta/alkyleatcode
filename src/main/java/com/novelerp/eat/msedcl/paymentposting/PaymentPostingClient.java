package com.novelerp.eat.msedcl.paymentposting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.novelerp.appcontext.service.SystemConfiguratorService;

@Component
public class PaymentPostingClient {

	private static final Logger log = LoggerFactory.getLogger(PaymentPostingClient.class);

	@Autowired
	@Qualifier("documentPostingWebServiceTemplate")
	private WebServiceTemplate webServiceTemplate;

	@Autowired
	private SystemConfiguratorService sysConfigService;
	
	public ZFIETENDERResponse postPayment(ZFIETENDER zfietender) {
		ZFIETENDERResponse zfietenderResponse = null;
		try {
			/*ObjectFactory objectFactory = new ObjectFactory();*/
			/*configurWebServiceTemplate(webServiceTemplate);*/
			zfietenderResponse = (ZFIETENDERResponse) webServiceTemplate.marshalSendAndReceive(zfietender);
			/*ZFIETENDER zfietender = objectFactory.createZFIETENDER();
			zfietender.setBKTXT("491224");
			zfietender.setBLDAT("23.08.2018");
			zfietender.setBUDAT("23.08.2018");
			zfietender.setCHARGES(new BigDecimal("3.07"));
			zfietender.setGATEWYOWNR("100005346");
			zfietender.setPAYTYPE("11");
			zfietender.setPRCTR("9950");
			zfietender.setSGTXT("1000101");
			zfietender.setVENDOR("100000949");
			zfietender.setWRBTR(new BigDecimal("2950"));
			zfietender.setXBLNR("491224");*/
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return zfietenderResponse;
	}
	
	/*public void configurWebServiceTemplate(WebServiceTemplate webServiceTemplate){
		String uri=sysConfigService.getPropertyConfigurator(AppBaseConstant.EAT_SAP_SERVICE_PAYMENT_POSTING);
		String user_name=sysConfigService.getPropertyConfigurator(AppBaseConstant.EAT_SAP_SERVICE_USER_NAME);
		String password=sysConfigService.getPropertyConfigurator(AppBaseConstant.EAT_SAP_SERVICE_PASSWORD);
		webServiceTemplate.setDefaultUri(uri);
		webServiceTemplate.setMessageSender(SAPServiceConfig.getHttpComponentsMessageSender(user_name, password));
	}*/
	
}
