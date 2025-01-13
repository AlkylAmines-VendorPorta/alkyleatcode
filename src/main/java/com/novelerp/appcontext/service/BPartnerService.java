package com.novelerp.appcontext.service;

import java.util.List;

import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.SapVendorDetailDto;
import com.novelerp.eat.msedcl.sap.ZVENDORCREATEResponse;

public interface BPartnerService extends CommonService<Bpartner, BPartnerDto> {

	/**
	 * Get BPartner with Partner Details
	 * @param bPartnerId
	 * @return BPartnerDto loaded with PartnerCompanyDetailsDto
	 */
	public BPartnerDto getPartnerWithBPDetails (Long bPartnerId);
	public ResponseDto submitPartnerDetails(BPartnerDto dto);
	public ResponseDto partnerRegistrationApproval(BPartnerDto partner,BPartnerDto dto);
	public List<BPartnerDto> getPartnerForApproval(Long bpartnerId);
	
	
	
	/*public String getPartnerEmail(Long bpartnerId);*/
	
	public boolean sendEmailToPartner(String emailId,String sub,String message);
	public BPartnerDto updatePartner(BPartnerDto partner);
	public List<BPartnerDto> getVendors(String status);
	public void deletePartners();
	int updateBpartnerRating(BPartnerDto bPartnerDto, double newRating);
	public List<BPartnerDto> getVendors(String status, int pageNumber, int pageSize);
	public List<BPartnerDto> getPartnerForApproval(Long bpartnerId, int pageNumber, int pageSize);
	public List<BPartnerDto> getPartnerForApproval(Long bpartnerId, int pageNumber, int pageSize,String searchMode,String searchValue);
	public long getRecordCount(String status);
	public Long getCountForPrtnrAprvl(Long bpartnerId,String searchMode,String searchValue);
	public Long getCountForPrtnrAprvl(Long bpartnerId);
	public boolean  partnersRenewal(BPartnerDto dto);
	public List<Object[]> getPartnerBankAndLocDetail(Long bpartnerId);
	public ResponseDto updateVendorSapCode(ZVENDORCREATEResponse zvendorcreateResponse, SapVendorDetailDto sapVendorDetailDto);
    public ResponseDto sendMailOnSubmit(ResponseDto response,BPartnerDto dto);
    public ResponseDto sendMailOnApproval(ResponseDto response,BPartnerDto dto);
    public int updatePartnerForEditProfile(BPartnerDto dto,RoleDto role);
    public void sendMailOnEditProfile(BPartnerDto dto);
    public void sendMailOnVedorExpired(BPartnerDto partner,UserDto internalUser);
    public void sendMailOnRenewalReminder(UserDto dto,UserDto internalUser);
    public List<BPartnerDto> getVendors(String status, int pageNumber, int pageSize,String searchMode,String searchValue);
    public long getRecordCount(String status,String searchMode,String searchValue);
    public boolean sendEmailToPartner(List<String> emailList,String sub,String message);
	public boolean updatePartnerRole(Long bPartnerId, String registrationType);
//	public List<BPartnerDto> getVendorListReportbyFilter(VendorCredentialReadDto dto);
	
	
}

