package com.novelerp.eat.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.novelerp.eat.dto.OtpDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.service.LiveBidService;

@Controller
public class LiveBidController {
	
	
	@Autowired
	private LiveBidService liveBidService;
	
	@RequestMapping(value= {"/liveBid"},method =RequestMethod.GET)
	public ModelAndView liveBid(){
		return new ModelAndView("liveBid");
	}
	
	@RequestMapping(value= {"/myLiveBid"},method =RequestMethod.GET)
	public ModelAndView myTenderScheduling(@RequestParam("tahdrId") Long tahdrId){
		ModelAndView view=new ModelAndView("liveBid");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		view.addObject("myTenderUrl","getMyAuctionForLiveBid/"+tahdrId);
	    return view;
	}
	
	@RequestMapping(value= {"/getMyAuctionForLiveBid/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getMyAuctionForLiveBid(@PathVariable("tahdrId") Long tahdrId){
		return liveBidService.getMyAuctionForLiveBid(tahdrId);
	}
	
	
	@RequestMapping(value= {"/getLiveAuctions/{typeCode}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getLiveAuctions(@PathVariable("typeCode") String typeCode){
		return liveBidService.getLiveAuctions(typeCode);
	}
	
	@RequestMapping(value= {"/getOverAllRank/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getOverAllRank(@PathVariable("tahdrId") Long tahdrId){
		return liveBidService.getOverAllRank(tahdrId);
	}

	
	@RequestMapping(value= "/getLiveBidAuctionMaterialListById/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTAHDRMaterialList(@PathVariable("tahdrId")Long tahdrId){
		return liveBidService.getTAHDRMaterialList(tahdrId);
	}
	
	@RequestMapping(value= {"/getLiveBidByTahdrId/{tahdrId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getBidderForLiveBidByTahdrId(@PathVariable("tahdrId") Long tahdrId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		return liveBidService.getBidderForLiveBidByTahdrId(tahdrId, tahdrMaterialId);
	}
	
	@RequestMapping(value= {"/getBidderListByTahdrId/{tahdrId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getBidderListByTahdrId(@PathVariable("tahdrId") Long tahdrId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		return liveBidService.getBidderListByTahdrId(tahdrId, tahdrMaterialId);
	}
	
	@RequestMapping(value= {"/getBidListByTahdrId/{tahdrId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getBidListByTahdrId(@PathVariable("tahdrId") Long tahdrId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		return liveBidService.getBidListByTahdrId(tahdrId, tahdrMaterialId);
	}
	
	@RequestMapping(value= {"/resendOtp"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto resendOtp(@RequestParam("tahdrId")Long tahdrId,@RequestParam("tahdrName")String tahdrName, HttpServletRequest request){
		return liveBidService.resendOtp(tahdrId, tahdrName, request);
	}
	
	@RequestMapping(value= {"/sendOtp"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto sendOtp(@RequestParam("tahdrId")Long tahdrId,@RequestParam("tahdrName")String tahdrName, HttpServletRequest request){
	  return liveBidService.sendOtp(tahdrId, tahdrName, request);
	}

	@RequestMapping(value= {"/validateOtp"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto validateOtp(@ModelAttribute("otp") OtpDto otp, HttpServletRequest request){
		return liveBidService.validateOtp(otp, request);
	}
	
	
	@RequestMapping(value= {"/saveNewBid"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveNewBid(@ModelAttribute("newBidData") PriceBidDto newBid,HttpServletRequest request){
		return liveBidService.saveNewBid(newBid, request);
	}
	

	@RequestMapping(value= {"/autoRefreshData/{tahdrId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto autoRefreshData(@PathVariable("tahdrId") Long tahdrId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		return liveBidService.autoRefreshData(tahdrId, tahdrMaterialId);
		
	}
	
		
}
