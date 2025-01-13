package com.novelerp.appbase.master.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.BomVersionDto;
import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.service.BOMVersionService;
import com.novelerp.appbase.master.service.MaterialService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;

@Controller
public class BOMVersionController {
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private BOMVersionService bomVersionService;
	
	private static Logger log = LoggerFactory.getLogger(BOMVersionController.class);
	
	@RequestMapping(value = "/getBOMListFromMaterial/{materialId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto getBOMListFromMaterial(@PathVariable("materialId") Long materialId) {
		
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("materialId", materialId);
 		List<BomVersionDto> bomVersion = bomVersionService.findDtos("getBomVersionFromMaterial", params);
		MaterialDto material= materialService.findDto(materialId);
 		CustomResponseDto response = new CustomResponseDto("bomVersion", bomVersion);
		response.addObject("material", material);
 		return response;
	}
	
	@RequestMapping(value="/saveBomVersion", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BomVersionDto saveBomVersion(@ModelAttribute ("bomVersion") BomVersionDto dto){
		BomVersionDto bomDto=new BomVersionDto();
		if(dto.getBomVersionId() == null){
			bomDto= bomVersionService.save(dto);
		}
		else{
			bomDto= bomVersionService.updateDto(dto);
		}
		/*bomDto.setMaterial(dto.getMaterial());*/
		return bomDto;
	}
	
	@RequestMapping(value="/deleteBomVersion/{bomVersionId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("bomVersionId") Long bomVersionId){
		try{
			boolean deleted =  bomVersionService.deleteById(bomVersionId);
			if(deleted){
				return new ResponseDto(false, "Record deleted");
			}
		}catch(Exception ex){
			log.info("error deleting Bom Version"+ex.getMessage());
		}
		return new ResponseDto(true, "Delete its Parts,Before deleting Bom Version.");
	}

	@RequestMapping(value = "/getBOMListForTahdrMaterial/{materialId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto getBOMListForTahdrMaterial(@PathVariable("materialId") Long materialId) {
		
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("materialId", materialId);
 		List<BomVersionDto> bomVersion = bomVersionService.findDtos("getBomVersionForTahdrMaterial", params);
		MaterialDto material= materialService.findDto(materialId);
 		CustomResponseDto response = new CustomResponseDto("bomVersion", bomVersion);
		response.addObject("material", material);
 		return response;
	}

}
