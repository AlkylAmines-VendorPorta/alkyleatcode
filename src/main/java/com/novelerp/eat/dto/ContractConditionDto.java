package com.novelerp.eat.dto;

import java.math.BigDecimal;

import com.novelerp.appcontext.dto.CommonContextDto;

public class ContractConditionDto extends CommonContextDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long contractConditionId;
	private ContractHeaderDto contractHeader;
	private String srvcLineItemNo;
	private String srvcLineItem;
	private String conditionType;
	private BigDecimal amount;
	private WinnerSelectionDto winnerSelection;
	
	public Long getContractConditionId() {
		return contractConditionId;
	}
	public void setContractConditionId(Long contractConditionId) {
		this.contractConditionId = contractConditionId;
	}
	
	public String getSrvcLineItemNo() {
		return srvcLineItemNo;
	}
	public void setSrvcLineItemNo(String srvcLineItemNo) {
		this.srvcLineItemNo = srvcLineItemNo;
	}
	public String getSrvcLineItem() {
		return srvcLineItem;
	}
	public void setSrvcLineItem(String srvcLineItem) {
		this.srvcLineItem = srvcLineItem;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public ContractHeaderDto getContractHeader() {
		return contractHeader;
	}
	public void setContractHeader(ContractHeaderDto contractHeader) {
		this.contractHeader = contractHeader;
	}
	public WinnerSelectionDto getWinnerSelection() {
		return winnerSelection;
	}
	public void setWinnerSelection(WinnerSelectionDto winnerSelection) {
		this.winnerSelection = winnerSelection;
	}

}
