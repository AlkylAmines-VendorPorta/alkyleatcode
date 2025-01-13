package com.novelerp.alkyl.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.dto.SecurityPOHeaderDto;
import com.novelerp.alkyl.dto.SecurityPOItemDto;
import com.novelerp.alkyl.service.PurchaseOrderLineService;
import com.novelerp.alkyl.service.SecurityPOHeaderService;
import com.novelerp.alkyl.service.SecurityPOLineItemService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;

@Component
public class CommercialASNLineComponent {
	
	
	
	@Autowired SecurityPOLineItemService asnLineService;
	
	@Autowired
	private PurchaseOrderLineService poLineService;
	
	@Autowired SecurityPOHeaderService asnService;

	public List<SecurityPOItemDto> getASNLineForASNCreation(Long asnHeaderId) {
		List<SecurityPOItemDto> asnLineList = asnLineService.findDtos("getASNLinesByASNIdforSecurity",AbstractContextServiceImpl.getParamMap("asnHeaderId", asnHeaderId));
		SecurityPOHeaderDto asn = asnService.findDto("getASNByASNIdforCommercial",AbstractContextServiceImpl.getParamMap("asnHeaderId", asnHeaderId));
		
		List<PurchaseOrderLineDto> poLineList = poLineService.findDtos("getPOLineForASNLineCommercial",AbstractContextServiceImpl.getParamMap("poId", asn.getPo().getPurchaseOrderId()));
		
		if (!CommonUtil.isCollectionEmpty(poLineList)) {
			if (asnLineList == null) {
				asnLineList = new ArrayList<>();
			}
//			for (PurchaseOrderLineDto poLine : poLineList) {
//				SecurityPOItemDto asnLine = new SecurityPOItemDto();
//
//				asnLine.setPoLine(poLine);
//				asnLineList.add(asnLine);
//			}
		}

		return asnLineList;
	}
}
