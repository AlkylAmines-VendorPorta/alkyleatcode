package com.novelerp.eat.service;

import java.util.List;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.entity.ItemBid;

/**
 * @author Aman Sahu
 *
 */
public interface ItemBidService extends CommonService<ItemBid, ItemBidDto>{

	/**
	 * @param dto
	 * @return
	 */
	public ItemBidDto saveTechnicalBid(ItemBidDto dto);

	/**
	 * @param dto
	 * @param oldItemBid
	 * @return
	 */
	/*public ItemBidDto saveAnnexuteX1Bid(ItemBidDto dto, ItemBidDto oldItemBid);*/

	/**
	 * @param itemBid
	 * @return
	 */
	public ItemBidDto submit(ItemBidDto itemBid);

	/**
	 * @param itemBid
	 * @return
	 *//*
	public ItemBidDto submitPriceBid(ItemBidDto itemBid);*/

	/**
	 * @param itemBid
	 * @return
	 */
	public ItemBidDto submitTechnicalBid(ItemBidDto itemBid);

	/**
	 * @param dto
	 * @return
	 */
	public ItemBidDto savePriceSectionDocument(ItemBidDto dto);

	/**
	 * @param dto
	 * @return
	 */
	public ItemBidDto saveTechnicalSectionDocument(ItemBidDto dto);

	
	public int updateItemBidStatusByOpeningType(String status,String checkStatus,Long tahdrId);
	
	public int updateItemBidStatusByPBOpeningTypeForSingle(String status,String checkStatus,Long tahdrId);
	
	public int updateItemBidStatusByPBOpeningTypeForTwo(String status,String checkStatus,Long tahdrId);
	
	public boolean updateItemBidStatusForMarkingLowestBid(Long tahdrId);
	
	public boolean updateItemBidStatusForMarkingAnnexureC1Called(Long tahdrId, int sum,String status);
	
	public boolean updateItemBidStatus(String status,Long itemBidId);
	
	public boolean updateItemBidScrutinyStatus(String status,ItemBidDto itemBid);

	/**
	 * @param dto
	 * @param tahdr
	 * @return
	 */
	public ItemBidDto savePriceBid(ItemBidDto dto);
	
	public boolean selectLowestItemBidByTahdrId(Long tahdrId);

	/**
	 * @param dto
	 * @return
	 */
	public ItemBidDto saveRevisedBid(ItemBidDto dto);

	/**
	 * @param dto
	 * @param pbNew
	 * @return
	 */
	public ItemBidDto savePriceBid(ItemBidDto dto, PriceBidDto pbNew);
	
	public ItemBidDto saveNewRevisedBid(ItemBidDto dto,Long tahdrId);

	/**
	 * @param dto
	 * @param oldPriceBid
	 * @return
	 */
	public PriceBidDto saveAnnexuteX1Bid(PriceBidDto dto, PriceBidDto oldPriceBid);

	
	public boolean selectHighestItemBidByTahdrId(Long tahdrId);
	/**
	 * @param dto
	 * @param parts
	 * @return
	 */
	public ItemBidDto submitPriceBid(ItemBidDto dto, List<PriceBidDto> parts);
	
	public boolean selectHighOrLowItemBidByTahdrId(Long tahdrId,String typeCode,String isAuction);

	/**
	 * @param status
	 * @param id
	 * @return
	 */
	public int updateStatus(String status, Long id);

	/**
	 * @param dto
	 * @param parts
	 * @return
	 */
	public ItemBidDto submitRevisedPriceBid(ItemBidDto dto, List<PriceBidDto> parts);
	
	public boolean selectLowestQuickItemBidByTahdrId(Long tahdrId);
	
	public boolean selectHighestQuickItemBidByTahdrId(Long tahdrId);
	
	public boolean updateQuickItemForBidWinner(Long tahdrId);

	public List<ItemBidDto> saveEnquiryLines(List<ItemBidDto> enquiryLines, BidderDto enquiry, BPartnerDto bPartnerDto);

	public int updateLowestBid(Long prId);;

}