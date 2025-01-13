package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.SessionKeyDto;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.service.CommercialBidService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TenderOpeningService;

@Controller
public class TenderOpeningController {
	
	@Autowired
	private TenderOpeningService tenderOpeningService;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private CommercialBidService commercialBidService;
	
	@RequestMapping(value= {"/tenderOpening"},method =RequestMethod.GET)
	public ModelAndView tenderOpening(){
		ModelAndView view =new ModelAndView("tenderOpening");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
	    return view;
	}
	
	@RequestMapping(value= {"/auctionOpening"},method =RequestMethod.GET)
	public ModelAndView auctionOpening(){
		ModelAndView view =new ModelAndView("tenderOpening");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
	    return view;
	}
	
	@RequestMapping(value= {"/myTenderOpening"},method =RequestMethod.GET)
	public ModelAndView myTenderScheduling(@RequestParam("tahdrId") Long tahdrId){
		ModelAndView view=new ModelAndView("tenderOpening");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
		view.addObject("myTenderUrl","mySearchOpeningTender/"+tahdrId);
	    return view;
	}
	
	@RequestMapping(value= {"/myAuctionOpening"},method =RequestMethod.GET)
	public ModelAndView myAuctionScheduling(@RequestParam("tahdrId") Long tahdrId){
		ModelAndView view=new ModelAndView("tenderOpening");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		view.addObject("myTenderUrl","mySearchOpeningTender/"+tahdrId);
	    return view;
	}
	
	
	
	@RequestMapping(value= {"/tenderOpening/{tahdrId}/{tenderType}"},method =RequestMethod.GET)
	public ModelAndView myTenderTenderOpening(@PathVariable("tahdrId") Long tahdrId,@PathVariable("tenderType") String tenderType){
		ModelAndView view =new ModelAndView("tenderOpening");
		/*TAHDRDto tahdr=tAHDRService.findDto(tahdrId);*/
		view.addObject("tahdrId", tahdrId);
		view.addObject("tenderType", tenderType);
		return view;
	}
	
