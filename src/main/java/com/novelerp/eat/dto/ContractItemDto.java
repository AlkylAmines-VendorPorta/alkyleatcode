package com.novelerp.eat.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class ContractItemDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long contractItemId;
	private ContractHeaderDto contractHeader;
	private String item;
	private String materialNo;
	private String shortTexr;
	private String targetQuantity;
	private String netPrice;
	private String taxCode;
	private String valuationType;
	private String materialGroup;
	private WinnerSelectionDto winnerSelection;
	
	public Long getContractItemId() {
		return contractItemId;
	}
	public void setContractItemId(Long contractItemId) {
		this.contractItemId = contractItemId;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public String getShortTexr() {
		return shortTexr;
	}
	public void setShortTexr(String shortTexr) {
		this.shortTexr = shortTexr;
	}
	public String getTargetQuantity() {
		return targetQuantity;
	}
	public void setTargetQuantity(String targetQuantity) {
		this.targetQuantity = targetQuantity;
	}
	public String getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(String netPrice) {
		this.netPrice = netPrice;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getValuationType() {
		return valuationType;
	}
	public void setValuationType(String valuationType) {
		this.valuationType = valuationType;
	}
	public String getMaterialGroup() {
		return materialGroup;
	}
	public void setMaterialGroup(String materialGroup) {
		this.materialGroup = materialGroup;
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
