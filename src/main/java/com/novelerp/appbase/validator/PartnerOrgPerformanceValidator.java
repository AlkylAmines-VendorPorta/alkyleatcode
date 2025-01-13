package com.novelerp.appbase.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgPerformanceDto;
import com.novelerp.appbase.master.service.PartnerOrgPerformanceService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

/**
 * 
 * @author Varsha Patil
 *
 */
@Component
public class PartnerOrgPerformanceValidator implements Validator{

	@Autowired
	private ValidationUtil validator;
	@Autowired
	private PartnerOrgPerformanceService partnerOrgPerformanceService;
	@Override
	public void validate(Object object, Errors errors) {
		
		if(!object.getClass().isAssignableFrom(PartnerOrgPerformanceDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		PartnerOrgPerformanceDto performance=(PartnerOrgPerformanceDto) object;
		checkForDublicatePerformance(performance,errors);
	}
	public void checkForDublicatePerformance(PartnerOrgPerformanceDto performance,Errors errors)
	{
		PartnerOrgPerformanceDto dto=partnerOrgPerformanceService.checkForDuplicatePerformance(performance);
        if(dto!=null && !CommonUtil.isEqual(dto.getPartnerOrgPerformanceId(), performance.getPartnerOrgPerformanceId())){
        	validator.reject(errors, "duplicateData", "Material with given PO Number has already added");
        }
	}

}
