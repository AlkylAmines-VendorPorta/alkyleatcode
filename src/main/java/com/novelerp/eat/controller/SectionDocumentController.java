/**
 * @author Ankush
 */
package com.novelerp.eat.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.SectionDocumentDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.service.SectionDocumentService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRMaterialService;

@Controller
public class SectionDocumentController {
	
	@Autowired
	private SectionDocumentService secDocService;
	
	@Autowired
	private TAHDRMaterialService tahdrMaterialService;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	/*@RequestMapping(value="/saveSectionDocument", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto saveSectionDocument(@ModelAttribute("reqDocList")ReqDocList reqDocList){
		CustomResponseDto response=new CustomResponseDto();
		response.addObject("sectionDocSet",secDocService.save(reqDocList.getSecDocSet()) );
		return response;
	}*/

	@RequestMapping(value="/saveSectionDocument", method=RequestMethod.POST)
	@ResponseBody
	public SectionDocumentDto saveSectionDocument(@ModelAttribute("sectionDoc")SectionDocumentDto sectionDoc){
		int updateTahdrDetail=0;
		TAHDRDetailDto tahdrDetailDto=tahdrDetailService.findDto("getTenderDocDetails", AbstractContextServiceImpl.getParamMap("tahdrDetailId", sectionDoc.getTahdrDetail().getTahdrDetailId()));
		updateTahdrDetail=tahdrDetailService.updateTenderDocOnDrafted(tahdrDetailDto);
		return secDocService.save(sectionDoc);
	}
	
	@RequestMapping(value="/getSectionDocument/{tahdrDetailId}", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto saveSectionDocument(@PathVariable("tahdrDetailId")Long tahdrDetailId){
		CustomResponseDto response=new CustomResponseDto();
		List<TAHDRMaterialDto> materialList=tahdrMaterialService.getTahdrMaterialList(tahdrDetailId);
		Set<SectionDocumentDto> secDocSet= secDocService.getSectionDoc(tahdrDetailId);
		response.addObject("materialList", materialList);
		response.addObject("secDocSet", secDocSet);
		return response;
	}

	@RequestMapping(value= "/deleteSectionDocument/{sectionDocumentId}", method=RequestMethod.POST)
	public @ResponseBody ResponseDto deleteSectionDocument(@PathVariable("sectionDocumentId")Long sectionDocumentId){
		ResponseDto response=null;
		boolean isDeleted=false;
		if(sectionDocumentId>0){
			isDeleted=secDocService.deleteSectionDoc(sectionDocumentId);
		}
		if(isDeleted){
			response= new ResponseDto(false,"Section Document Deleted !");
		}else{
			response= new ResponseDto(true,"Could Not Section Document !");
		}
		
		return response;
	}
	
}
