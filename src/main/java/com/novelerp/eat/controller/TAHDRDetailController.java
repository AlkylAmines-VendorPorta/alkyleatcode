/**
 * @author Ankush
 */
package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.service.DesignationService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.StandardCustomDocDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.StandardCustomDocService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.validator.TAHDRDatesValidator;

@Controller
public class TAHDRDetailController {

	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@Autowired
	private StandardCustomDocService stdCstDocService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private DesignationService designationService;
	
	@Autowired
	private TAHDRDatesValidator tahdrDatesValidation;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;

	private Logger Log =  LoggerFactory.getLogger(PaymentApprovalController.class);
	
	@RequestMapping(value= "/saveTahdrDetails", method=RequestMethod.POST)
	public @ResponseBody TAHDRDetailDto saveTahdrDetails(@ModelAttribute("tahdrDetail") TAHDRDetailDto tahdrDetail){
		TAHDRDetailDto oldDto=null;
		String extendedMinutes=sysConfiguratorService.getPropertyConfigurator("eat.auto.extend.minutes");
		if((null==tahdrDetail.getTahdrDetailId() || !(tahdrDetail.getTahdrDetailId()>0))){
			tahdrDetail.setAutoExtentionTime(extendedMinutes);
			tahdrDetail=tahdrDetailService.saveTAHDRDetail(tahdrDetail);
		}else{
			oldDto=tahdrDetailService.findDto(tahdrDetail.getTahdrDetailId());
			tahdrDetail=tahdrDetailService.updateDto(tahdrDetail,oldDto);
		}
		Long tahdId=tahdrDetail.getTahdr()==null?0l:tahdrDetail.getTahdr().getTahdrId();
		String oldStatus=oldDto==null?"":oldDto.getTahdr()==null?"":oldDto.getTahdr().getTahdrStatusCode();
		if(tahdId!=0l && AppBaseConstant.DOCUMENT_STATUS_PUBLISHED.equals(oldStatus)){
			Map<String, Object> param= new HashMap<>();
			param.put("tahdrId", tahdId);
			List<BidderDto> bidderData=bidderService.findDtos("getQueryForMailListByTahdrId", param);
			for(BidderDto bidder : bidderData){
				 tahdrDetailService.mailNotificationToBidders(bidder, tahdrDetail);
			}
		}
		
		return tahdrDetail;
	}
	
