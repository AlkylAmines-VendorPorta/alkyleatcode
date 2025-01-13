package com.novelerp.alkyl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.util.DateUtil;

@Entity
@Table(name="t_pr_line")
public class PRLine extends ContextPO {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long prLineId;
	private String code;
	private String name;
	private String description;
	private String prLineNumber;
	private PR pr;
	private String materialCode;
	private String lineNumber;
	private String materialDesc;
	private Double quantity = new Double(0);
	private Double rate = new Double(0);
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date deliverDate;
	private User fixedVendor;
	private User desiredVendor;
	private String trackingNo;
	private String purchaseGroup;
	private PRLine parentPRLine; 
	private String plant;
	private Date requiredDate;

	private String assignment;
	private String accountAssignment;
	private String uom;
	private String price;
	private User buyer;
	private String headerText;
	private String matGrp;
	private String matGrpDesc;
	private String itemCategory;
	private String itemText;
	private String materialPOText;
	private String fixedVendorCode;
	private String desireVendorCode;
	private String plantDESC;
	
	@Id
	@SequenceGenerator(name="t_pr_line_seq", sequenceName="t_pr_line_seq",allocationSize=1)
	@GeneratedValue(generator="t_pr_line_seq",strategy=GenerationType.SEQUENCE)
	@Column(name="t_pr_line_id",updatable=false)
	public Long getPrLineId() {
		return prLineId;
	}
	public void setPrLineId(Long prLineId) {
		this.prLineId = prLineId;
	}
	
	@Column(name="value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="pr_line_number")
	public String getPrLineNumber() {
		return prLineNumber;
	}
	public void setPrLineNumber(String prLineNumber) {
		this.prLineNumber = prLineNumber;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_pr_id")
	public PR getPr() {
		return pr;
	}
	public void setPr(PR pr) {
		this.pr = pr;
	}
	
	@Column(name="material_code")
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	@Column(name="line_number")
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	@Column(name="material_desc")
	public String getMaterialDesc() {
		return materialDesc;
	}
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	
	@Column(name="qty")
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	@Column(name="rate")
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	@Column(name="delivery_date")
	public Date getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fixed_vendor",referencedColumnName="ad_user_id")
	public User getFixedVendor() {
		return fixedVendor;
	}
	public void setFixedVendor(User fixedVendor) {
		this.fixedVendor = fixedVendor;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="desired_vendor",referencedColumnName="ad_user_id")
	public User getDesiredVendor() {
		return desiredVendor;
	}
	public void setDesiredVendor(User desiredVendor) {
		this.desiredVendor = desiredVendor;
	}
	
	@Column(name="tracking_no")
	public String getTrackingNo() {
		return trackingNo;
	}
	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	@Column(name="purchase_group")
	public String getPurchaseGroup() {
		return purchaseGroup;
	}
	public void setPurchaseGroup(String purchaseGroup) {
		this.purchaseGroup = purchaseGroup;
	}
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="parent_pr_line_id",referencedColumnName="t_pr_line_id")
//	public PRLine getParentPRLine() {
//		return parentPRLine;
//	}
//	public void setParentPRLine(PRLine parentPRLine) {
//		this.parentPRLine = parentPRLine;
//	}
	
	
	@Column(name="plant")
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	
	@Column(name="required_date")
	public Date getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	
	@Column(name="assignment")
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	
	@Column(name="account_assignment")
	public String getAccountAssignment() {
		return accountAssignment;
	}
	public void setAccountAssignment(String accountAssignment) {
		this.accountAssignment = accountAssignment;
	}
	
	@Column(name="uom")
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	
	@Column(name="price")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="buyer_id",referencedColumnName="ad_user_id")
	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	@Column(name="header_text")
	public String getHeaderText() {
		return headerText;
	}
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	@Column(name="mat_grp")
	public String getMatGrp() {
		return matGrp;
	}
	public void setMatGrp(String matGrp) {
		this.matGrp = matGrp;
	}
	@Column(name="mat_grp_desc")
	public String getMatGrpDesc() {
		return matGrpDesc;
	}
	public void setMatGrpDesc(String matGrpDesc) {
		this.matGrpDesc = matGrpDesc;
	}
	@Column(name="item_category")
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	@Column(name="item_text")
	public String getItemText() {
		return itemText;
	}
	public void setItemText(String itemText) {
		this.itemText = itemText;
	}
	@Column(name="material_po_text")
	public String getMaterialPOText() {
		return materialPOText;
	}
	public void setMaterialPOText(String materialPOText) {
		this.materialPOText = materialPOText;
	}
	@Column(name="fixed_vendor_code")
	public String getFixedVendorCode() {
		return fixedVendorCode;
	}
	public void setFixedVendorCode(String fixedVendorCode) {
		this.fixedVendorCode = fixedVendorCode;
	}
	@Column(name="desire_vendor_code")
	public String getDesireVendorCode() {
		return desireVendorCode;
	}
	public void setDesireVendorCode(String desireVendorCode) {
		this.desireVendorCode = desireVendorCode;
	}

	
	
	@Column(name="plantdesc")
	public String getPlantDESC() {
		return plantDESC;
	}
	public void setPlantDESC(String plantDESC) {
		this.plantDESC = plantDESC;
	}
	
	
	

}
