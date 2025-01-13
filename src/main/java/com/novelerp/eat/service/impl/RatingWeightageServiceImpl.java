package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.RatingWeightageDao;
import com.novelerp.eat.dto.RatingWeightageDto;
import com.novelerp.eat.entity.RatingWeightage;
import com.novelerp.eat.service.RatingWeightageService;

@Service
public class RatingWeightageServiceImpl extends AbstractContextServiceImpl<RatingWeightage, RatingWeightageDto>
		implements RatingWeightageService {

	@Autowired
	RatingWeightageDao ratingWeightageDao;

	@PostConstruct
	private void init() {
		super.init(RatingWeightageServiceImpl.class, ratingWeightageDao, RatingWeightage.class, RatingWeightageDto.class);
		setByPassProxy(true);
	}

	@Override
	public boolean deleteRatWeight(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
