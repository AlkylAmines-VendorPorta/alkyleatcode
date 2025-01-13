package com.novelerp.alkyl.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.util.DateUtil;

public class PRLineDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long prLineId;
	private String code;
	private String name;
	private String description;
	private String prLineNumber;
	private PRDto pr;
	private String materialCode;
	private String lineNumber;
	private String materialDesc;
	private Double quantity = new Double(0);
	private Double rate = new Double(0);
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date deliverDate;
	private UserDto fixedVendor;
	private UserDto desiredVendor;
	private String trackingNo;
	private String purchaseGroup;
	private PRLineDto parentPRLine; 
	private List<PRLineDto> prServiceLines;
	private String plant;
	private Date requiredDate;
	private String assignment;

	private String uom;

	private String price;

	private String accountAssignment;
	private UserDto buyer;
	private String headerText;
	private String matGrp;
	private String matGrpDesc;
	private String itemCategory;
	private String itemText;
	private Long buyerId;
	private String materialPOText;
	private String fixedVendorCode;
	private String desireVendorCode;
//	private String plantDesc;
	private String plantDESC;
	
	public Long getPrLineId() {
		return prLineId;
	}
	public void setPrLineId(Long prLineId) {
		this.prLineId = prLineId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrLineNumber() {
		return prLineNumber;
	}
	public void setPrLineNumber(String prLineNumber) {
		this.prLineNumber = prLineNumber;
	}
	public PRDto getPr() {
		return pr;
	}
	public void setPr(PRDto pr) {
		this.pr = pr;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getMaterialDesc() {
		return materialDesc;
	}
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Date getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	public UserDto getFixedVendor() {
		return fixedVendor;
	}
	public void setFixedVendor(UserDto fixedVendor) {
		this.fixedVendor = fixedVendor;
	}
	public UserDto getDesiredVendor() {
		return desiredVendor;
	}
	public void setDesiredVendor(UserDto desiredVendor) {
		this.desiredVendor = desiredVendor;
	}
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	public String getPurchaseGroup() {
		return purchaseGroup;
	}
	public void setPurchaseGroup(String purchaseGroup) {
		this.purchaseGroup = purchaseGroup;
	}
	public PRLineDto getParentPRLine() {
		return parentPRLine;
	}
	public void setParentPRLine(PRLineDto parentPRLine) {
		this.parentPRLine = parentPRLine;
	}
	public List<PRLineDto> getPrServiceLines() {
		return prServiceLines;
	}
	public void setPrServiceLines(List<PRLineDto> prServiceLines) {
		this.prServiceLines = prServiceLines;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public Date getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAccountAssignment() {
		return accountAssignment;
	}
	public void setAccountAssignment(String accountAssignment) {
		this.accountAssignment = accountAssignment;
	}
	public UserDto getBuyer() {
		return buyer;
	}
	public void setBuyer(UserDto buyer) {
		this.buyer = buyer;
	}
	public String getHeaderText() {
		return headerText;
	}
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	public String getMatGrp() {
		return matGrp;
	}
	public void setMatGrp(String matGrp) {
		this.matGrp = matGrp;
	}
	public String getMatGrpDesc() {
		return matGrpDesc;
	}
	public void setMatGrpDesc(String matGrpDesc) {
		this.matGrpDesc = matGrpDesc;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String getItemText() {
		return itemText;
	}
	public void setItemText(String itemText) {
		this.itemText = itemText;
	}
	public Long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}
	public String getMaterialPOText() {
		return materialPOText;
	}
	public void setMaterialPOText(String materialPOText) {
		this.materialPOText = materialPOText;
	}
	public String getFixedVendorCode() {
		return fixedVendorCode;
	}
	public void setFixedVendorCode(String fixedVendorCode) {
		this.fixedVendorCode = fixedVendorCode;
	}
	public String getDesireVendorCode() {
		return desireVendorCode;
	}
	public void setDesireVendorCode(String desireVendorCode) {
		this.desireVendorCode = desireVendorCode;
	}
	public String getPlantDESC() {
		return plantDESC;
	}
	public void setPlantDESC(String plantDESC) {
		this.plantDESC = plantDESC;
	}
	@Override
	public String toString() {
		return "PRLineDto [prLineId=" + prLineId + ", code=" + code + ", name=" + name + ", description=" + description
				+ ", prLineNumber=" + prLineNumber + ", pr=" + pr + ", materialCode=" + materialCode + ", lineNumber="
				+ lineNumber + ", materialDesc=" + materialDesc + ", quantity=" + quantity + ", rate=" + rate
				+ ", deliverDate=" + deliverDate + ", fixedVendor=" + fixedVendor + ", desiredVendor=" + desiredVendor
				+ ", trackingNo=" + trackingNo + ", purchaseGroup=" + purchaseGroup + ", parentPRLine=" + parentPRLine
				+ ", prServiceLines=" + prServiceLines + ", plant=" + plant + ", requiredDate=" + requiredDate
				+ ", assignment=" + assignment + ", uom=" + uom + ", price=" + price + ", accountAssignment="
				+ accountAssignment + ", buyer=" + buyer + ", headerText=" + headerText + ", matGrp=" + matGrp
				+ ", matGrpDesc=" + matGrpDesc + ", itemCategory=" + itemCategory + ", itemText=" + itemText
				+ ", buyerId=" + buyerId + ", materialPOText=" + materialPOText + ", fixedVendorCode=" + fixedVendorCode
				+ ", desireVendorCode=" + desireVendorCode + ", plantDESC=" + plantDESC + "]";
	}	
	
//	public String getPlantDesc() {
//		return plantDesc;
//	}
//	public void setPlantDesc(String plantDesc) {
//		this.plantDesc = plantDesc;
//	}

	
	

	
	
	
	
}
