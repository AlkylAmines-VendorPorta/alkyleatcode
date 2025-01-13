package com.novelerp.eat.service;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.RatingWeightageDto;
import com.novelerp.eat.entity.RatingWeightage;

public interface RatingWeightageService extends CommonService<RatingWeightage, RatingWeightageDto> {

	public boolean deleteRatWeight(Long id);
}
