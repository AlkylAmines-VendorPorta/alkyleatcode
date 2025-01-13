package com.novelerp.eat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TAHDRSearchDto;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRService;

/**
 * @author Ankita Tirodkar
 *
 */

@Controller
public class AdvanceSearchController {
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	@Autowired
	private ReferenceListService referenceListService;
	@Autowired
	private TAHDRService tahdrService;
	
	@RequestMapping(value= {"/advanceSearch"},method =RequestMethod.GET)
	public ModelAndView advanceSearch()
	{	
		ModelAndView view=new ModelAndView("advanceSearch");
		return view;
	}
	@RequestMapping(value= {"/getTenderSearchDetail"},method =RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String,Object> getTenderSearchDetail()
	{	
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("tahdrType",referenceListService.getReferenceListMap(AppBaseConstant.TENDER_TYPE));
		map.put("bidType", referenceListService.getReferenceListMap(AppBaseConstant.BID_TYPE));
		return map;
	}
	@RequestMapping(value= {"/getAdvanceSearchCount"},method =RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String,Object> getAdvanceSearchCount()
	{	
		Map<String,Object> map=new HashMap<String,Object>();
		List<TAHDRDto> tenderListFA = new ArrayList<>();
		List<TAHDRDto> tenderListRA = new ArrayList<>();
		
		tenderListFA=(List<TAHDRDto>) tahdrService.find(" tahdrStatusCode='PU' AND isPrivateAuction IS NULL AND tahdrTypeCode='RA'", map, "updated");
		tenderListRA=(List<TAHDRDto>) tahdrService.find(" tahdrStatusCode='PU' AND isPrivateAuction IS NULL  AND tahdrTypeCode='FA'", map, "updated");
		map.put("FACount",tenderListFA.size());
		map.put("RACount", tenderListRA.size());
		return map;
	}	
	
	@RequestMapping(value = "/getSearchList", method = RequestMethod.POST,produces="application/json")
	public @ResponseBody Map<String,Object> getSearchList(@ModelAttribute("searchParameter") TAHDRSearchDto tahdrsSearchDto,HttpServletRequest request) {	
		HttpSession session = request.getSession();
		UserDto userDto=(UserDto) session.getAttribute("UserSession");
		Map<String,Object> map=new HashMap<String,Object>();
		List<TAHDRDetailDto> tenderAuctionList=new ArrayList<TAHDRDetailDto>();
		if(userDto!=null)
		{
			tahdrsSearchDto.setRole(userDto.getRoles().iterator().next().getValue());
			if(tahdrsSearchDto.getRole().equals("MSEBUSER"))
			{
				tahdrsSearchDto.setLocationType(userDto.getUserDetails().getLocationTypeRef());
			}
			tenderAuctionList = tahdrDetailService.getTAHDRList(tahdrsSearchDto);
		}	
		else
		{
			tenderAuctionList = tahdrDetailService.getTAHDRList(tahdrsSearchDto);
		}
			
		
		map.put("DATA", tenderAuctionList);
		return map;
	}
	
	@RequestMapping(value = "/getTahdrTypeCode/{tahdrTypeCode}", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getTahdrType(@PathVariable("tahdrTypeCode") String tahdrTypeCode) {
		TAHDRSearchDto tahdrSearchDto= new TAHDRSearchDto();
		List<TAHDRDetailDto> tenderAuctionList=new ArrayList<TAHDRDetailDto>();
		Map<String,Object> map=new HashMap<String,Object>();
		TAHDRDto tahdrDto = new TAHDRDto();
		tahdrDto.setTahdrTypeCode(tahdrTypeCode);
		tahdrSearchDto.setTahdr(tahdrDto);
		tenderAuctionList = tahdrDetailService.getTAHDRList(tahdrSearchDto);
		map.put("DATA", tenderAuctionList);
		return map;
}
	@RequestMapping(value = "/getLatestTahdrTypeCode/{tahdrTypeCode}", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getLatestTahdrType(@PathVariable("tahdrTypeCode") String tahdrTypeCode) {
		TAHDRSearchDto tahdrSearchDto= new TAHDRSearchDto();
		List<TAHDRDetailDto> tenderAuctionList=new ArrayList<TAHDRDetailDto>();
		List<TAHDRDetailDto> topFive=new ArrayList<TAHDRDetailDto>();
		Map<String,Object> map=new HashMap<String,Object>();
		TAHDRDto tahdrDto = new TAHDRDto();
		tahdrDto.setTahdrTypeCode(tahdrTypeCode);
		tahdrSearchDto.setTahdr(tahdrDto);
		tenderAuctionList = tahdrDetailService.getTAHDRList(tahdrSearchDto);
		int count=1;
		for(TAHDRDetailDto dto:tenderAuctionList){
			topFive.add(dto);
			if(count==AppBaseConstant.LATEST_TAHDR_COUNT){
				break;
			}
			count++;
		}
		map.put("DATA", topFive);
		return map;
}
}
