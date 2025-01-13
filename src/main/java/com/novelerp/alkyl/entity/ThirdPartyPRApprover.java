package com.novelerp.alkyl.entity;

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

@Entity
@Table(name="t_tp_pr_approver")
public class ThirdPartyPRApprover extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long thirdPartyPRApproverId;
	private String email;
	private String code;
	private String name;
	private String description;
	private PR pr;
	
	@Id
	@SequenceGenerator(name = "t_tp_pr_approver_seq", sequenceName = "t_tp_pr_approver_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_tp_pr_approver_seq")
	@Column(name="t_tp_pr_approver_id",updatable = false)
	public Long getThirdPartyPRApproverId() {
		return thirdPartyPRApproverId;
	}
	public void setThirdPartyPRApproverId(Long thirdPartyPRApproverId) {
		this.thirdPartyPRApproverId = thirdPartyPRApproverId;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="t_pr_id",referencedColumnName="t_pr_id")
	public PR getPr() {
		return pr;
	}
	public void setPr(PR pr) {
		this.pr = pr;
	}
	
}
