package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.appbase.master.service.TileMasterService;
import com.novelerp.appbase.validator.TileMasterValidator;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;

@Controller
public class TileMasterController {
	private static final Logger log=LoggerFactory.getLogger(BidTypeController.class);
	@Autowired
	private TileMasterService tileMasterService;
	
	@Autowired
	private TileMasterValidator tileMasterValidator;

	@RequestMapping(value= {"/tileMaster"},method =RequestMethod.GET)
	public @ResponseBody ModelAndView openProfilePage() {
		return new ModelAndView("tileMaster");
	}
	
	@RequestMapping(value = "/getTileMasterList/{pageNumber}/{pageSize}/{searchMode}/{serachValue}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto getTileMasterList(@PathVariable("pageNumber")int pageNumber, 
																	 @PathVariable("pageSize") int pageSize,
																	 @PathVariable("searchMode") String searchMode , 
																	 @PathVariable("serachValue") String searchValue) {
		Map<String, Object> params=new HashMap<>();
        /*List<TileMasterDto> getTileMasterList = tileMasterService.getTileList("queryForTilesMasterList",params,pageNumber, pageSize, searchMode, searchValue);
        long countResult = tileMasterService.getTileCount(params, searchMode, searchValue);*/
		List<TileMasterDto> getTileMasterList = tileMasterService.findAll();
        long countResult = CommonUtil.isCollectionEmpty(getTileMasterList)==true?0:getTileMasterList.size();
		CustomResponseDto customResponseDto = new CustomResponseDto();
		customResponseDto.setData(getTileMasterList);
		customResponseDto.addObject("LastPage", (int) ((countResult / pageSize) + 1));
		return customResponseDto;
	}
	
	@RequestMapping(value = "/getTileMaster/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody TileMasterDto getTileMaster(@PathVariable("id") Long id) {
		return tileMasterService.findDto(id);
	}
	
	@RequestMapping(value="/saveTileMaster", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto saveTileMaster(@ModelAttribute("TileMaster") TileMasterDto dto){
		CustomResponseDto response =new CustomResponseDto();
		Errors errors =  new Errors();
		tileMasterValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			response.addObject("hasError", errors);
			response.addObject("result", false);
			response.addObject("message", "Configurator not saved or updated");
		}
		else{
			if(dto.getTileMasterId()==null){
				dto =tileMasterService.save(dto);
				response.addObject("message", "Tile add successfully");
			}else{
				dto =tileMasterService.updateDto(dto);
				response.addObject("message", "Tile Updated successfully");
			}
			response.addObject("systemConfigurator", dto);
			response.addObject("result", true);
			
		}
		return response;
	}
	
	@RequestMapping(value = "/deleteTileMasterService/{tileMasterServiceId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto delete(@PathVariable("tileMasterServiceId") Long tileMasterServiceId) {
		CustomResponseDto response=new CustomResponseDto();
			boolean deleted = tileMasterService.deleteById(tileMasterServiceId);
			if(deleted){
				response.addObject("result", true);
				response.addObject("message", "Tile Deleted Successfully ");
			}else{
				response.addObject("result", false);
				response.addObject("message", "Not deleted ");
			}
			return	response;
			
	}
}
