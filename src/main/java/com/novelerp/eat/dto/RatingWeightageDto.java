package com.novelerp.eat.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class RatingWeightageDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long ratingWeightageId;
	private Integer qualityWeightage;
	private Integer priceWeightage;
	private Integer deliveryWeightage;
	private Integer serviceWeightage;
	
	public Long getRatingWeightageId() {
		return ratingWeightageId;
	}
	public void setRatingWeightageId(Long ratingWeightageId) {
		this.ratingWeightageId = ratingWeightageId;
	}
	public Integer getQualityWeightage() {
		return qualityWeightage;
	}
	public void setQualityWeightage(Integer qualityWeightage) {
		this.qualityWeightage = qualityWeightage;
	}
	public Integer getPriceWeightage() {
		return priceWeightage;
	}
	public void setPriceWeightage(Integer priceWeightage) {
		this.priceWeightage = priceWeightage;
	}
	public Integer getDeliveryWeightage() {
		return deliveryWeightage;
	}
	public void setDeliveryWeightage(Integer deliveryWeightage) {
		this.deliveryWeightage = deliveryWeightage;
	}
	public Integer getServiceWeightage() {
		return serviceWeightage;
	}
	public void setServiceWeightage(Integer serviceWeightage) {
		this.serviceWeightage = serviceWeightage;
	}

}
