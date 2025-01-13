/*package com.novelerp.eat.msedcl.createContract;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.novelerp.core.util.DateUtil;
import com.novelerp.eat.dto.ContractConditionDto;
import com.novelerp.eat.dto.ContractHeaderDto;
import com.novelerp.eat.dto.ContractItemDto;
import com.novelerp.eat.dto.ContractServiceDto;
import com.novelerp.eat.msedcl.paymentposting.PaymentPostingClient;

@Component
public class CreateContractClient {

	private static final Logger log = LoggerFactory.getLogger(PaymentPostingClient.class);

	@Autowired
	@Qualifier("contractCreationWebServiceTemplate")
	private WebServiceTemplate webServiceTemplate;

	ObjectFactory objectFactory = new ObjectFactory();

	public ZMMCREATECONTRACTResponse createContract(CreateContractDto createContractDto) {

		ZMMCREATECONTRACTResponse zmmcreatecontractResponse = null;
		ZMMCREATECONTRACT zmmcreatecontract = objectFactory.createZMMCREATECONTRACT();

		zmmcreatecontract.setGSCONTRACTHEADER(getHeader(createContractDto.getZmmcontractheader()));
		zmmcreatecontract.setCONTRACTITEM(getItemList(createContractDto.getContractItemList()));
		zmmcreatecontract.setCONTRACTCONDITION(getContratctConditionList(createContractDto.getContractItemCondList()));
		if("ZVAL".equalsIgnoreCase(createContractDto.getZmmcontractheader().getAgrType())){
			zmmcreatecontract.setVALCONTRACTSERVICE(getServiceItemList(createContractDto.getContractItemserviceList()));
		}
		zmmcreatecontractResponse = (ZMMCREATECONTRACTResponse) webServiceTemplate
				.marshalSendAndReceive(zmmcreatecontract);
		return zmmcreatecontractResponse;
	}

	private ZMMCONTRACTHEADER getHeader(ContractHeaderDto contractHeaderDto) {
		ZMMCONTRACTHEADER zmmcontractheader = objectFactory.createZMMCONTRACTHEADER();
		zmmcontractheader.setLIFNR(contractHeaderDto.getBidder().getPartner().getVendorSapCode());
		zmmcontractheader.setEVART(contractHeaderDto.getAgrType());
		zmmcontractheader.setEKORG(contractHeaderDto.getPurOrg());
		zmmcontractheader.setEKGRP(contractHeaderDto.getPurGrp());
		zmmcontractheader.setWERKS(contractHeaderDto.getPlant());
		zmmcontractheader.setLGORT(contractHeaderDto.getStorageLoc());
		zmmcontractheader.setKNTTP(contractHeaderDto.getAccAssCateg() );
		zmmcontractheader.setKDATB(DateUtil.covertDateFormat(contractHeaderDto.getValStartDate(), "dd-MM-yyyy", "dd.MM.yyyy") );
		zmmcontractheader.setKDATE(DateUtil.covertDateFormat(contractHeaderDto.getValEndDate(), "dd-MM-yyyy", "dd.MM.yyyy"));
		zmmcontractheader.setKTWRT(contractHeaderDto.getTargValue());
		zmmcontractheader.setZZBGZSECDEP(contractHeaderDto.getSecurityDep());
		zmmcontractheader.setZZBGZPFMDEP(contractHeaderDto.getPerformanceDep());
		return zmmcontractheader;
	}

	private TABLEOFZMMCONTRACTITEM getItemList(List<ContractItemDto> itemList) {
		TABLEOFZMMCONTRACTITEM tableofzmmcontractitem = objectFactory.createTABLEOFZMMCONTRACTITEM();
		for (ContractItemDto contractItemDto : itemList) {
			ZMMCONTRACTITEM zmmcontractitem = new ZMMCONTRACTITEM();
			zmmcontractitem.setEBELP(contractItemDto.getItem());
			zmmcontractitem.setTXZ01(contractItemDto.getShortTexr());
			zmmcontractitem.setMWSKZ(contractItemDto.getTaxCode());
			zmmcontractitem.setMATKL(contractItemDto.getMaterialGroup());
			
			zmmcontractitem.setBWTAR("");
			zmmcontractitem.setEMATN("");
			zmmcontractitem.setKTMNG(new BigDecimal(0));
			zmmcontractitem.setNETPR(new BigDecimal(0));
			
			tableofzmmcontractitem.getItem().add(zmmcontractitem);
		}
		return tableofzmmcontractitem;
	}

	private TABLEOFZMMCONTRACTSERVICE getServiceItemList(List<ContractServiceDto> serviceList) {
		TABLEOFZMMCONTRACTSERVICE tableofzmmcontractservice = objectFactory.createTABLEOFZMMCONTRACTSERVICE();
		for (ContractServiceDto contractServiceDto : serviceList) {
			ZMMCONTRACTSERVICE zmmcontractservice = new ZMMCONTRACTSERVICE();
			zmmcontractservice.setEBELP(contractServiceDto.getMaterialNo());
			zmmcontractservice.setSEREBELP(contractServiceDto.getSrvcLineItemNo());
			zmmcontractservice.setSRVPOS(contractServiceDto.getServiceNo());
			zmmcontractservice.setMENGE(contractServiceDto.getQuantity());
			zmmcontractservice.setTBTWR(contractServiceDto.getAmount());
			zmmcontractservice.setKOSTL(contractServiceDto.getCostCenter());
			tableofzmmcontractservice.getItem().add(zmmcontractservice);
		}
		return tableofzmmcontractservice;
	}

	private TABLEOFZMMCONTRACTITEMCOND getContratctConditionList(List<ContractConditionDto> contractConditionList) {
		TABLEOFZMMCONTRACTITEMCOND tableofzmmcontractitemcond = objectFactory.createTABLEOFZMMCONTRACTITEMCOND();
		for (ContractConditionDto contractConditionDto : contractConditionList) {
			ZMMCONTRACTITEMCOND zmmcontractitemcond = new ZMMCONTRACTITEMCOND();
			zmmcontractitemcond.setEBELP(contractConditionDto.getSrvcLineItemNo());
			zmmcontractitemcond.setSEREBELP(contractConditionDto.getSrvcLineItem());
			zmmcontractitemcond.setKSCHL(contractConditionDto.getConditionType());
			zmmcontractitemcond.setKBETR(contractConditionDto.getAmount());
			tableofzmmcontractitemcond.getItem().add(zmmcontractitemcond);
		}
		return tableofzmmcontractitemcond;
	}

}
*/