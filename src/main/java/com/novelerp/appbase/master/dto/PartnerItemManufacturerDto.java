package com.novelerp.appbase.master.dto;

import java.util.List;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.LocationDto;

public class PartnerItemManufacturerDto  extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4438791325535966853L;
	private Long partnerItemManufacturerId;
	private String name;
	private String email;
	private LocationDto location;
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
	private List<PartnerTradingItemDto> partnerTradingItems;
	private String isItemsEEApproved;
	private String isItemsCEApproved;
	private String itemsEEComment;
	private String itemsCEComment;
	
	public Long getPartnerItemManufacturerId() {
		return partnerItemManufacturerId;
	}
	public void setPartnerItemManufacturerId(Long partnerItemManufacturerId) {
		this.partnerItemManufacturerId = partnerItemManufacturerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocationDto getLocation() {
		return location;
	}
	public void setLocation(LocationDto location) {
		this.location = location;
	}
	public List<PartnerTradingItemDto> getPartnerTradingItems() {
		return partnerTradingItems;
	}
	public void setPartnerTradingItems(List<PartnerTradingItemDto> partnerTradingItems) {
		this.partnerTradingItems = partnerTradingItems;
	}
	public String getFax1() {
		return fax1;
	}
	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}
	public String getFax2() {
		return fax2;
	}
	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}
	public String getTelephone1() {
		return telephone1;
	}
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}
	public String getTelephone2() {
		return telephone2;
	}
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	public String getEeComment() {
		return eeComment;
	}
	public void setEeComment(String eeComment) {
		this.eeComment = eeComment;
	}
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public String getIsItemsEEApproved() {
		return isItemsEEApproved;
	}
	public void setIsItemsEEApproved(String isItemsEEApproved) {
		this.isItemsEEApproved = isItemsEEApproved;
	}
	public String getIsItemsCEApproved() {
		return isItemsCEApproved;
	}
	public void setIsItemsCEApproved(String isItemsCEApproved) {
		this.isItemsCEApproved = isItemsCEApproved;
	}
	public String getItemsEEComment() {
		return itemsEEComment;
	}
	public void setItemsEEComment(String itemsEEComment) {
		this.itemsEEComment = itemsEEComment;
	}
	public String getItemsCEComment() {
		return itemsCEComment;
	}
	public void setItemsCEComment(String itemsCEComment) {
		this.itemsCEComment = itemsCEComment;
	}
	
	
	
}
