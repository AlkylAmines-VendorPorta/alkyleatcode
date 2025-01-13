package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;


@Entity
@Table(name = "t_contract_item")
public class ContractItem extends ContextPO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long contractItemId;
	private ContractHeader contractHeader;
	private String item;
	private String materialNo;
	private String shortTexr;
	private String targetQuantity;
	private String netPrice;
	private String taxCode;
	private String valuationType;
	private String materialGroup;
	private WinnerSelection winnerSelection;
	
	
	@Id
	@SequenceGenerator(name = "t_contract_item_seq", sequenceName = "t_contract_item_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_contract_item_seq")
	@Column(name = "t_contract_item_id", updatable = false)
	public Long getContractItemId() {
		return contractItemId;
	}
	public void setContractItemId(Long contractItemId) {
		this.contractItemId = contractItemId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_contract_header_id")
	public ContractHeader getContractHeader() {
		return contractHeader;
	}
	public void setContractHeader(ContractHeader contractHeader) {
		this.contractHeader = contractHeader;
	}
	
	@Column(name = "item")
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	@Column(name = "material_no")
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	
	@Column(name = "short_texr")
	public String getShortTexr() {
		return shortTexr;
	}
	public void setShortTexr(String shortTexr) {
		this.shortTexr = shortTexr;
	}
	
	@Column(name = "target_quantity")
	public String getTargetQuantity() {
		return targetQuantity;
	}
	public void setTargetQuantity(String targetQuantity) {
		this.targetQuantity = targetQuantity;
	}
	
	@Column(name = "net_price")
	public String getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(String netPrice) {
		this.netPrice = netPrice;
	}
	
	@Column(name = "tax_code")
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	
	@Column(name = "valuation_type")
	public String getValuationType() {
		return valuationType;
	}
	public void setValuationType(String valuationType) {
		this.valuationType = valuationType;
	}
	
	@Column(name = "material_group")
	public String getMaterialGroup() {
		return materialGroup;
	}
	public void setMaterialGroup(String materialGroup) {
		this.materialGroup = materialGroup;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_winner_selection_id")
	public WinnerSelection getWinnerSelection() {
		return winnerSelection;
	}
	public void setWinnerSelection(WinnerSelection winnerSelection) {
		this.winnerSelection = winnerSelection;
	}
	
}
