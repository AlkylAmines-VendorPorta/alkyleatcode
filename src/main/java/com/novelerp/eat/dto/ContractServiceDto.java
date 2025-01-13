package com.novelerp.eat.dto;

import java.math.BigDecimal;

import com.novelerp.appcontext.dto.CommonContextDto;

public class ContractServiceDto extends CommonContextDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long contractServiceId;
	private ContractHeaderDto contractHeader;
	private String materialNo;
	private String srvcLineItemNo;
	private String serviceNo;
	private BigDecimal quantity;
	private BigDecimal amount;
	private String costCenter;
	private WinnerSelectionDto winnerSelection;
	
	public Long getContractServiceId() {
		return contractServiceId;
	}
	public void setContractServiceId(Long contractServiceId) {
		this.contractServiceId = contractServiceId;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public String getSrvcLineItemNo() {
		return srvcLineItemNo;
	}
	public void setSrvcLineItemNo(String srvcLineItemNo) {
		this.srvcLineItemNo = srvcLineItemNo;
	}
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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
