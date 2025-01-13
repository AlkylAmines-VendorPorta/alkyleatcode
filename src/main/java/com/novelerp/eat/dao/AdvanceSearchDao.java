package com.novelerp.eat.dao;

import java.util.List;

import com.novelerp.appbase.master.entity.Material;
import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.entity.TAHDR;

/**
 * @author Ankita Tirodkar
 *
 */

public interface AdvanceSearchDao extends CommonDao<TAHDR, TAHDRDto> {

	public List<Material> getMaterialList();
	
	
}
