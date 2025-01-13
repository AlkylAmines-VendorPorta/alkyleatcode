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

import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.core.dto.ResponseDto;

@Controller
public class ReferenceListController {
	
	

	@Autowired
	private ReferenceListService referenceListService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	/*@RequestMapping(value = "/referenceList", method = RequestMethod.GET)
	public ModelAndView reference() {
		ModelAndView modelAndView = new ModelAndView("reference");
		return modelAndView;
	}
*/
	
	/*@RequestMapping(value="/getReferenceList/{referenceCode}", method=RequestMethod.POST)
	public List<ReferenceListDto> getReferenceList(@PathVariable("referenceCode")String reference){
		return referenceListService.getReferenceList(reference);
	}*/
	
	@RequestMapping(value = "/getReferenceListTab/{referenceId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<ReferenceListDto> getReferenceList(@PathVariable("referenceId") Long referenceId) {
		//Set<RoleDto> role=contextService.getRoles();
		
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params=new HashMap<>();
		/*param.put("BPartnerId", bPartnerDto.getbPartnerId());*/
		params.put("referenceId", referenceId);
		/*Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());*/
		List<ReferenceListDto> referenceList = referenceListService.findDtos("getReferenceListByBPartner", params);
		
		return referenceList;
	}
	
	@RequestMapping(value = "/getReferenceListById/{referenceListId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ReferenceListDto getReferenceListById(@PathVariable("referenceListId") Long referenceListId) {
		
		ReferenceListDto reference = referenceListService.findDto(referenceListId);
		return reference;
	}
	
	
	@RequestMapping(value = "/addNewReferenceList", method=RequestMethod.POST)
	public @ResponseBody ReferenceListDto  addNewReferenceList(@ModelAttribute("ReferenceList") ReferenceListDto referenceListDto){
		
		BPartnerDto bPartnerDto =contextService.getPartner();
		referenceListDto.setPartner(bPartnerDto);
		referenceListDto.setIsActive("Y");
		ReferenceListDto referenceList=new ReferenceListDto();
		if(referenceListDto.getReferenceListId()==null){
			referenceList=referenceListService.save(referenceListDto);
		}
		else{
			referenceList=referenceListService.updateDto(referenceListDto);
		}
		return referenceList;
		
	}
	
	@RequestMapping(value = "/deleteReferenceList/{referenceListId}", method=RequestMethod.POST)
	public @ResponseBody ResponseDto deleteReference(@PathVariable("referenceListId") Long referenceListId){
	
		
		 ResponseDto response=null;
	       boolean isDeleted=false;
	       if(referenceListId>0){
	    	   isDeleted= referenceListService.deleteReferenceList(referenceListId);
	       }
	       if(isDeleted){
	    	   response= new ResponseDto(false, "Reference List Deleted...!");
	       }
	       else{
	    	   response=new ResponseDto(true,"Cannot Delete Reference List...!");
	       }
			return response;
	    
	
		
	}
	
	
}