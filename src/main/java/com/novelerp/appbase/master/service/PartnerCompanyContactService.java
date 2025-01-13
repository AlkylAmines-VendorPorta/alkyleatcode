package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerCompanyContactDto;
import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appbase.master.entity.PartnerCompanyContact;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 */
public interface PartnerCompanyContactService extends CommonService<PartnerCompanyContact, PartnerCompanyContactDto>{

	public PartnerCompanyContactDto getPartnerCompanyContact(Long partnerId);
	public ResponseDto updatePartnerCompanyContact(PartnerCompanyContactDto dto);

}
