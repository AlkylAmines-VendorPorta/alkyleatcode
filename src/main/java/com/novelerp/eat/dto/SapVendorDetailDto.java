package com.novelerp.eat.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class SapVendorDetailDto extends CommonContextDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long sapVendorDetailId;
	private WithHoldingTaxCodeDto idctrWithtTaxType;
	private WithHoldingTaxCodeDto withHdTaxCode1;
	private WithHoldingTaxCodeDto withHdTaxCode2;
	private VendorAccountGroupDto vendorAccountGroup;
	private ReconAccountDto reconAccount;
	private GSTVendorClassDto gstVendorClass;
	
	public Long getSapVendorDetailId() {
		return sapVendorDetailId;
	}
	public void setSapVendorDetailId(Long sapVendorDetailId) {
		this.sapVendorDetailId = sapVendorDetailId;
	}
	public WithHoldingTaxCodeDto getIdctrWithtTaxType() {
		return idctrWithtTaxType;
	}
	public void setIdctrWithtTaxType(WithHoldingTaxCodeDto idctrWithtTaxType) {
		this.idctrWithtTaxType = idctrWithtTaxType;
	}
	public WithHoldingTaxCodeDto getWithHdTaxCode1() {
		return withHdTaxCode1;
	}
	public void setWithHdTaxCode1(WithHoldingTaxCodeDto withHdTaxCode1) {
		this.withHdTaxCode1 = withHdTaxCode1;
	}
	public WithHoldingTaxCodeDto getWithHdTaxCode2() {
		return withHdTaxCode2;
	}
	public void setWithHdTaxCode2(WithHoldingTaxCodeDto withHdTaxCode2) {
		this.withHdTaxCode2 = withHdTaxCode2;
	}
	public VendorAccountGroupDto getVendorAccountGroup() {
		return vendorAccountGroup;
	}
	public void setVendorAccountGroup(VendorAccountGroupDto vendorAccountGroup) {
		this.vendorAccountGroup = vendorAccountGroup;
	}
	public ReconAccountDto getReconAccount() {
		return reconAccount;
	}
	public void setReconAccount(ReconAccountDto reconAccount) {
		this.reconAccount = reconAccount;
	}
	public GSTVendorClassDto getGstVendorClass() {
		return gstVendorClass;
	}
	public void setGstVendorClass(GSTVendorClassDto gstVendorClass) {
		this.gstVendorClass = gstVendorClass;
	}
	
}
