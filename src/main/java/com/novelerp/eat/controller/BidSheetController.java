package com.novelerp.eat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.BidderParticipationDto;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.GraphDto;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.service.MailService;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.CommercialBidService;
import com.novelerp.eat.service.LiveBidService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRMaterialService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TechnicalBidService;
import com.novelerp.eat.service.TempPriceBidService;

@Controller
public class BidSheetController {

	@Autowired
	private TAHDRService tAHDRService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private TempPriceBidService tempPriceBidService;
	
	@Autowired
	private TechnicalBidService technicalBidService;
	
	@Autowired
	private CommercialBidService commercialBidService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private TAHDRMaterialService  tahdrMaterialService;

	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private TAHDRDetailService tenderDetailService;
	
	@Autowired
	private LiveBidService liveBidService;
	
	@Autowired
	private TAHDRDetailService tAHDRDetailService;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	/*@RequestMapping(value= {"/bidSheet"},method =RequestMethod.GET)
	public ModelAndView liveBid()
	{
		return new ModelAndView("bidSheet");
	}*/
	
	@RequestMapping(value= {"/bidSheet"},method =RequestMethod.GET)
	public ModelAndView bidSheet(){
		ModelAndView view=new ModelAndView("bidSheet");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
	    return view;
	}
	
	@RequestMapping(value= {"/auctionBidSheet"},method =RequestMethod.GET)
	public ModelAndView auctionBidSheet(){
		ModelAndView view=new ModelAndView("bidSheet");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
	    return view;
	}
	
