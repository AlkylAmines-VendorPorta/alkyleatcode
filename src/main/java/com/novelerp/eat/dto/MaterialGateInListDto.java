package com.novelerp.eat.dto;

import com.novelerp.alkyl.dto.GateEntryLineDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.eat.entity.MaterialGateIn;

public class MaterialGateInListDto extends CommonContextDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long gateInListId;
	private GateEntryLineDto gateEntryLine;
	private Double gateInQty;
	private Double rejectQty;
	private Double acceptQty;
	private MaterialGateInDto materialGateIn;
	public Long getGateInListId() {
		return gateInListId;
	}
	public void setGateInListId(Long gateInListId) {
		this.gateInListId = gateInListId;
	}
	public GateEntryLineDto getGateEntryLine() {
		return gateEntryLine;
	}
	public void setGateEntryLine(GateEntryLineDto gateEntryLine) {
		this.gateEntryLine = gateEntryLine;
	}
	public Double getGateInQty() {
		return gateInQty;
	}
	public void setGateInQty(Double gateInQty) {
		this.gateInQty = gateInQty;
	}
	public Double getRejectQty() {
		return rejectQty;
	}
	public void setRejectQty(Double rejectQty) {
		this.rejectQty = rejectQty;
	}
	public Double getAcceptQty() {
		return acceptQty;
	}
	public void setAcceptQty(Double acceptQty) {
		this.acceptQty = acceptQty;
	}
	public MaterialGateInDto getMaterialGateIn() {
		return materialGateIn;
	}
	public void setMaterialGateIn(MaterialGateInDto materialGateIn) {
		this.materialGateIn = materialGateIn;
	}
	
	

}
