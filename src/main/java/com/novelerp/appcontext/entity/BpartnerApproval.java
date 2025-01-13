package com.novelerp.appcontext.entity;

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

/**
 * 
 * @author varsha
 *
 */
@Entity
@Table(name="bp_partner_approval")
public class BpartnerApproval extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long bpartnerApprovalId;
	private Bpartner approvalPartner;
	private String isASApproved;/*Account Section*/
	private String isPGApproved;/*Purchase Group*/
	private String isEEApproved;/*Executive Engineer*/
	private String isSEApproved;/*Superintend Engineer*/
	private String isCEApproved;/*Chief Engineer*/
	
	@Id
	@SequenceGenerator(name="bp_partner_approval_seq",sequenceName="bp_partner_approval_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="bp_partner_approval_seq")	
	@Column(name = "bp_partner_approval_id", updatable=false)
	public Long getBpartnerApprovalId() {
		return bpartnerApprovalId;
	}
	public void setBpartnerApprovalId(Long bpartnerApprovalId) {
		this.bpartnerApprovalId = bpartnerApprovalId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="approval_partner",referencedColumnName="m_bpartner_id")
	public Bpartner getApprovalPartner() {
		return approvalPartner;
	}
	public void setApprovalPartner(Bpartner approvalPartner) {
		this.approvalPartner = approvalPartner;
	}
	@Column(name="is_as_approved")
	public String getIsASApproved() {
		return isASApproved;
	}
	public void setIsASApproved(String isASApproved) {
		this.isASApproved = isASApproved;
	}
	@Column(name="is_pg_approved")
	public String getIsPGApproved() {
		return isPGApproved;
	}
	public void setIsPGApproved(String isPGApproved) {
		this.isPGApproved = isPGApproved;
	}
	@Column(name="is_ee_approved")
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	@Column(name="is_se_approved")
	public String getIsSEApproved() {
		return isSEApproved;
	}
	public void setIsSEApproved(String isSEApproved) {
		this.isSEApproved = isSEApproved;
	}
	@Column(name="is_ce_approved")
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	
	

}
