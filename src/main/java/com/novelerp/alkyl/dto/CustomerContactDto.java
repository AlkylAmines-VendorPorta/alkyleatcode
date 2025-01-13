package com.novelerp.alkyl.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class CustomerContactDto extends CommonContextDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long customerContactId;
	private String code;
	private String name;
	private String description;
	private CustomerDto customer;
	private String email;
	private String mob;
	public Long getCustomerContactId() {
		return customerContactId;
	}
	public void setCustomerContactId(Long customerContactId) {
		this.customerContactId = customerContactId;
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
	public CustomerDto getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	
	
}
