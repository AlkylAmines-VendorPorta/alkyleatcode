package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name = "m_bp_sapvendor_detail")
public class SapVendorDetail extends ContextPO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long sapVendorDetailId;
	private WithHoldingTaxCode idctrWithtTaxType;
	private WithHoldingTaxCode withHdTaxCode1;
	private WithHoldingTaxCode withHdTaxCode2;
	private VendorAccountGroup vendorAccountGroup;
	private ReconAccount reconAccount;
	private GSTVendorClass gstVendorClass;
	
	@Id
	@SequenceGenerator(name = "m_bp_sapvendor_detail_seq", sequenceName = "m_bp_sapvendor_detail_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_bp_sapvendor_detail_seq")
	@Column(name = "m_bp_sapvendor_detail_id", updatable = false)
	public Long getSapVendorDetailId() {
		return sapVendorDetailId;
	}
	public void setSapVendorDetailId(Long sapVendorDetailId) {
		this.sapVendorDetailId = sapVendorDetailId;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_indirectwithhold_tax_type_id")
	public WithHoldingTaxCode getIdctrWithtTaxType() {
		return idctrWithtTaxType;
	}
	public void setIdctrWithtTaxType(WithHoldingTaxCode idctrWithtTaxType) {
		this.idctrWithtTaxType = idctrWithtTaxType;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_withhold_tax_code1_id")
	public WithHoldingTaxCode getWithHdTaxCode1() {
		return withHdTaxCode1;
	}
	public void setWithHdTaxCode1(WithHoldingTaxCode withHdTaxCode1) {
		this.withHdTaxCode1 = withHdTaxCode1;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_withhold_tax_code2_id")
	public WithHoldingTaxCode getWithHdTaxCode2() {
		return withHdTaxCode2;
	}
	public void setWithHdTaxCode2(WithHoldingTaxCode withHdTaxCode2) {
		this.withHdTaxCode2 = withHdTaxCode2;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_vendor_account_group_id")
	public VendorAccountGroup getVendorAccountGroup() {
		return vendorAccountGroup;
	}
	public void setVendorAccountGroup(VendorAccountGroup vendorAccountGroup) {
		this.vendorAccountGroup = vendorAccountGroup;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_recon_account_id")
	public ReconAccount getReconAccount() {
		return reconAccount;
	}
	public void setReconAccount(ReconAccount reconAccount) {
		this.reconAccount = reconAccount;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_gst_vendor_class_id")
	public GSTVendorClass getGstVendorClass() {
		return gstVendorClass;
	}
	public void setGstVendorClass(GSTVendorClass gstVendorClass) {
		this.gstVendorClass = gstVendorClass;
	}
}
