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
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.dto.TenderCommitteeParticipantDto;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TenderCommitteeParticipantService;
import com.novelerp.eat.service.TenderCommitteeService;

/**
 * 
 * @author varsha
 *
 */
@Controller
public class TenderCommitteeController {
	@Autowired
	private TenderCommitteeService tenderCommitteeService;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TenderCommitteeParticipantService tenderCommitteeParticipantService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@RequestMapping(value= {"/tenderCommittee"},method =RequestMethod.GET)
	public ModelAndView tenderCommittee(){
		ModelAndView view=new ModelAndView("tenderCommittee");
		Map<String, String> bidOpeningType = refListService.getReferenceListMap(CoreReferenceConstants.TENDER_COMMITTEE_TYPE);
		view.addObject("bidOpeningType", bidOpeningType);
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
	    return view;
	}
	
	@RequestMapping(value= {"/auctionCommittee"},method =RequestMethod.GET)
	public ModelAndView auctionCommittee(){
		ModelAndView view=new ModelAndView("tenderCommittee");
		Map<String, String> bidOpeningType = refListService.getReferenceListMap(CoreReferenceConstants.TENDER_COMMITTEE_TYPE);
		view.addObject("bidOpeningType", bidOpeningType);
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
	    return view;
	}
	
	@RequestMapping(value="/getTenderCommittees/{openingType}/{typeCode}",method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderCommittee(@PathVariable("openingType") String openingType,@PathVariable("typeCode") String typeCode){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params=new HashMap<>();
		params.put("openingType",openingType);
		if(typeCode.equals("TENDER")){
			params.put("typeCode1","WT");
			params.put("typeCode2","PT");
		}else{
			params.put("typeCode1","FA");
			params.put("typeCode2","RA");
		}
		List<TenderCommitteeDto> tenderCommittees=tenderCommitteeService.findDtos("getTenderCommitteesByOpeningType", params);
		List<TAHDRDto> tahdr=tahdrService.getTenderForCommitteeFormation(params);
		response.addObject("tenderCommittees",tenderCommittees);
		response.addObject("tahdr", tahdr);
		return response;
	}
	
	@RequestMapping(value= {"/fetchTenderCommittee"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderList(@RequestParam("pageNumber")int pageNumber, 
														@RequestParam("pageSize") int pageSize,
														@RequestParam("searchMode") String searchColumn, 
														@RequestParam("serachValue") String searchValue,@RequestParam("openingType") String openingType,
														@RequestParam("typeCode") String typeCode){
		CustomResponseDto response=new CustomResponseDto();
		 Map<String, Object> newObjectMap= new HashMap<>();
		 Map<String, Object> objectMap = new HashMap<>();
		 long countResult = 0l;
		 Map<String, Object> params=new HashMap<>();
			params.put("openingType",openingType);
			List<TenderCommitteeDto> tenderCommittees=null;
			if(typeCode.equals("TENDER")){
				params.put("typeCode1","WT");
				params.put("typeCode2","PT");
				tenderCommittees=tenderCommitteeService.getCommittee("getTenderCommitteesByOpeningType", params, pageNumber, pageSize, searchColumn, searchValue);
			}else{
				params.put("typeCode1","FA");
				params.put("typeCode2","PT");
				tenderCommittees=tenderCommitteeService.getCommittee("getAuctionCommitteesByOpeningType", params, pageNumber, pageSize, searchColumn, searchValue);
			}
			 countResult=tenderCommitteeService.getCommitteeCount(openingType, typeCode, params,searchColumn,searchValue);
			 newObjectMap.put("tenderCommittees",tenderCommittees);
			if(searchColumn.equals("none") && searchValue.equals("none")){
				List<TAHDRDto> tahdr=tahdrService.getTenderForCommitteeFormation(params);
				 newObjectMap.put("tahdr", tahdr);
			}
			 int LastPage=(int) (countResult / pageSize);
			 LastPage = (int) ((LastPage==1 ||LastPage==0)?1:LastPage + 1);
		 objectMap.put("LastPage", LastPage);
		 response.setData( newObjectMap);
		 response.setObjectMap(objectMap);
		return response;
		
	}
	
	@RequestMapping(value="/saveTenderCommittee",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody TenderCommitteeDto saveTenderCommittee(@ModelAttribute("tenderCommittee")TenderCommitteeDto dto){
		TenderCommitteeDto tenderCommittee=new TenderCommitteeDto();
		if(dto.getTahdr().getTahdrId()!=null && dto.getTenderVersion().getTahdrDetailId()!=null && dto.getBidOpeningType().trim()!=""){
			Map<String,Object> params=AbstractServiceImpl.getParamMap("tahdrId", dto.getTahdr().getTahdrId());
			params.put("tahdrDetailId",dto.getTenderVersion().getTahdrDetailId());
			params.put("bidOpeningType",dto.getBidOpeningType());
			tenderCommittee=tenderCommitteeService.findDto("getQueryForCommitteeAlreadyExist", params);
			tenderCommittee=tenderCommitteeService.saveTenderCommittee(tenderCommittee,dto);
			
		}else{
			tenderCommittee.setResponse(new ResponseDto(true, "Something went wrong..!"));
		}
		return tenderCommittee;
	}
	
	@RequestMapping(value="/deleteTenderCommittee/{tenderCommitteeId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto deleteTenderCommittee(@PathVariable("tenderCommitteeId")Long tenderCommitteeId){
		try{
			boolean deleted = tenderCommitteeService.deleteById(tenderCommitteeId);
			if (deleted) {
				return new ResponseDto(false, "Committee Deleted");
			}
			return new ResponseDto(true, "Committee Deleting Failed");
		}catch(Exception ex){
			return new ResponseDto(true, "Before Deleting Committee,Delete its Participants ! ");
		}
		
	}
	@RequestMapping(value="/getTenderCommitteeByTahdrId/{tahdrId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getTenderCommitteeByTahdrId(@PathVariable("tahdrId")Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String,Object> params=AbstractServiceImpl.getParamMap("tahdrId", tahdrId);
		TenderCommitteeDto tenderCommittee=tenderCommitteeService.findDto("getTenderCommitteeByTahdrId",params);
		response.addObject("tenderCommittee", tenderCommittee);
		return response;
	}
	
	@RequestMapping(value="/generateSessionKey/{tenderCommitteeId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto generateSessionKey(@PathVariable("tenderCommitteeId")Long tenderCommitteeId){
		CustomResponseDto response=new CustomResponseDto();
		if(tenderCommitteeId!=null){
			Map<String,Object> params=AbstractServiceImpl.getParamMap("tenderCommitteId", tenderCommitteeId);
			TenderCommitteeDto tenderCommittee=tenderCommitteeService.findDto("getTenderCommitteeById",params);
			List<TenderCommitteeParticipantDto> participantList=tenderCommitteeParticipantService.findDtos("getQueryForCommitteeParticipant", params);
			if(!CommonUtil.isCollectionEmpty(participantList) && tenderCommittee!=null){
				boolean status=tenderCommitteeService.generateSessionKey(tenderCommitteeId,tenderCommittee,participantList);
				response.addObject("resultStatus", status);
				if(status){
					response.addObject("resultMessage", "Session has been created,Please Check Email !");
				}else{
					response.addObject("resultMessage", "Session key not generated.");
				}
			}else{
				response.addObject("resultStatus", false);
				response.addObject("resultMessage", "Please add at least one participant");
			}
		}else{
			response.addObject("resultStatus", false);
			response.addObject("resultMessage", "Something Went Wrong");
		}
		return response;
	}
	
	@RequestMapping(value="/getUserForCommittee/{tenderId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getUserForCommittee(@PathVariable("tenderId")Long tenderId){
		Map<String, Object> params=AbstractServiceImpl.getParamMap("roleValue", ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER);
		params.put("tenderId", tenderId);
		List<UserDto> users=userService.findDtos("getQueryForInternalUser", params);
		return new CustomResponseDto("users", users);
		
	}

}
