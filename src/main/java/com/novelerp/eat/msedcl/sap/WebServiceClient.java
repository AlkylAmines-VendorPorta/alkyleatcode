/*package com.novelerp.eat.msedcl.sap;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.eat.dto.SapVendorDetailDto;

@Component
public class WebServiceClient {

	private static final Logger log = LoggerFactory.getLogger(WebServiceClient.class);

	@Autowired
	@Qualifier("vendorCreationWebServiceTemplate")
	private WebServiceTemplate webServiceTemplate;
	@Autowired
	private BPartnerService bPartnerService;
	@Autowired
	private ObjectFactory objectFactory;
	
	@Autowired
	private SystemConfiguratorService systemConfiguratorService;
		
	public ZVENDORCREATEResponse createVendor(SapVendorDetailDto sapVendorDto) {
		ZVENDORCREATEResponse zvendorcreateResponse = null;
		try {
			//configurWebServiceTemplate(webServiceTemplate);
			ZVENDORCREATE zvendorcreate = objectFactory.createZVENDORCREATE0718();
			
			List<Object[]> partnerDetails = bPartnerService.getPartnerBankAndLocDetail(sapVendorDto.getPartner().getbPartnerId());
			Object[] partnerDetail =  partnerDetails.iterator().next(); 
			
			zvendorcreate.setAKONT(sapVendorDto.getReconAccount().getValue());
			zvendorcreate.setBANKA(getStringValue(partnerDetail[3]));
			zvendorcreate.setBANKL01(getStringValue(partnerDetail[5]));
			zvendorcreate.setBANKN01(getStringValue(partnerDetail[6]));
			zvendorcreate.setBRNCH(getStringValue(partnerDetail[4]));
			zvendorcreate.setJ1IPANNO(getStringValue(partnerDetail[1]));
			zvendorcreate.setKOINH01(getStringValue(partnerDetail[7]));
			zvendorcreate.setKTOKK(getStringValue(sapVendorDto.getVendorAccountGroup().getAccountGrp().toString()));
			zvendorcreate.setLAND1("IN");
			zvendorcreate.setNAME1(getStringValue(partnerDetail[7]));
			zvendorcreate.setORT01(getStringValue(partnerDetail[10]));
			zvendorcreate.setORT02(getStringValue(partnerDetail[11]));
			zvendorcreate.setPROVZ(getStringValue(partnerDetail[15]));
			zvendorcreate.setPSTLZ(getStringValue(partnerDetail[12]));
			zvendorcreate.setREGIO(getStringValue(partnerDetail[15]));
			zvendorcreate.setSTCD1("");
			zvendorcreate.setSTCD3(getStringValue(partnerDetail[2]));
			String address = null;
			if(getStringValue(partnerDetail[9]).length() > 30){
				address = getStringValue(partnerDetail[9]).substring(0, 30);
			}else{
				address = getStringValue(partnerDetail[9]);
			}
			zvendorcreate.setSTRAS(address);
			zvendorcreate.setTELF1(getStringValue(partnerDetail[13]));
			zvendorcreate.setTELF2("1234");
			zvendorcreate.setTELX1(getStringValue(partnerDetail[13]));
			zvendorcreate.setVENCLASS(sapVendorDto.getGstVendorClass().getCode());
			zvendorcreate.setWITHT02(sapVendorDto.getIdctrWithtTaxType().getWithholdingTaxCode());
			zvendorcreate.setWTWITHCD01(sapVendorDto.getWithHdTaxCode1().getWithholdingTaxCode());
			zvendorcreate.setWTWITHCD02(sapVendorDto.getWithHdTaxCode2().getWithholdingTaxCode());
			zvendorcreate.setAKONT("10902002");
			zvendorcreate.setBANKA("State bank of India");
			zvendorcreate.setBANKL01("SBIN0011424");
			zvendorcreate.setBANKN01("3141022688");
			zvendorcreate.setBRNCH("NAGBHID");
			zvendorcreate.setJ1IPANNO("BCTPG4937P");
			zvendorcreate.setKOINH01("Vinod Kadam");
			zvendorcreate.setKTOKK("OTFI");
			zvendorcreate.setLAND1("IN");
			zvendorcreate.setNAME1("Vinod Kadam");
			zvendorcreate.setORT01("Kolhapur");
			zvendorcreate.setORT02("Kolhapur");
			zvendorcreate.setPROVZ("12");
			zvendorcreate.setPSTLZ("400051");
			zvendorcreate.setREGIO("13");
			zvendorcreate.setSTCD1("");
			zvendorcreate.setSTCD3("27BCTPG4937P2Z5");
			zvendorcreate.setSTRAS("Unit 44 Nav Ketan Estate");
			zvendorcreate.setTELF1("9860659100");
			zvendorcreate.setTELF2("1234");
			zvendorcreate.setTELX1("12345");
			zvendorcreate.setVENCLASS("2");
			zvendorcreate.setWITHT02("I1");
			zvendorcreate.setWTWITHCD01("I1");
			zvendorcreate.setWTWITHCD02("I1");
			zvendorcreateResponse = (ZVENDORCREATEResponse) webServiceTemplate.marshalSendAndReceive(zvendorcreate);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return zvendorcreateResponse;
	}
	private String getStringValue(Object obj){
		if(obj == null){
			return "";
		}else{
			return String.valueOf(obj);
		}
	}
	
	public void configurWebServiceTemplate(WebServiceTemplate webServiceTemplate){
		String uri=systemConfiguratorService.getPropertyConfigurator(AppBaseConstant.EAT_SAP_SERVICE_VENDOR_POSTING);
		String user_name=systemConfiguratorService.getPropertyConfigurator(AppBaseConstant.EAT_SAP_SERVICE_USER_NAME);
		String password=systemConfiguratorService.getPropertyConfigurator(AppBaseConstant.EAT_SAP_SERVICE_PASSWORD);
		webServiceTemplate.setDefaultUri(uri);
		webServiceTemplate.setMessageSender(SAPServiceConfig.getHttpComponentsMessageSender(user_name, password));
	}
	
}
*/