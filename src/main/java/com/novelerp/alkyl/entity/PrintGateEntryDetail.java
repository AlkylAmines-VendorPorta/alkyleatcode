package com.novelerp.alkyl.entity;

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

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;

@Entity
@Table(name="t_print_gate_entry_details")
public class PrintGateEntryDetail extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long printGateEntryDetialsId;
	private String docType;
	private String objectNo;
	private String plant;
	
	
	@Id
	@SequenceGenerator(name = "t_print_gate_entry_details_seq", sequenceName = "t_print_gate_entry_details_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_print_gate_entry_details_seq")
	@Column(name="t_print_gate_entry_details_id",updatable = false)
	public Long getPrintGateEntryDetialsId() {
		return printGateEntryDetialsId;
	}
	public void setPrintGateEntryDetialsId(Long printGateEntryDetailsId) {
		this.printGateEntryDetialsId = printGateEntryDetailsId;
	}
	

	@Column(name="doc_type")
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	@Column(name="object_no")
	public String getObjectNo() {
		return objectNo;
	}
	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}
	
	@Column(name="plant")
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	
}
