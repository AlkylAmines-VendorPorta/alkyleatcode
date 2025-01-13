package com.novelerp.alkyl.validator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
import com.novelerp.eat.dto.WinnerSelectionDto;

@Component
public class AnnexureValidator implements Validator{
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ValidationUtil validationUtil;

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	
	public void validateReject(Long annexureId, String role, Errors errors) {
		if(null==annexureId){
			log.info("ERROR","Empty Annexure Id", "Please try again later.");
			validationUtil.reject(errors, "Empty Annexure Id", "Please try again later.");
		}
		
		if(!AppBaseConstant.ROLE_PURCHASE_MANAGER_ADMIN.equals(role)){
			log.info("ERROR","Unauthenticated User!", "Only Purchase Manager can Reject Annexure.");
			validationUtil.reject(errors, "Unauthenticated User!", "Only Purchase Manager can Reject Annexure.");
		}
	}

	public void validateApprove(List<WinnerSelectionDto> winnerSelectionList, String role, Errors errors) {
		// TODO Auto-generated method stub
		if(null==winnerSelectionList){
			log.info("ERROR","Empty Annexure Id", "Please try again later.");
			validationUtil.reject(errors, "Empty Annexure Id", "Please try again later.");
		}
		
		if(!AppBaseConstant.ROLE_PURCHASE_MANAGER_ADMIN.equals(role)){
			log.info("ERROR","Unauthenticated User!", "Only Purchase Manager can Reject Annexure.");
			validationUtil.reject(errors, "Unauthenticated User!", "Only Purchase Manager can Reject Annexure.");
		}
		
		validateWinnerSelection(winnerSelectionList,errors);
		
	}
	
	
	public void validateWinnerSelection(List<WinnerSelectionDto> winnerSelectionList,Errors errors){
		try {
			
			/*List<WinnerSelectionDto> winnerSelectionList=winnerSelectionService.findDtos("getWinnerSelectionByAnnexureId", AbstractContextServiceImpl.getParamMap("annexureId", annexureId));*/
			
			for(WinnerSelectionDto dto:winnerSelectionList){
				
				BPartnerDto partnerDto=dto.getItemBid().getBidder().getPartner();
				
				if(CommonUtil.isStringEmpty(partnerDto.getVendorSapCode())){
					validationUtil.reject(errors, "Vendor Code Not Found", "Vendor Code Not Found "+partnerDto.getbPartnerId()+" - "+partnerDto.getName());
				}
				
			}
			
		} catch (Exception e) {
			log.info("ERROR",e.getMessage());
			validationUtil.reject(errors, "Error", e.getMessage());
		}
			
	}
}
