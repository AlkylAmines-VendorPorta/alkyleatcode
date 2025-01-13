package com.novelerp.alkyl.dto;

import com.novelerp.appbase.master.dto.FinancialYearDto;
import com.novelerp.appbase.master.dto.PartnerFinancialAttachmentDto;

public class PartnerFinancialDetailDto {

//	private FinancialYearDto financialYear;
	private PartnerFinancialAttachmentDto balanceSheet;
	private PartnerFinancialAttachmentDto profitAndLoss;
	private PartnerFinancialAttachmentDto turnOver;
	private String financialYearNew;
	
//	public FinancialYearDto getFinancialYear() {
//		return financialYear;
//	}
//	public void setFinancialYear(FinancialYearDto financialYear) {
//		this.financialYear = financialYear;
//	}
	public PartnerFinancialAttachmentDto getBalanceSheet() {
		return balanceSheet;
	}
	public void setBalanceSheet(PartnerFinancialAttachmentDto balanceSheet) {
		this.balanceSheet = balanceSheet;
	}
	public PartnerFinancialAttachmentDto getProfitAndLoss() {
		return profitAndLoss;
	}
	public void setProfitAndLoss(PartnerFinancialAttachmentDto profitAndLoss) {
		this.profitAndLoss = profitAndLoss;
	}
	public PartnerFinancialAttachmentDto getTurnOver() {
		return turnOver;
	}
	public void setTurnOver(PartnerFinancialAttachmentDto turnOver) {
		this.turnOver = turnOver;
	}
	public String getFinancialYearNew() {
		return financialYearNew;
	}
	public void setFinancialYearNew(String financialYearNew) {
		this.financialYearNew = financialYearNew;
	}
	
	
	
}
