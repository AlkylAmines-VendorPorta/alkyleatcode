package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appcontext.dto.OfficeLocationDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.converter.TAHDRDetailConverter;
import com.novelerp.eat.dto.TAHDRApprovalMatrixDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.TAHDRApprovalMatrixService;
import com.novelerp.eat.service.TAHDRService;

@Controller
public class TAHDRApprovalMatrixController {

	private Logger Log =  LoggerFactory.getLogger(PaymentApprovalController.class);
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contexService;
	
	@Autowired
	private TAHDRDetailConverter tahdrDetailConverter;
	
	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private TAHDRApprovalMatrixService tahdrApprovalMatrixService;
	@RequestMapping(value= "/getApprovalMatrixData/{tahdrId}", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto getApprovalMatrixData(@PathVariable("tahdrId")Long tahdrId){
		TAHDRDto tahdr=tahdrService.findDto("getQueryForTAHDRById",AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId),tahdrDetailConverter);
		List<UserDto> userData = null;
		UserDto user=contexService.getUser();
		UserDetailsDto userDetail= contexService.getUserDetails();
		Map<String,Object> listParam=new HashMap<String,Object>();
		userDetail=userDetailService.findDto("getOfficeLocationFromUserDetailsID", AbstractContextServiceImpl.getParamMap("userDetailsId", userDetail.getUserDetailsId()));
		OfficeLocationDto officeLocation=userDetail.getOfficeLocation();
		listParam.put("locationType",officeLocation.getLocationTypeRef());
		listParam.put("userId", user.getUserId());
		/*if(tahdr.getOfficeType().equals(locationType)){*/
			userData= userService.findDtos("getUserListfromLocation",listParam);
		/*}*/
		CustomResponseDto response=new CustomResponseDto();
		response.addObject("userData", userData);
		return response;
	}

	/*@RequestMapping(value="/deleteApprovalMatrix/{tahdrApprovalMatrixId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("tahdrApprovalMatrixId") Long tahdrApprovalMatrixId){
		boolean deleted =  tahdrApprovalMatrixService.deleteById(tahdrApprovalMatrixId);
		if(deleted){
			return new ResponseDto(false, "Record deleted");
		}
		return new ResponseDto(true, "Problem in deleting record");
	}*/
	
	@RequestMapping(value="/deleteApprovalMatrix/{tahdrApprovalMatrixId}/{tahdrId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto delete(@PathVariable("tahdrApprovalMatrixId") Long tahdrApprovalMatrixId,@PathVariable("tahdrId") Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		int tahdrData=0;
		TAHDRDto tahdr=null;
		TAHDRApprovalMatrixDto approvalDto= tahdrApprovalMatrixService.findDto(tahdrApprovalMatrixId);
		tahdrData=tahdrApprovalMatrixService.updateLevelOnDelete(tahdrId,approvalDto.getLevels());
		boolean deleted =  tahdrApprovalMatrixService.deleteById(tahdrApprovalMatrixId);
		if(deleted){
			response.setMessage("Record deleted");
		}
		else{
			response.setMessage("Problem in deleting record");
			return response;
		}
		
		if(tahdrData!=0){
			Map<String,Object> listParam=new HashMap<String,Object>();
			TAHDRApprovalMatrixDto tahdrApprovalMatrix=null;
			UserDto userDto=contexService.getUser();
			List<TAHDRApprovalMatrixDto> dto=tahdrApprovalMatrixService.findDtos("getTahdrApprovalMatrixDetails", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
			tahdr=tahdrService.findDto("getQueryForTAHDRById",AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
			listParam.put("userId", userDto.getUserId());
			listParam.put("tahdrId", tahdrId);
			tahdrApprovalMatrix=tahdrApprovalMatrixService.findDto("getApprovalMatrixDataFromTahdrId",listParam);
			response.addObject("approvalData", dto);
			response.addObject("tahdr", tahdr);
			response.addObject("tahdrApprovalMatrix", tahdrApprovalMatrix);
		}
		
		return response;
	}
	
	@RequestMapping(value= "/saveApprovalMatrix", method=RequestMethod.POST)
	public @ResponseBody TAHDRApprovalMatrixDto saveApprovalMatrix(@ModelAttribute("approvalMatrix") TAHDRApprovalMatrixDto tahdrApprovalMatrix){
		ResponseDto response=null;
		Map<String,Object> listParam=new HashMap<String,Object>();
		listParam.put("userId", tahdrApprovalMatrix.getUser().getUserId());
		listParam.put("tahdrId", tahdrApprovalMatrix.getTahdr().getTahdrId());
		TAHDRApprovalMatrixDto dto=tahdrApprovalMatrixService.findDto("getQueryForUserByUserId", listParam);
		Log.info("userId--tahdr approval matrix " + tahdrApprovalMatrix.getUser().getUserId());
		Log.info("tahdrId--tahdr approval matrix " + tahdrApprovalMatrix.getTahdr().getTahdrId());
		if(null!=dto && (CommonUtil.isEqual(dto.getUser().getUserId(), tahdrApprovalMatrix.getUser().getUserId()))){
			response= new ResponseDto(true, "Approval is already added..!");
			tahdrApprovalMatrix.setResponse(response);
		}else if(null!=tahdrApprovalMatrix.getTahdrApprovalMatrixId()){
			Log.info("tahdrApprovalMatrixId--update--tahdr approval matrix " + tahdrApprovalMatrix.getTahdrApprovalMatrixId());
			tahdrApprovalMatrix=tahdrApprovalMatrixService.updateDto(tahdrApprovalMatrix);
		}else if(null==tahdrApprovalMatrix.getTahdrApprovalMatrixId()){
			Log.info("tahdrApprovalMatrixId--save--tahdr approval matrix " + tahdrApprovalMatrix.getTahdrApprovalMatrixId());
				tahdrApprovalMatrix=tahdrApprovalMatrixService.save(tahdrApprovalMatrix);
		}
		return tahdrApprovalMatrix;
	}
		/*if(null!=tahdrApprovalMatrix.getTahdrApprovalMatrixId()){
			tahdrApprovalMatrix=tahdrApprovalMatrixService.updateDto(tahdrApprovalMatrix);
			}else {
				tahdrApprovalMatrix=tahdrApprovalMatrixService.save(tahdrApprovalMatrix);
		}
		return tahdrApprovalMatrix;
	}*/

		@RequestMapping(value= "/approvalAddedforTahdr/{tahdrId}", method=RequestMethod.POST)
		@ResponseBody
		public CustomResponseDto approvalAddedforTahdr(@PathVariable("tahdrId")Long tahdrId){
			CustomResponseDto response=new CustomResponseDto();
		Map<String,Object> listParam=new HashMap<String,Object>();
		listParam.put("tahdrId",tahdrId);
		List<TAHDRApprovalMatrixDto> dto=tahdrApprovalMatrixService.findDtos("getTahdrApprovalMatrixDetails", listParam);
		
		if(CommonUtil.isCollectionEmpty(dto)){
			response.addObject("result", false);
			response.addObject("resultMessage", "Add Atleast One Approval.");
		}else{
			response.addObject("result", true);
		}
		return response;
		}
}
