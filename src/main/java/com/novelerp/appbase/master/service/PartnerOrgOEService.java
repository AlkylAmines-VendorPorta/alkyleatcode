package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.PartnerOrgOEDto;
import com.novelerp.appbase.master.entity.PartnerOrgOE;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 * @author Vivek Birdi
 */
public interface PartnerOrgOEService extends CommonService<PartnerOrgOE, PartnerOrgOEDto>{
	public List<PartnerOrgOEDto> processRequest(List<PartnerOrgOEDto> dtos);
	public PartnerOrgOEDto processPartnerOrg(PartnerOrgOEDto dto);
	public PartnerOrgOEDto savePartnerOrgOE(PartnerOrgOEDto dto,RoleDto role);
}
