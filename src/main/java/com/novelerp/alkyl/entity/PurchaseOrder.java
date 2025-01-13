package com.novelerp.alkyl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;


@Entity
@Table(name="t_purchase_order")
public class PurchaseOrder extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long purchaseOrderId;
	private String name;
	private String code;
	private String description;
	private String documentType;
	private String purchaseOrderNumber;
	private Date date;
	private String vendorCode;
	private String vendorName;
	private String incomeTerms;
	private String purchaseGroup;
	private Integer versionNumber;
	private String status;
	private String remark;
	private String pstyp;
	private Attachment poAtt;
	private User reqby;
	private String deleted;
	private String poemail;
	public String vendorPortal;
	public String outboundDeliveryNo;
	
	public String poTypeMsg;
	//public String doctyp;
	private Date prDate;
	public String userID;
	
	@Id
	@SequenceGenerator(name = "t_purchase_order_seq", sequenceName = "t_purchase_order_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_purchase_order_seq")
	@Column(name="t_purchase_order_id",updatable = false)
	
	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="document_type")
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	@Column(name="purchase_odrer_number")
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="vendor_code")
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	
	@Column(name="vendor_name")
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	@Column(name="income_terms")
	public String getIncomeTerms() {
		return incomeTerms;
	}
	public void setIncomeTerms(String incomeTerms) {
		this.incomeTerms = incomeTerms;
	}
	
	@Column(name="purchase_group")
	public String getPurchaseGroup() {
		return purchaseGroup;
	}
	public void setPurchaseGroup(String purchaseGroup) {
		this.purchaseGroup = purchaseGroup;
	}
	
	@Column(name="version_number")
	public Integer getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name="pstyp")
	public String getPstyp() {
		return pstyp;
	}
	public void setPstyp(String pstyp) {
		this.pstyp = pstyp;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_attachment_id")
	public Attachment getPoAtt() {
		return poAtt;
	}
	public void setPoAtt(Attachment poAtt) {
		this.poAtt = poAtt;
	}
	
	@OneToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="ad_user_id")
	public User getReqby() {
		return reqby;
	}
	public void setReqby(User reqby) {
		this.reqby = reqby;
	}
	
	
	@Column(name="deleted")
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	@Column(name="poemail")
	public String getPoemail() {
		return poemail;
	}
	public void setPoemail(String poemail) {
		this.poemail = poemail;
	}
	
	@Column(name="vendorPortal")
	public String getVendorPortal() {
		return vendorPortal;
	}
	public void setVendorPortal(String vendorPortal) {
		this.vendorPortal = vendorPortal;
	}
	
	@Column(name="outboundDeliveryNo")	
	public String getOutboundDeliveryNo() {
		return outboundDeliveryNo;
	}
	public void setOutboundDeliveryNo(String outboundDeliveryNo) {
		this.outboundDeliveryNo = outboundDeliveryNo;
	}

	@Column(name="potypemsg")
	public String getPoTypeMsg() {
		return poTypeMsg;
	}
	
	public void setPoTypeMsg(String poTypeMsg) {
		this.poTypeMsg = poTypeMsg;
	}

	
//	@Column(name="doctyp")
//	public String getDoctyp() {
//		return doctyp;
//	}
//	public void setDoctyp(String doctyp) {
//		this.doctyp = doctyp;
//	}
//	
	
	@Column(name="prdate")
	public Date getPrDate() {
		return prDate;
	}
	public void setPrDate(Date prDate) {
		this.prDate = prDate;
	}
	
	
	@Column(name="sap_user_id")
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
