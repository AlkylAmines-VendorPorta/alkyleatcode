package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.ExemptedCategoriesDto;
import com.novelerp.appbase.master.entity.ExemptedCategories;
import com.novelerp.core.service.CommonService;

public interface ExemptedCategoriesService extends CommonService<ExemptedCategories, ExemptedCategoriesDto> {

	public List<ExemptedCategoriesDto> getExemptedCategoriesList();
	public ExemptedCategoriesDto getExemptedCategories(Long id);
	public CustomResponseDto saveExemptedCategories(ExemptedCategoriesDto dto);
	public CustomResponseDto editExemptedCategories(ExemptedCategoriesDto dto);
	public CustomResponseDto deleteExemptedCategories(Long id);
}
