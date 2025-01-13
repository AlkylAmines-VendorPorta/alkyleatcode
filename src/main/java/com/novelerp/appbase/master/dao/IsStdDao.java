package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.appbase.master.dto.IsStdDto;
import com.novelerp.appbase.master.entity.IsStd;
import com.novelerp.core.dao.CommonDao;

/**
 *  @author Aman
 */
public interface IsStdDao extends CommonDao<IsStd, IsStdDto> {
	
	public List<IsStd> getIsStdList();
	public List<IsStd> getIsStdById(Long id);

}
