package com.novelerp.alkyl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.service.PurchaseOrderLineService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Component
public class POLineComponent {
	
	@Autowired
	private PurchaseOrderLineService poLineService;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public PurchaseOrderLineDto getPOLineByPOLineId(Long poLineId){
		return poLineService.findDto("getPOLineByPOLineId", AbstractContextServiceImpl.getParamMap("poLineId", poLineId));
	}


}
