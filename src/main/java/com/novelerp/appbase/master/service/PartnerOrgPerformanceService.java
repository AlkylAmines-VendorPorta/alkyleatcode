package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerOrgPerformanceDto;
import com.novelerp.appbase.master.entity.PartnerOrgPerformance;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 */
public interface PartnerOrgPerformanceService extends CommonService<PartnerOrgPerformance, PartnerOrgPerformanceDto>{
	public PartnerOrgPerformanceDto checkForDuplicatePerformance(PartnerOrgPerformanceDto dto);
	public PartnerOrgPerformanceDto saveOrgPerformance(PartnerOrgPerformanceDto dto,RoleDto role);
	public PartnerOrgPerformanceDto saveTraderPerformance(PartnerOrgPerformanceDto dto,RoleDto role);
}