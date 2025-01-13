package com.novelerp.eat.dto;

import java.math.BigDecimal;

import com.novelerp.appcontext.dto.CommonContextDto;

public class ContractHeaderDto extends CommonContextDto{
	
	/**
	 * 
	 */
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
	private String vendorSapCode;
	private String isContractCreated; 
	private BidderDto bidder;
	private TAHDRDto tahdr;
	
	public Long getContractHeaderId() {
		return contractHeaderId;
	}
	public void setContractHeaderId(Long contractHeaderId) {
		this.contractHeaderId = contractHeaderId;
	}
	public String getAgrType() {
		return agrType;
	}
	public void setAgrType(String agrType) {
		this.agrType = agrType;
	}
	public String getPurOrg() {
		return purOrg;
	}
	public void setPurOrg(String purOrg) {
		this.purOrg = purOrg;
	}
	public String getPurGrp() {
		return purGrp;
	}
	public void setPurGrp(String purGrp) {
		this.purGrp = purGrp;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getStorageLoc() {
		return storageLoc;
	}
	public void setStorageLoc(String storageLoc) {
		this.storageLoc = storageLoc;
	}
	public String getAccAssCateg() {
		return accAssCateg;
	}
	public void setAccAssCateg(String accAssCateg) {
		this.accAssCateg = accAssCateg;
	}
	public String getValStartDate() {
		return valStartDate;
	}
	public void setValStartDate(String valStartDate) {
		this.valStartDate = valStartDate;
	}
	public String getValEndDate() {
		return valEndDate;
	}
	public void setValEndDate(String valEndDate) {
		this.valEndDate = valEndDate;
	}
	public BigDecimal getTargValue() {
		return targValue;
	}
	public void setTargValue(BigDecimal targValue) {
		this.targValue = targValue;
	}
	public String getSecurityDep() {
		return securityDep;
	}
	public void setSecurityDep(String securityDep) {
		this.securityDep = securityDep;
	}
	public String getPerformanceDep() {
		return performanceDep;
	}
	public void setPerformanceDep(String performanceDep) {
		this.performanceDep = performanceDep;
	}
	public String getVendorSapCode() {
		return vendorSapCode;
	}
	public void setVendorSapCode(String vendorSapCode) {
		this.vendorSapCode = vendorSapCode;
	}
	public BidderDto getBidder() {
		return bidder;
	}
	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}
	public TAHDRDto getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDRDto tahdr) {
		this.tahdr = tahdr;
	}
	public String getIsContractCreated() {
		return isContractCreated;
	}
	public void setIsContractCreated(String isContractCreated) {
		this.isContractCreated = isContractCreated;
	}

}
