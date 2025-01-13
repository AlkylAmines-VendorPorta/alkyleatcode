package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ScrutinyFileDto;
import com.novelerp.eat.service.ItemScrutinyService;
import com.novelerp.eat.service.ScrutinyFileService;

@Controller
public class ScrutinyFileController {
	
	@Autowired
	private ScrutinyFileService scrutinyFileService;
	
	@Autowired
	private ItemScrutinyService itemScrutinyService;
	
	@RequestMapping(value= {"/getTechnicalScrutiny"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTechnicalScrutiny(@RequestParam("bidderId") Long bidderId,@RequestParam("itemBidId") Long itemBidId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		 params.put("bidderId", bidderId);
		 params.put("itemBidId", itemBidId);
		 params.put("scrutinyType", "TECHSCR");
		 params.put("scrutinyLevel", "PRELIMINARY");
		 ScrutinyFileDto scrutinyFile=scrutinyFileService.findDto("getTechnicalScrutinyFileByItemBidIdAndBidderId", params);
		response.addObject("data", scrutinyFile);
		return response;
	}
	
	@RequestMapping(value= {"/getCommercialScrutiny"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getCommercialScrutiny(@RequestParam("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		params.put("bidderId", bidderId);
		 params.put("scrutinyType", "COMMSCR");
		 params.put("scrutinyLevel", "PRELIMINARY");
		ScrutinyFileDto scrutinyFile=scrutinyFileService.findDto("getCommercialScrutinyFileAndBidderId", params);
		response.addObject("data", scrutinyFile);
		return response;
	}
	
	@RequestMapping(value= {"/getFinalTechnicalScrutiny"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getFinalTechnicalScrutiny(@RequestParam("bidderId") Long bidderId,@RequestParam("itemBidId") Long itemBidId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		params.put("bidderId", bidderId);
		 params.put("itemBidId", itemBidId);
		 params.put("scrutinyType", "TECHSCR");
		 params.put("scrutinyLevel", "FINAL");
		ScrutinyFileDto scrutinyFile=scrutinyFileService.findDto("getTechnicalScrutinyFileByItemBidIdAndBidderId", params);
		response.addObject("data", scrutinyFile);
		return response;
	}
	
	@RequestMapping(value= {"/getFinalCommercialScrutiny"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getFinalCommercialScrutiny(@RequestParam("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		params.put("bidderId", bidderId);
		 params.put("scrutinyType", "COMMSCR");
		 params.put("scrutinyLevel", "FINAL");
		ScrutinyFileDto scrutinyFile=scrutinyFileService.findDto("getCommercialScrutinyFileAndBidderId", params);
		response.addObject("data", scrutinyFile);
		return response;
	}
	
	@RequestMapping(value= {"/savePreliminaryScrutinyFile"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto savePreliminaryScrutinyFile(@ModelAttribute("scrutinyFile") ScrutinyFileDto scrutinyFile){
		CustomResponseDto response=new CustomResponseDto();
		ItemScrutinyDto itemScrutiny=itemScrutinyService.findDto(scrutinyFile.getItemScrutiny().getItemScrutinyId());
		boolean auditStatus=scrutinyFileService.isAuditingSubmitted(scrutinyFile,itemScrutiny);
		if(auditStatus){
			response=scrutinyFileService.savePreliminaryScrutinyFile(scrutinyFile,itemScrutiny);
		}else{
			response.addObject("result", false);	
        	response.addObject("message", "Auditing has not been approved");	
		}
		return response;
	}
        
	@RequestMapping(value= {"/saveFinalScrutinyFile"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveFinalScrutinyFile(@ModelAttribute("scrutinyFile") ScrutinyFileDto scrutinyFile){
		CustomResponseDto response=new CustomResponseDto();
		ItemScrutinyDto itemScrutiny=itemScrutinyService.findDto(scrutinyFile.getItemScrutiny().getItemScrutinyId());
		boolean auditStatus=scrutinyFileService.isAuditingSubmitted(scrutinyFile,itemScrutiny);
		if(auditStatus){
			response=scrutinyFileService.saveFinalScrutinyFile(scrutinyFile,itemScrutiny);
		}else{
			response.addObject("result", false);	
        	response.addObject("message", "Auditing has not been approved");
		}
		return response;
	}
}
