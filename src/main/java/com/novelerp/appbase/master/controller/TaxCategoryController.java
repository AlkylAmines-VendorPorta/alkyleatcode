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

import com.novelerp.appbase.master.dto.TaxCategoryDto;
import com.novelerp.appbase.master.service.TaxCategoryService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;

/**
 * 
 * @author Aman
 *
 */
@Controller
public class TaxCategoryController {
	@Autowired
	private TaxCategoryService taxCategoryService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@RequestMapping(value = "/TaxCategory", method = RequestMethod.GET)
	public ModelAndView TaxCategoryView() {
		return  new ModelAndView("taxCategory");
	}
	
	@RequestMapping(value = "/getTaxCategoryList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<TaxCategoryDto> getTaxCategoryList() {
		
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<TaxCategoryDto> taxCatList = taxCategoryService.findDtos("getTaxCatListByPartner", params);
		return taxCatList;
	}
	
	@RequestMapping(value = "/getTaxCategory/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody TaxCategoryDto getTaxCategory(@PathVariable("id") Long id) {
		
		TaxCategoryDto taxCategory=taxCategoryService.findDto(id);
		return taxCategory;
	}
	@RequestMapping(value = "/addNewTaxCat", method=RequestMethod.POST , produces="application/json")
	public @ResponseBody TaxCategoryDto  addNewTaxCat(@ModelAttribute("TaxCategory") TaxCategoryDto taxCatDto){
		TaxCategoryDto catDto=new TaxCategoryDto();
		BPartnerDto bPartnerDto =contextService.getPartner();
		taxCatDto.setPartner(bPartnerDto);
		taxCatDto.setIsActive("Y");
		/*TaxCategoryDto resdto = taxCategoryService.save(taxCatDto);*/
		if(taxCatDto.getTaxCategoryId()==null){
			catDto=taxCategoryService.save(taxCatDto);
		}
		else{
			catDto=taxCategoryService.updateDto(taxCatDto);
		}
		
		return catDto;
	}
	
		
	
	/*@RequestMapping(value = "/upadatetaxCat", method=RequestMethod.POST)
	public @ResponseBody TaxCategoryDto  upadateUom(@ModelAttribute("TaxCategory") TaxCategoryDto taxCatDto){
		TaxCategoryDto dto =taxCategoryService.findDto(taxCatDto.getTaxCategoryId());
		BPartnerDto bPartnerDto =contextService.getPartner();
		dto.setPartner(bPartnerDto);
		dto.setName(taxCatDto.getName());
		dto.setCode(taxCatDto.getCode());
		dto.setDescription(taxCatDto.getDescription());
		TaxCategoryDto resdto = taxCategoryService.updateDto(dto);
		
		return resdto;
		
		
	}*/
	/*@RequestMapping(value = "/deleteTaxCat", method=RequestMethod.POST)
	public @ResponseBody TaxCategoryDto  deleteTaxCat(@ModelAttribute("TaxCategory")  TaxCategoryDto taxCatDto){
		TaxCategoryDto dto =taxCategoryService.findDto(taxCatDto.getTaxCategoryId());
		dto.setIsActive("N");
		TaxCategoryDto resdto = taxCategoryService.updateDto(dto);
		
		return resdto;
		
		
	}*/
	@RequestMapping(value = "/deleteTaxCat/{taxCategoryId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseDto deleteTaxCategory(@PathVariable("taxCategoryId") Long id) {
		ResponseDto response=null;
		try{
		boolean isDeleted=false;
		if(id>0){
			isDeleted=taxCategoryService.deleteTaxCat(id);
		}
		
		if(isDeleted){
			response= new ResponseDto(false,"Tax Category Deleted..!");
		}else{
			response= new ResponseDto(true,"Could Not Delete Tax Category..!");
		}
		}
		catch(Exception e)
		{
			response= new ResponseDto(true,"Tax Category is Already in use..!");
		}
		return response;
	}
	

}


