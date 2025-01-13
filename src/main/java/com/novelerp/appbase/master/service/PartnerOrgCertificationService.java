package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerOrgCertificationDto;
import com.novelerp.appbase.master.entity.PartnerOrgCertification;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman/ Vivek Birdi
 */
public interface PartnerOrgCertificationService extends CommonService<PartnerOrgCertification, PartnerOrgCertificationDto>{

	public PartnerOrgCertificationDto saveOrgCert(PartnerOrgCertificationDto dto,RoleDto role);
}