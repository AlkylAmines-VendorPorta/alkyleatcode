package com.novelerp.eat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ItemScrutinyLineDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.FinalScrutinyService;

/**
 * @author Aman Sahu
 *
 */
@Controller
public class FinalScrutinyController {
	
	
	
	@Autowired
	private FinalScrutinyService finalScrutinyService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
    
	@RequestMapping(value= {"/finalScrutiny"},method =RequestMethod.GET)
	public ModelAndView finalScrutiny(){
		ModelAndView view=new ModelAndView("finalScrutiny");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
	    return view;
	}
	
	@RequestMapping(value= {"/auctionFinalScrutiny"},method =RequestMethod.GET)
	public ModelAndView auctionFinalScrutiny(){
		ModelAndView view=new ModelAndView("finalScrutiny");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
	    return view;
	}
	
	@RequestMapping(value= {"/myTenderFinalScrutiny"},method =RequestMethod.GET)
	public ModelAndView myTenderFinalScrutiny(@RequestParam("tahdrId") Long tahdrId){
		ModelAndView view=new ModelAndView("finalScrutiny");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
		view.addObject("myTenderUrl","getMyTenderForFinalScrutiny/"+tahdrId);
	    return view;
	}
	
	@RequestMapping(value= {"/myAuctionFinalScrutiny"},method =RequestMethod.GET)
	public ModelAndView myAuctionFinalScrutiny(@RequestParam("tahdrId") Long tahdrId){
		ModelAndView view=new ModelAndView("finalScrutiny");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		view.addObject("myTenderUrl","getMyTenderForFinalScrutiny/"+tahdrId);
	    return view;
	}
	
	@RequestMapping(value= {"/getMyTenderForFinalScrutiny/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderList(@PathVariable("tahdrId") Long tahdrId){
		return finalScrutinyService.getTenderList(tahdrId);
	}
	
	@RequestMapping(value= {"/fetchTenderListForFinalScrutiny"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderList(@RequestParam("pageNumber")int pageNumber, 
														@RequestParam("pageSize") int pageSize,
														@RequestParam("searchMode") String searchColumn , 
														@RequestParam("serachValue") String searchValue,@RequestParam("typeCode") String typeCode){
		BPartnerDto partner=contextService.getPartner();
		contextService.setSFTPRequiredInfo(partner,typeCode,null);
		return finalScrutinyService.getTenderList(pageNumber, pageSize, searchColumn, searchValue, typeCode);
	}
	
	@RequestMapping(value= {"/getItemListForFinalBybidderId/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getItemListBybidderId(@PathVariable("bidderId") Long bidderId){
		return finalScrutinyService.getItemListBybidderId(bidderId);
	}
	@RequestMapping(value= {"/saveFinalTechnicalDeviationResponse"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveFinalTechnicalDeviationResponse(@ModelAttribute("itemScrutinyLine") ItemScrutinyLineDto itemScrutinyLine){
		return finalScrutinyService.saveFinalTechnicalDeviationResponse(itemScrutinyLine);
	}
	@RequestMapping(value= {"/saveFinalCommercialDeviationResponse"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveFinalCommercialDeviationResponse(@ModelAttribute("itemScrutinyLine") ItemScrutinyLineDto itemScrutinyLine){
		return finalScrutinyService.saveFinalCommercialDeviationResponse(itemScrutinyLine);
	}
	@RequestMapping(value= {"/saveTechnicalFinalDocumentDeviationResponse"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveTechnicalFinalDocumentDeviationResponse(@ModelAttribute("itemScrutinyLine") ItemScrutinyLineDto itemScrutinyLine){
		return finalScrutinyService.saveTechnicalFinalDocumentDeviationResponse(itemScrutinyLine);
	}
	@RequestMapping(value= {"/saveCommercialFinalDocumentDeviationResponse"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveCommercialFinalDocumentDeviationResponse(@ModelAttribute("itemScrutinyLine") ItemScrutinyLineDto itemScrutinyLine){
		return finalScrutinyService.saveCommercialFinalDocumentDeviationResponse(itemScrutinyLine);
	}
	@RequestMapping(value= {"/confirmFinalTechnicalScrutiny"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto confirmFinalTechnicalScrutiny(@ModelAttribute("itemScrutinyLine") ItemScrutinyDto itemScrutiny){
		return finalScrutinyService.confirmFinalTechnicalScrutiny(itemScrutiny);
	}
	@RequestMapping(value= {"/confirmFinalCommercialScrutiny"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto confirmFinalCommercialScrutiny(@ModelAttribute("itemScrutiny") ItemScrutinyDto itemScrutiny){
		return finalScrutinyService.confirmFinalCommercialScrutiny(itemScrutiny);
	}
	@RequestMapping(value= {"/getFinalScrutinyStatus/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getFinalScrutinyStatus(@PathVariable("tahdrId") Long tahdrId){
		return finalScrutinyService.getFinalScrutinyStatus(tahdrId);
	}
	
	@RequestMapping(value= {"/confirmFinalScrutiny"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto confirmFinalScrutiny(@ModelAttribute("tahdr") TAHDRDto tahdrDto){
		return finalScrutinyService.confirmFinalScrutiny(tahdrDto);
	}
	
	@RequestMapping(value= {"/saveAuditorFinalComment"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveAuditorComment(@ModelAttribute("auditorData") ItemScrutinyLineDto dto){
		return finalScrutinyService.saveAuditorComment(dto);
	}
	
	@RequestMapping(value= {"/confirmAuditorFinalComment"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto confirmAuditorComment(@ModelAttribute("auditorData") ItemScrutinyDto dto){
		return finalScrutinyService.confirmAuditorComment(dto);
	}
	
	@RequestMapping(value= {"/intimidateFinalAuditing"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto intimidateFinalAuditing(@ModelAttribute("tahdr") TAHDRDto tahdr){
		return finalScrutinyService.intimidateFinalAuditing(tahdr);
	}
	
	@RequestMapping(value= {"/notifyAuditor"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto notifyAuditor(@RequestParam("tahdrId") Long tahdrId){
		return finalScrutinyService.notifyAuditor(tahdrId);
	}
	
	@RequestMapping(value= {"/getTechnicalScrutinyDeviation/{itemBidId}"},method =RequestMethod.POST)
	public @ResponseBody ItemScrutinyDto getTechnicalScrutinyDeviation(@PathVariable("itemBidId") Long itemBidId){
		return finalScrutinyService.getTechnicalScrutinyDeviation(itemBidId);
	}
	
	@RequestMapping(value= {"/getCommercialScrutinyDeviation/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody ItemScrutinyDto getCommercialScrutinyDeviation(@PathVariable("bidderId") Long bidderId){
		return finalScrutinyService.getCommercialScrutinyDeviation(bidderId);
	}

}
