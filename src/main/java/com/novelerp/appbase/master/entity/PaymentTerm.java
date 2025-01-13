package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;
/**
 * @author Iqval Singh
 * (Changed By Vivek Birdi)
 */
@Entity
@Table(name="m_payment_term")
public class PaymentTerm extends ContextPO {

	
	private static final long serialVersionUID = 7145905595117314888L;

	private Long paymenttermId;
	private String name;
	private String code;

	private String description;
	
	

	@Id	
	@SequenceGenerator(name="m_payment_term_seq",sequenceName="m_payment_term_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_payment_term_seq")	
	@Column(name = "m_payment_term_id", updatable=false)
	public Long getPaymenttermId() {
		return paymenttermId;
	}
	
	public void setPaymenttermId(Long paymenttermId) {
		this.paymenttermId = paymenttermId;
	}

	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "value")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	
}
