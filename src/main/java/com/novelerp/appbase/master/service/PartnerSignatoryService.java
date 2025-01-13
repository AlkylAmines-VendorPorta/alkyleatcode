package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerSignatoryDto;
import com.novelerp.appbase.master.entity.PartnerSignatory;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 */
public interface PartnerSignatoryService extends CommonService<PartnerSignatory, PartnerSignatoryDto>{
	public PartnerSignatoryDto saveSignatory(PartnerSignatoryDto dto,RoleDto role);
}
