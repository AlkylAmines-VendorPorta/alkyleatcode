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
@Table(name = "t_contract_condition")
public class ContractCondition extends ContextPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long contractConditionId;
	private ContractHeader contractHeader;
	private String srvcLineItemNo;
	private String srvcLineItem;
	private String conditionType;
	private BigDecimal amount;
	private WinnerSelection winnerSelection;
	
	@Id
	@SequenceGenerator(name = "t_contract_condition_seq", sequenceName = "t_contract_condition_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_contract_condition_seq")
	@Column(name = "t_contract_condition_id", updatable = false)
	public Long getContractConditionId() {
		return contractConditionId;
	}
	public void setContractConditionId(Long contractConditionId) {
		this.contractConditionId = contractConditionId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_contract_header_id")
	public ContractHeader getContractHeader() {
		return contractHeader;
	}
	public void setContractHeader(ContractHeader contractHeader) {
		this.contractHeader = contractHeader;
	}
	
	@Column(name = "service_lineitem_no")
	public String getSrvcLineItemNo() {
		return srvcLineItemNo;
	}
	public void setSrvcLineItemNo(String srvcLineItemNo) {
		this.srvcLineItemNo = srvcLineItemNo;
	}
	
	@Column(name = "service_line_item")
	public String getSrvcLineItem() {
		return srvcLineItem;
	}
	public void setSrvcLineItem(String srvcLineItem) {
		this.srvcLineItem = srvcLineItem;
	}
	
	@Column(name = "condition_type")
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	
	@Column(name = "amount")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
