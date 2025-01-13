package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;

@Controller
public class CMSController {
	
	@Autowired
	private AttachmentService attachmentService;
	
	@RequestMapping(value = {"/cms"}, method = RequestMethod.GET)
	public ModelAndView materialGroupView() {
		return new ModelAndView("cms");
	}
	
	@RequestMapping(value = "/getAllAttachmentst/{pageNumber}/{pageSize}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto getAllAttachmentst(@PathVariable("pageNumber")int pageNumber, @PathVariable("pageSize") int pageSize) {
		CustomResponseDto customResponseDto = new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		Map<String, Object> objectMap = new HashMap<>();
		int LastPage=0;
		List<AttachmentDto> attachmentList = null;
		try{
		
		 attachmentList =attachmentService.getAttachmentList(params, pageNumber, pageSize);
		 long countResult = attachmentService.getRecordCount();
			LastPage = (int) ((countResult / pageSize) + 1);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		objectMap.put("LastPage", LastPage);
		customResponseDto.setData(attachmentList);
		customResponseDto.setObjectMap(objectMap);
		return customResponseDto;
	}
	@RequestMapping(value = "/getAllAttachmentdateWise/{startdate}/{enddate}/{pageNumber}/{pageSize}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto getAllAttachmentdateWise(@PathVariable("startdate")String startdate,@PathVariable("enddate")String enddate,@PathVariable("pageNumber")int pageNumber, @PathVariable("pageSize") int pageSize) {
		
		CustomResponseDto customResponseDto = new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		params.put("StartDate", startdate);
		params.put("EndADte", enddate);
		Map<String, Object> objectMap = new HashMap<>();
		int LastPage=0;
		List<AttachmentDto> attachmentList = null;
		try{
		
		 attachmentList =attachmentService.getAttachmentListDateWise(params, pageNumber, pageSize);
		 long countResult = attachmentService.getRecordCount();
			LastPage = (int) ((countResult / pageSize) + 1);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		objectMap.put("LastPage", LastPage);
		customResponseDto.setData(attachmentList);
		customResponseDto.setObjectMap(objectMap);
		return customResponseDto;
	}
	
	@RequestMapping(value = "/getAttachmentById/{Id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody AttachmentDto getAttachmentById(@PathVariable("Id") Long id) {
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("AttachmentId", id);
		AttachmentDto attachmentdto=attachmentService.findDto("getAttachmentByIdQuery", params);
		return attachmentdto;
	}

}
