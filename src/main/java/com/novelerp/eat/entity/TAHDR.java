/**
	 * @author Ankush
	 * 
	 */
package com.novelerp.eat.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appbase.master.entity.Department;
import com.novelerp.appbase.master.entity.LocationType;
import com.novelerp.appbase.master.entity.MAuctionParticipantMap;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.OfficeLocation;
import com.novelerp.appcontext.entity.User;

@Entity
@Table(name = "t_tahdr")
public class TAHDR extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long tahdrId;
	private String tahdrCode;
	private String tahdrTypeCode;
	private String title;
	private Department department;
	private String bidTypeCode;
	private Set<TAHDRDetail> tahdrDetail;
	private String schemeName;
	private String schemeCode;
	private String budgetType;
	private LocationType officeType;
	private OfficeLocation officeLocation;
	private String tahdrStatusCode;
	/* private Set<TenderCommittee> tenderCommittee; */
	private String remarks;
	private String isAuction;
	private String isManufacturer;
	private String isTrader;
	private Attachment mom;
	private Set<Bidder> bidders;
	private User auditorUser;
	private Timestamp bidOpenedDate;
	private String isPrivateAuction;
	private String isAuctionExtended;
	private Set<MAuctionParticipantMap> auctionParticipant;
		private String isAuditing;

	@Id
	@SequenceGenerator(name = "t_tahdr_seq", sequenceName = "t_tahdr_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_tahdr_seq")
	@Column(name = "t_tahdr_id", updatable = false)
	public Long getTahdrId() {
		return tahdrId;
	}

	public void setTahdrId(Long tahdrId) {
		this.tahdrId = tahdrId;
	}

	@Column(name = "value")
	public String getTahdrCode() {
		return tahdrCode;
	}

	public void setTahdrCode(String tahdrCode) {
		this.tahdrCode = tahdrCode;
	}

	@Column(name = "tahdr_type_code")
	public String getTahdrTypeCode() {
		return tahdrTypeCode;
	}

	public void setTahdrTypeCode(String tahdrTypeCode) {
		this.tahdrTypeCode = tahdrTypeCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_department_id")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "bid_type_code")
	public String getBidTypeCode() {
		return bidTypeCode;
	}

	public void setBidTypeCode(String bidTypeCode) {
		this.bidTypeCode = bidTypeCode;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tahdr")
	@Where(clause = "isActive='Y'")
	/* @JoinColumn(name="t_tahdr_id") */
	public Set<TAHDRDetail> getTahdrDetail() {
		return tahdrDetail;
	}

	public void setTahdrDetail(Set<TAHDRDetail> tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "budget_type")
	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}


	@Column(name = "scheme_name")
	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	@Column(name = "scheme_code")
	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	@Column(name = "tahdr_status_code")
	public String getTahdrStatusCode() {
		return tahdrStatusCode;
	}

	public void setTahdrStatusCode(String tahdrStatusCode) {
		this.tahdrStatusCode = tahdrStatusCode;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/*
	 * @OneToMany(fetch=FetchType.LAZY,mappedBy="tahdr") public
	 * Set<TenderCommittee> getTenderCommittee() { return tenderCommittee; }
	 * public void setTenderCommittee(Set<TenderCommittee> tenderCommittee) {
	 * this.tenderCommittee = tenderCommittee; }
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mom", referencedColumnName = "m_attachment_id")
	public Attachment getMom() {
		return mom;
	}

	public void setMom(Attachment mom) {
		this.mom = mom;
	}

	@Column(name = "is_auction")
	public String getIsAuction() {
		return isAuction;
	}

	public void setIsAuction(String isAuction) {
		this.isAuction = isAuction;
	}
	
	@Column(name = "is_auction_extended")
	public String getIsAuctionExtended() {
		return isAuctionExtended;
	}

	public void setIsAuctionExtended(String isAuctionExtended) {
		this.isAuctionExtended = isAuctionExtended;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tahdr")
	public Set<Bidder> getBidders() {
		return bidders;
	}

	public void setBidders(Set<Bidder> bidders) {
		this.bidders = bidders;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auditor_user", referencedColumnName = "ad_user_id")
	public User getAuditorUser() {
		return auditorUser;
	}

	public void setAuditorUser(User auditorUser) {
		this.auditorUser = auditorUser;
	}

	@Column(name = "is_manufacturer")
	public String getIsManufacturer() {
		return isManufacturer;
	}

	public void setIsManufacturer(String isManufacturer) {
		this.isManufacturer = isManufacturer;
	}

	@Column(name = "is_trader")
	public String getIsTrader() {
		return isTrader;
	}

	public void setIsTrader(String isTrader) {
		this.isTrader = isTrader;
	}
	
	@Column(name = "bid_opened_date")
	public Timestamp getBidOpenedDate() {
		return bidOpenedDate;
	}

	public void setBidOpenedDate(Timestamp bidOpenedDate) {
		this.bidOpenedDate = bidOpenedDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_location_type_id")
	public LocationType getOfficeType() {
		return officeType;
	}

	public void setOfficeType(LocationType officeType) {
		this.officeType = officeType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_office_location_id")
	public OfficeLocation getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(OfficeLocation officeLocation) {
		this.officeLocation = officeLocation;
	}
	@Column(name="is_private_auction")
	public String getIsPrivateAuction() {
		return isPrivateAuction;
	}
	public void setIsPrivateAuction(String isPrivateAuction) {
		this.isPrivateAuction = isPrivateAuction;
	}
	@OneToMany(fetch=FetchType.LAZY, mappedBy="tahdr")
	public Set<MAuctionParticipantMap> getAuctionParticipant() {
		return auctionParticipant;
	}
	public void setAuctionParticipant(Set<MAuctionParticipantMap> auctionParticipant) {
		this.auctionParticipant = auctionParticipant;
	}
	
	@Column(name = "is_auditing")
	public String getIsAuditing() {
		return isAuditing;
	}
	public void setIsAuditing(String isAuditing) {
		this.isAuditing = isAuditing;
	}
	

}
