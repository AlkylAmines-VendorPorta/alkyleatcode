/**
 * @author Ankush
 */
package com.novelerp.appcontext.controller;

import java.util.HashMap;
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

import com.novelerp.appbase.master.dto.ReferenceDto;
import com.novelerp.appbase.master.service.ReferenceService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;

@Controller
public class ReferenceController {
	
	

	@Autowired
	private ReferenceService referenceService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = "/reference", method = RequestMethod.GET)
	public ModelAndView reference() {
		ModelAndView modelAndView = new ModelAndView("reference");
		return modelAndView;
	}

	
	/*@RequestMapping(value="/getReferenceList/{referenceCode}", method=RequestMethod.POST)
	public List<ReferenceListDto> getReferenceList(@PathVariable("referenceCode")String reference){
		return referenceListService.getReferenceList(reference);
	}*/
	
	@RequestMapping(value = "/getReferenceList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<ReferenceDto> getReferenceList() {
		//Set<RoleDto> role=contextService.getRoles();
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<ReferenceDto> referenceList = referenceService.findDtos("getReferenceByBPartner", params);
		
		return referenceList;
	}
	
	@RequestMapping(value = "/getReferenceById/{referenceId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ReferenceDto getReferenceListById(@PathVariable("referenceId") Long referenceId) {
		
		ReferenceDto reference = referenceService.findDto(referenceId);
		return reference;
	}
	
	@RequestMapping(value = "/addNewReference", method=RequestMethod.POST)
	public @ResponseBody ReferenceDto addNewReference(@ModelAttribute("Reference") ReferenceDto referenceDto){
		
		
		BPartnerDto bPartnerDto =contextService.getPartner();
		referenceDto.setPartner(bPartnerDto);
		referenceDto.setIsActive("Y");
		ReferenceDto reference=new ReferenceDto();
		if(referenceDto.getReferenceId()==null){
			reference=referenceService.save(referenceDto);
		}
		else{
			reference=referenceService.updateDto(referenceDto);
		}
		return reference;
		
		
	}
	
	/*@RequestMapping(value = "/editReference", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto  editReference(@ModelAttribute("Reference") ReferenceDto reference){
		CustomResponseDto response =new CustomResponseDto();
		BPartnerDto bPartnerDto =contextService.getPartner();
		reference.setPartner(bPartnerDto);
		reference.setIsActive("Y");
		ReferenceDto referencedto = referenceService.updateDto(reference);
		response.addObject("reference", referencedto);
		response.addObject("result", true);
		response.addObject("resultMessage","Reference Added Successfully");
		return response;
		
		
	}*/
	@RequestMapping(value = "/deleteReference/{referenceId}", method=RequestMethod.POST)
	public @ResponseBody ResponseDto deleteReference(@PathVariable("referenceId") Long referenceId){
		 ResponseDto response=null;
		try{
	       boolean isDeleted=false;
	       if(referenceId!=null){
	    	   isDeleted= referenceService.deleteReference(referenceId);
	       }
	       if(isDeleted){
	    	   response= new ResponseDto(false, "Reference Deleted...!");
	       }
	       else{
	    	   response=new ResponseDto(true,"Cannot Delete Reference...!");
	       }
		}
	
		catch(Exception e){
			
			response=new ResponseDto(true,"Reference is Already in Use...!");
		}
		return response;
	
		
	}
	@RequestMapping(value = "/getReferenceList/{index}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Map<String,Object> getReferenceList(@PathVariable("index") Long id) {
		
		Map<String,Object> map=new HashMap<String,Object>();
		List<ReferenceDto> referenceList = referenceService.getReference(id);
		map.put("DATA", referenceList);
		return map;
	}
	
	
}