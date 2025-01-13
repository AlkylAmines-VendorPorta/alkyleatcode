package com.novelerp.eat.entity;

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
import com.novelerp.appcontext.entity.User;

@Entity
@Table(name="eat_approval_matrix")
public class TAHDRApprovalMatrix extends ContextPO {

	/**
	 * Ankita
	 */
	private static final long serialVersionUID = 1L;
	private Long tahdrApprovalMatrixId;
	private Long levels;
	private User user;
	private String remarks;
	private String status;
	private String action;
	private TAHDR tahdr;
	
	@Id
	@SequenceGenerator(name="eat_approval_matrix_seq",sequenceName="eat_approval_matrix_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="eat_approval_matrix_seq")	
	@Column(name = "eat_approval_matrix_id", updatable=false)
	public Long getTahdrApprovalMatrixId() {
		return tahdrApprovalMatrixId;
	}
	public void setTahdrApprovalMatrixId(Long tahdrApprovalMatrixId) {
		this.tahdrApprovalMatrixId = tahdrApprovalMatrixId;
	}
	/*@Column(name = "level")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ad_user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "action")
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_detail_id")
	public TAHDRDetail getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetail tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_id")
	public TAHDR getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDR tahdr) {
		this.tahdr = tahdr;
	}
	@Column(name = "levels")
	public Long getLevels() {
		return levels;
	}
	public void setLevels(Long levels) {
		this.levels = levels;
	}
	
	
}
