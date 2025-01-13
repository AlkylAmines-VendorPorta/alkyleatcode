package com.novelerp.eat.service;

import java.util.List;
import java.util.Set;

import com.novelerp.appbase.master.dto.ScrutinyPointDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.BidderGtpDto;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.entity.ItemScrutiny;

/**
 * @author Aman Sahu
 *
 */
public interface ItemScrutinyService extends CommonService<ItemScrutiny, ItemScrutinyDto>{
	
	public List<ItemScrutinyDto> saveItemScrutiny(List<ItemScrutinyDto> ItemScrutinyDtoList);
	public boolean savePreliminarySatus(ItemScrutinyDto newItemScrutinyDto);
	public boolean saveBidderDevitionSatus(ItemScrutinyDto newItemScrutinyDto);
	public boolean updateFinalScrutinyInfo(ItemScrutinyDto newItemScrutinyDto);

	public ItemScrutinyDto createTechnicalScrutiny(ItemBidDto itemBid,Set<BidderGtpDto> bidderGtpList,Set<BidderSectionDocDto> technicalDoc);
	public ItemScrutinyDto createCommercialScrutiny(BidderDto bidderDto,List<ScrutinyPointDto> scrutinyPointList,Set<BidderSectionDocDto> commercialDoc);
	
	/*public List<ItemScrutinyDto> createScrutiny(ItemBidDto itemBidDto,Set<BidderGtpDto> bidderGtpList,List<ScrutinyPointDto> scrutinyPointList,Set<BidderSectionDocDto> technicalDoc,Set<BidderSectionDocDto> commercialDoc);*/
	public List<ItemScrutinyDto> createScrutinyForCommercial(BidderDto bidder,List<ScrutinyPointDto> scrutinyPointList,Set<BidderSectionDocDto> commercialDoc);
	public List<ItemScrutinyDto> createScrutinyForTechnical(ItemBidDto itemBidDto,Set<BidderGtpDto> bidderGtpList,Set<BidderSectionDocDto> technicalDoc);
	
	public boolean resetFinalScrutinySatus(Long itemScrutinyId);
	
	public boolean updateFinalScrutinyAuditorResponseInfo(ItemScrutinyDto dto);
	
	public boolean resetPreliminaryScrutinySatus(Long itemScrutinyId);
	
	public boolean revokeDeviationConfirmation(Long itemScrutinyId);
	
	public List<String> getScrutinyEngrEmailList(Long tahdrId);
}
