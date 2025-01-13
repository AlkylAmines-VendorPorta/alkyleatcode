package com.novelerp.alkyl.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.alkyl.dto.SecurityPOHeaderDto;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="t_asn_items")
public class SecurityPOItem extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String code;
	private String description;
	private Long securityAsnLineId;
	private String lineItemNo;
	private Double deliveryQuantity;
	private PurchaseOrderLine poLine; 
	private SecurityPOHeader securityPOHeaderDto;

	
	@Id
	@SequenceGenerator(name = "t_asn_items_seq", sequenceName = "t_asn_items_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_asn_items_seq")
	
	
	@Column(name="t_advance_shipment_notice_line_id",updatable = false)
	public Long getAdvanceShipmentNoticeLineId() {
		return securityAsnLineId;
	}
	public void setAdvanceShipmentNoticeLineId(Long securityAsnLineId) {
		this.securityAsnLineId = securityAsnLineId;
	}
	@Column(name="line_item_no")
	public String getLineItemNo() {
		return lineItemNo;
	}
	public void setLineItemNo(String lineItemNo) {
		this.lineItemNo = lineItemNo;
	}
	
	@Column(name="delivery_quanity")
	public Double getDeliveryQuantity() {
		return deliveryQuantity;
	}
	public void setDeliveryQuantity(Double deliveryQuantity) {
		this.deliveryQuantity = deliveryQuantity;
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
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_purchase_order_line_id",referencedColumnName="t_purchase_order_line_id")
	
	public PurchaseOrderLine getPoLine() {
		return poLine;
	}
	public void setPoLine(PurchaseOrderLine poLine) {
		this.poLine = poLine;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_asn_header_id",referencedColumnName="t_asn_header_id")
	public SecurityPOHeader getSecurityPOHeaderDto() {
		return securityPOHeaderDto;
	}
	public void setSecurityPOHeaderDto(SecurityPOHeader securityPOHeaderDto) {
		this.securityPOHeaderDto = securityPOHeaderDto;
	}



}
