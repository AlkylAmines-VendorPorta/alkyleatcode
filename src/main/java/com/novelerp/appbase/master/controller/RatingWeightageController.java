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

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.RatingWeightageDto;
import com.novelerp.eat.service.RatingWeightageService;

@Controller
public class RatingWeightageController {
	
	@Autowired
	private RatingWeightageService ratingWeightService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = {"/ratingWeightage"}, method = RequestMethod.GET)
	public ModelAndView role() {
		System.out.println("..RoleController-role()");
		return new ModelAndView("ratingWeightage");
	}

	@RequestMapping(value = "/getRatingWeightageList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<RatingWeightageDto> getRatingWeightageList() {
		//Set<RoleDto> role=contextService.getRoles();
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<RatingWeightageDto> ratingList = ratingWeightService.findDtos("getRatingByBPartner", params);
		
		return ratingList;
	}
	
	@RequestMapping(value = "/getRatingWeightageById/{ratingWeightId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody RatingWeightageDto getRatingWeightageListById(@PathVariable("ratingWeightId") Long ratingWeightageId) {
		
		RatingWeightageDto  ratingWeightageDto = ratingWeightService.findDto(ratingWeightageId);
		return ratingWeightageDto;
	}
	
	@RequestMapping(value = "/addRatingWeightage", method=RequestMethod.POST , produces="application/json")
	public @ResponseBody RatingWeightageDto  addRatingWeightage(@ModelAttribute("ratingWeightage") RatingWeightageDto ratingDto){
		RatingWeightageDto ratingWeightage=new RatingWeightageDto();
		BPartnerDto bPartnerDto =contextService.getPartner();
		ratingDto.setPartner(bPartnerDto);
		ratingDto.setIsActive("Y");
		if(ratingDto.getRatingWeightageId()==null){
			ratingWeightage=ratingWeightService.save(ratingDto);
		}
		else{
			ratingWeightage=ratingWeightService.updateDto(ratingDto);
		}
		return ratingWeightage;
	}
	
	
	@RequestMapping(value = "/deleteRatingweightage/{Id}", method=RequestMethod.POST , produces = "application/json")
	public @ResponseBody ResponseDto  deleteRatingweightage(@PathVariable("Id") Long id){
		ResponseDto response=null;
		boolean isDeleted=false;
		if(id>0){
			isDeleted=ratingWeightService.deleteRatWeight(id);
		}
		
		if(isDeleted){
			response= new ResponseDto(false,"Rating Weightage Deleted..!");
		}else{
			response= new ResponseDto(true,"Could Not Delete Rating Weightage..!");
		}
		
		return response;
	}

}