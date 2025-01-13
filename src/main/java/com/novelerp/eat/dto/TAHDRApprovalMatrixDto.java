package com.novelerp.eat.dto;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;

public class TAHDRApprovalMatrixDto extends CommonContextDto{

	/**
	 * Ankita
	 */
	private static final long serialVersionUID = 1L;
	private Long tahdrApprovalMatrixId;
	private Long levels;
	private UserDto user;
	private String remarks;
	private String status;
	private String action;
	private TAHDRDto tahdr;
	
	public Long getTahdrApprovalMatrixId() {
		return tahdrApprovalMatrixId;
	}
	public void setTahdrApprovalMatrixId(Long tahdrApprovalMatrixId) {
		this.tahdrApprovalMatrixId = tahdrApprovalMatrixId;
	}
	/*public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}*/
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	/*public TAHDRDetailDto getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetailDto tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}*/
	public TAHDRDto getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDRDto tahdr) {
		this.tahdr = tahdr;
	}
	public Long getLevels() {
		return levels;
	}
	public void setLevels(Long levels) {
		this.levels = levels;
	}
	
	
	
}
