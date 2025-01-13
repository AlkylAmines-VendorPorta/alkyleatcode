package com.novelerp.alkyl.entity;

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
@Table(name="t_praposed_reason")
public class PraposedReason extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long praposedReasonId;
	private String code;
	private String name;
	private String description;
	private Annexure annexure;
	
	@Id
	@SequenceGenerator(name = "t_praposed_reason_seq", sequenceName = "t_praposed_reason_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_praposed_reason_seq")
	@Column(name="t_praposed_reason_id",updatable = false)
	public Long getPraposedReasonId() {
		return praposedReasonId;
	}
	public void setPraposedReasonId(Long praposedReasonId) {
		this.praposedReasonId = praposedReasonId;
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
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_annexure_id")
	public Annexure getAnnexure() {
		return annexure;
	}
	public void setAnnexure(Annexure annexure) {
		this.annexure = annexure;
	}
	
	
	

}
