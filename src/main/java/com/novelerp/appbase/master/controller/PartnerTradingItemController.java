package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.MaterialSearchDto;
import com.novelerp.appbase.master.dto.PartnerTradingItemDto;
import com.novelerp.appbase.master.service.MaterialService;
import com.novelerp.appbase.master.service.PartnerItemManufacturerService;
import com.novelerp.appbase.master.service.PartnerTradingItemService;
import com.novelerp.appbase.validator.PartnerTradingItemValidator;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;

/**
 * 
 * @author Aman Sahu
 *
 */
@Controller
public class PartnerTradingItemController {
	
	
	@Autowired
	private PartnerTradingItemService partnerTradingItemService;
	@Autowired
	private PartnerTradingItemValidator partnerTradingItemValidator;
	@Autowired
	private PartnerItemManufacturerService partnerItemManufacturerService;
	@Autowired
	private MaterialService materialService;
	@RequestMapping(value = "/getPartnerTradingItem/{partnerItemManufacturerId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getPartnerTradingItem(@PathVariable ("partnerItemManufacturerId") Long partnerItemManufacturerId){
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("partnerItemManufacturerId", partnerItemManufacturerId);
		List<PartnerTradingItemDto> partnerTradingItemDtoList = partnerTradingItemService.findDtos("getPartnerItemManufacturerQuery", params);
 		CustomResponseDto response  = new CustomResponseDto("partnerTradingItem", partnerTradingItemDtoList);
		return response;
		
	}
	
	@RequestMapping(value="/savePartnerTradingItem", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PartnerTradingItemDto savePartnerTradingItem(@ModelAttribute ("partnerTradingItem") PartnerTradingItemDto dto){
		
		  Errors errors=new Errors();
	      partnerTradingItemValidator.validate(dto, errors);
		  {
			if(errors.getErrorCount()>0){
				ResponseDto response=new ResponseDto();
				response.setHasError(true);
				response.setErrors(errors.getErrorList());
				dto.setResponse(response);
				return dto;
			}
		   }
		
		if(dto.getPartnerTradingItemId() == null){
			return partnerTradingItemService.save(dto);
		}
		return  partnerTradingItemService.updateDto(dto);
		
	}
	
	@RequestMapping( value ="/deletePartnerTradingItem/{partnerTradingItemId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody ResponseDto delete (@PathVariable("partnerTradingItemId") Long partnerTradingItemId){
		boolean deleted = partnerTradingItemService.deleteById(partnerTradingItemId);
		if(deleted){
			return new ResponseDto(false, "Record Deleted");
		}
		return new ResponseDto(true, "Problem in deleting record;");
	}
	@RequestMapping(value ="/getTradingItem", method=RequestMethod.POST,produces = "application/json")	
	public @ResponseBody CustomResponseDto getTradingItem(@ModelAttribute("material") MaterialSearchDto dto){
		List<MaterialDto> tradingItems=materialService.getTradingMaterial(dto);
		return new CustomResponseDto("tradingItems", tradingItems);
	}
	@RequestMapping(value ="/updateItemsApproval", method=RequestMethod.POST,produces = "application/json")	
	public @ResponseBody ResponseDto updateItemApproval(@ModelAttribute("tradingItem") PartnerTradingItemDto dto){
		
		return partnerItemManufacturerService.updateItemsApproval(dto);
	}
	
}
