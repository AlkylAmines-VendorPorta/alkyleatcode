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
@Table(name="t_asn_line_cost_center")
public class ASNLineCostCenter extends ContextPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long asnLineCostCenterId;
	private String name;
	private String code;
	private String description;
	private AdvanceShipmentNoticeLine asnLine;
	private String costCenter;
	private String storageLocation;
	private String quantity;
	//private String fundCenter;
	
	@Id
	@SequenceGenerator(name = "t_asn_line_cost_center_seq", sequenceName = "t_asn_line_cost_center_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_asn_line_cost_center_seq")
	@Column(name="t_asn_line_cost_center_id",updatable = false)
	public Long getAsnLineCostCenterId() {
		return asnLineCostCenterId;
	}
	public void setAsnLineCostCenterId(Long asnLineCostCenterId) {
		this.asnLineCostCenterId = asnLineCostCenterId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="code")
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="asn_line_id",referencedColumnName="t_advance_shipment_notice_line_id")
	public AdvanceShipmentNoticeLine getAsnLine() {
		return asnLine;
	}
	public void setAsnLine(AdvanceShipmentNoticeLine asnLine) {
		this.asnLine = asnLine;
	}
	@Column(name="cost_center")
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	@Column(name="storage_location")
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	@Column(name="quantity")
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	
//	@Column(name="fund_center")
//	public String getFundCenter() {
//		return fundCenter;
//	}
//	public void setFundCenter(String fundCenter) {
//		this.fundCenter = fundCenter;
//	}
	
}
