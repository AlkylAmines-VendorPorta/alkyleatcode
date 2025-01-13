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

import com.novelerp.alkyl.entity.GateEntryLine;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="t_material_gate_in_list")
public class MaterialGateInList extends ContextPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long gateInListId;
	private GateEntryLine gateEntryLine;
	private Double gateInQty;
	private Double rejectQty;
	private Double acceptQty;
	private MaterialGateIn materialGateIn;
	@Id
	@SequenceGenerator(name = "t_material_gate_in_list_seq",sequenceName="t_material_gate_in_list_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t_material_gate_in_list_seq")
	@Column(name="t_material_gate_in_list_id",updatable=false)
	public Long getGateInListId() {
		return gateInListId;
	}
	public void setGateInListId(Long gateInListId) {
		this.gateInListId = gateInListId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_gate_entry_line_id")
	public GateEntryLine getGateEntryLine() {
		return gateEntryLine;
	}
	public void setGateEntryLine(GateEntryLine gateEntryLine) {
		this.gateEntryLine = gateEntryLine;
	}
	@Column(name="gate_in_qty")
	public Double getGateInQty() {
		return gateInQty;
	}
	public void setGateInQty(Double gateInQty) {
		this.gateInQty = gateInQty;
	}
	@Column(name="reject_qty")
	public Double getRejectQty() {
		return rejectQty;
	}
	public void setRejectQty(Double rejectQty) {
		this.rejectQty = rejectQty;
	}
	@Column(name="accept_qty")
	public Double getAcceptQty() {
		return acceptQty;
	}
	public void setAcceptQty(Double acceptQty) {
		this.acceptQty = acceptQty;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_material_gate_in_id")
	public MaterialGateIn getMaterialGateIn() {
		return materialGateIn;
	}
	public void setMaterialGateIn(MaterialGateIn materialGateIn) {
		this.materialGateIn = materialGateIn;
	}
	
	
	

}
