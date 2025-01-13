package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.entity.PartnerOrg;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 */
public interface PartnerOrgService extends CommonService<PartnerOrg, PartnerOrgDto>{

	public List<PartnerOrgDto> getPartnerOrgs(Long bpartnerId);
	public PartnerOrg getPartnerOrg(Long partnerOrgId);
	public PartnerOrgDto savePartnerOrg(PartnerOrgDto dto);
	public ResponseDto deleteOrg(Long partnerOrgId,Long paymentId);
	public boolean updateOrgForRenewal(PartnerOrgDto dto);
	public void updateOrgForRenewalPayment(PartnerOrgDto dto);
	public PartnerOrgDto saveManufactureOrg(PartnerOrgDto dto);
	public void sendMailOnFactoryExpired(PartnerOrgDto dto,UserDto internalUser);
	public void sendMailOnFactoryRenewalReminder(PartnerOrgDto dto,UserDto internalUser);
	public ResponseDto updatePartnerOrgValidity(PartnerOrgDto dto);
}
