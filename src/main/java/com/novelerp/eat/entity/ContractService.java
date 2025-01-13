package com.novelerp.eat.entity;

import java.math.BigDecimal;

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
@Table(name = "t_contract_service")
public class ContractService extends ContextPO{
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long contractServiceId;
	private ContractHeader contractHeader;
	private String materialNo;
	private String srvcLineItemNo;
	private String serviceNo;
	private BigDecimal quantity;
	private BigDecimal amount;
	private String costCenter;
	private WinnerSelection winnerSelection;
	
	@Id
	@SequenceGenerator(name = "t_contract_service_seq", sequenceName = "t_contract_service_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_contract_service_seq")
	@Column(name = "t_contract_service_id", updatable = false)
	public Long getContractServiceId() {
		return contractServiceId;
	}
	public void setContractServiceId(Long contractServiceId) {
		this.contractServiceId = contractServiceId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_contract_header_id")
	public ContractHeader getContractHeader() {
		return contractHeader;
	}
	public void setContractHeader(ContractHeader contractHeader) {
		this.contractHeader = contractHeader;
	}
	
	@Column(name = "material_no")
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	
	@Column(name = "service_lineitem_no")
	public String getSrvcLineItemNo() {
		return srvcLineItemNo;
	}
	public void setSrvcLineItemNo(String srvcLineItemNo) {
		this.srvcLineItemNo = srvcLineItemNo;
	}
	
	@Column(name = "service_no")
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	
	@Column(name = "quantity")
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	@Column(name = "amount")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Column(name = "cost_center")
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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
