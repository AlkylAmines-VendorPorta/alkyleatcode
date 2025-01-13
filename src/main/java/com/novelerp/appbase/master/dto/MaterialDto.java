package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class MaterialDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long materialId;
	private String name;
	private String code;
	private String description;
	private MaterialGroupDto materialGroup;
	private MaterialSubGroupDto materialSubGroup;
	private UOMDto uom;
	
	private String itemTrade;
	private String isTestReport;
	private String isSSI;
	private String isNSIC;
	private String isPropReport;
	private HSNDto hsnCode;
	private String typeCode;
	private String itemCode;
	/*private ItemGroupDto itemGroup;*/
	
	
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
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
	public MaterialGroupDto getMaterialGroup() {
		return materialGroup;
	}
	public void setMaterialGroup(MaterialGroupDto materialGroup) {
		this.materialGroup = materialGroup;
	}
	public MaterialSubGroupDto getMaterialSubGroup() {
		return materialSubGroup;
	}
	public void setMaterialSubGroup(MaterialSubGroupDto materialSubGroup) {
		this.materialSubGroup = materialSubGroup;
	}
	public UOMDto getUom() {
		return uom;
	}
	public void setUom(UOMDto uom) {
		this.uom = uom;
	}
	
	
	public String getIsTestReport() {
		return isTestReport;
	}
	public void setIsTestReport(String isTestReport) {
		this.isTestReport = isTestReport;
	}
	public String getIsSSI() {
		return isSSI;
	}
	public void setIsSSI(String isSSI) {
		this.isSSI = isSSI;
	}
	public String getIsNSIC() {
		return isNSIC;
	}
	public void setIsNSIC(String isNSIC) {
		this.isNSIC = isNSIC;
	}
	public String getIsPropReport() {
		return isPropReport;
	}
	public void setIsPropReport(String isPropReport) {
		this.isPropReport = isPropReport;
	}
	/*public ItemGroupDto getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(ItemGroupDto itemGroup) {
		this.itemGroup = itemGroup;
	}*/
	public String getItemTrade() {
		return itemTrade;
	}
	public void setItemTrade(String itemTrade) {
		this.itemTrade = itemTrade;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public HSNDto getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(HSNDto hsnCode) {
		this.hsnCode = hsnCode;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	

}
