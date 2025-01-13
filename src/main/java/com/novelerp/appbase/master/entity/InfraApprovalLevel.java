package com.novelerp.appbase.master.entity;

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
import com.novelerp.appcontext.entity.Designation;
import com.novelerp.appcontext.entity.Role;

@Entity
@Table(name="m_infra_ap_lvl")
public class InfraApprovalLevel extends ContextPO{
	
	private static final long serialVersionUID = 1L;

	private Long infraApprovalLevelId;
	private Role role;
	private Designation designation;
	private Long level;
	private String statusCode;
	
	@Id
	@SequenceGenerator(name="m_infra_ap_lvl_seq",sequenceName="m_infra_ap_lvl_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_infra_ap_lvl_seq")	
	@Column(name = "m_infra_ap_lvl_id", updatable=false)
	public Long getInfraApprovalLevelId() {
		return infraApprovalLevelId;
	}
	public void setInfraApprovalLevelId(Long infraApprovalLevelId) {
		this.infraApprovalLevelId = infraApprovalLevelId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ad_role_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Column(name="ap_level")
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	@Column(name="status_code")
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_designation_id")
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	
	
}
