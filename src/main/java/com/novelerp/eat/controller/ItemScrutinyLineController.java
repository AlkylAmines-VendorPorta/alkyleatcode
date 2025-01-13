package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.ItemScrutinyLineDto;
import com.novelerp.eat.service.ItemScrutinyLineService;

@Controller
public class ItemScrutinyLineController {
	
	@Autowired
	private ItemScrutinyLineService itemScrutinyLineService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	
	
	@RequestMapping(value= {"/getScrutinyPoint/{itemScrunityId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getScrutinyPoint(@PathVariable("itemScrunityId") Long itemScrunityId){
		Map<String, Object> params= new HashMap<>();		
		params.put("itemScrutinyId", itemScrunityId);
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getScrutinyPoint", params);
		CustomResponseDto response=new CustomResponseDto();
		if(!CommonUtil.isCollectionEmpty(itemScrutinylineList)){
			response.addObject("result", true);
			response.addObject("itemScrutinyList", itemScrutinylineList);
			response.addObject("resultMessage", "Technical Scrutiny is Approved");
		}
		else{
			response.addObject("result", false);
			response.addObject("resultMessage", "Technical Scrutiny is not Approved");
		}
		response.addObject("role", userRole);
		return response;
		
	}
	
	@RequestMapping(value= {"/getBidderGtpByItemScrutinyId/{itemScrunityId}"},method =RequestMethod.POST)
	public @ResponseBody List<ItemScrutinyLineDto> getBidderGtp(@PathVariable("itemScrunityId") Long itemScrunityId){
		Map<String, Object> params= new HashMap<>();		
		params.put("itemScrutinyId", itemScrunityId);
		List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getBidderGtp", params);
		return itemScrutinylineList;
		
	}
	
	@RequestMapping(value= {"/getBidderGtpByTahdrMaterialIdAndBidderId/{tahdrMaterialId}/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody List<ItemScrutinyLineDto> getBidderGtpByTahdrMaterialIdAndBidderId(@PathVariable("tahdrMaterialId") Long tahdrMaterialId,
			                                                                                @PathVariable("bidderId") Long bidderId){
		Map<String, Object> params= new HashMap<>();		
		params.put("tahdrMaterialId", tahdrMaterialId);
		params.put("bidderId", bidderId);
		List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getBidderGtpByTahdrMaterialIdAndBidderId", params);
		return itemScrutinylineList;
		
	}
	
	@RequestMapping(value= {"/getFinalBidderGtpByTahdrMaterialIdAndBidderId/{tahdrMaterialId}/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody List<ItemScrutinyLineDto> getFinalBidderGtpByTahdrMaterialIdAndBidderId(@PathVariable("tahdrMaterialId") Long tahdrMaterialId,
			                                                                                @PathVariable("bidderId") Long bidderId){
		Map<String, Object> params= new HashMap<>();		
		params.put("tahdrMaterialId", tahdrMaterialId);
		params.put("bidderId", bidderId);
		List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getFinalBidderGtpByTahdrMaterialIdAndBidderId", params);
		return itemScrutinylineList;
		
	}
	
	@RequestMapping(value= {"/getBidderGtpByItemBidId/{itemBidId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getBidderGtpByItemBidId(@PathVariable("itemBidId") Long itemBidId){
		CustomResponseDto response= new CustomResponseDto();
		Set<RoleDto> role=contextService.getRoles();
		if(!CommonUtil.isCollectionEmpty(role)){ 
			String userRole=role.iterator().next().getValue();
			Map<String, Object> params= new HashMap<>();		
			params.put("itemBidId", itemBidId);
			/*ItemScrutinyDto itemScrutiny=itemScrutinyService.findDto("getItemscrutinyByItemBidId", params);*/
			List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getBidderGtpByItemBidId", params);
			response.addObject("bidderGtpList", itemScrutinylineList);
			response.addObject("role", userRole);
			/*response.addObject("itemScrutiny", itemScrutiny);*/
		}
		
		return response;
		
	}
	
	@RequestMapping(value= {"/getScrutinyPointByBidderId/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getScrutinyPointByItemBidId(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response= new CustomResponseDto();
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		Map<String, Object> params= new HashMap<>();		
		params.put("bidderId", bidderId);
		List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getScrutinyPointByItemBidId", params);
		response.addObject("scrutinyPointList", itemScrutinylineList);
		response.addObject("role", userRole);
		return response;
		
	}
	
	@RequestMapping(value= {"/getPreScrutinyPointByBidderId/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPreScrutinyPointByBidderId(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response= new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("bidderId", bidderId);
		List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getPreScrutinyPointByBidderId", params);
		response.addObject("scrutinyPointList", itemScrutinylineList);
		return response;
		
	}
	
	@RequestMapping(value= {"/getFinalScrutinyPointByBidderId/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getFinalScrutinyPointByBidderId(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response= new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("bidderId", bidderId);
		List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getFinalScrutinyPointByBidderId", params);
		response.addObject("scrutinyPointList", itemScrutinylineList);
		return response;
		
	}
	
	@RequestMapping(value= {"/getTechnicalDocumentsByItemBidId/{itemBidId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTechnicalDocumentsByItemBidId(@PathVariable("itemBidId") Long itemBidId){
		CustomResponseDto response= new CustomResponseDto();
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		Map<String, Object> params= new HashMap<>();		
		params.put("itemBidId", itemBidId);
		/*ItemScrutinyDto itemScrutiny=itemScrutinyService.findDto("getItemscrutinyByItemBidId", params);*/
		List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getDeviationTechnicalDocuments", params);
		response.addObject("documentList", itemScrutinylineList);
		response.addObject("role", userRole);
		/*response.addObject("itemScrutiny", itemScrutiny);*/
		return response;
		
	}
	
	@RequestMapping(value= {"/getTechnicalDocumentsByTahdrMaterialIdAndBidderId/{tahdrMaterialId}/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTechnicalDocumentsByTahdrMaterialIdAndBidderId(@PathVariable("tahdrMaterialId") Long tahdrMaterialId
			                                                               ,@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response= new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("tahdrMaterialId", tahdrMaterialId);
		params.put("bidderId", bidderId);
		List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getTechnicalDocumentsByTahdrMaterialIdAndBidderId", params);
		response.addObject("documentList", itemScrutinylineList);
		return response;
	}
	
	@RequestMapping(value= {"/getFinalTechnicalDocumentsByTahdrMaterialIdAndBidderId/{tahdrMaterialId}/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getFinalTechnicalDocumentsByTahdrMaterialIdAndBidderId(@PathVariable("tahdrMaterialId") Long tahdrMaterialId
			                                                               ,@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response= new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("tahdrMaterialId", tahdrMaterialId);
		params.put("bidderId", bidderId);
		List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getFinalTechnicalDocumentByTahdrMaterialIdAndBidderId", params);
		response.addObject("documentList", itemScrutinylineList);                      
		return response;
	}
	
	@RequestMapping(value= {"/getCommercialDocumentsBybidderId/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getCommercialDocumentsBybidderId(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response= new CustomResponseDto();
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		Map<String, Object> params= new HashMap<>();		
		params.put("bidderId", bidderId);
		List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getDeviationCommercialDocuments", params);
		response.addObject("documentList", itemScrutinylineList);
		response.addObject("role", userRole);
		return response;
		
	}
	
	@RequestMapping(value= {"/getPreCommercialDocumentsBybidderId/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPreCommercialDocumentsBybidderId(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response= new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("bidderId", bidderId);
		List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getPreCommercialDocumentsByBidderId", params);
		response.addObject("documentList", itemScrutinylineList);
		return response;
	}
	
	@RequestMapping(value= {"/getFinalCommercialDocumentsBybidderId/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getFinalCommercialDocumentsBybidderId(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response= new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("bidderId", bidderId);
		List<ItemScrutinyLineDto> itemScrutinylineList=itemScrutinyLineService.findDtos("getFinalCommercialDocumentsByBidderId", params);
		response.addObject("documentList", itemScrutinylineList);
		return response;
	}
	
	
	
	@RequestMapping(value= {"/getTechnicalDoc/{itemBidId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTechnicalDoc(@PathVariable("itemBidId") Long itemBidId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("itemBidId", itemBidId);
		List<ItemScrutinyLineDto> itemScrutinyLineDtoList=itemScrutinyLineService.findDtos("getTechnicalDocuments",params);
		response.addObject("ItemList", itemScrutinyLineDtoList);
		return response;
		
	}
	
	@RequestMapping(value= {"/getCommercialDoc/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getCommercialDoc(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("bidderId", bidderId);
		List<ItemScrutinyLineDto> itemScrutinyLineDtoList=itemScrutinyLineService.findDtos("getCommercialDocuments",params);
		response.addObject("ItemList", itemScrutinyLineDtoList);
		return response;
		
	}
	
	@RequestMapping(value= {"/getTechnicalStatusByItemBidId/{itemBidId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTechnicalStatusByItemBidId(@PathVariable("itemBidId") Long itemBidId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("itemBidId", itemBidId);
		params.put("scrutinyType", AppBaseConstant.TECHNICAL_SCRUTINY);
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		List<ItemScrutinyLineDto> itemScrutinyLineDtoList=itemScrutinyLineService.findDtos("getStatusByItemBidId",params);
		response.addObject("ItemList", itemScrutinyLineDtoList);
		response.addObject("role", userRole);
		return response;
		
	}
	@RequestMapping(value= {"/getCommercialStatusBybidderId/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getCommercialStatusBybidderId(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("bidderId", bidderId);
		params.put("scrutinyType",AppBaseConstant.COMMERCIAL_SCRUTINY);
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		List<ItemScrutinyLineDto> itemScrutinyLineDtoList=itemScrutinyLineService.findDtos("getStatusByBidderId",params);
		response.addObject("ItemList", itemScrutinyLineDtoList);
		response.addObject("role", userRole);
		return response;
		
	}
	
	@RequestMapping(value= {"/getFinalCommercialStatusBybidderId/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getFinalCommercialStatusBybidderId(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("bidderId", bidderId);
		
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		
		List<ItemScrutinyLineDto> rejectedAuditingList=itemScrutinyLineService.findDtos("getRejectedAuditingListByBidderId", params);
		response.addObject("isAnyRejected", rejectedAuditingList==null?false:true);
		
		params.put("scrutinyType",AppBaseConstant.COMMERCIAL_SCRUTINY);
		List<ItemScrutinyLineDto> itemScrutinyLineDtoList=itemScrutinyLineService.findDtos("getFinalStatusByBidderId",params);
		response.addObject("ItemList", itemScrutinyLineDtoList);
		
		
		response.addObject("role", userRole);
		return response;
		
	}
	
	@RequestMapping(value= {"/getFinalTechnicalStatusByItemBidId/{itemBidId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getFinalTechnicalStatusByItemBidId(@PathVariable("itemBidId") Long itemBidId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("itemBidId", itemBidId);
		params.put("scrutinyType",AppBaseConstant.TECHNICAL_SCRUTINY);
		List<ItemScrutinyLineDto> rejectedAuditingList=itemScrutinyLineService.findDtos("getFinalRejectedListByItemBidId", params);
		response.addObject("isAnyRejected", rejectedAuditingList==null?false:true);
		
		
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		List<ItemScrutinyLineDto> itemScrutinyLineDtoList=itemScrutinyLineService.findDtos("getFinalStatusByItemBidId",params);
		response.addObject("ItemList", itemScrutinyLineDtoList);
		response.addObject("role", userRole);
		return response;
		
	}
	@RequestMapping(value= {"/getFinalCommercialStatusByItemBidId/{itemBidId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getFinalCommercialStatusByItemBidId(@PathVariable("itemBidId") Long itemBidId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("itemBidId", itemBidId);
		params.put("scrutinyType", AppBaseConstant.COMMERCIAL_SCRUTINY );
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		List<ItemScrutinyLineDto> itemScrutinyLineDtoList=itemScrutinyLineService.findDtos("getFinalStatusByItemBidId",params);
		response.addObject("ItemList", itemScrutinyLineDtoList);
		response.addObject("role", userRole);
		return response;
		
	}
	
	
}
