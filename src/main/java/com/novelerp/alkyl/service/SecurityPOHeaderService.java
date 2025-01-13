package com.novelerp.alkyl.service;


import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.SecurityPOHeaderDto;
import com.novelerp.alkyl.entity.SecurityPOHeader;
import com.novelerp.appbase.master.dto.PartnerItemDrawingDocDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;

public interface SecurityPOHeaderService extends CommonService<SecurityPOHeader, SecurityPOHeaderDto>{

	Integer getNewASNNumber(String plant);

//	ResponseDto insertinheaderdto(AdvanceShipmentNoticeDto dto);

	//ResponseDto insertinheaderdto(SecurityPOHeaderDto dto);


	 

	



	

}
