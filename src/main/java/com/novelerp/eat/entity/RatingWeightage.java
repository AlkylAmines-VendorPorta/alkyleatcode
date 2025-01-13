package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author akshay
 *
 */
@Entity
@Table(name = "m_rating_weightage")
public class RatingWeightage extends ContextPO {

	private static final long serialVersionUID = 1L;
	private Long ratingWeightageId;
	private Integer qualityWeightage;
	private Integer priceWeightage;
	private Integer deliveryWeightage;
	private Integer serviceWeightage;

	@Id
	@SequenceGenerator(name = "m_rating_weightage_seq", sequenceName = "m_rating_weightage_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_rating_weightage_seq")
	@Column(name = "m_rating_weightage_id", updatable = false)
	public Long getRatingWeightageId() {
		return ratingWeightageId;
	}

	public void setRatingWeightageId(Long ratingWeightageId) {
		this.ratingWeightageId = ratingWeightageId;
	}

	@Column(name = "quality_weightage")
	public Integer getQualityWeightage() {
		return qualityWeightage;
	}

	public void setQualityWeightage(Integer qualityWeightage) {
		this.qualityWeightage = qualityWeightage;
	}

	@Column(name = "price_weightage")
	public Integer getPriceWeightage() {
		return priceWeightage;
	}

	public void setPriceWeightage(Integer priceWeightage) {
		this.priceWeightage = priceWeightage;
	}

	@Column(name = "delivery_weightage")
	public Integer getDeliveryWeightage() {
		return deliveryWeightage;
	}

	public void setDeliveryWeightage(Integer deliveryWeightage) {
		this.deliveryWeightage = deliveryWeightage;
	}

	@Column(name = "service_weightage")
	public Integer getServiceWeightage() {
		return serviceWeightage;
	}

	public void setServiceWeightage(Integer serviceWeightage) {
		this.serviceWeightage = serviceWeightage;
	}

}
