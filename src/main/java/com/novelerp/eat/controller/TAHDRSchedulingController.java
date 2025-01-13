package com.novelerp.eat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.service.TAHDRSchedulingService;

/**
 * @author Ankita Tirodkar
 *
 */

@Controller
public class TAHDRSchedulingController  {
	
	@Autowired
	private TAHDRSchedulingService tAHDRSchedulingService;
	
	@RequestMapping(value= {"/tenderScheduling"},method =RequestMethod.GET)
	public ModelAndView tenderScheduling(){
		ModelAndView view=new ModelAndView("tenderScheduling");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
	    return view;
	}
	
	@RequestMapping(value= {"/auctionScheduling"},method =RequestMethod.GET)
	public ModelAndView auctionScheduling(){
		ModelAndView view=new ModelAndView("tenderScheduling");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
	    return view;
	}
	
	@RequestMapping(value= {"/myTenderScheduling"},method =RequestMethod.GET)
	public ModelAndView myTenderScheduling(@RequestParam("tahdrId") Long tahdrId){
		ModelAndView view=new ModelAndView("tenderScheduling");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
		view.addObject("myTenderUrl","getMyTenderDetailsForScheduling/"+tahdrId);
	    return view;
	}
	
	@RequestMapping(value= {"/myAuctionScheduling"},method =RequestMethod.GET)
	public ModelAndView myAuctionScheduling(@RequestParam("tahdrId") Long tahdrId){
		ModelAndView view=new ModelAndView("tenderScheduling");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		view.addObject("myTenderUrl","getMyTenderDetailsForScheduling/"+tahdrId);
	    return view;
	}
	
	@RequestMapping(value="/getMyTenderDetailsForScheduling/{tahdrId}",method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getMyTenderDetailsForScheduling(@PathVariable("tahdrId") Long tahdrId){
		return tAHDRSchedulingService.getMyTenderDetailsForScheduling(tahdrId);
	}
	
	@RequestMapping(value="/getTenderDetailsForScheduling/{tenderTypeCode}",method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderDetailsForScheduling(@PathVariable("tenderTypeCode") String tenderTypeCode){
		return tAHDRSchedulingService.getTenderDetailsForScheduling(tenderTypeCode);
	}
	
	@RequestMapping(value= {"/fetchTenderListForScheduling"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderList(@RequestParam("pageNumber")int pageNumber, 
														@RequestParam("pageSize") int pageSize,
														@RequestParam("searchMode") String searchColumn , 
														@RequestParam("serachValue") String searchValue,
														@RequestParam("typeCode") String typeCode){
		return tAHDRSchedulingService.getTenderList(pageNumber, pageSize, searchColumn, searchValue, typeCode);
	}
	
	@RequestMapping(value= "/updateTahdrScheduling", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto updateTahdrScheduling(@ModelAttribute("tahdrSchedule") TAHDRDetailDto tahdrDetail){
		return tAHDRSchedulingService.updateTahdrScheduling(tahdrDetail);
	}
}
