package com.novelerp.appcontext.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.novelerp.core.dto.CommonDto;

public class CommonContextDto extends CommonDto {
	

	private static final long serialVersionUID = 1L;
	
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
			property="userId")
	private UserDto createdBy;
	
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
			property="userId")
	private UserDto updatedBy;
	
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
			property="bPartnerId")
	private BPartnerDto partner;

	public BPartnerDto getPartner() {
		return partner;
	}
	public void setPartner(BPartnerDto partner) {
		this.partner = partner;
	}
	public UserDto getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDto createdBy) {
		this.createdBy = createdBy;
	}
	public UserDto getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(UserDto updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@JsonIgnore	
	public UserDto getOwner() {
		return this.createdBy;
	}
}
