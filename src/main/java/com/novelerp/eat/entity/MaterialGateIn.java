package com.novelerp.eat.entity;

import java.util.Date;

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

import com.novelerp.alkyl.entity.GateEntry;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;

@Entity
@Table(name="t_material_gate_in")
public class MaterialGateIn extends ContextPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long gateInId;
	private GateEntry gateEntry;
	private String status;
	private String docNo;
	private User gateIn;
	private Date gateInDate;
	private User materialCheck;
	private Date materialCheckDate;
	private User closedBy;
	private Date closedDate;
	
	
	@Id
	@SequenceGenerator(name = "t_material_gate_in_seq",sequenceName="t_material_gate_in_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t_material_gate_in_seq")
	@Column(name="t_material_gate_in_id",updatable=false)
	public Long getGateInId() {
		return gateInId;
	}
	public void setGateInId(Long gateInId) {
		this.gateInId = gateInId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_gate_entry_id")
	public GateEntry getGateEntry() {
		return gateEntry;
	}
	public void setGateEntry(GateEntry gateEntry) {
		this.gateEntry = gateEntry;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="doc_no")
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="gate_in",referencedColumnName="ad_user_id")
	public User getGateIn() {
		return gateIn;
	}
	public void setGateIn(User gateIn) {
		this.gateIn = gateIn;
	}
	@Column(name="gate_in_date")
	public Date getGateInDate() {
		return gateInDate;
	}
	public void setGateInDate(Date gateInDate) {
		this.gateInDate = gateInDate;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="material_check",referencedColumnName="ad_user_id")
	public User getMaterialCheck() {
		return materialCheck;
	}
	public void setMaterialCheck(User materialCheck) {
		this.materialCheck = materialCheck;
	}
	@Column(name="material_check_date")
	public Date getMaterialCheckDate() {
		return materialCheckDate;
	}
	public void setMaterialCheckDate(Date materialCheckDate) {
		this.materialCheckDate = materialCheckDate;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="closed_by",referencedColumnName="ad_user_id")
	public User getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(User closedBy) {
		this.closedBy = closedBy;
	}
	@Column(name="closed_date")
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	
}
