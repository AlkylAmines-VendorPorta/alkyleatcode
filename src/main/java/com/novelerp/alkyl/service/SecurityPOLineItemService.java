package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.SecurityPOHeaderDto;
import com.novelerp.alkyl.dto.SecurityPOItemDto;
import com.novelerp.alkyl.entity.AdvanceShipmentNoticeLine;
import com.novelerp.alkyl.entity.SecurityPOItem;

import com.novelerp.core.service.CommonService;

public interface SecurityPOLineItemService extends CommonService<SecurityPOItem, SecurityPOItemDto>{

//	List<SecurityPOItemDto> save(List<SecurityPOItemDto> asnLines, SecurityPOItemDto asn);

	public List<SecurityPOItemDto> save(List<SecurityPOItemDto> asnLines, SecurityPOHeaderDto asn);
//	public SecurityPOHeaderDto save(SecurityPOHeaderDto dto);

	

}
