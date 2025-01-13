package com.novelerp.alkyl.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.alkyl.dto.PRLineFilterDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

@Component
public class PRValidator implements Validator{

	@Autowired
	private ValidationUtil validationUtil;

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	
	public void validate(PRDto pr,Errors errors){
		
		if(pr==null){
			validationUtil.reject(errors, "Empty Object", "Empty Form");
		}
		
		if(pr.getPriority()==null || pr.getPriority().equals("") || pr.getPriority().equals("NULL")) {
			validationUtil.reject(errors, "Empty Priority", "Please Select Priority");
		}
		
		if(!CommonUtil.isListEmpty(pr.getPrLines())){
		
			for(PRLineDto prLine:pr.getPrLines()){
				if(prLine.getDeliverDate()==null){
					validationUtil.reject(errors, "Mandatory", "Delivery Date cannot be null");
				}
				/*if(prLine.getBuyer()==null){
					validationUtil.reject(errors, "Mandatory", "Buyer Missing on Line Number :"+prLine.getPrLineNumber()+" ");
				}*/
			}
			
		}else{
			validationUtil.reject(errors, "Empty PRLine", "PRLine cannot be Empty");
		}
		
	}
	
	public void validateTC(PRDto prDto,Errors errors){
		if("Y".equals(prDto.getIsTC()) && prDto.getTcApprover()==null){
			validationUtil.reject(errors, "Empty Buyer", "Technical Approver not found.");
		}
	}
	
	public void validateBuyer(PRDto prDto,Errors errors){
		if(prDto.getBuyer()==null){
			validationUtil.reject(errors, "Empty Buyer", "Please choose Buyer to Proceed.");
		}
	}

	public void validateSubmit(PRDto prDto, Errors errors) {
		validate(prDto,errors);
		
		validateTC(prDto, errors);
	}

	public void validateApprove(PRDto prDto, Errors errors) {
		validate(prDto,errors);	
		
		validateTC(prDto, errors);
	}

	public void validateReject(Long prId, String role, Errors errors) {
		if(null==prId){
			validationUtil.reject(errors, "Empty Pr Id", "Please try again later.");
		}
		
		if(!AppBaseConstant.ROLE_APPROVER_ADMIN.equals(role)){
			validationUtil.reject(errors, "Unauthenticated User!", "Only Approver can Reject PR.");
		}
	}
	
	public void validateBuyerAssign(PRDto prDto, Errors errors) {
		validate(prDto,errors);
		
		/*validateBuyer(prDto, errors);*/
	}
public void validate(List<PRLineDto> prLineList,Errors errors){
		
		if(prLineList==null){
			validationUtil.reject(errors, "Empty Object", "Empty Form");
		}
		
		if(!CommonUtil.isListEmpty(prLineList)){
		
			for(PRLineDto prLine:prLineList){
				if(prLine.getDeliverDate()==null){
					validationUtil.reject(errors, "Mandatory", "Delivery Date cannot be null");
				}
				/*if(prLine.getRequiredDate()==null){
					validationUtil.reject(errors, "Mandatory", "Required Date cannot be null");
				}*/
				if(prLine.getBuyerId()==null){
					validationUtil.reject(errors, "Mandatory", "Buyer Missing on Line Number :"+prLine.getPrLineNumber()+" ");
				}
			}
			
		}else{
			validationUtil.reject(errors, "Empty PRLine", "PRLine cannot be Empty");
		}
		
	}

public void validateprPlant(PRLineFilterDto prLineDto,Errors errors){
	
	if(prLineDto==null){
		validationUtil.reject(errors, "Empty Object", "Empty Form");
	}
	
	if(prLineDto.getPlant()==null || prLineDto.getPlant().equals("")){
		validationUtil.reject(errors, "Empty Plant", "Please Select Plant");
	}
	
	if(prLineDto.getPlant()!=null && (prLineDto.getPurchaseGroupFrom()==null && prLineDto.getPrNoFrom()==null)){
		validationUtil.reject(errors, "Empty Plant", "Please Select One of above field");
	}
	
}
}
