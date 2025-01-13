package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.RatingWeightageDao;
import com.novelerp.eat.dto.RatingWeightageDto;
import com.novelerp.eat.entity.RatingWeightage;

@Repository
public class RatingWeightageDaoImpl extends AbstractJpaDAO<RatingWeightage, RatingWeightageDto>
		implements RatingWeightageDao {

	@PostConstruct
	public void postConstruct() {
		setClazz(RatingWeightage.class, RatingWeightageDto.class);
	}
}
