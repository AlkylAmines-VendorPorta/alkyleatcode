package com.novelerp.eat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.util.AppPropertyUtil;
import com.novelerp.eat.service.LiveBidService;

@Controller
public class QuickLiveBidController {
	
	@Autowired
	private LiveBidService liveBidService;
	
	@Autowired
	private AppPropertyUtil appPropertyUtil;
	
	@RequestMapping(value= {"/QuickLiveBid"},method =RequestMethod.GET)
	public ModelAndView liveBid(){
		ModelAndView model= new ModelAndView("QuickLiveBid") ;
		model.addObject("isReverseAuctionActive",appPropertyUtil.getProperty("eat.auction.reverse.active"));
		model.addObject("isForwardAuctionActive",appPropertyUtil.getProperty("eat.auction.forward.active"));
		return model;
	}
	
	@RequestMapping(value= "/getQuickLiveBidAuctionMaterialListById/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTAHDRMaterialList(@PathVariable("tahdrId")Long tahdrId){
		return liveBidService.getQuickTAHDRMaterialList(tahdrId);
	}

	@RequestMapping(value= "/saveQuickAuctionWinner/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveQuickAuctionWinner(@PathVariable("tahdrId")Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		try{
			response=liveBidService.saveQuickAuctionWinner(tahdrId);
		}catch(Exception e){
			response.addObject("Message", "Auction Ended,But Could Not Select Winner");
			response.addObject("Status", false);
		}
		return response;
	}
}
