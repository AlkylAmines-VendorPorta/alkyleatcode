package com.novelerp.alkyl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="t_po_line_condition")
public class PoLineCondition extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long poLineConditionId;
	private PurchaseOrderLine purchaseOrderLine;
	private ConditionMaster condition;
	
	private String name;
	private String code;
	private String description;
	
	@Id
	@SequenceGenerator(name = "t_po_line_condition_seq", sequenceName = "t_po_line_condition_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_po_line_condition_seq")
	@Column(name="t_po_line_condition_id",updatable = false)
	public Long getPoLineConditionId() {
		return poLineConditionId;
	}
	public void setPoLineConditionId(Long poLineConditionId) {
		this.poLineConditionId = poLineConditionId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_purchase_order_line_id", referencedColumnName = "t_purchase_order_line_id")
	public PurchaseOrderLine getPurchaseOrderLine() {
		return purchaseOrderLine;
	}
	public void setPurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
		this.purchaseOrderLine = purchaseOrderLine;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_condition_master_id", referencedColumnName = "m_condition_master_id")
	public ConditionMaster getCondition() {
		return condition;
	}
	public void setCondition(ConditionMaster condition) {
		this.condition= condition;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
