package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.DepartmentDto;
import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.DepartmentService;
import com.novelerp.appbase.master.service.LocationTypeService;
import com.novelerp.appbase.master.service.PaymentTypeService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.TAHDRService;

@Controller
public class TAHDRApprovalController {

	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
		
	@Autowired
	private LocationTypeService locationTypeService;
	
	@Autowired
	private PaymentTypeService paymentTypeService;

	@RequestMapping(value= "/tenderApproval", method=RequestMethod.GET)
	public ModelAndView tenderPreparation(){
		ModelAndView model= new ModelAndView("tenderPreparation");
		String dataUrl="tenderApprovalData";
		String dataForTypeCode="getTAHDRApprovalByTypeCode";
		model.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
		model.addObject("pageType","APPROVAL");
		model.addObject("dataUrl", dataUrl);
		model.addObject("dataForTypeCode", dataForTypeCode);
		return model;
	}
	
	@RequestMapping(value= "/auctionApproval", method=RequestMethod.GET)
	public ModelAndView auctionPreparation(){
		ModelAndView model= new ModelAndView("tenderPreparation");
		String dataUrl="tenderApprovalData";
		String dataForTypeCode="getTAHDRApprovalByTypeCode";
		model.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		model.addObject("dataUrl", dataUrl);
		model.addObject("dataForTypeCode", dataForTypeCode);
		return model;
	}
	
	@RequestMapping(value= "/getTAHDRApprovalByTypeCode", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto getTAHDRApprovalByTypeCode(@RequestParam("tahdrTypeCode")String tenderTypeCode,
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
			/*approvalMatrixList=tahdrService.findDtos("getTahdrListFromApprovalMatrix",param);*/
			long countResult = tahdrService.getTahdrApprovalListQueryCount(param,searchMode, searchValue);
			int LastPage = (int) ((countResult / pageSize) + 1);
			approvalMatrixList=tahdrService.getTahdrApprovalList(param, pageNumber, pageSize,searchMode, searchValue);
			objectMap.put("LastPage", LastPage);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		customResponseDto.setData(approvalMatrixList);
		customResponseDto.setObjectMap(objectMap);
		BPartnerDto partnerDto=contextService.getPartner();
		contextService.setSFTPRequiredInfo(partnerDto,tenderTypeCode,null);
		return customResponseDto;
	}
	
	@RequestMapping(value= "/tenderApprovalData", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto tenderApprovalData(@RequestParam("tenderTypeCode")String tenderTypeCode,
			   									@RequestParam("pageNumber")int pageNumber, 
			   									@RequestParam("pageSize") int pageSize,
			   									@RequestParam("searchMode") String searchMode , 
			   									@RequestParam("searchValue") String searchValue){
		Map<String,Object> param=new HashMap<String,Object>();
		Map<String,Object> listParam=new HashMap<String,Object>();
		UserDetailsDto userDetail=contextService.getUserDetails();
		BPartnerDto partner=contextService.getPartner();
		String locationType=userDetail.getLocationTypeRef();
		param.put("code",locationType);
		LocationTypeDto locationTypeDto=locationTypeService.findDto("getLocationTypeOnType", param);
		listParam.put("levels", locationTypeDto.getLevels());
		List<LocationTypeDto> officeType=locationTypeService.findDtos("getLocationTypeOnLevels", listParam);
		
		RoleDto roleDto= contextService.getDefaultRole();
		List<DepartmentDto> deptList=deptService.getDepartmentList(partner);
		Map<String,String> tahdrTypeList=refListService.getReferenceListMap(AppBaseConstant.TENDER_TYPE);
		Map<String,String> bidTypeList=refListService.getReferenceListMap(AppBaseConstant.BID_TYPE);
		Map<String,String> priceBasis=refListService.getReferenceListMap(AppBaseConstant.BASIS_TYPE);
		/*Map<String,String> officeType=refListService.getReferenceListMap(AppBaseConstant.OFFICE_TYPE);*/
		Map<String,String> budgetType=refListService.getReferenceListMap(AppBaseConstant.BUDGET_TYPE);
		Map<String,String> bidSection=refListService.getReferenceListMap(AppBaseConstant.BID_SECTION);
		Map<String,String> tenderStatus=refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		
		CustomResponseDto tahdrList=getTAHDRApprovalByTypeCode(tenderTypeCode,pageNumber,pageSize,searchMode,searchValue);
		List<ReferenceListDto> viewButton= tahdrService.getAccessAction(roleDto.getValue());
		CustomResponseDto response=new CustomResponseDto();
		response.addObject("paymentType", paymentTypeService.findDto("getPaymentTypeByCode",
				AbstractContextServiceImpl.getParamMap("paymentTypeCode",AppBaseConstant.TENDER_PURCHASE_FEE)));
		response.addObject("deptList", deptList);
		response.addObject("tahdrTypeList", tahdrTypeList);
		response.addObject("bidTypeList", bidTypeList);
		response.addObject("listTahdr", tahdrList);
		response.addObject("priceBasis", priceBasis);
		response.addObject("officeType", officeType);
		response.addObject("budgetType", budgetType);
		response.addObject("tenderTypeCode", tenderTypeCode);
		response.addObject("bidSection", bidSection);
		response.addObject("viewButton", viewButton);
		response.addObject("tenderStatus", tenderStatus);
		response.addObject("roleDto", roleDto);
		contextService.setSFTPRequiredInfo(partner, tenderTypeCode,null);
		return response;
	}
}
