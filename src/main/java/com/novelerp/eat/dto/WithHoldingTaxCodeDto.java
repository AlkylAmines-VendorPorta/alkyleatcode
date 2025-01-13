package com.novelerp.eat.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class WithHoldingTaxCodeDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long withHoldingTaxCodeId;
	private String name;
	private String withholdingTaxType;
	private String withholdingTaxCode;
	private String ofclWithholdingTaxKey;
	private Double PercentageSubtoTax;
	private Long withholdingTaxRate;
	
	public Long getWithHoldingTaxCodeId() {
		return withHoldingTaxCodeId;
	}
	public void setWithHoldingTaxCodeId(Long withHoldingTaxCodeId) {
		this.withHoldingTaxCodeId = withHoldingTaxCodeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWithholdingTaxType() {
		return withholdingTaxType;
	}
	public void setWithholdingTaxType(String withholdingTaxType) {
		this.withholdingTaxType = withholdingTaxType;
	}
	public String getWithholdingTaxCode() {
		return withholdingTaxCode;
	}
	public void setWithholdingTaxCode(String withholdingTaxCode) {
		this.withholdingTaxCode = withholdingTaxCode;
	}
	public String getOfclWithholdingTaxKey() {
		return ofclWithholdingTaxKey;
	}
	public void setOfclWithholdingTaxKey(String ofclWithholdingTaxKey) {
		this.ofclWithholdingTaxKey = ofclWithholdingTaxKey;
	}
	public Double getPercentageSubtoTax() {
		return PercentageSubtoTax;
	}
	public void setPercentageSubtoTax(Double percentageSubtoTax) {
		PercentageSubtoTax = percentageSubtoTax;
	}
	public Long getWithholdingTaxRate() {
		return withholdingTaxRate;
	}
	public void setWithholdingTaxRate(Long withholdingTaxRate) {
		this.withholdingTaxRate = withholdingTaxRate;
	}
	
}
