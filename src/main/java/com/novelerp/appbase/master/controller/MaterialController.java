package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.GtpParameterDto;
import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.MaterialSearchDto;
import com.novelerp.appbase.master.service.GtpParameterService;
import com.novelerp.appbase.master.service.HSNService;
import com.novelerp.appbase.master.service.MaterialGroupService;
import com.novelerp.appbase.master.service.MaterialService;
import com.novelerp.appbase.master.service.MaterialSubGroupService;
import com.novelerp.appbase.master.service.UOMService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;

/**
 * @author Aman
 *
 */
@Controller
public class MaterialController {
	@Autowired
	private MaterialService materialService;

	@Autowired
	private MaterialGroupService materialGroupService;

	@Autowired
	private MaterialSubGroupService materialSubGroupService;

	@Autowired
	private UOMService uomService;
	
	@Autowired
	
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private HSNService hsnService;

	@Autowired
	private GtpParameterService gtpParameterService;

	@RequestMapping(value = "/material", method = RequestMethod.GET)
	public ModelAndView MaterialView() {
		ModelAndView mv = new ModelAndView("material");
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		/*mv.addObject("materialGroup", materialGroupService.getMaterialGroupList());
		mv.addObject("materialSubGroup", materialSubGroupService.getMaterialSubGroupList());*/
		/*mv.addObject("uom", uomService.getUomList());*/
		mv.addObject("uom",uomService.findDtos("getUomListByPartner", params));
		mv.addObject("hsn",hsnService.findDtos("getHSnByBPartner", params));
		mv.addObject("materialGroup",materialGroupService.findDtos("getMaterialGroupListByBPartner", params));
		mv.addObject("materialSubGroup",materialSubGroupService.findDtos("getMaterialSubGroupListByBPartner", params));
		return mv;
	}

