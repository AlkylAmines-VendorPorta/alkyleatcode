package com.novelerp.eat.entity;

import java.math.BigDecimal;

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
@Table(name = "t_contract_header")
public class ContractHeader extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long contractHeaderId;

	private String agrType;
	private String purOrg;
	private String purGrp;
	private String plant;
	private String storageLoc;
	private String accAssCateg;
	private String valStartDate;
	private String valEndDate;
	private BigDecimal targValue;
	private String securityDep;
	private String performanceDep;
	private Bidder bidder;
	private TAHDR tahdr;
	private String isContractCreated; 
	
	@Id
	@SequenceGenerator(name = "t_contract_header_seq", sequenceName = "t_contract_header_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_contract_header_seq")
	@Column(name = "t_contract_header_id", updatable = false)
	public Long getContractHeaderId() {
		return contractHeaderId;
	}
	public void setContractHeaderId(Long contractHeaderId) {
		this.contractHeaderId = contractHeaderId;
	}
	
	@Column(name = "agr_type")
	public String getAgrType() {
		return agrType;
	}
	public void setAgrType(String agrType) {
		this.agrType = agrType;
	}
	
	@Column(name = "pur_org")
	public String getPurOrg() {
		return purOrg;
	}
	public void setPurOrg(String purOrg) {
		this.purOrg = purOrg;
	}
	
	@Column(name = "pur_grp")
	public String getPurGrp() {
		return purGrp;
	}
	public void setPurGrp(String purGrp) {
		this.purGrp = purGrp;
	}
	
	@Column(name = "plant")
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	
	@Column(name = "storage_loc")
	public String getStorageLoc() {
		return storageLoc;
	}
	public void setStorageLoc(String storageLoc) {
		this.storageLoc = storageLoc;
	}
	
	@Column(name = "acc_ass_categ")
	public String getAccAssCateg() {
		return accAssCateg;
	}
	public void setAccAssCateg(String accAssCateg) {
		this.accAssCateg = accAssCateg;
	}
	
	@Column(name = "val_start_date")
	public String getValStartDate() {
		return valStartDate;
	}
	public void setValStartDate(String valStartDate) {
		this.valStartDate = valStartDate;
	}
	
	@Column(name = "val_end_date")
	public String getValEndDate() {
		return valEndDate;
	}
	public void setValEndDate(String valEndDate) {
		this.valEndDate = valEndDate;
	}
	
	@Column(name = "targ_value")
	public BigDecimal getTargValue() {
		return targValue;
	}
	public void setTargValue(BigDecimal targValue) {
		this.targValue = targValue;
	}
	
	@Column(name = "security_dep")
	public String getSecurityDep() {
		return securityDep;
	}
	public void setSecurityDep(String securityDep) {
		this.securityDep = securityDep;
	}
	
	@Column(name = "performance_dep")
	public String getPerformanceDep() {
		return performanceDep;
	}
	public void setPerformanceDep(String performanceDep) {
		this.performanceDep = performanceDep;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_bidder_id")
	public Bidder getBidder() {
		return bidder;
	}
	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_tahdr_id")
	public TAHDR getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDR tahdr) {
		this.tahdr = tahdr;
	}
	
	@Column(name = "is_contract_created")
	public String getIsContractCreated() {
		return isContractCreated;
	}
	public void setIsContractCreated(String isContractCreated) {
		this.isContractCreated = isContractCreated;
	}

}
