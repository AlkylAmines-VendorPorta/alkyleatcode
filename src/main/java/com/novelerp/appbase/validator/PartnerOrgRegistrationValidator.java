package com.novelerp.appbase.validator;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgRegistrationDto;
import com.novelerp.appbase.master.service.PartnerOrgRegistrationService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

/**
 * 
 * @author varsha patil
 *
 */
@Component
public class PartnerOrgRegistrationValidator implements Validator{
    @Autowired
	private ValidationUtil validator;
    @Autowired
    private PartnerOrgRegistrationService partnerOrgRegistrationService;
	@Override
	public void validate(Object object, Errors errors){
		
		if(!object.getClass().isAssignableFrom(PartnerOrgRegistrationDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		PartnerOrgRegistrationDto dto=(PartnerOrgRegistrationDto)object;
		checkForRegType(dto,errors);
	}
	public void checkForRegType(PartnerOrgRegistrationDto dto,Errors errors){
		   Map<String, Object> params = AbstractServiceImpl.getParamMap("partnerOrgId",dto.getPartnerOrg().getPartnerOrgId());
			params.put("partnerId",dto.getPartner().getbPartnerId());
			List<PartnerOrgRegistrationDto> regDtos=partnerOrgRegistrationService.findDtos("getOrgRegQuery",params);
			if(!CommonUtil.isCollectionEmpty(regDtos)){
				for(PartnerOrgRegistrationDto regDto:regDtos){
					if(("Y".equals(dto.getIsApplicable()) || "Y".equals(regDto.getIsApplicable())) && !CommonUtil.isEqual(regDto.getPartnerOrgRegistrationId(),dto.getPartnerOrgRegistrationId())){
						validator.reject(errors,"invalid","you can not add records for this type");
						break;
					}else if(dto.getRegistrationType()!=null && dto.getRegistrationType().equals(regDto.getRegistrationType()) && !CommonUtil.isEqual(regDto.getPartnerOrgRegistrationId(), dto.getPartnerOrgRegistrationId())){
					    validator.reject(errors, "already.Used", "Factory with selected registration type has already registered");
					    break;
					}
				}
			}
	}
}

