package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.appbase.master.dto.FinancialYearDto;
import com.novelerp.appbase.master.entity.FinancialYear;
import com.novelerp.core.dao.CommonDao;

/**
 * 
 * @author Administrator
 *
 */
public interface FinancialYearDao extends CommonDao<FinancialYear, FinancialYearDto> {
    public List<FinancialYear> getYear();
}
