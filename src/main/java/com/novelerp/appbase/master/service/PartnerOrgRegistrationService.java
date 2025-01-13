package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerOrgRegistrationDto;
import com.novelerp.appbase.master.entity.PartnerOrgRegistration;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 */
public interface PartnerOrgRegistrationService extends CommonService<PartnerOrgRegistration, PartnerOrgRegistrationDto>{
	public PartnerOrgRegistrationDto saveOrgRegistration(PartnerOrgRegistrationDto dto);
}