	@RequestMapping(value= "/createVersion/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody TAHDRDetailDto createVersion(@PathVariable("tahdrId") Long tahdrId){
		ResponseDto response=new ResponseDto();
		TAHDRDetailDto tahdrDetail =new TAHDRDetailDto();
		if(null!=tahdrId){
			TAHDRDetailDto oldDto=tahdrDetailService.findDto("getTenderDocFromTahdrId", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
			if(null!=oldDto){
				try{
					tahdrDetail= tahdrDetailService.createVersion(oldDto);
					Long tahdId=tahdrId==null?0l:tahdrId;
					String oldStatus=oldDto==null?"":oldDto.getTahdr()==null?"":oldDto.getTahdr().getTahdrStatusCode();
					if(tahdId!=0l && AppBaseConstant.DOCUMENT_STATUS_PUBLISHED.equals(oldStatus)){
						Map<String, Object> param= new HashMap<>();
						param.put("tahdrId", tahdId);
						List<BidderDto> bidderData=bidderService.findDtos("getQueryForMailListByTahdrId", param);
						for(BidderDto bidder : bidderData){
							 tahdrDetailService.mailNotificationToBidders(bidder, tahdrDetail);
						}
					}
					return tahdrDetail;
				}catch(Exception ex){
					Log.error(ex.getMessage());
					ResponseDto resp=new ResponseDto(true, ex.getMessage());
					oldDto.setResponse(resp);
					return oldDto;
				}
			}else{
				response.setHasError(true);
				response.setMessage("Error Creating Version.");
				oldDto=new TAHDRDetailDto();
				oldDto.setResponse(response);
				return oldDto;
			}
			
		}else{
			response.setHasError(true);
			response.setMessage("Error Creating Version.");
			TAHDRDetailDto td=new TAHDRDetailDto();
			td.setResponse(response);
			return td;
		}
	}
	
	@RequestMapping(value= "/getTAHDRDetail/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTAHDRDetail(@PathVariable("tahdrId")Long tahdrId){
		Map<String,String> commencementPeriodValue=refListService.getReferenceListMap(CoreReferenceConstants.COMMENCEMENT_PERIOD_CODE);
		Map<String, Object> params= new HashMap<>();
		List<DesignationDto> designations = designationService.findDtos("getQueryForDesignation",params);
		List<TAHDRDetailDto> tahdrDetail=tahdrDetailService.findDtos("getQueryForTAHDRDetail", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
		CustomResponseDto response=new CustomResponseDto();
		response.addObject("tahdrDetail", tahdrDetail);
		response.addObject("commencementPeriodValue", commencementPeriodValue);
		response.addObject("designations", designations);
		return response;
			
	}
	
	@RequestMapping(value= "/saveTahdrDates", method=RequestMethod.POST)
	public @ResponseBody TAHDRDetailDto saveTahdrDates(@ModelAttribute("tahdrDetail") TAHDRDetailDto tahdrDetail){
		Errors errors =  new Errors();
		TAHDRDetailDto tahdrDetailDto=tahdrDetailService.findDto("QueryForTAHDRDetailById", AbstractContextServiceImpl.getParamMap("tahdrDetailId", tahdrDetail.getTahdrDetailId()));
		StringBuilder html = new StringBuilder();
		try{
		if(tahdrDetail.getEditedDates()!=null && tahdrDetail.getEditedDates().equalsIgnoreCase(AppBaseConstant.DATES_EDITED)){
		
			html =tahdrDetailService.findDifferentDate(tahdrDetail,tahdrDetailDto);
		}
		TAHDRDetailDto oldDto=tahdrDetailService.copyNewDatesToOld(tahdrDetail,tahdrDetailDto);
		if(oldDto.getVersion()>1 && oldDto.getIsActive().equalsIgnoreCase("Y")){
			tahdrDatesValidation.checkForActiveCreateVersion(oldDto, errors);
		}else if(oldDto.getTahdr().getTahdrTypeCode().equalsIgnoreCase("RFQ")){
			tahdrDetailService.validate(oldDto, errors);
		}else{
			tahdrDatesValidation.validate(oldDto, errors);
		}
		
		if(errors.getErrorCount()>0){
			ResponseDto response=new ResponseDto();
			response.setHasError(true);
			response.setErrors(errors.getErrorList());
			oldDto.setResponse(response);
			return oldDto;
		}else{
		tahdrDetail=tahdrDetailService.saveTAHDRDates(oldDto);
		if(tahdrDetail.getEditedDates()!=null && tahdrDetail.getEditedDates().equalsIgnoreCase(AppBaseConstant.DATES_EDITED)){
			Map<String, Object> param= new HashMap<>();
			param.put("tahdrId", tahdrDetail.getTahdr().getTahdrId());
			List<BidderDto> bidderData=bidderService.findDtos("getQueryForMailListByTahdrId", param);
			for(BidderDto bidder : bidderData){
				 tahdrDetailService.mailNotificationOfDateToBidders(bidder, tahdrDetail,html);
			}
		}
		}
		}
		catch (NullPointerException ex) {
			Log.info("saveTahdrDates/tahdrdetailcontroller" + ex);
		}
		catch (Exception e) {
			Log.info("saveTahdrDates/tahdrdetailcontroller" + e);
		}
		return tahdrDetail;
	}
	
	@RequestMapping(value= "/saveStdCstDoc", method=RequestMethod.POST)
	public @ResponseBody TAHDRDetailDto saveStdCstDoc(@ModelAttribute("tahdrDetail") TAHDRDetailDto tahdrDetail){
		TAHDRDetailDto tahdrDetailDto=tahdrDetailService.findDto("getTenderDocDetails", AbstractContextServiceImpl.getParamMap("tahdrDetailId", tahdrDetail.getTahdrDetailId()));
		tahdrDetail=stdCstDocService.save(tahdrDetail,tahdrDetailDto);
		return tahdrDetail;
	}
	
	@RequestMapping(value= "/getStdCstDoc/{tahdrDetailId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TAHDRDetailDto getStdCstDoc(@PathVariable("tahdrDetailId") Long tahdrDetailId){
		TAHDRDetailDto tahdrDetail=tahdrDetailService.findDto("getStdCstDocSet", AbstractContextServiceImpl.getParamMap("tahdrDetailId", tahdrDetailId));
		Set<StandardCustomDocDto> stdCustDocs=null;
		if(null!=tahdrDetail){
			stdCustDocs=new TreeSet<StandardCustomDocDto>(tahdrDetail.getStandardCustomDoc());
			tahdrDetail.setStandardCustomDoc(stdCustDocs);
		}
		return tahdrDetail;
	}
	
	@RequestMapping(value= "/removeCutomDoc/{stdDocId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto removeCutomDoc(@PathVariable("stdDocId") Long stdDocId){
		ResponseDto responseDto= new ResponseDto();
		boolean flag=stdCstDocService.deleteById(stdDocId);
		if(flag){
			responseDto.setHasError(false);
			responseDto.setMessage("Removed");
		}else{
			responseDto.setHasError(true);
			responseDto.setMessage("Could not Remove");
		}
		return responseDto;
	}
	
	@RequestMapping(value= "/getTAHDRDates/{tahdrDetailId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTAHDRDates(@PathVariable("tahdrDetailId")Long tahdrDetailId){
		Map<String,Object> listParam=new HashMap<String,Object>();
		listParam.put("tahdrDetailId", tahdrDetailId);
		List<TAHDRDetailDto> tahdrDetail=tahdrDetailService.findDtos("getQueryForTAHDRDates", listParam);
		CustomResponseDto response=new CustomResponseDto();
		response.addObject("tahdrDate", tahdrDetail);
		return response;
			
	}
	
	@RequestMapping(value= "/getTAHDRDocDetail/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTAHDRDocDetail(@PathVariable("tahdrId")Long tahdrId){
		/*Map<String, Object> params= new HashMap<>();*/
		TAHDRDetailDto tahdrDetail=tahdrDetailService.findDto("getTenderDocFromTahdrId", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
		CustomResponseDto response=new CustomResponseDto();
		response.addObject("tahdrDetail", tahdrDetail);
		return response;
			
	}
	
}
