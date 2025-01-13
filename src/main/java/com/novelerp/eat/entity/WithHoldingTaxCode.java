package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name = "m_withholding_tax_code")
public class WithHoldingTaxCode extends ContextPO {

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

	@Id
	@SequenceGenerator(name = "m_withholding_tax_code_seq", sequenceName = "m_withholding_tax_code_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_withholding_tax_code_seq")
	@Column(name = "m_withholding_tax_code_id", updatable = false)
	public Long getWithHoldingTaxCodeId() {
		return withHoldingTaxCodeId;
	}

	public void setWithHoldingTaxCodeId(Long withHoldingTaxCodeId) {
		this.withHoldingTaxCodeId = withHoldingTaxCodeId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "withholding_tax_type")
	public String getWithholdingTaxType() {
		return withholdingTaxType;
	}

	public void setWithholdingTaxType(String withholdingTaxType) {
		this.withholdingTaxType = withholdingTaxType;
	}

	@Column(name = "withholding_tax_code")
	public String getWithholdingTaxCode() {
		return withholdingTaxCode;
	}

	public void setWithholdingTaxCode(String withholdingTaxCode) {
		this.withholdingTaxCode = withholdingTaxCode;
	}

	@Column(name = "official_withholding_tax_type")
	public String getOfclWithholdingTaxKey() {
		return ofclWithholdingTaxKey;
	}

	public void setOfclWithholdingTaxKey(String ofclWithholdingTaxKey) {
		this.ofclWithholdingTaxKey = ofclWithholdingTaxKey;
	}

	@Column(name = "percentage_subject_totax")
	public Double getPercentageSubtoTax() {
		return PercentageSubtoTax;
	}

	public void setPercentageSubtoTax(Double percentageSubtoTax) {
		PercentageSubtoTax = percentageSubtoTax;
	}

	@Column(name = "withholding_tax_rate")
	public Long getWithholdingTaxRate() {
		return withholdingTaxRate;
	}

	public void setWithholdingTaxRate(Long withholdingTaxRate) {
		this.withholdingTaxRate = withholdingTaxRate;
	}

}
