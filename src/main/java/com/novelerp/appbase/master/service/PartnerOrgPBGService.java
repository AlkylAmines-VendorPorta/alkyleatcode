package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerOrgPBGDto;
import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appbase.master.entity.PartnerOrgPBG;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 */
public interface PartnerOrgPBGService extends CommonService<PartnerOrgPBG, PartnerOrgPBGDto>{

	public PartnerOrgPBGDto getPartnerOrgPBG(Long partnerId);
	public ResponseDto updatePartnerOrgPBG(PartnerOrgPBGDto dto);
	
}
