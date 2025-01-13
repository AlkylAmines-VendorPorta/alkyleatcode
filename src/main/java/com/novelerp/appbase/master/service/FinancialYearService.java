package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.FinancialYearDto;
import com.novelerp.appbase.master.entity.FinancialYear;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author varsha
 *
 */
public interface FinancialYearService extends CommonService<FinancialYear, FinancialYearDto>{

	public List<FinancialYearDto> getYears();
	public FinancialYearDto saveFinancialYear();
}
