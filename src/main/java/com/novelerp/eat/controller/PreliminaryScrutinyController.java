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
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.PreliminaryScrutinyService;

/**
 * @author Aman Sahu
 *
 */
@Controller
public class PreliminaryScrutinyController {

	@Autowired
	private PreliminaryScrutinyService preliminaryScrutinyService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
		
	@RequestMapping(value= {"/tenderTechnicalScrutiny"},method =RequestMethod.GET)
	public ModelAndView tenderTechnicalScrutinyView(){
		ModelAndView view=new ModelAndView("tenderTechnicalScrutiny");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
	    return view;
	}
	
	@RequestMapping(value= {"/auctionTechnicalScrutiny"},method =RequestMethod.GET)
	public ModelAndView auctionTechnicalScrutiny(){
		ModelAndView view=new ModelAndView("tenderTechnicalScrutiny");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
	    return view;
	}
	
	@RequestMapping(value= {"/myTenderTechnicalScrutiny"},method =RequestMethod.GET)
	public ModelAndView myTenderTechnicalScrutiny(@RequestParam("tahdrId") Long tahdrId){
		ModelAndView view=new ModelAndView("tenderTechnicalScrutiny");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
		view.addObject("myTenderUrl","getMyTenderForScrutiny/"+tahdrId);
	    return view;
	}
	
	@RequestMapping(value= {"/myAuctionTechnicalScrutiny"},method =RequestMethod.GET)
	public ModelAndView myAuctionTechnicalScrutiny(@RequestParam("tahdrId") Long tahdrId){
		ModelAndView view=new ModelAndView("tenderTechnicalScrutiny");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		view.addObject("myTenderUrl","getMyTenderForScrutiny/"+tahdrId);
	    return view;
	}
	
	@RequestMapping(value= {"/getMyTenderForScrutiny/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getMyTender(@PathVariable("tahdrId") Long tahdrId){
		return preliminaryScrutinyService.getMyTender(tahdrId);
		
	}
	
	@RequestMapping(value= {"/fetchTenderListForPreliminaryScrutiny"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderList(@RequestParam("pageNumber")int pageNumber, 
			@RequestParam("pageSize") int pageSize,
			@RequestParam("searchMode") String searchColumn , 
			@RequestParam("serachValue") String searchValue,@RequestParam("typeCode") String typeCode){
	
		BPartnerDto partner=contextService.getPartner();
		contextService.setSFTPRequiredInfo(partner,typeCode,null);
		return preliminaryScrutinyService.getTenderList(pageNumber, pageSize, searchColumn, searchValue, typeCode);
		
	}
	
	@RequestMapping(value= {"/getBidderListByTahdrDetailId/{tahdrDetailId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getBidderListByTahdrDetailId(@PathVariable("tahdrDetailId") Long tahdrDetailId){
		return preliminaryScrutinyService.getBidderListByTahdrDetailId(tahdrDetailId);
	}
	
	@RequestMapping(value= {"/getItemListBybidderId/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getItemListBybidderId(@PathVariable("bidderId") Long bidderId){
		return preliminaryScrutinyService.getItemListBybidderId(bidderId);
	}
	
	@RequestMapping(value= {"/savePreliminaryTechnicalScrutiny"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto savePreliminaryTechnicalScrutiny(@ModelAttribute("itemScrutiny") ItemScrutinyDto newItemScrutinyDto){
		return preliminaryScrutinyService.savePreliminaryTechnicalStatus(newItemScrutinyDto);
	}
	
	@RequestMapping(value= {"/savePreliminaryCommercialScrutiny"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto savePreliminaryCommercialScrutiny(@ModelAttribute("itemScrutiny") ItemScrutinyDto newItemScrutinyDto){
		return preliminaryScrutinyService.savePreliminaryCommercialStatus(newItemScrutinyDto);
	}
	
	@RequestMapping(value= {"/checkDeviationSchedule/{tahdrDetailId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto checkDeviationSchedule(@PathVariable() Long tahdrDetailId){
		return preliminaryScrutinyService.checkDeviationSchedule(tahdrDetailId);
	}
	
	@RequestMapping(value= {"/saveItemScrutinyLine"},method =RequestMethod.POST)
	public @ResponseBody ItemScrutinyLineDto saveGtpScrutiny(@ModelAttribute("itemScrutinyLine") ItemScrutinyLineDto dto){
		return preliminaryScrutinyService.saveGtpScrutiny(dto);
	}
	
	@RequestMapping(value= {"/updateTechnicalItemScrutinyLine"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto updateTechnicalItemScrutinyLine(@ModelAttribute("itemScrutinyLine") ItemScrutinyLineDto dto){
		return 	preliminaryScrutinyService.updateTechnicalItemScrutinyLine(dto);
	}
	
	@RequestMapping(value= {"/updateCommercialItemScrutinyLine"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto updateCommercialItemScrutinyLine(@ModelAttribute("itemScrutinyLine") ItemScrutinyLineDto dto){
		return 	preliminaryScrutinyService.updateCommercialItemScrutinyLine(dto);
	}

	@RequestMapping(value= {"/saveDeviationSchedule"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveDeviationSchedule(@ModelAttribute("tahdrDetail") TAHDRDetailDto dto){
		return preliminaryScrutinyService.saveDeviationSchedule(dto);
	}
	
	@RequestMapping(value= {"/saveAuditorComment"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveAuditorComment(@ModelAttribute("auditorData") ItemScrutinyLineDto dto){
		return preliminaryScrutinyService.saveAuditorComment(dto);
	}
	
	@RequestMapping(value= {"/confirmAuditorComment"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto confirmAuditorComment(@ModelAttribute("auditorData") ItemScrutinyDto dto){
		return preliminaryScrutinyService.confirmAuditorComment(dto);
	}
	
	@RequestMapping(value= {"/checkAuditingStatus/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto checkAuditingStatus(@PathVariable("tahdrId") Long tahdrId){
	    return preliminaryScrutinyService.checkAuditingStatus(tahdrId);
	}
	
	@RequestMapping(value= {"/addAuditor"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto addAuditor(@ModelAttribute("tahdr") TAHDRDto tahdr){
		return preliminaryScrutinyService.addAuditor(tahdr);
	}
	
	@RequestMapping(value= {"/intimidateAuditing"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto intimidateAuditing(@ModelAttribute("tahdr") TAHDRDto tahdr){
		return preliminaryScrutinyService.intimidateAuditing(tahdr);
	}
	
	@RequestMapping(value= {"/intimidateAuditor"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto intimidateAuditor(@RequestParam("tahdrId") Long tahdrId,@RequestParam("tahdrName") String tahdrName,@RequestParam("auditorId") Long auditorId){
		return preliminaryScrutinyService.intimidateAuditor(tahdrId,tahdrName,auditorId);
	}
	
}


