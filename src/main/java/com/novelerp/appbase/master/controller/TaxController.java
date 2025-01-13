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

import com.novelerp.appbase.master.dto.TaxDto;
import com.novelerp.appbase.master.service.TaxCategoryService;
import com.novelerp.appbase.master.service.TaxService;
import com.novelerp.appbase.validator.TaxValidator;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;

/** 
 * @author Aman
 *
 */
@Controller
public class TaxController {
	@Autowired
	private TaxService taxService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private TaxValidator getTaxValidator;
	
	@Autowired
	private TaxCategoryService taxCategoryService;
	
	@RequestMapping(value = "/tax", method = RequestMethod.GET)
	public ModelAndView TaxView() {
		BPartnerDto bPartnerDto =contextService.getPartner();
		ModelAndView mv =new ModelAndView("tax");
		mv.addObject("taxCategory", taxCategoryService.getTaxCategoryList(bPartnerDto.getbPartnerId()));
		return  mv;
	}
	
	@RequestMapping(value = "/getTaxList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<TaxDto> getTaxList() {
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<TaxDto> taxDtoList = taxService.findDtos("getTaxByBPartner", params);
		
		return taxDtoList;
	}
	
	/*@RequestMapping(value = "/getTaxList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public CustomResponseDto getTaxList(){
		CustomResponseDto response= new CustomResponseDto();
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<TaxDto> taxDtoList = taxService.findDtos("getTaxByBPartner", params);
		List<TaxCategoryDto> taxCategoryDto= taxCategoryService.getTaxCategoryList();
		response.addObject("taxDtoList", taxDtoList);
		response.addObject("taxCategoryDto", taxCategoryDto);
		return response;
		
	}*/
	
	@RequestMapping(value = "/getTax/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody TaxDto getTax(@PathVariable("id") Long id) {
		
		TaxDto taxDto=taxService.getTax(id);
		return taxDto;
	}
	
	@RequestMapping(value ="/saveTax" , method = RequestMethod.POST , produces="application/json")
	public @ResponseBody TaxDto saveTax(@ModelAttribute ("tax") TaxDto dto){
		TaxDto taxdto= new TaxDto();
		BPartnerDto bPartnerDto =contextService.getPartner();
		dto.setPartner(bPartnerDto);
		dto.setIsActive("Y");
		if (dto.getTaxId()==null){
			taxdto=taxService.save(dto);
		}
		else{
			taxdto=taxService.updateDto(dto);
		}
		return taxdto;
	}
	
    
	@RequestMapping(value = "/deleteTax/{taxId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseDto deleteTax(@PathVariable("taxId") Long id) {
		ResponseDto response=null;
		boolean isDeleted=false;
		if(id>0){
			isDeleted=taxService.deleteTax(id);
		}
		
		if(isDeleted){
			response= new ResponseDto(false,"Tax Deleted..!");
		}else{
			response= new ResponseDto(true,"Could Not Delete Tax..!");
		}
		
		return response;
	}
	
	/*@RequestMapping(value="/saveTax", method=RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto saveTax(@ModelAttribute("Tax") TaxDto dto){
		BPartnerDto bPartnerDto =contextService.getPartner();
		dto.setPartner(bPartnerDto);
		dto.setIsActive("Y");
		Errors errors= new Errors();
		getTaxValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			return new CustomResponseDto(errors.getErrorList(), "Request has validation errors, look for error List",false);
		}
		return taxService.saveTax(dto);
	}*/
	
	/*@RequestMapping(value = "/editTax", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto editGtpParameter(@ModelAttribute("Tax") TaxDto dto) {
		BPartnerDto bPartnerDto =contextService.getPartner();
		dto.setPartner(bPartnerDto);
		dto.setIsActive("Y");
		Errors errors =  new Errors();
//		ogetTaxValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			return new CustomResponseDto(errors.getErrorList(), "Request has validation errors, look for error List",false);
		}
		else
		return taxService.editTax(dto);
	}
	*/
}


