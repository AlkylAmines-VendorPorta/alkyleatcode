package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.appbase.master.dto.TaxDto;
import com.novelerp.appbase.master.entity.Tax;
import com.novelerp.core.dao.CommonDao;

/**
 *  @author Aman
 */
public interface TaxDao extends CommonDao<Tax, TaxDto> {

	public List<Tax> getTaxList();
	public List<Tax> getTaxById(Long id);
}

