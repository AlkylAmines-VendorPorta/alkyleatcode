package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author Aman
 */

@Entity
@Table(name="m_bp_org_financial")
public class PartnerOrgFinancial extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long partnerOrgFinancialId;
/*	private Set<PartnerFinancialAttachment> profitAndLossCopies;
	private Set<PartnerFinancialAttachment> balanceSheetCopies;
	private Set<PartnerFinancialAttachment> turnOverCopies;*/
	
	@Id
	@SequenceGenerator(name="m_bp_org_financial_seq",sequenceName="m_bp_org_financial_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_financial_seq")	
	@Column(name = "m_bp_org_financial_id", updatable=false)
	public Long getPartnerOrgFinancialId() {
		return partnerOrgFinancialId;
	}
	public void setPartnerOrgFinancialId(Long partnerOrgFinancialId) {
		this.partnerOrgFinancialId = partnerOrgFinancialId;
	}
/*	@OneToMany(mappedBy="partnerOrgFinancial")
	public Set<PartnerFinancialAttachment> getProfitAndLossCopies() {
		return profitAndLossCopies;
	}
	public void setProfitAndLossCopies(Set<PartnerFinancialAttachment> profitAndLossCopies) {
		this.profitAndLossCopies = profitAndLossCopies;
	}
	@OneToMany(mappedBy="partnerOrgFinancial")
	public Set<PartnerFinancialAttachment> getBalanceSheetCopies() {
		return balanceSheetCopies;
	}
	public void setBalanceSheetCopies(Set<PartnerFinancialAttachment> balanceSheetCopies) {
		this.balanceSheetCopies = balanceSheetCopies;
	}
	@OneToMany(mappedBy="partnerOrgFinancial")
	public Set<PartnerFinancialAttachment> getTurnOverCopies() {
		return turnOverCopies;
	}
	public void setTurnOverCopies(Set<PartnerFinancialAttachment> turnOverCopies) {
		this.turnOverCopies = turnOverCopies;
	}*/
	
	
	
}
