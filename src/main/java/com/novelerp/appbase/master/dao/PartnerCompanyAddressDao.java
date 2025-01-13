package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.entity.PartnerCompanyAddress;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Location;
import com.novelerp.core.dao.CommonDao;
/**
 * @author Aman
 *
 */
public interface PartnerCompanyAddressDao extends CommonDao<PartnerCompanyAddress, PartnerCompanyAddressDto>{

	public List<Location> getLocationByLocationType(String locationType,Long bPartnerId);
//	public List<PartnerCompanyAddress> getUsersPrimaryAddressList();
	public List<PartnerCompanyAddress> getUsersPrimaryAddressList(String searchText);
	public List<PartnerCompanyAddress> getRegisteredVendors(String searchText);
	public List<PartnerCompanyAddress> getNotRegisteredVendors(String searchText);
	public List<PartnerCompanyAddress> getAllInvitedVendors(String searchText);
	public List<PartnerCompanyAddress> getAllVendors(String searchText);
	public List<PartnerCompanyAddress> getVendorsforEnquiry(String searchText);
	List<Object> getAllVendorsForUpdateCredential(String searchText);
	public List<Object> getResendInvitationVendorsQuery(String searchText);
	public String getVendorCredentailReportlist(VendorCredentialReadDto dto);
	public List<Object> getregionByLocationandID(String locationType, Long partnerId);
}
