package com.novelerp.eat.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.DepartmentDto;
import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appbase.master.dto.MAuctionParticipantMapDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.OfficeLocationDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.commons.util.CommonUtil;

public class TAHDRDto extends CommonContextDto {

	private static final long serialVersionUID = 1L;
	private Long tahdrId;
	private String tahdrCode;
	private String tahdrTypeCode;
	private String title;
	private DepartmentDto department;
	private String bidTypeCode;
	private Set<TAHDRDetailDto> tahdrDetail;
	private String budgetType;
	private LocationTypeDto officeType;
	private OfficeLocationDto officeLocation;
	private String schemeName;
	private String schemeCode;
	private String tahdrStatusCode;
	private String remarks;
	private String isPrivateAuction;
	private Set<MAuctionParticipantMapDto> auctionParticipant;
	/* private Set<TenderCommitteeDto> tenderCommittee; */
	private List<TAHDRDetailDto> tahdrDetailList;
	private String isAuction;
	private String isManufacturer;
	private String isTrader;
	private AttachmentDto mom;
	private UserDto auditorUser;
	private Timestamp bidOpenedDate;
	private String isAuditing;
	private String isAuctionExtended;
	
	
	@JsonIdentityInfo(generator = PropertyGenerator.class, property = "bidderId", scope = Long.class)
	private Set<BidderDto> bidders;

	public Long getTahdrId() {
		return tahdrId;
	}

	public void setTahdrId(Long tahdrId) {
		this.tahdrId = tahdrId;
	}

	public String getTahdrCode() {
		return tahdrCode;
	}

	public void setTahdrCode(String tahdrCode) {
		this.tahdrCode = tahdrCode;
	}

	public String getTahdrTypeCode() {
		return tahdrTypeCode;
	}

	public void setTahdrTypeCode(String tahdrTypeCode) {
		this.tahdrTypeCode = tahdrTypeCode;
	}

	public String getBidTypeCode() {
		return bidTypeCode;
	}

	public void setBidTypeCode(String bidTypeCode) {
		this.bidTypeCode = bidTypeCode;
	}

	public DepartmentDto getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDto department) {
		this.department = department;
	}

	public Set<TAHDRDetailDto> getTahdrDetail() {
		if(CommonUtil.isCollectionEmpty(tahdrDetail) && !CommonUtil.isCollectionEmpty(tahdrDetailList)){
			tahdrDetail=new HashSet<>();
			tahdrDetail.addAll(getTahdrDetailList());
		}
		return tahdrDetail;
	}

	public void setTahdrDetail(Set<TAHDRDetailDto> tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getTahdrStatusCode() {
		return tahdrStatusCode;
	}

	public void setTahdrStatusCode(String tahdrStatusCode) {
		this.tahdrStatusCode = tahdrStatusCode;
	}

	/*
	 * public Set<TenderCommitteeDto> getTenderCommittee() { return
	 * tenderCommittee; } public void setTenderCommittee(Set<TenderCommitteeDto>
	 * tenderCommittee) { this.tenderCommittee = tenderCommittee; }
	 */
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<TAHDRDetailDto> getTahdrDetailList() {
		return tahdrDetailList;
	}

	public void setTahdrDetailList(List<TAHDRDetailDto> tahdrDetailList) {
		this.tahdrDetailList = tahdrDetailList;
	}

	public AttachmentDto getMom() {
		return mom;
	}

	public void setMom(AttachmentDto mom) {
		this.mom = mom;
	}

	public String getIsAuction() {
		if (null == isAuction) {
			isAuction = "N";
		}
		return isAuction;
	}

	public void setIsAuction(String isAuction) {
		this.isAuction = isAuction;
	}

	public Set<BidderDto> getBidders() {
		return bidders;
	}

	public void setBidders(Set<BidderDto> bidders) {
		this.bidders = bidders;
	}

	public UserDto getAuditorUser() {
		return auditorUser;
	}

	public void setAuditorUser(UserDto auditorUser) {
		this.auditorUser = auditorUser;
	}

	public String getIsManufacturer() {
		if (null == isManufacturer) {
			isManufacturer = "N";
		}
		return isManufacturer;
	}

	public void setIsManufacturer(String isManufacturer) {
		this.isManufacturer = isManufacturer;
	}

	public String getIsTrader() {
		if (null == isTrader) {
			isTrader = "N";
		}
		return isTrader;
	}

	public void setIsTrader(String isTrader) {
		this.isTrader = isTrader;
	}

	public Timestamp getBidOpenedDate() {
		return bidOpenedDate;
	}

	public void setBidOpenedDate(Timestamp bidOpenedDate) {
		this.bidOpenedDate = bidOpenedDate;
	}

	public LocationTypeDto getOfficeType() {
		return officeType;
	}

	public void setOfficeType(LocationTypeDto officeType) {
		this.officeType = officeType;
	}

	public OfficeLocationDto getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(OfficeLocationDto officeLocation) {
		this.officeLocation = officeLocation;
	}

	public String getIsAuditing() {
		return isAuditing;
	}

	public void setIsAuditing(String isAuditing) {
		this.isAuditing = isAuditing;
	}

	public String getIsPrivateAuction() {
		return isPrivateAuction;
	}
	public void setIsPrivateAuction(String isPrivateAuction) {
		this.isPrivateAuction = isPrivateAuction;
	}
	public Set<MAuctionParticipantMapDto> getAuctionParticipant() {
		return auctionParticipant;
	}
	public void setAuctionParticipant(Set<MAuctionParticipantMapDto> auctionParticipant) {
		this.auctionParticipant = auctionParticipant;
	}
	
	public String getIsAuctionExtended() {
		return isAuctionExtended;
	}

	public void setIsAuctionExtended(String isAuctionExtended) {
		this.isAuctionExtended = isAuctionExtended;
	}
}
