package com.novelerp.eat.dao;

import java.util.List;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.entity.ItemBid;

/**
 * @author Aman Sahu
 *
 */
public interface ItemBidDao extends CommonDao<ItemBid, ItemBidDto> {

	public int updateItemBidStatusByOpeningType(String status,String checkStatus,Long tahdrId);
	
	public int updateItemBidStatusByPBOpeningTypeForSingle(String status,String checkStatus,Long tahdrId);
	public int updateItemBidStatusByPBOpeningTypeForTwo(String status,String checkStatus,Long tahdrId);
	
	public int updateItemBidStatusForMarkingLowestBid(Long tahdrId);
	
	public int updateItemBidStatusForMarkingLowestBid(Long tahdrId,Long tahdrMaterialIld);
	
	public int updateItemBidStatusForMarkingAnnexureC1Called(Long tahdrId, int baseRate,String status);
	
	public int updateItemBidStatus(String status,Long itemBidId);
	
	public String getItemBidByBidSubmitted();
	
	public int updateItemBidStatusForMarkingHighestBid(Long tahdrId);
	
	public int updateQuickItemBidStatusForMarkingLowestBid(Long tahdrId);
	
	public int updateQuickItemBidStatusForMarkingHighestBid(Long tahdrId);
	
	public int updateQuickItemBidForWinner(Long tahdrId);

	public int updateLowestBid(Long prId);

	public List<ItemBidDto> getprNumber(Long enquiryId);

	public List<ItemBidDto> getprPlant(Long enquiryId);

	public String getItemBidByPRLineID();
}

