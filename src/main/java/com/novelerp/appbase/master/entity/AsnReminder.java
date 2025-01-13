package com.novelerp.appbase.master.entity;

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

import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;

@Entity
@Table(name="m_asnreminder")
public class AsnReminder extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long reminderId;
	private String vendorEmail;
	private String description;
	private String path;
	private String fileName;
	private String type;
	private byte[] media;
	private String isDocEncrypted;
	private String poNo;
	private String invoiceNo;
	private String invoiceDate;
	private User vendor;
	
	@Id
	@SequenceGenerator(name="m_asnreminder_seq",sequenceName="m_asnreminder_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.AUTO,  generator="m_asnreminder_seq")	
	@Column(name = "m_reminder_id", updatable=false)
	public Long getReminderId() {
		return reminderId;
	}
	public void setReminderId(Long reminderId) {
		this.reminderId = reminderId;
	}
	
	@Column(name="vendor_email")
	public String getVendorEmail() {
		return vendorEmail;
	}
	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Column(name="file_name")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="media")
	public byte[] getMedia() {
		return media;
	}
	public void setMedia(byte[] media) {
		this.media = media;
	}
	@Column(name="is_doc_encrypted")
	public String getIsDocEncrypted() {
		return isDocEncrypted;
	}
	public void setIsDocEncrypted(String isDocEncrypted) {
		this.isDocEncrypted = isDocEncrypted;
	}
	@Column(name="po_no")
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	@Column(name="invoice_no")
	public String getInvoiceNo() {
		return invoiceNo;
	}
	@OneToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="ad_user_id")
	public User getVendor() {
		return vendor;
	}
	public void setVendor(User vendor) {
		this.vendor = vendor;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	@Column(name="invoice_date")
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	
	
}
