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

import com.novelerp.appbase.master.dto.UOMDto;
import com.novelerp.appbase.master.service.UOMService;
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
public class UOMController {

	@Autowired
	private UOMService uomService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = {"/uom"}, method = RequestMethod.GET)
	public ModelAndView materialGroupView() {
		return new ModelAndView("uom");
	}
	
	@RequestMapping(value = "/getUomList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<UOMDto> getUomList() {
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<UOMDto> uomList = uomService.findDtos("getUomListByPartner", params);
		return uomList;
	}
	
	@RequestMapping(value = "/getUom/{uomId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody UOMDto getUom(@PathVariable("uomId") Long id) {
		
		UOMDto uomDto=uomService.findDto(id);
		return uomDto;
	}
	@RequestMapping(value = "/getsearcheduom/{searchliteral}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<UOMDto> getSearchedUOMList( @PathVariable("searchliteral") String s) 
	{
		List<UOMDto> uomList=uomService.getSearchedUomList(s);
		return uomList;
	}
	
	
	@RequestMapping(value = "/addNewUom", method=RequestMethod.POST  , produces="application/json")
	public @ResponseBody UOMDto   addNewUom(@ModelAttribute("UOM") UOMDto uomDto){
		
		UOMDto uom=new UOMDto();
		BPartnerDto bPartnerDto =contextService.getPartner();
		uomDto.setPartner(bPartnerDto);
		uomDto.setIsActive("Y");
		if (uomDto.getUomId()==null){
			uom=uomService.save(uomDto);
		}
		else{
			uom=uomService.updateDto(uomDto);
		}
		
		return uom;
	}
	
	@RequestMapping(value = "/deleteUOM/{Id}", method=RequestMethod.POST , produces = "application/json")
	public @ResponseBody ResponseDto  deleteUOM(@PathVariable("Id") Long id){
		ResponseDto response=null;
		try{
		boolean isDeleted=false;
		if(id>0){
			isDeleted=uomService.deleteUOM(id);
		}
		
		if(isDeleted){
			response= new ResponseDto(false,"UOM Deleted..!");
		}else{
			response= new ResponseDto(true,"Could Not Delete UOM..!");
		}
		}
		catch(Exception e){
			response= new ResponseDto(true,"UOM is Already in Use..!");
		}
		return response;
	}

}
	/*@RequestMapping(value = "/deleteUom", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto  deleteUom(@ModelAttribute("UOM") UOMDto uom){
		CustomResponseDto response =new CustomResponseDto();
		BPartnerDto bPartnerDto =contextService.getPartner();
		uom.setPartner(bPartnerDto);
		uom.setIsActive("N");
		UOMDto resdto = uomService.updateDto(uom);
		
		response.addObject("uom", resdto);
		response.addObject("result", true);
		response.addObject("resultMessage","UOM Deleted Successfully");
		return response;
		
		
	}*/
	
	/*@RequestMapping(value = "/getsearcheduom", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Map<String,Object> getSearchedUOMList( @RequestParam("literal")String s, @RequestParam("index")int i) {
		
		String word=s;
		int pageno=i;
		Map<String,Object> map=new HashMap<String,Object>();
		ResponseDto response=uomService.getSearchedUomList(word, pageno);
		map.put("DATA", response.getData());
		map.put("COUNT",response.getCount());
		return map;
	}*/
	
	/*@RequestMapping(value="/saveUOM", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody ResponseDto save(@RequestBody UOMDto uomDto){
		UOMDto uom = uomService.save(uomDto);
		if(uom == null){
			return new ResponseDto(AppBaseConstant.FAILURE,"Problem in saving Record");
		}
		return new ResponseDto(AppBaseConstant.SUCCESS, "Success", uom.getUomId());
	}
	
	@RequestMapping(value = "/editUom", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseDto editUom(@RequestBody UOMDto uomDto) {

		ResponseDto response=uomService.editUom(uomDto);
		
		return response;
	}
	
	@RequestMapping(value = "/deleteUom/{uomId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseDto deleteUom(@PathVariable("uomId") Long id, HttpSession session) {

		ResponseDto response=uomService.deleteUom(id);
		List<UOMDto> uomList=uomService.getUomList();
		
		return response;
	}*/
	
	


