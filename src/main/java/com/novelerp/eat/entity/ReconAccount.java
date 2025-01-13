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
@Table(name = "m_recon_account")
public class ReconAccount extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long reconAccountId;
	private String value;
	private String name;

	@Id
	@SequenceGenerator(name = "m_recon_account_seq", sequenceName = "m_recon_account_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_recon_account_seq")
	@Column(name = "m_recon_account_id", updatable = false)
	public Long getReconAccountId() {
		return reconAccountId;
	}

	public void setReconAccountId(Long reconAccountId) {
		this.reconAccountId = reconAccountId;
	}

	@Column(name = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
