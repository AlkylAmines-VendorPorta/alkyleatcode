package com.novelerp.alkyl.dto;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.DateUtil;
//@JsonIgnoreProperties(ignoreUnknown = true)

public class PurchaseOrderDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="purchaseOrderId",scope=PurchaseOrderDto.class)
	private Long purchaseOrderId;
	private String name;
	private String code;
	private String description;
	private String documentType;
	private String purchaseOrderNumber;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date date;
	private String vendorCode;
	private String vendorName;
	private String incomeTerms;
	private String purchaseGroup;
	private Integer versionNumber;
	private String status;
	private List<PurchaseOrderLineDto> poLineList; 
	private String remark;
	private String pstyp;
	private AttachmentDto poAtt;
	private String requestedBy;
	private UserDto reqby;
	private String deleted;
	public Double actualLimit;
	public Double overallLimit;
	public Double balanceLimit;
	private String poemail;
	public String contractPo;
	public String message;
	public String Err_cd;
	public String Stts_flg;
	public String HEADER;
	public String releaseStatus;
	public String openPo;
	public String serialNo;
	public String vendorPortal;
	public String materialType;
	public String poTypeMsg;
	//public String outboundDeliveryNo;
	//public String doctyp;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date prDate;
	public String userID;
	public String blocked;

	
	public String getMessage() {
		return message;
	}
	public String getErr_cd() {
		return Err_cd;
	}
	public String getStts_flg() {
		return Stts_flg;
	}
	public String getHEADER() {
		return HEADER;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setErr_cd(String err_cd) {
		Err_cd = err_cd;
	}
	public void setStts_flg(String stts_flg) {
		Stts_flg = stts_flg;
	}
	public void setHEADER(String hEADER) {
		HEADER = hEADER;
	}
	
	public Double getActualLimit() {
		return actualLimit;
	}
	public Double getOverallLimit() {
		return overallLimit;
	}
	public Double getBalanceLimit() {
		return balanceLimit;
	}
	public void setActualLimit(Double actualLimit) {
		this.actualLimit = actualLimit;
	}
	public void setOverallLimit(Double overallLimit) {
		this.overallLimit = overallLimit;
	}
	public void setBalanceLimit(Double balanceLimit) {
		this.balanceLimit = balanceLimit;
	}
	public String getContractPo() {
		return contractPo;
	}
	public void setContractPo(String contractPo) {
		this.contractPo = contractPo;
	}
	
	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getIncomeTerms() {
		return incomeTerms;
	}
	public void setIncomeTerms(String incomeTerms) {
		this.incomeTerms = incomeTerms;
	}
	public String getPurchaseGroup() {
		return purchaseGroup;
	}
	public void setPurchaseGroup(String purchaseGroup) {
		this.purchaseGroup = purchaseGroup;
	}
	public Integer getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<PurchaseOrderLineDto> getPoLineList() {
		return poLineList;
	}
	public void setPoLineList(List<PurchaseOrderLineDto> poLineList) {
		this.poLineList = poLineList;
	}		
	
	public void addPOLine(PurchaseOrderLineDto poLine){
		if(CommonUtil.isCollectionEmpty(this.poLineList)){
			this.poLineList = new ArrayList<>();
		}
		this.poLineList.add(poLine);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPstyp() {
		return pstyp;
	}
	public void setPstyp(String pstyp) {
		this.pstyp = pstyp;
	}
	public AttachmentDto getPoAtt() {
		return poAtt;
	}
	public void setPoAtt(AttachmentDto poAtt) {
		this.poAtt = poAtt;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public UserDto getReqby() {
		return reqby;
	}
	public void setReqby(UserDto reqby) {
		this.reqby = reqby;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getPoemail() {
		return poemail;
	}
	public void setPoemail(String poemail) {
		this.poemail = poemail;
	}
	public String getReleaseStatus() {
		return releaseStatus;
	}
	public String getOpenPo() {
		return openPo;
	}
	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	public void setOpenPo(String openPo) {
		this.openPo = openPo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getVendorPortal() {
		return vendorPortal;
	}
	public void setVendorPortal(String vendorPortal) {
		this.vendorPortal = vendorPortal;
	}
//	public String getOutboundDeliveryNo() {
//		return outboundDeliveryNo;
//	}
//	public void setOutboundDeliveryNo(String outboundDeliveryNo) {
//		this.outboundDeliveryNo = outboundDeliveryNo;
//	}
//	public String getDoctyp() {
//		return doctyp;
//	}
//	public void setDoctyp(String doctyp) {
//		this.doctyp = doctyp;
//	}
	public String getMaterialType() {
		return materialType;
	}
	public String getPoTypeMsg() {
		return poTypeMsg;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public void setPoTypeMsg(String poTypeMsg) {
		this.poTypeMsg = poTypeMsg;
	}
	public Date getPrDate() {
		return prDate;
	}
	public void setPrDate(Date prDate) {
		this.prDate = prDate;
	}
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getBlocked() {
		return blocked;
	}
	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}

	
	
	

	

	
	
	
}
