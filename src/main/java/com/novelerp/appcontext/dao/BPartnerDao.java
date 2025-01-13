package com.novelerp.appcontext.dao;

import java.util.Date;
import java.util.List;

import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.dao.CommonDao;

public interface BPartnerDao  extends CommonDao<Bpartner, BPartnerDto> {
	/**
	 * Get BPartner with Partner Details
	 * @param bPartnerId
	 * @return Bpartner loaded with PartnerCompanyDetails
	 */
	public Bpartner getPartnerWithBPDetails (Long bPartnerId);
	public Bpartner getPartnerWithDetails(Long bPartnerId);
	public Bpartner getPartnerByPartnerId(Long bpartnerId);
	public String getQueryForPartnerApproval(RoleDto role);
	
	public String getQueryForBPDetailsForSendInvitation();
	public String getQueryForPartnersByStatus(RoleDto role,String status);
	public int deletePartners(Date toDate);
	public String getRecordCountWhereClaus(RoleDto role, String status);
	public String getPrtnrAprlCountWhereClaus(RoleDto role);
	public List<Object[]> getPartnerLocAndBankDetail(long bpartnerId);
	public String getQueryForPartnersByStatus(RoleDto role,String status,String searchColumn,String searchValue);
	public String getRecordCountWhereClaus(RoleDto role, String status,String searchMode,String searchValue);
	public String getQueryForPartnerApproval(RoleDto role,String searchMode,String searchValue);
	public String getPrtnrAprlCountWhereClaus(RoleDto role,String searchMode,String searchValue);
	String getQueryForApprovedPartners();
	public int vendorBlacklisting(Long bpartnerId,Long userId,String comment);
	public  String getInfraApprovalVendors(RoleDto role,BPartnerDto dto);
	String getInfraApprovalVendorsCount(RoleDto role, BPartnerDto dto);
//	public String getVendorCredentailReportlist(VendorCredentialReadDto dto);
//	public String getVendorCredentailReportlist(VendorCredentialReadDto dto);
}
