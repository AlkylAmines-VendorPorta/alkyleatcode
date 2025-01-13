package com.novelerp.eat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dao.TAHDRAwardWinnerDao;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ContractConditionDto;
import com.novelerp.eat.dto.ContractHeaderDto;
import com.novelerp.eat.dto.ContractItemDto;
import com.novelerp.eat.dto.ContractServiceDto;
import com.novelerp.eat.dto.RatingWeightageDto;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.entity.WinnerSelection;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.ContractConditionService;
import com.novelerp.eat.service.ContractHeaderService;
import com.novelerp.eat.service.ContractItemService;
import com.novelerp.eat.service.ContractServiceService;
import com.novelerp.eat.service.TahdrAwardWinnerService;

@Service
public class TAHDRAwardWinnerServiceImpl extends AbstractContextServiceImpl<WinnerSelection, WinnerSelectionDto>
		implements TahdrAwardWinnerService {

	@Autowired
	private TAHDRAwardWinnerDao tahdrAwardWinnerDao;

	@Autowired
	private BPartnerService bPartnerService;
	@Autowired
	private BidderService bidderService;
	@Autowired
	private TahdrAwardWinnerService tahdrAwardWinnerService; 
	@Autowired
	private ContractHeaderService contractHeaderService;
	@Autowired
	private ContractItemService contractItemService;
	@Autowired
	private ContractServiceService contractServiceService;
	@Autowired
	private ContractConditionService contractConditionService;

	@PostConstruct
	private void init() {
		super.init(TAHDRAwardWinnerServiceImpl.class, tahdrAwardWinnerDao, WinnerSelection.class,
				WinnerSelectionDto.class);
		setByPassProxy(true);
	}

	@Override
	public CustomResponseDto calculateRating(WinnerSelectionDto dto, RatingWeightageDto ratingWeightageDto) {

		int qualityRatingWeightage=0;
		int priceRatingWeightage=0;
		int serviceRatingWeightage=0;
		int deliveryRatingWeightage=0;
		double rating = 0;
		CustomResponseDto customResponseDto = new CustomResponseDto();
		try{
			qualityRatingWeightage = dto.getQualityRating() * ratingWeightageDto.getQualityWeightage();
			priceRatingWeightage = dto.getPriceRating() * ratingWeightageDto.getPriceWeightage();
			serviceRatingWeightage = dto.getServiceRating() * ratingWeightageDto.getServiceWeightage();
			deliveryRatingWeightage = dto.getDeliveryRating() * ratingWeightageDto.getDeliveryWeightage();
			rating = (qualityRatingWeightage + priceRatingWeightage + serviceRatingWeightage + deliveryRatingWeightage) / 100;
			Map<String, Object> map = new HashMap<>();
			map.put("qualityRating", dto.getQualityRating());
			map.put("priceRating", dto.getPriceRating());
			map.put("deliveryRating", dto.getDeliveryRating());
			map.put("serviceRating", dto.getServiceRating());
			map.put("rating", rating);
			customResponseDto.addObject("ratingMap",map);
			customResponseDto.addObject("result", true);
			customResponseDto.addObject("message", "Rating Calculated");
		}catch(Exception ex){
			customResponseDto.addObject("result", false);
			customResponseDto.addObject("message", "Rating Not Calculated");
		}
		return customResponseDto;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int updateRating(CustomResponseDto customResponseDto, WinnerSelectionDto winnerSelectionDto, BidderDto bidderDto, BPartnerDto bPartnerDto) {
		
		 try {
			Map<String,Object> map = (Map<String,Object>)customResponseDto.getObjectMap().get("ratingMap");
			if(!map.isEmpty()){
				double newRating = (double) map.get("rating");
				 updateByJpql(map, "winnerSelectionId", winnerSelectionDto.getWinnerSelectionId());
				 bidderService.updateBidderRating(bidderDto, newRating);
				 bPartnerService.updateBpartnerRating(bPartnerDto, newRating);
				 return 1;
			}else{
				return -1;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return -1;
		}
	}
	
	@Override
	public List<WinnerSelectionDto> getTahdrWithDetailsForCrateContract(Map<String, Object> params, int pageNumber, int pageSize,String searchColumn, String searchValue) {

		List<WinnerSelectionDto> tahdrList = null;
		String query = tahdrAwardWinnerDao.getWinnerForCreateContractQuery();
		if (!"none".equalsIgnoreCase(searchValue)) {
			params.put("searchValue", "%" + searchValue.toUpperCase() + "%");
			tahdrList = findDtosByQuery(query, params, pageNumber, pageSize);
		} else {
			tahdrList = findDtosByQuery(query, params, pageNumber, pageSize);
		}
		return tahdrList;

	}
	
	@Override
	public List<BidderDto> getWinnerBidderListByTahdrId(Long tahdrId){
		Map<String,Object> map=new HashMap<>();
		map.put("tahdrId",tahdrId);
		String query = tahdrAwardWinnerDao.getWinnerForCreateContractQuery();
		return bidderService.findDtosByQuery(query, map);
	}
	
	@Override
	public CustomResponseDto getBidderContractDetails(Long bidderId, Long tahdrId){
		CustomResponseDto customResponseDto = new CustomResponseDto();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("bidderId", bidderId);
		BidderDto bidderDto = bidderService.findDto("getBidderByIdWithTahdrAndOfficeDetail", params);
		List<WinnerSelectionDto> winnerSelectionDtos = tahdrAwardWinnerService.findDtos("getItemListOfBidder", params);
		params.put("tahdrId", tahdrId);
		ContractHeaderDto contractHeaderDto =contractHeaderService.findDto("getContractHeader", params);
		
		if(contractHeaderDto != null){
			Map<String, Object> headerMap =  AbstractContextServiceImpl.getParamMap("headerId", contractHeaderDto.getContractHeaderId());
			List<ContractItemDto> itemDtos = contractItemService.findDtos("getContractItemsByHeaderId", headerMap);
			List<ContractConditionDto> conditionDtos = contractConditionService.findDtos("getContractCndnByHeaderId", headerMap);
			List<ContractServiceDto> serviceDtos = contractServiceService.findDtos("getContractSrvcByHeaderId", headerMap);
			
			customResponseDto.addObject("contractItem", itemDtos);
			customResponseDto.addObject("contractHeader", contractHeaderDto);
			customResponseDto.addObject("contractCondition", conditionDtos);
			customResponseDto.addObject("contractService", serviceDtos);

		}
		customResponseDto.addObject("bidder", bidderDto);
		customResponseDto.addObject("itemList", winnerSelectionDtos);
		
		return customResponseDto;
	} 
	
}
