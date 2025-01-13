package com.novelerp.eat.dto;

import java.util.Date;
import java.util.List;

import com.novelerp.alkyl.dto.GateEntryDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;

public class MaterialGateInDto extends CommonContextDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long gateInId;
	private GateEntryDto gateEntry;
	private String status;
	private String docNo;
	private List<MaterialGateInListDto> materialGateInList;
	private UserDto gateIn;
	private Date gateInDate;
	private UserDto materialCheck;
	private Date materialCheckDate;
	private UserDto closedBy;
	private Date closedDate;
	public Long getGateInId() {
		return gateInId;
	}
	public void setGateInId(Long gateInId) {
		this.gateInId = gateInId;
	}
	public GateEntryDto getGateEntry() {
		return gateEntry;
	}
	public void setGateEntry(GateEntryDto gateEntry) {
		this.gateEntry = gateEntry;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public List<MaterialGateInListDto> getMaterialGateInList() {
		return materialGateInList;
	}
	public void setMaterialGateInList(List<MaterialGateInListDto> materialGateInList) {
		this.materialGateInList = materialGateInList;
	}
	public UserDto getGateIn() {
		return gateIn;
	}
	public void setGateIn(UserDto gateIn) {
		this.gateIn = gateIn;
	}
	public Date getGateInDate() {
		return gateInDate;
	}
	public void setGateInDate(Date gateInDate) {
		this.gateInDate = gateInDate;
	}
	public UserDto getMaterialCheck() {
		return materialCheck;
	}
	public void setMaterialCheck(UserDto materialCheck) {
		this.materialCheck = materialCheck;
	}
	public Date getMaterialCheckDate() {
		return materialCheckDate;
	}
	public void setMaterialCheckDate(Date materialCheckDate) {
		this.materialCheckDate = materialCheckDate;
	}
	public UserDto getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(UserDto closedBy) {
		this.closedBy = closedBy;
	}
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	
	

}
