package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerOrgExperienceDto;
import com.novelerp.appbase.master.entity.PartnerOrgExperience;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 */
public interface PartnerOrgExperienceService extends CommonService<PartnerOrgExperience, PartnerOrgExperienceDto>{
	public PartnerOrgExperienceDto saveOrgExp(PartnerOrgExperienceDto dto);
	public PartnerOrgExperienceDto saveTraderExp(PartnerOrgExperienceDto dto);
}

