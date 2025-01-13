package com.novelerp.eat.dao;

import java.util.List;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.entity.ItemScrutiny;

/**
 * @author Aman Sahu
 *
 */
public interface ItemScrutinyDao extends CommonDao<ItemScrutiny, ItemScrutinyDto>{

	public int getItemscrutinyForPreliminaryScrutiny(Long itemScrutinyId,String comment,String status);
	public int resetItemscrutinyForPreliminaryScrutiny(Long itemScrutinyId);
	public int revokeDeviationConfirmation(Long itemScrutinyId);
	public int saveBidderDeviationResposne(Long itemScrutinyId,String status,Long fileId);
	public int updateFinalScrutinyResponseInfo(ItemScrutinyDto itemScrutinyDto);
	public int updateFinalScrutinyAuditorResponseInfo(ItemScrutinyDto dto);
	public int resetItemscrutinyForFinalScrutiny(Long itemScrutinyId);
	public List<Object> getScrutinyEngrEmailList(Long tahdrId);
}
