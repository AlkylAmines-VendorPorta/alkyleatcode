package com.novelerp.alkyl.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class ThirdPartyPRApproverDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long thirdPartyPRApproverId;
	private String email;
	private String code;
	private String name;
	private String description;
	private PRDto pr;
	
	public Long getThirdPartyPRApproverId() {
		return thirdPartyPRApproverId;
	}
	public void setThirdPartyPRApproverId(Long thirdPartyPRApproverId) {
		this.thirdPartyPRApproverId = thirdPartyPRApproverId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PRDto getPr() {
		return pr;
	}
	public void setPr(PRDto pr) {
		this.pr = pr;
	}
	
	
}
