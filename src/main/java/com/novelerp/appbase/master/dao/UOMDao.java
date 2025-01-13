package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.appbase.master.dto.UOMDto;
import com.novelerp.appbase.master.entity.UOM;
import com.novelerp.core.dao.CommonDao;

/**
 * 
 * @author Ankita Tirodkar
 *
 */

public interface UOMDao extends CommonDao<UOM, UOMDto> {
	
	public List<UOM> getSearchedUomList(String word);
}
