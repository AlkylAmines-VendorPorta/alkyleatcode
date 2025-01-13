package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name = "m_vendor_account_group")
public class VendorAccountGroup extends ContextPO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long vendorAccountGroupId;
	private String accountGrp;
	private String name;
	
	@Id
	@SequenceGenerator(name = "m_vendor_account_group_seq", sequenceName = "m_vendor_account_group_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_vendor_account_group_seq")
	@Column(name = "m_vendor_account_group_id", updatable = false)
	public Long getVendorAccountGroupId() {
		return vendorAccountGroupId;
	}
	public void setVendorAccountGroupId(Long vendorAccountGroupId) {
		this.vendorAccountGroupId = vendorAccountGroupId;
	}
	public String getAccountGrp() {
		return accountGrp;
	}
	public void setAccountGrp(String accountGrp) {
		this.accountGrp = accountGrp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
