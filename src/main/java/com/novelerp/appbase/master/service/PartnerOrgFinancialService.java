package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerOrgFinancialDto;
import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appbase.master.entity.PartnerOrgFinancial;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 */
public interface PartnerOrgFinancialService extends CommonService<PartnerOrgFinancial, PartnerOrgFinancialDto>{

	public PartnerOrgFinancialDto getPartnerOrgFinancial(Long entityId);
	public ResponseDto updatePartnerOrgFinancial(PartnerOrgFinancialDto dto);
	
}