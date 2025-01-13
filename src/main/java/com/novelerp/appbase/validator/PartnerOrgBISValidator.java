package com.novelerp.appbase.validator;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgBISDto;
import com.novelerp.appbase.master.service.PartnerOrgBISService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

/**
 * 
 * @author Varsha Patil
 *
 */
@Component
public class PartnerOrgBISValidator implements Validator{

	@Autowired
	private ValidationUtil validator;
	@Autowired
	private PartnerOrgBISService partnerOrgBISService;
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(PartnerOrgBISDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		PartnerOrgBISDto dto=(PartnerOrgBISDto) object;
		checkForBIS(dto,errors);
	}
	private void checkForBIS(PartnerOrgBISDto dto,Errors errors){
		Map<String,Object> param=AbstractServiceImpl.getParamMap("partnerOrgId",dto.getPartnerOrg().getPartnerOrgId());
		param.put("partnerId",dto.getPartner().getbPartnerId());
		List<PartnerOrgBISDto> bisDtos=partnerOrgBISService.findDtos("getPartnerOrgBIS",param);
		if(!CommonUtil.isCollectionEmpty(bisDtos)){
			for(PartnerOrgBISDto bisDto:bisDtos){
				if(("Y".equals(dto.getIsNotApplicable()) || "Y".equals(bisDto.getIsNotApplicable()))&& !CommonUtil.isEqual(bisDto.getPartnerOrgBisId(),dto.getPartnerOrgBisId())){
					validator.reject(errors,"invalid","you can not add records for this type");
					break;
				}
			}
		}
	}

}
