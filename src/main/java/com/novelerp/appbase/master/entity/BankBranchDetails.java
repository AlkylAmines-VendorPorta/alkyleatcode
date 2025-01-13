package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.Region;

/**
 * 
 * @author Ankita Tirodkar

 *
 */

@Entity
@Table(name="m_bank_branch_details")
public class BankBranchDetails extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long bankBranchDetailsId;
	private BankNameDetails bankName;
	private String branchName;
	private String iFSCCode;
	private Region branchState;
	
	@Id
	@SequenceGenerator(name="m_bank_branch_details_seq",sequenceName="m_bank_branch_details_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bank_branch_details_seq")	
	@Column(name = "m_bank_branch_details_id", updatable=false)
	public Long getBankBranchDetailsId() {
		return bankBranchDetailsId;
	}
	public void setBankBranchDetailsId(Long bankBranchDetailsId) {
		this.bankBranchDetailsId = bankBranchDetailsId;
	}
	@Column(name="branch_name")
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Column(name="ifsc_code")
	public String getiFSCCode() {
		return iFSCCode;
	}
	public void setiFSCCode(String iFSCCode) {
		this.iFSCCode = iFSCCode;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bank_name_details_id")
	public BankNameDetails getBankName() {
		return bankName;
	}
	public void setBankName(BankNameDetails bankName) {
		this.bankName = bankName;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_region_id",referencedColumnName="c_region_id")
	public Region getBranchState() {
		return branchState;
	}
	public void setBranchState(Region branchState) {
		this.branchState = branchState;
	}
	
	
}
