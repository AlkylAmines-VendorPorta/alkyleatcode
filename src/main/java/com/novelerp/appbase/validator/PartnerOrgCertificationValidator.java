package com.novelerp.appbase.validator;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgCertificationDto;
import com.novelerp.appbase.master.service.PartnerOrgCertificationService;
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
public class PartnerOrgCertificationValidator implements Validator{

	@Autowired
	private ValidationUtil validator;
	@Autowired
	private PartnerOrgCertificationService partnerOrgCertificationService;
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(PartnerOrgCertificationDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		PartnerOrgCertificationDto dto=(PartnerOrgCertificationDto) object;
		checkForCertification(dto,errors);
	}
    private void checkForCertification(PartnerOrgCertificationDto dto,Errors errors){
    	Map<String,Object> params=AbstractServiceImpl.getParamMap("partnerOrgId",dto.getPartnerOrg().getPartnerOrgId());
		params.put("partnerId",dto.getPartner().getbPartnerId());
		List<PartnerOrgCertificationDto> certDtos=partnerOrgCertificationService.findDtos("getPartnerOrgCertification",params);
		if(!CommonUtil.isCollectionEmpty(certDtos)){
			for(PartnerOrgCertificationDto certDto:certDtos){
				if(("Y".equals(dto.getIsNotApplicable()) || "Y".equals(certDto.getIsNotApplicable()))&& !CommonUtil.isEqual(certDto.getPartnerOrgCertificateId(),dto.getPartnerOrgCertificateId())){
					validator.reject(errors,"invalid","you can not add records for this type");
					break;
				}
			}
		}
    }
}
