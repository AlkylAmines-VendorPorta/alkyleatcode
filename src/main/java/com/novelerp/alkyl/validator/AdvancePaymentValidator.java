package com.novelerp.alkyl.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.dto.AdvancePaymentDto;
import com.novelerp.alkyl.dto.QuotationDto;
import com.novelerp.alkyl.service.AdvancePaymentService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PriceBidDto;

@Component
public class AdvancePaymentValidator  implements Validator {
	
	@Autowired
	private ValidationUtil validationUtil;
	
	@Autowired
	private AdvancePaymentService advanceService;

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	
public void validatePayment(List<AdvancePaymentDto> advance, Errors errors){
		
	if(null==advance){
		
		validationUtil.reject(errors, "Empty Payment ", "Please Select Payment.");
	}
	else if(advance.get(0).getInterestRate().equals("0")) {
		validationUtil.reject(errors, "Interest Rate Error", "Invoice is not displayed please contact AACL Team");
	}
	
	else {
	for(AdvancePaymentDto payment:advance) {
		
					Map<String, Object> params = new HashMap<String, Object>(); 
					params.put("docNumber", payment.getDocumentNumber());
					List<AdvancePaymentDto> payList=advanceService.findDtos("getpaymentbydocNumber",params);
					
					if(payList.size()>0) {
						
					if("Y".equals(payList.get(0).getIsMailSent()) && !"REJECTED".equals(payList.get(0).getStatus())){
						validationUtil.reject(errors, "Mail Already Sent", "Mail Already sent for document: "+payList.get(0).getDocumentNumber());
					}
					}
					
					
	               }	
	

					
		}
}



public void validateApprovalPayment(List<AdvancePaymentDto> advance, Errors errors){
	
	if(null==advance){
		
		validationUtil.reject(errors, "Empty Payment ", "Please Select Payment for Approval.");
	}
	
	else {
		ArrayList<Date> nextPaymentList = new ArrayList<Date>();
		ArrayList<String> vendorList = new ArrayList<String>();
	for(AdvancePaymentDto payment:advance) {
		

        
        String vendorCode=payment.getVendorCode();
        
        if(!vendorList.contains(vendorCode)) {
        	vendorList.add(vendorCode); }
		
		
		Date nextPaymentDate=payment.getNextPaymentDate();	
		
        if(!nextPaymentList.contains(nextPaymentDate)) {
        	nextPaymentList.add(nextPaymentDate); }
        
        
                   }
	
	 if(vendorList.size()>1) {
	   		validationUtil.reject(errors, "Multiple Vendor Error", "Please Select Payment for single Vendor");
	   	 
	     }
	
	           if(nextPaymentList.size()>1) {
	   		validationUtil.reject(errors, "Next Payment Date Error", "Please Select Payment for single payment date");
	   	 
	     }
	

					
		}
}
		
		
	}



