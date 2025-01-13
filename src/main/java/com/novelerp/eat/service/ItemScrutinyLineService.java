package com.novelerp.eat.service;

import java.util.List;
import java.util.Set;

import com.novelerp.appbase.master.dto.ScrutinyPointDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderGtpDto;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ItemScrutinyLineDto;
import com.novelerp.eat.entity.ItemScrutinyLine;

/**
 * 
 * @author Aman Sahu
 *
 */
public interface ItemScrutinyLineService extends CommonService<ItemScrutinyLine, ItemScrutinyLineDto>{
	
	public boolean createCommercialScrutinyLines(ItemScrutinyDto itemScrutiny,List<ScrutinyPointDto> scrutinyPointList);
	public ItemScrutinyLineDto saveItemScrutinyLine(ItemScrutinyLineDto itemScrutinylineDto);
	public boolean createTechnicalScrutinyLines(ItemScrutinyDto itemScrutiny,Set<BidderGtpDto> bidderGtpList);
	
	public boolean createCommercialDocScrutinyLines(ItemScrutinyDto itemScrutiny, Set<BidderSectionDocDto> commercialDoc);
	public boolean createTechnicalDocScrutinyLines(ItemScrutinyDto itemScrutiny, Set<BidderSectionDocDto> technicalDoc);
	
	public boolean updateTechnicalDeviationResponseInfo(ItemScrutinyLineDto itemScrutinylineDto);
	public boolean updateCommercialDeviationResponseInfo(ItemScrutinyLineDto itemScrutinylineDto);
	
	public boolean updateFinalTechnicalDeviationResponseInfo(ItemScrutinyLineDto itemScrutinylineDto);
	public boolean updateFinalCommercialDeviationResponseInfo(ItemScrutinyLineDto itemScrutinylineDto);
	
	public boolean updateFinalScrutinyAuditorResponseInfo(ItemScrutinyLineDto dto);

}
