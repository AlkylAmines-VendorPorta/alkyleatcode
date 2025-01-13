package com.novelerp.eat.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.errorconstant.TahdrPreparationErrorConstant;
import com.novelerp.eat.service.TAHDRService;

@Component
public class TAHDRValidator implements Validator{

	@Autowired
	private ValidationUtil validatorUtil;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(TAHDRDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}

		TAHDRDto tahdrDto =  (TAHDRDto) object;
		
		String documentType=null;
		
		if(!CommonUtil.isStringEmpty(tahdrDto.getTahdrTypeCode())){
		if(tahdrDto.getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_WORKS)
			|| tahdrDto.getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT))
		{
			documentType=ContextConstant.DOCUMENT_TYPE_TENDER;
		}
		else if(tahdrDto.getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.AUCTION_TYPE_CODE_FORWORD)){
			documentType=ContextConstant.DOCUMENT_TYPE_AUCTION;
		}
		}
		
		validatorUtil.rejectIfEmpty(tahdrDto.getTahdrCode(), errors, "invalid.TenderCode", documentType+TahdrPreparationErrorConstant.MANDATORY_TAHDR_CODE);
		validatorUtil.rejectIfEmpty(tahdrDto.getTitle(), errors, "invalid.TenderTitle", documentType+TahdrPreparationErrorConstant.MANDATORY_TAHDR_TITLE);
		validatorUtil.rejectIfEmpty(tahdrDto.getTahdrTypeCode(), errors, "invalid.TenderTypeCode", documentType+TahdrPreparationErrorConstant.MANDATORY_TAHDR_TYPE_CODE);
	
		if(tahdrDto.getDepartment().getDepartmentId()==null)
		{
			validatorUtil.rejectIfEmpty(null, errors, "invalid.Department", TahdrPreparationErrorConstant.MANDATORY_DEPARTMENT);
		}
		
		if(CommonUtil.isStringEmpty(tahdrDto.getBidTypeCode()))
		{
			validatorUtil.rejectIfEmpty(null, errors, "invalid.BidType", TahdrPreparationErrorConstant.MANDATORY_BID_TYPE);
		}
		
		if(CommonUtil.isStringEmpty(tahdrDto.getBudgetType()))
		{
			validatorUtil.rejectIfEmpty(null, errors, "invalid.BudgetType", TahdrPreparationErrorConstant.MANDATORY_BUDGET_TYPE);
		}
		
		if(!CommonUtil.isStringEmpty(tahdrDto.getBudgetType())){
		if(tahdrDto.getBudgetType().equalsIgnoreCase(AppBaseConstant.BUDGET_TYPE_CAPEX))
		{
			validatorUtil.rejectIfEmpty(tahdrDto.getSchemeCode(), errors, "invalid.SchemeCode", TahdrPreparationErrorConstant.MANDATORY_SCHEME_CODE);
			validatorUtil.rejectIfEmpty(tahdrDto.getSchemeName(), errors, "invalid.SchemeName", TahdrPreparationErrorConstant.MANDATORY_SCHEME_NAME);
		}
		}
		
		if(tahdrDto.getOfficeType().getLocationTypeId()==null)
		{
			validatorUtil.rejectIfEmpty(null, errors, "invalid.OfficeType", TahdrPreparationErrorConstant.MANDATORY_OFFICE_TYPE);
		}
		
		if(tahdrDto.getOfficeLocation().getOfficeLocationId()==null)
		{
			validatorUtil.rejectIfEmpty(null, errors, "invalid.OfficeLocation", TahdrPreparationErrorConstant.MANDATORY_OFFICE_LOCATION);
		}
		
		if(tahdrDto.getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT))
		{
			if(tahdrDto.getIsManufacturer().equalsIgnoreCase("N") && tahdrDto.getIsTrader().equalsIgnoreCase("N"))
			{
				validatorUtil.rejectIfEmpty(null, errors, "invalid.CheckBox", TahdrPreparationErrorConstant.MANDATORY_ONE_CHECKBOX);
			}
		}
		
		if(tahdrDto.getTahdrCode()!=null){
			TAHDRDto dto=tahdrService.findDto("getQueryForTAHDRByCode", AbstractContextServiceImpl.getParamMap("tahdrCode", tahdrDto.getTahdrCode()));
		
			if(null!=dto && (!CommonUtil.isEqual(dto.getTahdrId(), tahdrDto.getTahdrId()))){
				validatorUtil.rejectIfEmpty(null, errors, "invalid.TahdrCode", TahdrPreparationErrorConstant.EXISTING_TAHDR_CODE);
			}
		}
		
		/*if(tahdrDto.getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.AUCTION_TYPE_CODE_FORWORD)){
			if(tahdrDto.getIsAuction().equalsIgnoreCase("N")){
				validatorUtil.rejectIfEmpty(null, errors, "invalid.CheckBox", "Select atleast one checkbox of Manufacturer or Trader");
			}
		}*/
		
	}
}
