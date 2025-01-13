package com.novelerp.alkyl.dto;

public class SearchPObySAPDto {
	
	private static final long serialVersionUID = 1L;

	String documentType;
	String purchaseOrderNumber;
	String date;
	String contractPo;
	String releaseStatus;
	String vendorCode;
	String vendorName;
	String incomeTerms;
	String purchaseGroup;
	String versionNumber;
	String poemail;
	String pstyp;
	String deleted;
	String openPo;
	public String getDocumentType() {
		return documentType;
	}
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}
	public String getDate() {
		return date;
	}
	public String getContractPo() {
		return contractPo;
	}
	public String getReleaseStatus() {
		return releaseStatus;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public String getIncomeTerms() {
		return incomeTerms;
	}
	public String getPurchaseGroup() {
		return purchaseGroup;
	}
	public String getVersionNumber() {
		return versionNumber;
	}
	public String getPoemail() {
		return poemail;
	}
	public String getPstyp() {
		return pstyp;
	}
	public String getDeleted() {
		return deleted;
	}
	public String getOpenPo() {
		return openPo;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setContractPo(String contractPo) {
		this.contractPo = contractPo;
	}
	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public void setIncomeTerms(String incomeTerms) {
		this.incomeTerms = incomeTerms;
	}
	public void setPurchaseGroup(String purchaseGroup) {
		this.purchaseGroup = purchaseGroup;
	}
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
	public void setPoemail(String poemail) {
		this.poemail = poemail;
	}
	public void setPstyp(String pstyp) {
		this.pstyp = pstyp;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public void setOpenPo(String openPo) {
		this.openPo = openPo;
	}
	@Override
	public String toString() {
		return "SearchPObySAPDto [documentType=" + documentType + ", purchaseOrderNumber=" + purchaseOrderNumber
				+ ", date=" + date + ", contractPo=" + contractPo + ", releaseStatus=" + releaseStatus + ", vendorCode="
				+ vendorCode + ", vendorName=" + vendorName + ", incomeTerms=" + incomeTerms + ", purchaseGroup="
				+ purchaseGroup + ", versionNumber=" + versionNumber + ", poemail=" + poemail + ", pstyp=" + pstyp
				+ ", deleted=" + deleted + ", openPo=" + openPo + "]";
	}
	
	
}
