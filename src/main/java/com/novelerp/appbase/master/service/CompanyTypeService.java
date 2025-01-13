package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.CompanyTypeDto;
import com.novelerp.appbase.master.entity.CompanyType;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 *
 */
public interface CompanyTypeService extends CommonService<CompanyType, CompanyTypeDto> {

	public List<CompanyTypeDto> getCompanyTypeList();
	public CompanyTypeDto getCompanyType(Long id);
	/*public ResponseDto addCompanyType(CompanyTypeDto dto);
	public ResponseDto editCompanyType(CompanyTypeDto dto);
	public ResponseDto deleteCompanyType(Long id);*/
}
