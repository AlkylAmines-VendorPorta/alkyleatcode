/**
	 * @author Ankush
	 * 
	 *  
	 */

package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_reference_list")
public class ReferenceList extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long referenceListId;
	private String description;
	private String referenceCode;
	private String code;
	private Reference reference;
	private String name;
	private String actionName;
	private String auctionUrl;
	private String tenderUrl;
	
	@Id
	@SequenceGenerator(name="m_referenceList_seq",sequenceName="m_referenceList_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_referenceList_seq")	
	@Column(name = "m_reference_list_id", updatable=false)
	public Long getReferenceListId() {
		return referenceListId;
	}
	public void setReferenceListId(Long referenceListId) {
		this.referenceListId = referenceListId;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "reference_code")
	public String getReferenceCode() {
		return referenceCode;
	}
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	
	@Column(name = "value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "m_reference_id")
	public Reference getReference() {
		return reference;
	}
	public void setReference(Reference reference) {
		this.reference = reference;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="action_name")
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	@Column(name="auction_url")
	public String getAuctionUrl() {
		return auctionUrl;
	}
	public void setAuctionUrl(String auctionUrl) {
		this.auctionUrl = auctionUrl;
	}
	@Column(name="tender_url")
	public String getTenderUrl() {
		return tenderUrl;
	}
	public void setTenderUrl(String tenderUrl) {
		this.tenderUrl = tenderUrl;
	}
	
	
}