	@RequestMapping(value= {"/getOpeningTenderList"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getOpeningTenderList(){
		return tenderOpeningService.getOpeningTenderList();
	}
	@RequestMapping(value= {"/getOpeningTenderList/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getOpeningMyTenderList(@PathVariable("tahdrId") Long tahdrId){
		return tenderOpeningService.getOpeningMyTenderList(tahdrId);
	}
	
	@RequestMapping(value= {"/mySearchOpeningTender/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto searchOpeningTenderList(@PathVariable("tahdrId") Long tahdrId){
		return tenderOpeningService.searchOpeningTenderList(tahdrId);
	}

	@RequestMapping(value= {"/searchOpeningTenderList"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto searchOpeningTenderList(@ModelAttribute("tenderSearchData") TenderCommitteeDto tenderCommitteeDto){
		return tenderOpeningService.searchOpeningTenderList(tenderCommitteeDto);
	}
	/////////Commented By Sanjeev For NovelAuction
	
	/*public @ResponseBody CustomResponseDto searchOpeningTenderList(@ModelAttribute("tenderSearchData") TenderCommitteeDto tenderCommitteeDto)
	{
		CustomResponseDto response=new CustomResponseDto();
		UserDto userDto= contextService.getUser();
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		
		BPartnerDto partner=contextService.getPartner();
		
		if(partner !=null && userDto!=null){
			Map<String, Object> params= new HashMap<>();		
			params.put("bpartnerId", partner.getbPartnerId());
			
			if(userRole.equals("VENADM")){
				List<BidderDto> bidderTenderList=bidderService.getBidderAssociatedTenders(tenderCommitteeDto, partner);
				response.addObject("bidderTenderList", bidderTenderList);
			}
			else{
				List<TenderCommitteeDto>  tenderCommittee=tenderCommitteeService.getAssociatedTenders(tenderCommitteeDto,userDto);
				List<TAHDRDto>  tenderList=tAHDRService.getAssociatedTenders(tenderCommitteeDto,userDto);
				response.addObject("tenderCommittee", tenderList);
			}
		}else{
			
				response.addObject("bidderTenderList", null);
				response.addObject("tenderCommittee", null);
				response.addObject("resultMessage", "Partner or User not found in session !");
			
		}
		Map<String, String> bidderStatusList = refListService.getReferenceListMap(AppBaseConstant.BIDDER_STATUS);
		response.addObject("bidderStatusList", bidderStatusList);
		
		Map<String, String> tenderStatusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		response.addObject("tenderStatusList", tenderStatusList);
		response.addObject("viewBidDifference", ContextConstant.VIEW_BID_DELAY);
		return response;
	}*/
	/*@RequestMapping(value= {"/checkSessionKey/{sessionKey}/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto checkSessionKey(@PathVariable("tahdrId") Long tahdrId,@PathVariable("sessionKey") String sessionKey)*/
	@RequestMapping(value= {"/checkSessionKey"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto checkSessionKey(@ModelAttribute() SessionKeyDto sessionKeyDto){
		return tenderOpeningService.checkSessionKey(sessionKeyDto);
	}
	
	@RequestMapping(value= {"/vendorSessionKey/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto vendorSessionKey(@PathVariable("tahdrId") Long tahdrId){
		return tenderOpeningService.vendorSessionKey(tahdrId);
	}
	
	@RequestMapping(value= {"/tenderLogout/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto tenderLogout(@PathVariable("tahdrId") Long tahdrId){
		return tenderOpeningService.tenderLogout(tahdrId);
	}
	
	@RequestMapping(value= {"/openTenderByOpeningType"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto openingTenderByOpeningType(@ModelAttribute("tenderOpeningData") TAHDRDto tAHDRDto){
		return tenderOpeningService.openingTenderByOpeningType(tAHDRDto);
	}
	
	@RequestMapping(value= {"/viewTenderByOpeningType/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto viewTenderByOpeningType(@PathVariable("tahdrId") Long tahdrId){
		return tenderOpeningService.viewTenderByOpeningType(tahdrId);
	}
	
	@RequestMapping(value= {"/getBidderListByViewOpeningType/{tahdrId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto openAllBid(@PathVariable("tahdrId") Long tahdrId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		return tenderOpeningService.openAllBid(tahdrId, tahdrMaterialId);
	}
	
	@RequestMapping(value= {"/getBidderListByViewCommercialBid/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto openAllBid(@PathVariable("tahdrId") Long tahdrId){
		return tenderOpeningService.openAllBid(tahdrId);
	}
	
	@RequestMapping(value= {"/getBidderListByViewCommercialBidByBidderId/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getCommercialBid(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		CommercialBidDto commercialBid=commercialBidService.getCommercialBidByBidderId(bidderId);
		response.addObject("responseCbList", commercialBid);
		return response;
	}
	
	@RequestMapping(value= {"/getLoggedInAuditorList/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getLoggedInAuditorList(@PathVariable("tahdrId") Long tahdrId){
		return tenderOpeningService.getLoggedInAuditorList(tahdrId);
	}
	
	@RequestMapping(value= {"/getLoggedInBidderList/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getLoggedInBidderList(@PathVariable("tahdrId") Long tahdrId){
		return tenderOpeningService.getLoggedInBidderList(tahdrId);
	}
	
	@RequestMapping(value= "/getTAHDRMaterialListByTahdrId/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTAHDRMaterialList(@PathVariable("tahdrId")Long tahdrId){
		return tenderOpeningService.getTAHDRMaterialList(tahdrId);
	}
	
	@RequestMapping(value= "/getBidderListByTahdrId/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getBidderListByTahdrId(@PathVariable("tahdrId")Long tahdrId){
		return tenderOpeningService.getBidderListByTahdrId(tahdrId);
	}
	
	@RequestMapping(value= "/checkTenderOpened/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto checkTenderOpened(@PathVariable("tahdrId")Long tahdrId){
		return tenderOpeningService.checkTenderOpened(tahdrId);
	}
	
	@RequestMapping(value= "/getOpenedBids/{bidderId}/{tahdrId}/{tahdrMaterialId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getOpenedBids(@PathVariable("bidderId")Long bidderId,@PathVariable("tahdrMaterialId")Long tahdrMaterialId,@PathVariable("tahdrId")Long tahdrId){
		Map<String, Object> params=new HashMap<>();
		params.put("tahdrId", tahdrId);
		
		TAHDRDto tahdr = tahdrService.findDto("getQueryForTAHDRById", params);
		return tenderOpeningService.getOpenedBids(bidderId,tahdrMaterialId,tahdr.getTahdrStatusCode(),tahdr.getBidTypeCode());
	}
}
