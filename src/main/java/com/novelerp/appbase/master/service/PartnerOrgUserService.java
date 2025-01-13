package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerOrgUserDto;
import com.novelerp.appbase.master.entity.PartnerOrgUser;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 */
public interface PartnerOrgUserService extends CommonService<PartnerOrgUser, PartnerOrgUserDto>{
	public PartnerOrgUserDto saveOrgUser(PartnerOrgUserDto dto);
}