package com.novelerp.appbase.validator;
/**
 * 
 * @author Varsha Patil
 *
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgOEDto;
import com.novelerp.appbase.master.service.PartnerOrgOEService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

@Component
public class PartnerOrgOEValidator implements Validator{

	@Autowired
	private ValidationUtil validator;
	@Autowired
	private PartnerOrgOEService partnerOrgOEService;

	@Override
	public void validate(Object object, Errors errors){
		if(!object.getClass().isAssignableFrom(PartnerOrgOEDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		PartnerOrgOEDto dto=(PartnerOrgOEDto) object;	
		checkForDuplicate(dto,errors);
	}
	public void checkForDuplicate(PartnerOrgOEDto dto,Errors errors){
		Map<String,Object> params=AbstractServiceImpl.getParamMap("partnerId",dto.getPartner().getbPartnerId());
		params.put("partnerOrgId",dto.getPartnerOrg().getPartnerOrgId());
		List<PartnerOrgOEDto> oeDtos=partnerOrgOEService.findDtos("getOEQuery",params);
		if(!CommonUtil.isCollectionEmpty(oeDtos)){
			for(PartnerOrgOEDto oeDto:oeDtos){
				if(("Y".equals(dto.getIsNotApplicable()) || "Y".equals(oeDto.getIsNotApplicable()))&& !CommonUtil.isEqual(oeDto.getPartnerOrgOEId(),dto.getPartnerOrgOEId())){
					validator.reject(errors,"invalid","you can not add records for this type");
					break;
				}
			}
		}
	}
}