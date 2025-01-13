/**
 * @author Ankush
 */
package com.novelerp.eat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.service.MaterialService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRMaterialService;

@Controller
public class TAHDRMaterialController {
	
	@Autowired
	private TAHDRMaterialService tahdrMaterialService;

	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;

	@RequestMapping(value= "/saveTahdrMaterial", method=RequestMethod.POST)
	public @ResponseBody TAHDRMaterialDto saveTahdrMaterial(@ModelAttribute("tahdrMaterial") TAHDRMaterialDto tahdrMaterial){
		ResponseDto response=null;
		int updateTahdrDetail=0;
		TAHDRDetailDto tahdrDetail=tahdrDetailService.findDto("getTenderDocDetails", AbstractContextServiceImpl.getParamMap("tahdrDetailId", tahdrMaterial.getTahdrDetail().getTahdrDetailId()));
		if(null!=tahdrMaterial){
			if((null!=tahdrMaterial.getTahdrMaterialId() && tahdrMaterial.getTahdrMaterialId()>0)){
				tahdrMaterial.setMaterial(materialService.getMaterial(tahdrMaterial.getMaterial().getMaterialId()));
				tahdrMaterial=tahdrMaterialService.updateDto(tahdrMaterial);
				updateTahdrDetail=tahdrDetailService.updateTenderDocOnDrafted(tahdrDetail);
				/*Map<String, Object> tenderDoc = new HashMap<>();
				if(tahdrDetail!=null){
					if(tahdrDetail.getTenderDoc()!=null && tahdrDetail.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_DRAFTED)){
						tenderDoc.put("tenderDoc", null);
						tahdrDetailService.updateByJpql(tenderDoc, "tahdrDetailId",tahdrDetail.getTahdrDetailId());
					}
					}*/
				
			}else if(!tahdrMaterialService.isItemAlreadyExist(tahdrMaterial)){
				tahdrMaterial.setMaterial(materialService.getMaterial(tahdrMaterial.getMaterial().getMaterialId()));
				tahdrMaterial=tahdrMaterialService.save(tahdrMaterial);
			}else{
				response= new ResponseDto(true,"Material Already Exists..!");
				tahdrMaterial.setResponse(response);
			}
		}
		return tahdrMaterial;
	}
	
	@RequestMapping(value= "/deleteTahdrMaterial/{tahdrMaterialId}", method=RequestMethod.POST)
	public @ResponseBody ResponseDto deleteTahdrMaterial(@PathVariable("tahdrMaterialId")Long tahdrMaterialId){
		ResponseDto response=null;
		boolean isDeleted=false;
		try{
		if(tahdrMaterialId>0){
			isDeleted=tahdrMaterialService.deleteTAHDRMaterial(tahdrMaterialId);
		}
		
		if(isDeleted){
			response= new ResponseDto(false,"Material Deleted..!");
		}else{
			response= new ResponseDto(true,"Could Not Delete Material..!");
		}
		}
		catch (Exception e) {
			response= new ResponseDto(true,"Could Not Delete Material..!");
		}
		return response;
	}
	
	@RequestMapping(value= "/getTAHDRMaterialList/{tahdrDetailId}", method=RequestMethod.POST)
	public @ResponseBody List<TAHDRMaterialDto> getTAHDRMaterialList(@PathVariable("tahdrDetailId")Long tahdrDetailId){
		List<TAHDRMaterialDto> materialList=tahdrMaterialService.getTahdrMaterialList(tahdrDetailId); 
		return materialList;
	}
	
	@RequestMapping(value= "/getTAHDRMaterialById/{tahdrMaterialId}", method=RequestMethod.POST)
	public @ResponseBody TAHDRMaterialDto getTAHDRMaterialById(@PathVariable("tahdrMaterialId")Long tahdrMaterialId){
		TAHDRMaterialDto material=tahdrMaterialService.getTahdrMaterialById(tahdrMaterialId);
		return material;
	}
	
}
