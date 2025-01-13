package com.novelerp.appcontext.service;

import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appcontext.dto.PartnerCompanyDetailsDto;
import com.novelerp.appcontext.entity.PartnerCompanyDetails;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 */
public interface PartnerCompanyDetailsService extends CommonService<PartnerCompanyDetails, PartnerCompanyDetailsDto>{

	public PartnerCompanyDetailsDto getPartnerCompanyDetails(Long partnerId);
	public ResponseDto updatePartnerCompanyDetails(PartnerCompanyDetailsDto dto);
	
}
