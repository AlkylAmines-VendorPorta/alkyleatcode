package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.TAHDRService;

@Controller
public class QuickBidSheetController {
	
	@Autowired
	private TAHDRService tAHDRService;
	
	@Autowired
	private ReferenceListService refListService; 

	@RequestMapping(value= {"/quickBidSheet"},method =RequestMethod.GET)
	public ModelAndView bidSheet(){
		ModelAndView view=new ModelAndView("quickBidSheet");
		/*view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);*/
	    return view;
	}
	
	@RequestMapping(value= {"/getQuickAuctionForLiveBid/{typeCode}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderList(@PathVariable("typeCode") String typeCode){
		CustomResponseDto response=new CustomResponseDto();
		List<TAHDRDto> tahdrList=null;
		Map<String, Object> params= new HashMap<>();
		params.put("typeCode", typeCode);
		params.put("status1", AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
		
		tahdrList=tAHDRService.findDtos("getQuickAuctionListByStatusAndTypeCode", params);
			
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
}
