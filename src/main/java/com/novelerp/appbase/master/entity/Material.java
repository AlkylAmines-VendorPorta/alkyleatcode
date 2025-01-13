package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_material")
public class Material  extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long materialId;
	private String name;
	private String code;
	private String description;
	private MaterialGroup materialGroup;
	private MaterialSubGroup materialSubGroup;
	private UOM uom;
	
	private String itemTrade;
	private String isTestReport;
	private String isSSI;
	private String isNSIC;
	private String isPropReport;
	private HSN hsnCode;
	private String typeCode;
	private String itemCode;
	
	@Id
	@SequenceGenerator(name="m_material_seq",sequenceName="m_material_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_material_seq")	
	@Column(name = "m_material_id", updatable=false)
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_material_group_id")
	public MaterialGroup getMaterialGroup() {
		return materialGroup;
	}
	public void setMaterialGroup(MaterialGroup materialGroup) {
		this.materialGroup = materialGroup;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_material_subgroup_id")
	public MaterialSubGroup getMaterialSubGroup() {
		return materialSubGroup;
	}
	public void setMaterialSubGroup(MaterialSubGroup materialSubGroup) {
		this.materialSubGroup = materialSubGroup;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_uom_id")
	public UOM getUom() {
		return uom;
	}
	public void setUom(UOM uom) {
		this.uom = uom;
	}
	@Column(name = "istestreport")
	public String getIsTestReport() {
		return isTestReport;
	}
	public void setIsTestReport(String isTestReport) {
		this.isTestReport = isTestReport;
	}
	@Column(name = "isssi")
	public String getIsSSI() {
		return isSSI;
	}
	public void setIsSSI(String isSSI) {
		this.isSSI = isSSI;
	}
	@Column(name = "isnsic")
	public String getIsNSIC() {
		return isNSIC;
	}
	public void setIsNSIC(String isNSIC) {
		this.isNSIC = isNSIC;
	}
	@Column(name = "ispropreport")
	public String getIsPropReport() {
		return isPropReport;
	}
	public void setIsPropReport(String isPropReport) {
		this.isPropReport = isPropReport;
	}
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_itemgroup_id")
	public ItemGroup getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(ItemGroup itemGroup) {
		this.itemGroup = itemGroup;
	}*/
	@Column(name = "item_trade")
	public String getItemTrade() {
		return itemTrade;
	}
	public void setItemTrade(String itemTrade) {
		this.itemTrade = itemTrade;
	}
	/*@Column(name = "hsn_code")
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}*/
	@Column(name = "type_code")
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_hsn_id")
	public HSN getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(HSN hsnCode) {
		this.hsnCode = hsnCode;
	}
	@Column(name = "item_code")
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	
}
