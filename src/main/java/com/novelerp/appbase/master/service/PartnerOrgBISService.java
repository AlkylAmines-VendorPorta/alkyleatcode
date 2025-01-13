package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerOrgBISDto;
import com.novelerp.appbase.master.entity.PartnerOrgBIS;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 */
public interface PartnerOrgBISService  extends CommonService<PartnerOrgBIS, PartnerOrgBISDto>{

	public PartnerOrgBISDto getPartnerOrgBIS(Long partnerId);
	/*public ResponseDto updatePartnerOrgBIS(PartnerOrgBISDto dto);*/
	public PartnerOrgBISDto saveOrgBIS(PartnerOrgBISDto dto,RoleDto role);
	
}
