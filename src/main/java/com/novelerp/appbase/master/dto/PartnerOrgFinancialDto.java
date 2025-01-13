package com.novelerp.appbase.master.dto;

import java.util.Set;

import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * Partner organisation's financial details
 * 
 * @author Vivek Birdi
 *
 */
public class PartnerOrgFinancialDto extends CommonContextDto {

	
	private static final long serialVersionUID = 1L;
	private Long partnerOrgFinancialId;
	private Set<PartnerFinancialAttachmentDto> profitAndLossCopies;
	private Set<PartnerFinancialAttachmentDto> balanceSheetCopies;
	private Set<PartnerFinancialAttachmentDto> turnOverCopies;
	
	
	public Long getPartnerOrgFinancialId() {
		return partnerOrgFinancialId;
	}
	public void setPartnerOrgFinancialId(Long partnerOrgFinancialId) {
		this.partnerOrgFinancialId = partnerOrgFinancialId;
	}
	public Set<PartnerFinancialAttachmentDto> getProfitAndLossCopies() {
		return profitAndLossCopies;
	}
	public void setProfitAndLossCopies(Set<PartnerFinancialAttachmentDto> profitAndLossCopies) {
		this.profitAndLossCopies = profitAndLossCopies;
	}
	public Set<PartnerFinancialAttachmentDto> getBalanceSheetCopies() {
		return balanceSheetCopies;
	}
	public void setBalanceSheetCopies(Set<PartnerFinancialAttachmentDto> balanceSheetCopies) {
		this.balanceSheetCopies = balanceSheetCopies;
	}
	public Set<PartnerFinancialAttachmentDto> getTurnOverCopies() {
		return turnOverCopies;
	}
	public void setTurnOverCopies(Set<PartnerFinancialAttachmentDto> turnOverCopies) {
		this.turnOverCopies = turnOverCopies;
	}
	
	
}
