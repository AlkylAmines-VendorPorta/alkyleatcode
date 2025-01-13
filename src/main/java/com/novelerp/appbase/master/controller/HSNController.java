package com.novelerp.appbase.master.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.HSNDto;
import com.novelerp.appbase.master.service.HSNService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;

@Controller
public class HSNController {
	
	@Autowired
	private HSNService hsnservice;
	
	@Autowired
	
	@Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = {"/hsn"}, method = RequestMethod.GET)
	public ModelAndView role() {
		System.out.println("..RoleController-role()");
		return new ModelAndView("hsn");
	}

	@RequestMapping(value = "/getHSNList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<HSNDto> getHSNList() {
		//Set<RoleDto> role=contextService.getRoles();
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<HSNDto> hsnList = hsnservice.findDtos("getHSnByBPartner", params);
		
		return hsnList;
	}
	
	@RequestMapping(value = "/getHSneById/{hsnId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody HSNDto gethsnListById(@PathVariable("hsnId") Long hsnId) {
		
		HSNDto hsndto  = hsnservice.findDto(hsnId);
		return hsndto;
	}
	
	@RequestMapping(value = "/addNewHSN", method=RequestMethod.POST , produces="application/json")
	public @ResponseBody HSNDto  addNewHSN(@ModelAttribute("HSN") HSNDto hsnDto){
		HSNDto hsn=new HSNDto();
		BPartnerDto bPartnerDto =contextService.getPartner();
		hsnDto.setPartner(bPartnerDto);
		hsnDto.setIsActive("Y");
		if(hsnDto.getHsnId()==null){
			hsn=hsnservice.save(hsnDto);
		}
		else{
			hsn=hsnservice.updateDto(hsnDto);
		}
		return hsn;
	}
	
	/*@RequestMapping(value = "/editHSN", method=RequestMethod.POST)
	public @ResponseBody HSNDto  editHSN(@ModelAttribute("HSN") HSNDto hsnDto){
		HSNDto dto =hsnservice.findDto(hsnDto.getHsnId());
		BPartnerDto bPartnerDto =contextService.getPartner();
		dto.setPartner(bPartnerDto);
		dto.setName(hsnDto.getName());
		dto.setCode(hsnDto.getCode());
		dto.setDescription(hsnDto.getDescription());
		dto.setIsActive("Y");
		HSNDto resdto = hsnservice.updateDto(dto);
		
		return resdto;
		
		
	}*/
	/*@RequestMapping(value = "/deleteHSN/{Id}", method=RequestMethod.POST , produces = "application/json")
	public @ResponseBody HSNDto  deleteHSN(@PathVariable("Id") Long id){
		HSNDto dto =hsnservice.findDto(hsnDto.getHsnId());
		dto.setIsActive("N");
		HSNDto resdto = hsnservice.deleteHSN(id);
		
		return resdto;
	}*/
	@RequestMapping(value = "/deleteHSN/{Id}", method=RequestMethod.POST , produces = "application/json")
	public @ResponseBody ResponseDto  deleteHSN(@PathVariable("Id") Long id){
		ResponseDto response=null;
		try{
		boolean isDeleted=false;
		if(id>0){
			isDeleted=hsnservice.deleteHSN(id);
		}
		
		if(isDeleted){
			response= new ResponseDto(false,"HSN Deleted..!");
		}else{
			response= new ResponseDto(true,"Could Not Delete HSN..!");
		}
		}
		catch(Exception e){
			response= new ResponseDto(true,"HSN is Already in use...!");
		}
		return response;
	}

}