package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.entity.PartnerCompanyAddress;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 */
public interface PartnerCompanyAddressService extends CommonService<PartnerCompanyAddress, PartnerCompanyAddressDto>{
	
	public List<LocationDto> getLocationByLocationType(String locationType,Long bPartnerId);
	public PartnerCompanyAddressDto savePartnerAddress(PartnerCompanyAddressDto dto);
//	public List<PartnerCompanyAddressDto> getUsersPrimaryAddressList();
	public List<PartnerCompanyAddressDto> getUsersPrimaryAddressList(String searchText);
	
	public List<PartnerCompanyAddressDto> getRegisteredVendors(String searchText);
	public List<PartnerCompanyAddressDto> getNotRegisteredVendors(String searchText);
	public List<PartnerCompanyAddressDto> getAllInvitedVendors(String searchText);
	public List<PartnerCompanyAddressDto> getAllVendors(String searchText);
	public List<PartnerCompanyAddressDto> getVendorsforEnquiry(String name);
	public List<Object> getAllVendorsForUpdateCred(String searchText);
	public List<Object> getResendInvitationVendors(String name);
//	public List<PartnerCompanyAddressDto> getVendorListReportbyFilter(VendorCredentialReadDto dto);
	public List<PartnerCompanyAddressDto> getVendorListReportbyFilter(VendorCredentialReadDto dto);
	
}
