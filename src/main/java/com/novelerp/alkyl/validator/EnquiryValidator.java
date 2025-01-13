package com.novelerp.alkyl.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.dto.EnquiryDto;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;

@Component
public class EnquiryValidator implements Validator{

	@Autowired
	private ValidationUtil validationUtil;

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	
	public void validate(EnquiryDto enquiries,Errors errors){
		
		if(enquiries==null){
			validationUtil.reject(errors, "Empty Object", "Empty Form");
		}
		/*if(enquiries.getBidEndDate()==null || ("").equals(enquiries.getBidEndDate())){
			validationUtil.reject(errors, "Empty Bid End Date", "Bid End Date Cannot Be Empty");
			
		}*/
		
		
		
		
	}
	
	public void validateBidderList(EnquiryDto enquiries,Errors errors){
		
		if(!CommonUtil.isListEmpty(enquiries.getBidderList())){
			
			for(BidderDto bidder:enquiries.getBidderList()){
				
				if(bidder==null){
					validationUtil.reject(errors, "Empty Vendor Object", "Empty Vendor");
				}
				
				if(bidder.getPartner()==null){
					validationUtil.reject(errors, "Empty Vendor Object", "Empty Vendor");
				}
				
				if(bidder.getPr()==null){
					validationUtil.reject(errors, "Empty PR Object", "PR cannot be Empty");
				}
				
			}
			
		}else{
			validationUtil.reject(errors, "Empty Vendor Object", "Empty Vendor");
		}
		
	}
	
	
	public void validateItemBidList(EnquiryDto enquiries,Errors errors){
		
		if(!CommonUtil.isListEmpty(enquiries.getItemBidList())){
			
			for(ItemBidDto itemBid:enquiries.getItemBidList()){
				
				if(null!=itemBid){
				
				if(itemBid.getPrLine()==null){
					validationUtil.reject(errors, "Empty PRLine Object", "PRLine cannot be Empty");
				}
				}
			}
			
		}else{
			validationUtil.reject(errors, "Empty Material Object", "Empty Material");
		}
		
	}
	
	
	private void validateBidder(EnquiryDto enquiries, Errors errors) {
		// TODO Auto-generated method stub
		
		if(!CommonUtil.isListEmpty(enquiries.getBidderList())){
			
			for(BidderDto bidder:enquiries.getBidderList()){
				
				if(bidder==null){
					validationUtil.reject(errors, "Empty Vendor Object", "Empty Vendor");
				}
				
				if(bidder.getPartner()==null){
					validationUtil.reject(errors, "Empty Vendor Object", "Empty Vendor");
				}
				
			}
			
		}else{
			validationUtil.reject(errors, "Empty Vendor Object", "Empty Vendor");
		}
		
	}
	
	
	public void validateEnquiry(EnquiryDto enquiries,Errors errors){
		
		validate(enquiries, errors);
		
		validateBidderList(enquiries, errors);
		
		validateItemBidList(enquiries, errors);
		
	}
	
	public void validateUnAssignedPrLine(EnquiryDto enquiries,Errors errors){
		
		validate(enquiries, errors);
		
		validateBidder(enquiries, errors);
		
		validateItemBidList(enquiries, errors);
		
	}
	
}
