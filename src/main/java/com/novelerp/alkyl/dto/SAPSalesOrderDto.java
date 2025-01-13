package com.novelerp.alkyl.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.core.util.DateUtil;

public class SAPSalesOrderDto extends CommonContextDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String custBlockStatus;
	private String plant;
	private String saleOrdNo;
	private String date;
	private String deliveryDate;
	private String soldToParty;
	private String soldToPartyName;
	private String material;
	private String materialDesc;
	private String qty;
	private String balanceDeliveryQty;
	private String basicRate;
	private String inwardTransporter;
	private String outwardTransporter;
	private String inco;
	private String inco1;
	private String requestNo;
	private String vehicleType;
	
	private String fdate;
	
	private String tdate;
	public String getCustBlockStatus() {
		return custBlockStatus;
	}
	public void setCustBlockStatus(String custBlockStatus) {
		this.custBlockStatus = custBlockStatus;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getSaleOrdNo() {
		return saleOrdNo;
	}
	public void setSaleOrdNo(String saleOrdNo) {
		this.saleOrdNo = saleOrdNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getSoldToParty() {
		return soldToParty;
	}
	public void setSoldToParty(String soldToParty) {
		this.soldToParty = soldToParty;
	}
	public String getSoldToPartyName() {
		return soldToPartyName;
	}
	public void setSoldToPartyName(String soldToPartyName) {
		this.soldToPartyName = soldToPartyName;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getMaterialDesc() {
		return materialDesc;
	}
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getBalanceDeliveryQty() {
		return balanceDeliveryQty;
	}
	public void setBalanceDeliveryQty(String balanceDeliveryQty) {
		this.balanceDeliveryQty = balanceDeliveryQty;
	}
	public String getBasicRate() {
		return basicRate;
	}
	public void setBasicRate(String basicRate) {
		this.basicRate = basicRate;
	}
	public String getInwardTransporter() {
		return inwardTransporter;
	}
	public void setInwardTransporter(String inwardTransporter) {
		this.inwardTransporter = inwardTransporter;
	}
	public String getOutwardTransporter() {
		return outwardTransporter;
	}
	public void setOutwardTransporter(String outwardTransporter) {
		this.outwardTransporter = outwardTransporter;
	}
	public String getInco() {
		return inco;
	}
	public void setInco(String inco) {
		this.inco = inco;
	}
	public String getInco1() {
		return inco1;
	}
	public void setInco1(String inco1) {
		this.inco1 = inco1;
	}
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getFdate() {
		return fdate;
	}
	public String getTdate() {
		return tdate;
	}
	public void setFdate(String fdate) {
		this.fdate = fdate;
	}
	public void setTdate(String tdate) {
		this.tdate = tdate;
	}
//	public Date getFdate() {
//		return fdate;
//	}
//	public Date getTdate() {
//		return tdate;
//	}
//	public void setFdate(Date fdate) {
//		this.fdate = fdate;
//	}
//	public void setTdate(Date tdate) {
//		this.tdate = tdate;
//	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	
	
	
}