	@RequestMapping(value= {"/getTenderForLiveBid/{typeCode}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderList(@PathVariable("typeCode") String typeCode){
		CustomResponseDto response=new CustomResponseDto();
		List<TAHDRDto> tahdrList=null;
		Map<String, Object> params= new HashMap<>();
		params.put("typeCode", typeCode);
		params.put("status1", AppBaseConstant.AUCTION_SCHEDULING);
		params.put("status2", AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
		UserDto userDto=contextService.getUser();
		params.put("userId", userDto.getUserId());
		if(typeCode.equals("RA") || typeCode.equals("FA")){
			if(typeCode.equals("RA")){
				params.put("typeCode", "PT");
				}
			tahdrList=tAHDRService.findDtos("getAuctionListByStatusAndTypeCode", params);
			}else if (typeCode.equals("QRA") || typeCode.equals("QFA")){
				params= new HashMap<>();
				params.put("typeCode", typeCode);
				params.put("status1", AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
				tahdrList=tAHDRService.findDtos("getQuickAuctionListByStatusAndTypeCode", params);
			}else{
				tahdrList=tAHDRService.findDtos("getTenderListByStatusAndTypeCode", params); 
				}
		if(!CommonUtil.isCollectionEmpty(tahdrList)){
			response.addObject("tahdrList", tahdrList);
			response.addObject("result", true); 
			}else{
				response.addObject("resultMessage", "No Tender/Auction Found !");
				response.addObject("result", false);
				}
		Map<String, String> statusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
	    response.addObject("statusList", statusList);
		return response;
	}
	
	@RequestMapping(value= {"/fetchTenderListForBidSheet"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderList(@RequestParam("pageNumber")int pageNumber, 
			@RequestParam("pageSize") int pageSize,
			@RequestParam("searchMode") String searchColumn , 
			@RequestParam("serachValue") String searchValue,@RequestParam("typeCode") String typeCode){
		CustomResponseDto response=new CustomResponseDto();
		 Map<String, Object> newObjectMap= new HashMap<>();
		 Map<String, Object> objectMap = new HashMap<>();
		 Map<String, Object> params= new HashMap<>();
		 UserDto userDto=contextService.getUser();
		 params.put("userId", userDto.getUserId());
		 long countResult = 0l;
		List<TAHDRDto> tahdrList=null;
		params.put("typeCode", typeCode);
		params.put("status1", AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
		if(typeCode.equals("RA") || typeCode.equals("FA")){
				params.put("status2", AppBaseConstant.AUCTION_SCHEDULING);
				if(typeCode.equals("RA")){
					params.put("typeCode", "PT");
				}
				tahdrList=tAHDRService.getTenders("getAuctionListByStatusAndTypeCode", params, pageNumber, pageSize, searchColumn, searchValue);
		}else if(typeCode.equals("QRA") || typeCode.equals("QFA")){
			tahdrList=tAHDRService.getTenders("getQuickAuctionListByStatusAndTypeCode", params, pageNumber, pageSize, searchColumn, searchValue);
		}else{
			params.put("status2", AppBaseConstant.DOCUMENT_STATUS_PRICE_BID_OPENED);
			tahdrList=tAHDRService.getTenders("getTenderListByStatusAndTypeCode", params, pageNumber, pageSize, searchColumn, searchValue);
		}
		if(!CommonUtil.isCollectionEmpty(tahdrList)){
				newObjectMap.put("tahdrList", tahdrList);
				newObjectMap.put("result", true); 
		}else{
				newObjectMap.put("resultMessage", "No Tender/Auction Found !");
				newObjectMap.put("result", false); 
		}
		Map<String, String> statusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		countResult=tAHDRService.getBidSheetTenderCount(typeCode,params,searchColumn,searchValue);
		newObjectMap.put("tahdrList", tahdrList);
		int LastPage=(int) (countResult / pageSize);
		 LastPage = (int) ((LastPage)==1?LastPage:LastPage + 1);
		 newObjectMap.put("statusList", statusList);
		 objectMap.put("LastPage", LastPage);
		 response.setData( newObjectMap);
		 response.setObjectMap(objectMap);
		return response;
	}
	
	@RequestMapping(value= {"/getBidSheetByTahdrMaterialId/{tahdrId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getBidderForLiveBidByTahdrId(@PathVariable("tahdrId") Long tahdrId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		CustomResponseDto response=new CustomResponseDto();
		RoleDto role =contextService.getDefaultRole();
		 if(tahdrMaterialId!=null && role!=null){
		     Map<String, Object> params= new HashMap<>();
		     params.put("tahdrId", tahdrId);
			 TAHDRDetailDto tenderDetail=tenderDetailService.findDto("QueryForTAHDRDetailByTahdrId", params);
			 TAHDRDto tender=tenderDetail!=null?tenderDetail.getTahdr():null;
			 params.put("tahdrMaterialId", tahdrMaterialId);
			 
			 List<PriceBidDto> bidderListWithPriceBid=null;
			 List<Object> statisticData=null;
			 if(tenderDetail!=null && tender!=null){
				 String typeCode=tender.getTahdrTypeCode();
				 if(typeCode.equals("QFA")){
						 bidderListWithPriceBid=priceBidService.findDtos("getDecendingPBQuickBidderDetailListByTahdrMaterialId", params);
						 statisticData=priceBidService.getAscendingQuickBidderStatistic(tahdrId, tahdrMaterialId);
					 }else if (typeCode.equals("QRA")){
						 bidderListWithPriceBid=priceBidService.findDtos("getAscendingPBQuickBidderDetailListByTahdrMaterialId", params);
						 statisticData=priceBidService.getDecendingQuickBidderStatistic(tahdrId, tahdrMaterialId);
					 }else if(typeCode.equals("FA")){
					 bidderListWithPriceBid=priceBidService.findDtos("getDecendingPBBidderDetailListByTahdrMaterialId", params);
					 statisticData=priceBidService.getAscendingQuickBidderStatistic(tahdrId, tahdrMaterialId); 
					 }else{
					 bidderListWithPriceBid=priceBidService.findDtos("getAscendingPBBidderDetailListByTahdrMaterialId", params);
					 statisticData=priceBidService.getDecendingQuickBidderStatistic(tahdrId, tahdrMaterialId);
				 }
				if(!CommonUtil.isCollectionEmpty(bidderListWithPriceBid)){
					 Map<String, Object> map= new HashMap<>();
					for(int i=0;i<bidderListWithPriceBid.size();i++){
						PriceBidDto priceBid=bidderListWithPriceBid.get(i);
						BPartnerDto partner=priceBid.getPartner()==null?null:priceBid.getPartner();
						if(partner!=null){
							Long partnerId=partner.getbPartnerId();
							int rank=priceBidService.getRankByMaterial(tahdrId,tahdrMaterialId, partnerId,typeCode);
							map.put("rankMap_"+partnerId, rank);
						}
					}
					response.addObject("remainingTime", liveBidService.calculateRemainingTime(tenderDetail.getAuctionToDate())); 
					 
					response.addObject("rank", map);
					response.addObject("statisticData", statisticData);
					response.addObject("bidderList", bidderListWithPriceBid);
					response.addObject("result", true); 
					
				}else{
					response.addObject("remainingTime", liveBidService.calculateRemainingTime(tenderDetail.getAuctionToDate()));
					response.addObject("resultMessage", "No Bidder Found !");
					response.addObject("result", false); 
				}
			 }else{
				 response.addObject("result", false); 
				 response.addObject("resultMessage", "Tender Not Found !"); 
			 }
		 }else{
			 response.addObject("result", false); 
			 response.addObject("resultMessage", "Something went wrong !"); 
		 }
		return response;
	}
	
	@RequestMapping(value= {"/getBidStatisticByTahdrId/{tahdrId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getBidStatisticByTahdrId(@PathVariable("tahdrId") Long tahdrId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		CustomResponseDto response=new CustomResponseDto();
		RoleDto role =contextService.getDefaultRole();
		 if(tahdrMaterialId!=null && role!=null){
		     Map<String, Object> params= new HashMap<>();
		     params.put("tahdrId", tahdrId);
			 TAHDRDetailDto tenderDetail=tenderDetailService.findDto("QueryForTAHDRDetailByTahdrId", params);
			 TAHDRDto tender=tenderDetail!=null?tenderDetail.getTahdr():null;
			 params.put("tahdrMaterialId", tahdrMaterialId);
			 
			 /*List<PriceBidDto> bidderListWithPriceBid=null;
			 List<PriceBidDto> tempBidHistory=null;*/
			 List<Object> statisticData=null;
			 if(tenderDetail!=null && tender!=null){
				 String typeCode=tender.getTahdrTypeCode();
				 if(typeCode.equals("QFA")){
						/*bidderListWithPriceBid=priceBidService.findDtos("getDecendingPBQuickBidderDetailListByTahdrMaterialId", params);
						 tempBidHistory=tempPriceBidService.findDtos("getAllTempQuickPriceBidListByTahdrMaterialId", params);*/
					     statisticData=priceBidService.getAscendingQuickBidderStatistic(tahdrId, tahdrMaterialId);
						 
					 }else if (typeCode.equals("QRA")){
						/* bidderListWithPriceBid=priceBidService.findDtos("getAscendingPBQuickBidderDetailListByTahdrMaterialId", params);
						 tempBidHistory=tempPriceBidService.findDtos("getAllTempQuickPriceBidListByTahdrMaterialId", params);*/
						 statisticData=priceBidService.getDecendingQuickBidderStatistic(tahdrId, tahdrMaterialId);
					 }else if(typeCode.equals("FA")){
					  /*bidderListWithPriceBid=priceBidService.findDtos("getDecendingPBBidderDetailListByTahdrMaterialId", params);
					  tempBidHistory=tempPriceBidService.findDtos("getAllTempPriceBidListByTahdrMaterialId", params);*/
					  statisticData=priceBidService.getAscendingQuickBidderStatistic(tahdrId, tahdrMaterialId);
					 }else{
				     /* bidderListWithPriceBid=priceBidService.findDtos("getAscendingPBBidderDetailListByTahdrMaterialId", params);
					  tempBidHistory=tempPriceBidService.findDtos("getAllTempPriceBidListByTahdrMaterialId", params);*/
					  statisticData=priceBidService.getAscendingQuickBidderStatistic(tahdrId, tahdrMaterialId);
				 } 
				/*bidderListWithPriceBid.addAll(tempBidHistory);
				List<BidStatisticDto> grpahdto =priceBidService.setBidStatisticGraphDtoDetails(bidderListWithPriceBid);
				response.addObject("grpahdto", grpahdto);
				response.addObject("bidderList", bidderListWithPriceBid);*/
				response.addObject("statisticData", statisticData);
				response.addObject("result", true); 
					
				}else{
					response.addObject("resultMessage", "No Bidder Found !");
					response.addObject("result", false); 
				}
			 }else{
				 response.addObject("result", false); 
				 response.addObject("resultMessage", "Tender Not Found !"); 
			 }
		return response;
	}
	
	@RequestMapping(value= "/getBidSheetAuctionMaterialListById/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTAHDRMaterialList(@PathVariable("tahdrId")Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		List<TAHDRMaterialDto> materialList=tahdrMaterialService.getTahdrMaterialListByTahdrId(tahdrId); 
		response.addObject("responseList", materialList);
		return response;
	}
	
	
	/*@RequestMapping(value= "/getStatisticDetailsListByTender/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getStatisticDetailsListByTender(@PathVariable("tahdrId")Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		if(tahdrId!=null){
			TAHDRDto tender=tAHDRService.findDto(tahdrId);
			String status=tender.getTahdrStatusCode();
			Map<String, Object> params= new HashMap<>();
			params.put("tahdrId", tahdrId);
			
			List<BidderDto> bidderList=bidderService.findDtos("getBidderTahdrId", params);
			
			List<TAHDRMaterialDto> materialList=tahdrMaterialService.getTahdrMaterialListByTahdrId(tahdrId); 
			response.addObject("responseList", materialList);
			
 			if(status.equals("PU") && !CommonUtil.isCollectionEmpty(materialList)){
 				TAHDRMaterialDto material=materialList.get(0);
 				params.put("tahdrMaterialId", material.getTahdrMaterialId());
 				params.put("status", "SBMT");
				List<TechnicalBidDto> technicalBid=technicalBidService.findDtos("getTechnicalBidByStatusAndTahdrMaterialIdAndTahdrId", params);
				params.put("status", "PBSU");
				List<PriceBidDto> priceBid=priceBidService.findDtos("getPriceBidByStatusAndTahdrMaterialIdAndTahdrId", params);
				params.put("status", "SBMT");
				List<PriceBidDto> allSubmit=priceBidService.findDtos("getPriceBidByStatusAndTahdrMaterialIdAndTahdrId", params);
				response.addObject("technicalBidderCount", technicalBid==null?0:technicalBid.size());
				response.addObject("priceBidderCount", priceBid==null?0:priceBid.size());
				response.addObject("allBiddderCount", allSubmit==null?0:allSubmit.size());
				response.addObject("bidderCount", bidderList==null?0:bidderList.size());
			}else{
				response.addObject("bidderCount", bidderList);
			}
 			
			response.addObject("result", true);
		}else{
			response.addObject("result", false);
			response.addObject("resultMessage", "Something went Wrong !");
		}
		
		return response;
	}*/
	
	@RequestMapping(value= "/getStatisticDetailsListByMaterial/{tahdrId}/{tahdrMaterialId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getStatisticDetailsListByMaterial(@PathVariable("tahdrId")Long tahdrId,@PathVariable("tahdrMaterialId")Long tahdrMaterialId){
		CustomResponseDto response=new CustomResponseDto();
		if(tahdrId!=null & tahdrMaterialId!=null){
			TAHDRDto tender=tAHDRService.findDto(tahdrId);
			String status=tender.getTahdrStatusCode();
			Map<String, Object> params= new HashMap<>();
			params.put("tahdrId", tahdrId);
			List<BidderDto> bidderList=bidderService.findDtos("getBidderTahdrId", params);
 			if(status.equals("PU")){
 				params.put("tahdrMaterialId", tahdrMaterialId);
 				params.put("status", "SBMT");
				List<TechnicalBidDto> technicalBid=technicalBidService.findDtos("getTechnicalBidByStatusAndTahdrMaterialIdAndTahdrId", params);
				List<PriceBidDto> priceBid=priceBidService.findDtos("getPriceBidByStatusAndTahdrMaterialIdAndTahdrId", params);
				//List<PriceBidDto> allSubmit=priceBidService.findDtos("getPriceBidByStatusAndTahdrMaterialIdAndTahdrId", params);
				
				
				Map<String, Object> paramData= new HashMap<>();
 				paramData.put("status", "SBMT");
 				paramData.put("tahdrId", tahdrId);
 				List<CommercialBidDto> commercialBid=commercialBidService.findDtos("getCommercialBidByStatusAndTahdrId", paramData);
 				List<CommercialBidDto> allSubmit=commercialBidService.findDtos("getCommercialBidByStatusAndTahdrId", paramData);
				
				response.addObject("technicalBidderCount", technicalBid==null?0:technicalBid.size());
				response.addObject("priceBidderCount", priceBid==null?0:priceBid.size());
				response.addObject("allBiddderCount", allSubmit==null?0:allSubmit.size());
				response.addObject("bidderCount", bidderList==null?0:bidderList.size());
				response.addObject("bidders", bidderList);
				response.addObject("commercialBid", commercialBid==null?0:commercialBid.size());
			}else{
				response.addObject("bidderCount", bidderList);
			}
 			
			response.addObject("result", true);
		}else{
			response.addObject("result", false);
			response.addObject("resultMessage", "Something went Wrong !");
		}
		
		return response;
	}
	@RequestMapping(value= {"/getBidHistoryByTahdrMaterialId/{MaterialId}"},method =RequestMethod.POST)
	public @ResponseBody List<GraphDto> getBidHistoryByTahdrMaterialId(@PathVariable("MaterialId") Long MaterialId){
		Map<String, Object> params= new HashMap<>();
		params.put("MaterialId",MaterialId);
		List<PriceBidDto> PriceHistoryList=priceBidService.findDtos("getPriceBidHistorylist", params);
		List<GraphDto> grpahdto =priceBidService.setGraphDtoDetails(PriceHistoryList);
		
		return grpahdto;
	
	}
	@RequestMapping(value= {"/getBidderParticipation/{tahdrId}/{MaterialId}"},method =RequestMethod.POST)
	public @ResponseBody List<BidderParticipationDto> getBidderParticipation(@PathVariable("tahdrId") Long tahdrId,@PathVariable("MaterialId") Long MaterialId){
		List<BidderParticipationDto> BidderParticipationList=tempPriceBidService.getBidderParticipation(tahdrId,MaterialId);
		/*List<GraphDto> grpahdto =priceBidService.setGraphDtoDetails(PriceHistoryList);
		*/
		return BidderParticipationList;
	
	}
	
	@RequestMapping(value= "/endAuction/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto endAuction(@PathVariable("tahdrId")Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		boolean result=false;
		if(tahdrId!=null){
			TAHDRDetailDto tenderDetails=tAHDRDetailService.findDto("QueryForTAHDRDetailByTahdrId", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
			
            String extendedMinutes=sysConfiguratorService.getSystemPropertyConfigurator("eat.auto.extend.minutes");
			final int newEndDate =Integer.parseInt(extendedMinutes);
			
			Date newExtendedDate=new Date(tenderDetails.getAuctionToDate().getTime()+ (60*newEndDate * 1000));
			result=tenderDetailService.endQuicKAuction(tahdrId,newExtendedDate);
			TAHDRDto tender=tenderDetails.getTahdr();
			if(result){
				List<UserDto> bidderUser=userService.findDtos("getBidderMailListByTahdrId", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
				 String sub="Quick Auction Extension ";
				 String message="<p>Hi</p><br><h1><p>"
				 		+ "The Quick Auction: <b>"+tender.getTahdrCode()+"</b> limited,it will end in next few minutes("+newExtendedDate+")."
				 				+ " Kindly Submit ASAP. </h1><p><br><br><p><h1> Regards,<br> <b>"+partner.getName()+"</b></h1><p> ";
				sendAuctionExtensionMail(bidderUser,sub,message);
				response.addObject("Status", true);
				response.addObject("Message", "Auction Ended !");
			}else{
				response.addObject("Status", false);
				response.addObject("Message", "Request Failed !");
			}
		}else{
			response.addObject("Status", false);
			response.addObject("Message", "Something went wrong !");
		}
		return response;
	}
	
	public void sendAuctionExtensionMail(List<UserDto> bidderUser,String sub,String message){
		List<String> emailList=new ArrayList<>();
		if(!CommonUtil.isCollectionEmpty(bidderUser)){
			for(UserDto user:bidderUser){
				if(user!=null){
					emailList.add(user.getEmail());
				}
				
			}
		}
		if(!CommonUtil.isCollectionEmpty(emailList)){
			MailDto mailDto = new MailDto();
			mailDto.setSubject(sub);
			mailDto.setMailContent(message);
			mailDto.setRecipientList(emailList);
			mailService.sendEmail(mailDto,true);
		}
		
	}
}
