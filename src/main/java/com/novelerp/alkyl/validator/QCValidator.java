package com.novelerp.alkyl.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

@Component
public class QCValidator implements Validator{ 

	@Autowired
	private ValidationUtil validationUtil;
	
	
	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	

	public void validateQCDetails( AdvanceShipmentNoticeDto asn, Errors errors){
	
			int count = 0;
			int list = 0;
			
		for(AdvanceShipmentNoticeLineDto asnLine : asn.getAsnLineList()){
			
				if(asnLine.getDeliveryQuantity().equals(asnLine.getRejectedQuantity())){
					count++;
				}
				list++;
		}
		
		if(count==list){
				validationUtil.reject(errors, "QCFailed", "QC cantnot Be Pass Please Click on QC Failed Button");
				}
		
	}
	
}
