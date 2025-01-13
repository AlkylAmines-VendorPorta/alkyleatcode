package com.novelerp.eat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.ScrutinyFileDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.BidsService;
import com.novelerp.eat.service.CommercialBidService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.ScrutinyFileService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TechnicalBidService;
import com.novelerp.eat.service.TempPriceBidService;

@Service
public class BidsServiceImpl implements BidsService {
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private BidderService bidderService;
	
	/*@Autowired
	private ItemScrutinyService itemScrutinyService;*/
	
	@Autowired
	private TechnicalBidService technicalBidService;
	
	@Autowired
	private CommercialBidService commercialBidService;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private TempPriceBidService tempPriceBidService;
	
	@Autowired
	private ScrutinyFileService scrutinyFileService;

	@Override
	public CustomResponseDto getTenderList(int pageNumber, int pageSize, String searchColumn, String searchValue,
			String typeCode) {
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> newObjectMap= new HashMap<>();
		Map<String, Object> objectMap = new HashMap<>();
		long countResult = 0l;
		Map<String, Object> params=new HashMap<>();
		params.put("tenderTypeCode", typeCode);
		List<TAHDRDto> tahdrList=null;
		if(typeCode.equals("RA") || typeCode.equals("FA")){
			params.put("tenderTypeCode", "%"+typeCode+"%");
			tahdrList=tahdrService.getTenders("getPublishedAuctionListByTypeCode", params, pageNumber, pageSize, searchColumn, searchValue);
		}else if(typeCode.equals("QRFQ") || typeCode.equals("RFQ")){
			tahdrList=tahdrService.getTenders("getRfqListByTypeCode", params, pageNumber, pageSize, searchColumn, searchValue);
	    }else if(typeCode.equals("QRA") || typeCode.equals("QFA")){
			tahdrList=tahdrService.getTenders("getQuickAuctionListByTypeCode", params, pageNumber, pageSize, searchColumn, searchValue);
		}else{
			tahdrList=tahdrService.getTenders("getPublishedTenderListByTypeCode", params, pageNumber, pageSize, searchColumn, searchValue);
		}
		countResult=tahdrService.getTenderBidsCount(typeCode,params,searchColumn,searchValue);
		newObjectMap.put("tahdrList", tahdrList);
		int LastPage=(int) (countResult / pageSize);
		LastPage = (int) ((LastPage)==1?LastPage:LastPage + 1);
		Map<String, String> tenderStatusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		
		newObjectMap.put("tenderStatusList", tenderStatusList);
		newObjectMap.put("tahdrList", tahdrList);
		objectMap.put("LastPage", LastPage);
		response.setData( newObjectMap);
		response.setObjectMap(objectMap);
		return response;
	}

	@Override
	public CustomResponseDto getBidderList(Long tahdrId, Long tahdrMaterialId) {
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		params.put("tahdrId", tahdrId);
		TAHDRDto tender=tahdrService.findDto(tahdrId);
		List<BidderDto> bidderList=null;
		if(tender.getTahdrTypeCode().equals("QRFQ") || tender.getTahdrTypeCode().equals("RFQ")){
			 bidderList=bidderService.findDtos("getBidderListForQRFQByTahdrId", params);
		}else{
			params.put("tahdrMaterialId", tahdrMaterialId);
			 bidderList=bidderService.findDtos("getBidderListByTahdrMaterialId", params);
		}
		
		if(!CommonUtil.isCollectionEmpty(bidderList)){
			response.addObject("result", true);
			response.addObject("bidderList", bidderList);
			response.addObject("message", "Success");
			Map<String, String> bidderStatusList = refListService.getReferenceListMap(AppBaseConstant.BIDDER_STATUS);
			response.addObject("bidderStatusList", bidderStatusList);
		}else{
			response.addObject("result", false);
			response.addObject("message", "No bidder for selected item");
		}
		return response;
	}

	@Override
	public CustomResponseDto getBidderBids(Long bidderId, Long tahdrMaterialId) {
		CustomResponseDto response =new CustomResponseDto();
		Map<String, Object> technicalParams= new HashMap<>();
		technicalParams.put("bidderId", bidderId);
		technicalParams.put("tahdrMaterialId", tahdrMaterialId);
		TechnicalBidDto technicalBid=getTechnicalBid(technicalParams);
		
		Map<String, Object> commercialParams= new HashMap<>();
		commercialParams.put("bidderId", bidderId);
		CommercialBidDto commercialBid=getCommercialBid(commercialParams);
		
		// do test the root
     	ItemScrutinyDto technicalDeviation=null;
		
		ItemScrutinyDto commcialDeviation=null;
		
		PriceBidDto priceBid=getPriceBid(technicalParams);
		List<PriceBidDto> tempPriceBid=getTempPriceBid( technicalParams);
		response.addObject("technicalBid", technicalBid);
		response.addObject("commercialBid", commercialBid);
		response.addObject("technicalDeviation", technicalDeviation);
		response.addObject("commcialDeviation", commcialDeviation);
		response.addObject("priceBid", priceBid);
		response.addObject("tempPriceBid", !CommonUtil.isCollectionEmpty(tempPriceBid)?tempPriceBid.get(0):null);
		return response;
	}
	
	@Override
	public TechnicalBidDto getTechnicalBid(Map<String, Object> technicalParams){
		return technicalBidService.findDto("getTechnicalBidByBidderIdAndTahdrMaterialId", technicalParams);
	}
	
	@Override
	public CommercialBidDto getCommercialBid(Map<String, Object> commercialParams){
		return commercialBidService.findDto("getCommercialBidByBidderId", commercialParams);
	}
	
	@Override
	public PriceBidDto getPriceBid(Map<String, Object> technicalParams){
		return priceBidService.findDto("getPriceBidByTahdrMaterialIdAndBidderId", technicalParams);
	}
	
	@Override
	public List<PriceBidDto> getTempPriceBid(Map<String, Object> technicalParams){
		return tempPriceBidService.findDtos("getTempPriceBidByTahdrMaterialIdAndBidderId", technicalParams);
	}
	
	@Override
	public ScrutinyFileDto getFinalTechnicalScrutinyBid(Map<String, Object> technicalParams){
		return scrutinyFileService.findDto("getTechnicalScrutinyFileByTahdrMaterialIdAndBidderId", technicalParams);
	}
	
	@Override
	public ScrutinyFileDto getPreTechnicalScrutinyBid(Map<String, Object> technicalParams){
		return scrutinyFileService.findDto("getTechnicalScrutinyFileByTahdrMaterialIdAndBidderId", technicalParams);
	}
	
	@Override
	public ScrutinyFileDto getFinalCommercialScrutinyBid(Map<String, Object> commercialParams){
		return scrutinyFileService.findDto("getCommercialScrutinyFileAndBidderId", commercialParams);
	}
	
	@Override
	public ScrutinyFileDto getPreCommercialScrutinyBid(Map<String, Object> commercialParams){
		return scrutinyFileService.findDto("getCommercialScrutinyFileAndBidderId", commercialParams);
	}
	


}