	@RequestMapping(value = "/getMaterialList", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto getMaterialList(@RequestParam("pageNumber")int pageNumber, 
														   @RequestParam("pageSize") int pageSize,
														   @RequestParam("searchMode") String searchMode , 
														   @RequestParam("searchValue") String searchValue) {
		CustomResponseDto customResponseDto = new CustomResponseDto();
		Map<String, Object> objectMap = new HashMap<>();
		BPartnerDto bPartnerDto =contextService.getPartner();
		long countResult = materialService.getMaterialListQueryCount(searchMode, searchValue,bPartnerDto.getbPartnerId());
		int LastPage = (int) ((countResult / pageSize) + 1);
		List<MaterialDto> materialDtoList = materialService.getMaterialList(pageNumber, pageSize, searchMode, searchValue,bPartnerDto.getbPartnerId());
		objectMap.put("LastPage", LastPage);
		customResponseDto.setData(materialDtoList);
		customResponseDto.setObjectMap(objectMap);
		return customResponseDto;
	}

	@RequestMapping(value = "/savenewmaterial", method=RequestMethod.POST, produces = "application/json")
	public @ResponseBody MaterialDto  savenewmaterial(@ModelAttribute("Material") MaterialDto materialDto){
		BPartnerDto bPartnerDto =contextService.getPartner();
		materialDto.setPartner(bPartnerDto);
		materialDto.setIsActive("Y");
		MaterialDto material= new MaterialDto();
		/*MaterialDto resdto = materialService.save(materialDto);*/
		if (materialDto.getMaterialId()==null){
			material=materialService.save(materialDto);
		}
		else{
			material=materialService.updateDto(materialDto);
		}
		
		return material;
		
		
	}
	@RequestMapping(value = "/deletematerialmaster/{matId}", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody ResponseDto  deletematerialmaster(@PathVariable("matId") Long id){
		/*MaterialDto dto =materialService.findDto(materialDto.getMaterialId());*/
		ResponseDto response=null;
		try{
		boolean isDeleted=false;
		if(id>0){
			isDeleted=materialService.deleteMaterial(id);
		}
		
		if(isDeleted){
			response= new ResponseDto(false,"Material Deleted..!");
		}else{
			response= new ResponseDto(true,"Could Not Delete Material..!");
		}
		}
		catch(Exception e){
			response= new ResponseDto(true,"Material is Already in Use...!");
		}
		return response;
	}
	
	
	@RequestMapping(value = "/getMaterial/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody MaterialDto getMaterial(@PathVariable("id") Long id) {
		MaterialDto materialDto = materialService.getMaterial(id);
		return materialDto;
	}

	@RequestMapping(value = "/getMaterialBySubGroup/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<MaterialDto> getMaterialBySubGroup(@PathVariable("id") Long id) {

		List<MaterialDto> materialList = materialService.getMaterialBySubGroup(id);
		return materialList;
	}

	@RequestMapping(value = "/getItemList", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<MaterialDto> getItemList(@ModelAttribute("material") MaterialSearchDto materialDto) {
		List<MaterialDto> materialList = null;
		if (materialDto != null) {
			materialList = materialService.getSearchedMaterialList(materialDto);
		}
		return materialList;
	}

	@RequestMapping(value = "/copyGtp/{fromMaterialId}/{toMaterialId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto copyGtp(@PathVariable("fromMaterialId") Long fromMaterialId,
			@PathVariable("toMaterialId") Long toMaterialId) {
		CustomResponseDto response = new CustomResponseDto();

		Map<String, Object> params1 = new HashMap<>();
		params1.put("materialId", toMaterialId);
		List<GtpParameterDto> gtpToList = gtpParameterService.findDtos("getCopiedGtpParameterListByMaterial", params1);
		if (CommonUtil.isCollectionEmpty(gtpToList)) {

			Map<String, Object> params2 = new HashMap<>();
			params2.put("materialId", fromMaterialId);
			List<GtpParameterDto> gtpFromList = gtpParameterService.findDtos("getGtpParameterListByMaterial", params2);
			/*
			 * List<GtpParameterDto> gtpFromList=gtpParameterService.findDtos(
			 * "getNonCopiedGtpParameterListByMaterial", params2);
			 */

			if (!CommonUtil.isCollectionEmpty(gtpFromList) && !CommonUtil.isEqual(fromMaterialId, toMaterialId)) {
				response = gtpParameterService.copyGTP(gtpFromList, toMaterialId);
				if ((boolean) response.getObjectMap().get("result")) {
					response.addObject("result", true);
					response.addObject("resultMessage", "GTP Copied Successfully");
				} else {
					response.addObject("result", false);
					response.addObject("resultMessage", "GTP Not Copied");
				}

			} else {
				response.addObject("result", false);
				response.addObject("resultMessage", "No GTP Found to copy");
			}
		} else {
			response.addObject("result", false);
			response.addObject("resultMessage", "Already Contain Copied GTP,Delete previous Copied GTP !");
		}

		return response;
	}

	@RequestMapping(value = "/deleteGtp/{materialId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto deleteGtp(@PathVariable("materialId") Long materialId) {
		CustomResponseDto response = new CustomResponseDto();
		try {
			response = gtpParameterService.deleteGTP(materialId);
			if ((boolean) response.getObjectMap().get("result")) {
				response.addObject("result", true);
				response.addObject("resultMessage", "Copied GTP Deleted Successfully");
			} else {
				response.addObject("result", false);
				response.addObject("resultMessage", "No Copied GTP Found To delete !");
			}
		} catch (Exception ex) {
			response.addObject("result", false);
			response.addObject("resultMessage", "Some GTP Already in use");
		}
		return response;
	}
	/*@RequestMapping(value ="/getTradingItem", method=RequestMethod.POST,produces = "application/json")	
	public @ResponseBody CustomResponseDto getTradingItem(@ModelAttribute("material") MaterialSearchDto dto){
		List<MaterialDto> tradingItems=materialService.getTradingMaterial(dto);
		return new CustomResponseDto("tradingItems", tradingItems);
	}*/
}
