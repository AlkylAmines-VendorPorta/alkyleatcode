package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.QuickRfqService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRService;

@Controller
public class QuickRfqController {

	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private QuickRfqService quickRfqService;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	
	@RequestMapping(value= "/quickRfqPreparation", method=RequestMethod.GET)
	public ModelAndView quickRfqPreparation(){
		ModelAndView model= new ModelAndView("quickRfqPreparation");
		String dataUrl="quickRfqPreparationData";
		String dataForTypeCode="getQuickAuctionByTypeCode";
		model.addObject("documentType", ContextConstant.DOCUMENT_QUICK_REQUEST_FOR_PROPOSAL);
		model.addObject("dataUrl", dataUrl);
		model.addObject("dataForTypeCode", dataForTypeCode);
		return model;
	}
	
	@RequestMapping(value= "/getRfqByType", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto getRfqByType(@RequestParam("tahdrTypeCode")String tenderTypeCode,
												@RequestParam("pageNumber")int pageNumber, 
												@RequestParam("pageSize") int pageSize,
												@RequestParam("searchMode") String searchMode , 
												@RequestParam("searchValue") String searchValue){
		CustomResponseDto customResponseDto = new CustomResponseDto();
		Map<String, Object> objectMap = new HashMap<>();
		Map<String,Object> param=new HashMap<String,Object>();
		List<TAHDRDto> approvalMatrixList=null;
		UserDto userDto=contextService.getUser();
		param.put("userId", userDto.getUserId());
		param.put("typeCode", tenderTypeCode); 
		try{
				long countResult = quickRfqService.getQuickRfqListQueryCount(param,searchMode, searchValue);
				int LastPage = (int) ((countResult / pageSize) + 1);
				approvalMatrixList=quickRfqService.getQuickRfqList(param, pageNumber, pageSize,searchMode, searchValue);
				objectMap.put("LastPage", LastPage);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		customResponseDto.setData(approvalMatrixList);
		customResponseDto.setObjectMap(objectMap);
		return customResponseDto;
	}
	
	@RequestMapping(value= "/quickRfqPreparationData", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto quickAuctionPreparationData(@RequestParam("tenderTypeCode")String tenderTypeCode,
												   @RequestParam("pageNumber")int pageNumber, 
												   @RequestParam("pageSize") int pageSize,
												   @RequestParam("searchMode") String searchMode , 
												   @RequestParam("searchValue") String searchValue){
		CustomResponseDto response=new CustomResponseDto();
		RoleDto roleDto= contextService.getDefaultRole();
		Map<String,String> tahdrTypeList=refListService.getReferenceListMap(AppBaseConstant.TENDER_TYPE);
		Map<String,String> bidTypeList=refListService.getReferenceListMap(AppBaseConstant.BID_TYPE);
		Map<String,String> bidSection=refListService.getReferenceListMap(AppBaseConstant.BID_SECTION);
		Map<String,String> tenderStatus=refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		CustomResponseDto tahdrList=getRfqByType(tenderTypeCode,pageNumber,pageSize,searchMode,searchValue);
		List<ReferenceListDto> viewButton= tahdrService.getAccessAction(roleDto.getValue());
		Map<String,String> commencementPeriodValue=refListService.getReferenceListMap(CoreReferenceConstants.COMMENCEMENT_PERIOD_CODE);
		response.addObject("tahdrTypeList", tahdrTypeList);
		response.addObject("bidTypeList", bidTypeList);
		response.addObject("listTahdr", tahdrList);
		response.addObject("tenderTypeCode", tenderTypeCode);
		response.addObject("bidSection", bidSection);
		response.addObject("viewButton", viewButton);
		response.addObject("tenderStatus", tenderStatus);
		response.addObject("roleDto", roleDto);
		response.addObject("commencementPeriodValue", commencementPeriodValue);
		return response;
	}
	
	@RequestMapping(value= "/createQuickRfq", method=RequestMethod.POST)
	public @ResponseBody TAHDRDto createTAHDR(@ModelAttribute("tahdr") TAHDRDto tahdr){
		ResponseDto response=null;
		TAHDRDto dto=tahdrService.findDto("getQueryForTAHDRByCode", AbstractContextServiceImpl.getParamMap("tahdrCode", tahdr.getTahdrCode()));
		
		/*if(null!=dto && (dto.getTahdrId()!=tahdr.getTahdrId())){*/
		if(null!=dto && (!CommonUtil.isEqual(dto.getTahdrId(), tahdr.getTahdrId()))){
			response= new ResponseDto(true, "Tender Code Already Exists..!");
			tahdr.setResponse(response);
		}else if(null!=dto && (CommonUtil.isEqual(dto.getTahdrId(), tahdr.getTahdrId()))){
			Map<String, Object> tenderDoc = new HashMap<>();
			tahdr.setTahdrStatusCode(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
			TAHDRDetailDto td=tahdr.getTahdrDetail().iterator().next();
			TAHDRDetailDto oldDto=tahdrDetailService.findDto(td.getTahdrDetailId());
			tahdr=quickRfqService.updateQuickRfq(tahdr,td,oldDto);
			if(dto.getTahdrDetail()!=null && !dto.getTahdrDetail().isEmpty() ){
				if(dto.getTahdrDetail().iterator().next().getTenderDoc()!=null && dto.getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_DRAFTED)){
					tenderDoc.put("tenderDoc", null);
					tahdrDetailService.updateByJpql(tenderDoc, "tahdrDetailId",dto.getTahdrDetail().iterator().next().getTahdrDetailId());
				}
			}
		}else if(null==dto && (null==tahdr.getTahdrId() || !(tahdr.getTahdrId()>0))){
			tahdr.setTahdrStatusCode(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
			tahdr=quickRfqService.createQuickRfq(tahdr);	
		}
		return tahdr;
	}
}
