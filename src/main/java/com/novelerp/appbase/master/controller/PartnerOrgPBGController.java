package com.novelerp.appbase.master.controller;

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

import com.novelerp.appbase.master.converter.PartnerOrgConverterPlain;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PartnerOrgPBGDto;
import com.novelerp.appbase.master.service.PartnerOrgPBGService;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
/**
 * @author Aman /Vivek Birdi
 *
 */
@Controller
public class PartnerOrgPBGController {
	
	@Autowired
	private PartnerOrgPBGService orgPBGService;
	
	@Autowired
	private PartnerOrgService partnerOrgService;
	
	@Autowired
	private PartnerOrgConverterPlain partnerOrgConverterPlain;
	
	/*@RequestMapping(value = "/getOrgPBG/{partnerId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerOrgPBG(@PathVariable("partnerId") Long orgId){
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("partnerId", orgId);
		List<PartnerOrgPBGDto> pbgDtos =  orgPBGService.findDtos("getPBGQuery", params, orgPBGConverter);
		return new CustomResponseDto("partnerOrgPbgs", pbgDtos);
	}*/
	@RequestMapping(value = "/getOrgPBG/{partnerId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerOrgPBG(@PathVariable("partnerId") Long orgId){
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("partnerId", orgId);
		List<PartnerOrgDto> orgs = partnerOrgService.findAll(partnerOrgConverterPlain);
		List<PartnerOrgPBGDto> pbgDtos =  orgPBGService.findDtos("getPBGQuery", params);
		CustomResponseDto response =  new CustomResponseDto("partnerOrgPbgs",pbgDtos);
		response.addObject("orgs", orgs);
		return response;
	}
	/**
	 * Save/ upadte partner org's permanent bank guarantee
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/saveOrgPBG", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody PartnerOrgPBGDto save(@ModelAttribute("partnerOrgPBG") PartnerOrgPBGDto dto){
		if(dto.getPartnerOrgPbgId() == null){
			return orgPBGService.save(dto);
		}
		return orgPBGService.updateDto(dto);
	}
	
	@RequestMapping(value="/deleteOrgPBG/{partnerOrgPBGId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("partnerOrgPBGId") Long partnerOrgPBGId){
		boolean deleted = orgPBGService.deleteById(partnerOrgPBGId);
		if(deleted){
			return new ResponseDto(false,"Record deleted.");
		}
		return new ResponseDto(true,"Problem in deleting record.");
	}

}
