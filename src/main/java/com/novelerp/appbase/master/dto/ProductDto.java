package com.novelerp.appbase.master.dto;

import java.util.Date;

import com.novelerp.core.dto.CommonDto;

public class ProductDto extends CommonDto {

	private static final long serialVersionUID = -6402937868574216559L;

	private Long productId;
	private String value;
	private String name;
	private String description;
	private String upc;
	private String sku;
	private UOMDto uom;
	private ProductCategoryDto productCategory;
	private String discontinued;
	private Date discontinuedBy;
	private String isBoq;
	private TaxSetDto taxset;
	private ProductSpecificationSetDto productAttributeSet;

	public ProductCategoryDto getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategoryDto productCategory) {
		this.productCategory = productCategory;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public Date getDiscontinuedBy() {
		return discontinuedBy;
	}

	public void setDiscontinuedBy(Date discontinuedBy) {
		this.discontinuedBy = discontinuedBy;
	}


	public String getIsBoq() {
		return isBoq;
	}

	public void setIsBoq(String isBoq) {
		this.isBoq = isBoq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UOMDto getUom() {
		return uom;
	}

	public void setUom(UOMDto uom) {
		this.uom = uom;
	}

	public TaxSetDto getTaxset() {
		return taxset;
	}

	public void setTaxset(TaxSetDto taxset) {
		this.taxset = taxset;
	}

	public ProductSpecificationSetDto getProductAttributeSet() {
		return productAttributeSet;
	}

	public void setProductAttributeSet(ProductSpecificationSetDto productAttributeSet) {
		this.productAttributeSet = productAttributeSet;
	}

	
}
