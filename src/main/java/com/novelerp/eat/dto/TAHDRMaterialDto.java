package com.novelerp.eat.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.BomVersionDto;
import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.MaterialVersionDto;
import com.novelerp.appcontext.dto.CommonContextDto;

public class TAHDRMaterialDto extends CommonContextDto {

	/**
	 * @author Ankush
	 */
	private static final long serialVersionUID = 1L;
	
	private Long tahdrMaterialId;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrDetailId",scope=Long.class)
	private TAHDRDetailDto tahdrDetail;
	private MaterialDto material;
	private	String materialDescription;
	private String aboutSpecCode;
	private String specVersion;
	private String materialTypeCode;
	private Long quantity;
	private Long allocatedQty;
	private Set<TAHDRMaterialGTPDto> materialGtpList;
	private Set<SectionDocumentDto> sectionDocumentSet;
	private MaterialVersionDto materialVersion;
	private BomVersionDto bomVersion;
	/*@JsonIdentityInfo(generator=PropertyGenerator.class,property="itemBidId",scope=Long.class)
	private ItemBidDto itemBidDto;*/
	private String basePriceRate;
	
	public Long getTahdrMaterialId() {
		return tahdrMaterialId;
	}
	public void setTahdrMaterialId(Long tahdrMaterialId) {
		this.tahdrMaterialId = tahdrMaterialId;
	}
	public TAHDRDetailDto getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetailDto tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
	public MaterialDto getMaterial() {
		return material;
	}
	public void setMaterial(MaterialDto material) {
		this.material = material;
	}
	public String getMaterialDescription() {
		return materialDescription;
	}
	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}
	public String getAboutSpecCode() {
		return aboutSpecCode;
	}
	public void setAboutSpecCode(String aboutSpecCode) {
		this.aboutSpecCode = aboutSpecCode;
	}
	public String getSpecVersion() {
		return specVersion;
	}
	public void setSpecVersion(String specVersion) {
		this.specVersion = specVersion;
	}
	public String getMaterialTypeCode() {
		return materialTypeCode;
	}
	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Set<TAHDRMaterialGTPDto> getMaterialGtpList() {
		return materialGtpList;
	}
	public void setMaterialGtpList(Set<TAHDRMaterialGTPDto> materialGtpList) {
		this.materialGtpList = materialGtpList;
	}
	public Set<SectionDocumentDto> getSectionDocumentSet() {
		return sectionDocumentSet;
	}
	public void setSectionDocumentSet(Set<SectionDocumentDto> sectionDocumentSet) {
		this.sectionDocumentSet = sectionDocumentSet;
	}
	public MaterialVersionDto getMaterialVersion() {
		return materialVersion;
	}
	public void setMaterialVersion(MaterialVersionDto materialVersion) {
		this.materialVersion = materialVersion;
	}
	public BomVersionDto getBomVersion() {
		return bomVersion;
	}
	public void setBomVersion(BomVersionDto bomVersion) {
		this.bomVersion = bomVersion;
	}
	/*public ItemBidDto getItemBidDto() {
		return itemBidDto;
	}
	public void setItemBidDto(ItemBidDto itemBidDto) {
		this.itemBidDto = itemBidDto;
	}*/
	public Long getAllocatedQty() {
		return allocatedQty;
	}
	public void setAllocatedQty(Long allocatedQty) {
		this.allocatedQty = allocatedQty;
	}
	public String getBasePriceRate() {
		return basePriceRate;
	}
	public void setBasePriceRate(String basePriceRate) {
		this.basePriceRate = basePriceRate;
	}

}
