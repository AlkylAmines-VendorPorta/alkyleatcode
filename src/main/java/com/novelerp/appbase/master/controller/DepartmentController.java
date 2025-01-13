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

import com.novelerp.appbase.master.dto.DepartmentDto;
import com.novelerp.appbase.master.service.DepartmentService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;
/**
 * 
 * @author Vivek Birdi
 *
 */
@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	
	@Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = "/departmentView", method = RequestMethod.GET)
	public ModelAndView departmentView() {
		ModelAndView modelAndView = new ModelAndView("department");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getDepartmentList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<DepartmentDto> getDepartmentList() {
		
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<DepartmentDto> departmentList = departmentService.findDtos("getDepartmentListByPartner", params);
		return departmentList;
	}
	
	@RequestMapping(value = "/getDepartment/{departmentId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody DepartmentDto getDepartment(@PathVariable("departmentId") Long id) {
		
		DepartmentDto department=departmentService.findDto(id);
		return department;
	}
	@RequestMapping(value = "/saveDepartment", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody DepartmentDto  saveDepartment(@ModelAttribute("Department") DepartmentDto departmentDto){
		BPartnerDto bPartnerDto =contextService.getPartner();
		departmentDto.setPartner(bPartnerDto);
		departmentDto.setIsActive("Y");
		DepartmentDto department=new DepartmentDto();
		if(departmentDto.getDepartmentId()==null){
			department=departmentService.save(departmentDto);
		}
		else{
			department=departmentService.updateDto(departmentDto);
		}
		return department;
	}
	
	@RequestMapping(value="/deleteDepartment/{Id}" , method=RequestMethod.POST ,produces="application/json")
     public @ResponseBody ResponseDto deleteDepartment(@PathVariable("Id") Long id){
	       ResponseDto response=null;
	       try{
	       boolean isDeleted=false;
	       if(id>0){
	    	   isDeleted=departmentService.deleteDepartment(id);
	       }
	       if(isDeleted){
	    	   response= new ResponseDto(false, "Department Deleted...!");
	       }
	       else{
	    	   response=new ResponseDto(true,"Cannot Delete Department...!");
	       }}
	       catch(Exception e){
	    	   response=new ResponseDto(true,"Department is Already in Use...!");
	       }
			return response;
	    
	}
	}


	
	/*@RequestMapping(value = "/updateDedpartment", method=RequestMethod.POST)
	public @ResponseBody DepartmentDto  updateDedpartment(@ModelAttribute("Department") DepartmentDto departmentDto){
		DepartmentDto dto =departmentService.findDto(departmentDto.getDepartmentId());
		BPartnerDto bPartnerDto =contextService.getPartner();
		dto.setPartner(bPartnerDto);
		dto.setName(departmentDto.getName());
		dto.setCode(departmentDto.getCode());
		dto.setDescription(departmentDto.getDescription());
		DepartmentDto resdto = departmentService.updateDto(dto);
		
		return resdto;
		
		
	}*/
	/*@RequestMapping(value = "/deleteDepartment", method=RequestMethod.POST)
	public @ResponseBody DepartmentDto  deleteDepartment(@ModelAttribute("Department")  DepartmentDto departmentDto){
		DepartmentDto dto =departmentService.findDto(departmentDto.getDepartmentId());
		dto.setIsActive("N");
		DepartmentDto resdto = departmentService.updateDto(dto);
		
		return resdto;
		
		
	}*/


