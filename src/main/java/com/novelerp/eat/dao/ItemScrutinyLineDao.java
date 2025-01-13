package com.novelerp.eat.dao;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.ItemScrutinyLineDto;
import com.novelerp.eat.entity.ItemScrutinyLine;

/**
 * @author Aman Sahu
 *
 */
public interface ItemScrutinyLineDao extends CommonDao<ItemScrutinyLine, ItemScrutinyLineDto>{

	public int updateDeviationResponseInfo(ItemScrutinyLineDto itemScrutinylineDto);
	public int updateFinalScrutinyResponseInfo(ItemScrutinyLineDto itemScrutinylineDto);
	public int updateFinalScrutinyAuditorResponseInfo(ItemScrutinyLineDto dto);
}
