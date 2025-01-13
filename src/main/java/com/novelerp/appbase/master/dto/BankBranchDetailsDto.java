package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.RegionDto;

public class BankBranchDetailsDto extends CommonContextDto{

	/**
	 * Ankita Tirodkar
	 */
	private static final long serialVersionUID = 1L;
	private Long bankBranchDetailsId;
	private BankNameDetailsDto bankName;
	private String branchName;
	private String iFSCCode;
	private RegionDto branchState;
	
	
	public Long getBankBranchDetailsId() {
		return bankBranchDetailsId;
	}
	public void setBankBranchDetailsId(Long bankBranchDetailsId) {
		this.bankBranchDetailsId = bankBranchDetailsId;
	}
	public BankNameDetailsDto getBankName() {
		return bankName;
	}
	public void setBankName(BankNameDetailsDto bankName) {
		this.bankName = bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getiFSCCode() {
		return iFSCCode;
	}
	public void setiFSCCode(String iFSCCode) {
		this.iFSCCode = iFSCCode;
	}
	public RegionDto getBranchState() {
		return branchState;
	}
	public void setBranchState(RegionDto branchState) {
		this.branchState = branchState;
	}
}

