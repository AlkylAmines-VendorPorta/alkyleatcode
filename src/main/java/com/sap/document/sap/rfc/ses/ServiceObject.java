package com.sap.document.sap.rfc.ses;

import java.util.List;

public class ServiceObject {

	private List<YSERVICE> services;
	private String withHoldingTaxCode;
	private String withHoldingTaxType;
	private String miroDescription;
	private String tcs;
	private String roundoff1;
	private String roundoff2;
	private String freightAmount;
	private String packingFWRD;
	private String loadingUNLD;
	private String sgst;
	private String cgst;
	private String igst;
	private String ssnVersion;

	public List<YSERVICE> getServices() {
		return services;
	}

	public void setServices(List<YSERVICE> services) {
		this.services = services;
	}

	public String getWithHoldingTaxCode() {
		return withHoldingTaxCode;
	}

	public void setWithHoldingTaxCode(String withHoldingTaxCode) {
		this.withHoldingTaxCode = withHoldingTaxCode;
	}

	public String getWithHoldingTaxType() {
		return withHoldingTaxType;
	}

	public void setWithHoldingTaxType(String withHoldingTaxType) {
		this.withHoldingTaxType = withHoldingTaxType;
	}

	public String getMiroDescription() {
		return miroDescription;
	}

	public void setMiroDescription(String miroDescription) {
		this.miroDescription = miroDescription;
	}

	public String getTcs() {
		return tcs;
	}

	public void setTcs(String tcs) {
		this.tcs = tcs;
	}

	public String getRoundoff1() {
		return roundoff1;
	}

	public void setRoundoff1(String roundoff1) {
		this.roundoff1 = roundoff1;
	}

	public String getRoundoff2() {
		return roundoff2;
	}

	public void setRoundoff2(String roundoff2) {
		this.roundoff2 = roundoff2;
	}

	public String getFreightAmount() {
		return freightAmount;
	}

	public void setFreightAmount(String freightAmount) {
		this.freightAmount = freightAmount;
	}

	public String getPackingFWRD() {
		return packingFWRD;
	}

	public void setPackingFWRD(String packingFWRD) {
		this.packingFWRD = packingFWRD;
	}

	public String getLoadingUNLD() {
		return loadingUNLD;
	}

	public void setLoadingUNLD(String loadingUNLD) {
		this.loadingUNLD = loadingUNLD;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getIgst() {
		return igst;
	}

	public void setIgst(String igst) {
		this.igst = igst;
	}

	public String getSsnVersion() {
		return ssnVersion;
	}

	public void setSsnVersion(String ssnVersion) {
		this.ssnVersion = ssnVersion;
	}
	
}
