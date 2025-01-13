package com.novelerp.appbase.master.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.Location;

@Entity
@Table(name="m_bp_item_manufacturer")
public class PartnerItemManufacturer  extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4438791325535966853L;
	private Long partnerItemManufacturerId;
	private String name;
	private String email;
	private Location location;
	private String fax1;
	private String fax2;
	private String telephone1;
	private String telephone2;
	private String mobileNo;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	private Set<PartnerOrg> partnerOrg;
	private String isItemsEEApproved;
	private String isItemsCEApproved;
	private String itemsEEComment;
	private String itemsCEComment;
	private Set<PartnerTradingItem> tradingItems;
	
	@Id
	@SequenceGenerator(name="m_bp_item_manufacturer_seq",sequenceName="m_bp_item_manufacturer_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_item_manufacturer_seq")	
	@Column(name = "m_bp_item_manufacturer_id", updatable=false)
	public Long getPartnerItemManufacturerId() {
		return partnerItemManufacturerId;
	}
	public void setPartnerItemManufacturerId(Long partnerItemManufacturerId) {
		this.partnerItemManufacturerId = partnerItemManufacturerId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_location_id")
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	@Column(name="fax_1")
	public String getFax1() {
		return fax1;
	}
	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}
	@Column(name="fax_2")
	public String getFax2() {
		return fax2;
	}
	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}
	@Column(name="telephone_1")
	public String getTelephone1() {
		return telephone1;
	}
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}
	@Column(name="telephone_2")
	public String getTelephone2() {
		return telephone2;
	}
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}
	@Column(name="mobileno")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name="is_ee_approved")
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	@Column(name="ee_comment")
	public String getEeComment() {
		return eeComment;
	}
	public void setEeComment(String eeComment) {
		this.eeComment = eeComment;
	}
	@Column(name="is_ce_approved")
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	@Column(name="ce_comment")
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
	@Column(name="is_approved")
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@OneToMany(mappedBy="partnerItemManufacturer",fetch=FetchType.LAZY)
	public Set<PartnerOrg> getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(Set<PartnerOrg> partnerOrg) {
		this.partnerOrg = partnerOrg;
	}
	@Column(name="is_items_ee_approved")
	public String getIsItemsEEApproved() {
		return isItemsEEApproved;
	}
	public void setIsItemsEEApproved(String isItemsEEApproved) {
		this.isItemsEEApproved = isItemsEEApproved;
	}
	@Column(name="is_items_ce_approved")
	public String getIsItemsCEApproved() {
		return isItemsCEApproved;
	}
	public void setIsItemsCEApproved(String isItemsCEApproved) {
		this.isItemsCEApproved = isItemsCEApproved;
	}
	@Column(name="items_ee_comment")
	public String getItemsEEComment() {
		return itemsEEComment;
	}
	public void setItemsEEComment(String itemsEEComment) {
		this.itemsEEComment = itemsEEComment;
	}
	@Column(name="items_ce_comment")
	public String getItemsCEComment() {
		return itemsCEComment;
	}
	public void setItemsCEComment(String itemsCEComment) {
		this.itemsCEComment = itemsCEComment;
	}
	@OneToMany(mappedBy="partnerItemManufacutrer",fetch=FetchType.LAZY)
	public Set<PartnerTradingItem> getTradingItems() {
		return tradingItems;
	}
	public void setTradingItems(Set<PartnerTradingItem> tradingItems) {
		this.tradingItems = tradingItems;
	}
	
	
}
