package com.novelerp.eat.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appbase.master.entity.BOMVersion;
import com.novelerp.appbase.master.entity.Material;
import com.novelerp.appbase.master.entity.MaterialVersion;
import com.novelerp.appcontext.entity.ContextPO;


@Entity
@Table(name="t_tahdr_material")
public class TAHDRMaterial extends ContextPO {

	/**
	 * @author Ankush
	 */
	private static final long serialVersionUID = 1L;

	private Long tahdrMaterialId;
	private TAHDRDetail tahdrDetail;
	private Material material;
	private	String materialDescription;
	private String aboutSpecCode;
	private String specVersion;
	private String materialTypeCode;
	private Long quantity;
	private Long allocatedQty;
	private Set<TAHDRMaterialGTP> materialGtpList;
	private Set<SectionDocument> sectionDocumentSet;
	private MaterialVersion materialVersion;
	private BOMVersion bomVersion;
	private Set<ItemBid> itemBid;
	private String basePriceRate;
	/*private BigDecimal basePriceRate;*/
	
	@Id
	@SequenceGenerator(name="t_tahdrMaterial_seq",sequenceName="t_tahdrMaterial_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_tahdrMaterial_seq")	
	@Column(name = "t_tahdr_material_id", updatable=false)
	public Long getTahdrMaterialId() {
		return tahdrMaterialId;
	}
	public void setTahdrMaterialId(Long tahdrMaterialId) {
		this.tahdrMaterialId = tahdrMaterialId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_detail_id")
	public TAHDRDetail getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetail tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_material_id")
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	@Column(name="material_description")
	public String getMaterialDescription() {
		return materialDescription;
	}
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}
	
	@Column(name="about_spec_code")
	public String getAboutSpecCode() {
		return aboutSpecCode;
	}
	public void setAboutSpecCode(String aboutSpecCode) {
		this.aboutSpecCode = aboutSpecCode;
	}
	
	@Column(name="specification_version")
	public String getSpecVersion() {
		return specVersion;
	}
	public void setSpecVersion(String specVersion) {
		this.specVersion = specVersion;
	}
	
	@Column(name="material_type_code")
	public String getMaterialTypeCode() {
		return materialTypeCode;
	}
	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}
	
	@Column(name="quantity")
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="tahdrMaterial")
	public Set<TAHDRMaterialGTP> getMaterialGtpList() {
		return materialGtpList;
	}
	public void setMaterialGtpList(Set<TAHDRMaterialGTP> materialGtpList) {
		this.materialGtpList = materialGtpList;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="tahdrMaterial")
	public Set<SectionDocument> getSectionDocumentSet() {
		return sectionDocumentSet;
	}
	public void setSectionDocumentSet(Set<SectionDocument> sectionDocumentSet) {
		this.sectionDocumentSet = sectionDocumentSet;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_material_version_id")
	public MaterialVersion getMaterialVersion() {
		return materialVersion;
	}
	public void setMaterialVersion(MaterialVersion materialVersion) {
		this.materialVersion = materialVersion;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bom_version_id")
	public BOMVersion getBomVersion() {
		return bomVersion;
	}
	public void setBomVersion(BOMVersion bomVersion) {
		this.bomVersion = bomVersion;
	}
	@OneToMany(fetch=FetchType.LAZY, mappedBy="tahdrMaterial")
	public Set<ItemBid> getItemBid() {
		return itemBid;
	}
	public void setItemBid(Set<ItemBid> itemBid) {
		this.itemBid = itemBid;
	}
	/*@Column(name="base_price_rate")
	public BigDecimal getBasePriceRate() {
		return basePriceRate;
	}
	public void setBasePriceRate(BigDecimal basePriceRate) {
		this.basePriceRate = basePriceRate;
	}*/
	@Column(name="allocated_qty")
	public Long getAllocatedQty() {
		return allocatedQty;
	}
	public void setAllocatedQty(Long allocatedQty) {
		this.allocatedQty = allocatedQty;
	}
	@Column(name="base_price_rate")
	public String getBasePriceRate() {
		return basePriceRate;
	}
	public void setBasePriceRate(String basePriceRate) {
		this.basePriceRate = basePriceRate;
	}
	
}
