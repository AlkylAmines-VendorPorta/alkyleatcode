package com.novelerp.eat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.util.AppPropertyUtil;

@Controller
public class QuickAuctionWinnerSelectionController {

	@Autowired
	private AppPropertyUtil appPropertyUtil;
	
	@RequestMapping(value= "/quickAuctionWinnerSelection", method=RequestMethod.GET)
	public ModelAndView auctionWinnerSelection(){
		ModelAndView model= new ModelAndView("quickAuctionWinnerSelection");
		model.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		model.addObject("type", "winnerSelection");
		model.addObject("isReverseAuctionActive",appPropertyUtil.getProperty("eat.auction.reverse.active"));
		model.addObject("isForwardAuctionActive",appPropertyUtil.getProperty("eat.auction.forward.active"));
		return model;	}
}
