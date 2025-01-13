package com.novelerp.eat.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.DesignationService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dto.TenderCommitteeParticipantDto;
import com.novelerp.eat.service.TenderCommitteeParticipantService;

/**
 * 
 * @author varsha
 *
 */
@Controller
public class TenderCommitteeParticipantController {
	@Autowired
	private DesignationService designationService;
	@Autowired
	UserService userService;
	@Autowired
	private TenderCommitteeParticipantService tenderCommitteeParticipantService;
	@RequestMapping(value="/getTenderCommmitteeParticipant/{tenderCommitteId}/{tenderId}",method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderCommitteeParticipant(@PathVariable("tenderCommitteId") Long tenderCommitteId,@PathVariable("tenderId") Long tenderId)
	{
		Map<String,Object> params=AbstractServiceImpl.getParamMap("tenderCommitteId", tenderCommitteId);
		List<TenderCommitteeParticipantDto> commiteeParticipants=tenderCommitteeParticipantService.findDtos("getQueryForCommitteeParticipant", params);
		Map<String ,Object> param=AbstractServiceImpl.getParamMap("tenderId", tenderId);
	    List<UserDto> user=userService.findDtos("getQueryForParticipantUser", param);
	    Map<String, Object> p= new HashMap<>();
	    List<DesignationDto> designations=designationService.findDtos("getQueryForDesignation", p);
		CustomResponseDto response=new CustomResponseDto("commiteeParticipants",commiteeParticipants);
		response.addObject("designations", designations);
		response.addObject("user", user);
		return response;
	}
	
	@RequestMapping(value="/saveCommitteeParticipant",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody CustomResponseDto saveCommitteeParticipant(@ModelAttribute("committeParticipant")TenderCommitteeParticipantDto dto)
	{
		CustomResponseDto response=new CustomResponseDto();
		TenderCommitteeParticipantDto participantDto=new TenderCommitteeParticipantDto();
		if(dto.getCommitteeParticipantId()==null){
			 Map<String, Object> params= new HashMap<>();
			 params.put("userId", dto.getUser().getUserId());
			 params.put("committeeId", dto.getTenderCommittee().getTenderCommitteeId());
			List<TenderCommitteeParticipantDto> isAleadryPresent=tenderCommitteeParticipantService.findDtos("getCommitteeParticipantByCommitteeIdAndParticipantId", params);
			if(CommonUtil.isCollectionEmpty(isAleadryPresent)){
				participantDto=tenderCommitteeParticipantService.save(dto);
				response.addObject("result", true);
				response.addObject("participantDto", participantDto);
				response.addObject("message", "Participant Added Successfully");
			}else{
				response.addObject("result", false);
				response.addObject("participantDto", participantDto);
				response.addObject("message", "Participant Already Added");
			}
			
		}else{
			participantDto=tenderCommitteeParticipantService.updateDto(dto);
			response.addObject("result", true);
			response.addObject("participantDto", participantDto);
			response.addObject("message", "Participant Updated Successfully");
		}
		return response;
	}
	@RequestMapping(value="/deleteCommitteeParticipant",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto deleteTenderCommittee(@RequestParam("committeeParticipantId")Long committeeParticipantId){
		if(!committeeParticipantId.equals("")){
			boolean deleted = tenderCommitteeParticipantService.deleteById(committeeParticipantId);
			if (deleted) {
				return new ResponseDto(false, "Record Deleted");
			}
		}
		return new ResponseDto(true, "Problem in deleting record");
	}

}
