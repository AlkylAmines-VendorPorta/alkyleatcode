package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ItemScrutinyLineDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.BidderStatusService;
import com.novelerp.eat.service.FinalScrutinyService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.ItemScrutinyLineService;
import com.novelerp.eat.service.ItemScrutinyService;
import com.novelerp.eat.service.ScrutinyFileService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRService;

@Service
public class FinalScrutinyServiceImpl implements FinalScrutinyService{
	
	@Autowired
	private ItemScrutinyService itemScrutinyService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private ItemScrutinyLineService itemScrutinyLineService;
	
	@Autowired
	private BidderStatusService bidderStatusService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private TAHDRDetailService tAHDRDetailService;
	
	@Autowired
	private TAHDRService tAHDRService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private ScrutinyFileService scrutinyFileService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto confirmCommercialScrutiny(ItemScrutinyDto itemScrutiny) {
		CustomResponseDto response=new CustomResponseDto();
		boolean result =itemScrutinyService.updateFinalScrutinyInfo(itemScrutiny);
		if(result){
			scrutinyFileService.unhookPreviousScrutinyFile(itemScrutiny.getItemScrutinyId(),"FINAL","COMMSCR");
			if(itemScrutiny.getFinalScrutinyStatus().equalsIgnoreCase("APPROVED")){
				bidderService.updateStatus(AppBaseConstant.FINAL_COMMERCIAL_PASSED,itemScrutiny.getBidder().getBidderId());
				bidderStatusService.saveBidderStatus(itemScrutiny.getBidder(), 
				AppBaseConstant.FINAL_COMMERCIAL_PASSED,itemScrutiny.getFinalScrutinyComment());
			}
			else if(itemScrutiny.getFinalScrutinyStatus().equalsIgnoreCase("REJECTED")){
				bidderService.updateStatus(AppBaseConstant.FINAL_COMMERCIAL_FAILED,itemScrutiny.getBidder().getBidderId());
				bidderStatusService.saveBidderStatus(itemScrutiny.getBidder(), 
				AppBaseConstant.FINAL_COMMERCIAL_FAILED,itemScrutiny.getFinalScrutinyComment());
			}
			
			response.addObject("Status", "Final Scrutiny Updated Successfully");
			response.addObject("resultStatus",true);
		}else{
			response.addObject("Status", "Final Scrutiny not Updated/cannot approve if any point rejected");
			response.addObject("resultStatus",false);
		}
		
		return response;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto confirmTechicalScrutiny(ItemScrutinyDto itemScrutiny,ItemBidDto itemBid) {
		
		CustomResponseDto response=new CustomResponseDto();
		String status="";
		boolean result=itemScrutinyService.updateFinalScrutinyInfo(itemScrutiny);
		if(result){
			scrutinyFileService.unhookPreviousScrutinyFile(itemScrutiny.getItemScrutinyId(),"FINAL","TECHSCR");
			if(itemScrutiny.getFinalScrutinyStatus().equalsIgnoreCase("APPROVED")){
				 status=AppBaseConstant.SCRUTINY_PASSED;
				itemBidService.updateItemBidScrutinyStatus(status,itemBid);
				 bidderService.updateStatus( 
						AppBaseConstant.FINAL_TECHNICAL_PASSED,itemScrutiny.getBidder().getBidderId());
				bidderStatusService.saveBidderStatus(itemScrutiny.getBidder(), 
				AppBaseConstant.FINAL_TECHNICAL_PASSED,itemScrutiny.getFinalScrutinyComment());
			}
			else if (itemScrutiny.getFinalScrutinyStatus().equalsIgnoreCase("REJECTED")){
				status=AppBaseConstant.SCRUTINY_FAILED;
				itemBidService.updateItemBidScrutinyStatus(status,itemBid);
				bidderService.updateStatus(AppBaseConstant.FINAL_TECHNICAL_FAILED,itemScrutiny.getBidder().getBidderId());
				bidderStatusService.saveBidderStatus(itemScrutiny.getBidder(), 
				AppBaseConstant.FINAL_TECHNICAL_FAILED,itemScrutiny.getFinalScrutinyComment());
			}
			
			response.addObject("Status", "Final Scrutiny Updated Successfully");
			response.addObject("resultStatus",true);
		}else{
			response.addObject("Status", "Final Scrutiny not Updated/cannot approve if any point rejected");
			response.addObject("resultStatus",false);
		}
		return response;
		
	}

	@Override
	public CustomResponseDto getTenderList(Long tahdrId) {
    CustomResponseDto response=new CustomResponseDto();
		RoleDto roledto =contextService.getDefaultRole();
		String role=roledto.getValue();
		UserDto user=contextService.getUser();
		if(roledto!=null && user!=null){
			 List<TAHDRDetailDto> tahdrDetailList=new ArrayList<TAHDRDetailDto>();
			 TAHDRDetailDto tenderDetail=null;
			 Map<String, Object> params= new HashMap<>();
			  params.put("status", AppBaseConstant.TENDER_DEVIATION_OPENNING);
			  params.put("tahdrId", tahdrId);
					if(AppBaseConstant.ROLE_AUDITOR_USER.equals(role)){	
						  params.put("userId", user.getUserId());
						  tenderDetail=tAHDRDetailService.findDto("getTadhrDetailByStatusAndTahdrIdAndAuditorId",params);
					   }else{
						   tenderDetail=tAHDRDetailService.findDto("getMyTadhrDetailByStatusAndTahdrId",params);
					   }
			  tahdrDetailList.add(tenderDetail);	
			  response.addObject("tahdrDetailList", tahdrDetailList);   
		}
		Map<String, String> statusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		response.addObject("statusList", statusList);
		response.addObject("role", role);
		return response;
	}

	@Override
	public CustomResponseDto getTenderList(int pageNumber, int pageSize, String searchColumn, String searchValue,
			String typeCode) {
		CustomResponseDto response=new CustomResponseDto();
		 RoleDto role =contextService.getDefaultRole();
		 UserDto user=contextService.getUser();
		 Map<String, Object> newObjectMap= new HashMap<>();
		 Map<String, Object> objectMap = new HashMap<>();
		 long countResult = 0l;
		 if(role!=null && user!=null){
			 List<TAHDRDetailDto> tahdrDetailList=new ArrayList<TAHDRDetailDto>();
			 Map<String, Object> params= new HashMap<>();
			 params.put("status", AppBaseConstant.TENDER_DEVIATION_OPENNING);
			  params.put("typeCode", typeCode);
			 if(typeCode.equals("RA") || typeCode.equals("FA")){
					if(typeCode.equals("RA")){
						 params.put("typeCode", "PT");
					}
					if(AppBaseConstant.ROLE_AUDITOR_USER.equals(role.getValue())){
						 params.put("userId", user.getUserId());
						  tahdrDetailList=tAHDRDetailService.getPreliminaryScrutinyTender("getAuctionDetailByStatusAndAuditorId", params, pageNumber, pageSize, searchColumn, searchValue);
					   }else{
						   tahdrDetailList=tAHDRDetailService.getPreliminaryScrutinyTender("getAuctionDetailByStatus", params, pageNumber, pageSize, searchColumn, searchValue);
					   }
				}else{
					if(AppBaseConstant.ROLE_AUDITOR_USER.equals(role.getValue())){
						  params.put("userId", user.getUserId());
						  tahdrDetailList=tAHDRDetailService.getPreliminaryScrutinyTender("getTenderDetailByStatusAndAuditorId", params, pageNumber, pageSize, searchColumn, searchValue);
							
					   }else{
						   tahdrDetailList=tAHDRDetailService.getPreliminaryScrutinyTender("getTadhrDetailQueryByStatus", params, pageNumber, pageSize, searchColumn, searchValue);
					   }
				}
			 countResult=tAHDRDetailService.getPreliminaryScrutinyTenderCount(role.getValue(), typeCode, params,searchColumn,searchValue);
			  newObjectMap.put("tahdrDetailList", tahdrDetailList);
		}
		 int LastPage=(int) (countResult / pageSize);
		 LastPage = (int) ((LastPage)==1?LastPage:LastPage + 1);
		 newObjectMap.put("role", role.getValue());
		Map<String, String> statusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		 newObjectMap.put("statusList", statusList);
		 objectMap.put("LastPage", LastPage);
		 response.setData( newObjectMap);
		 response.setObjectMap(objectMap);
		return response;
	}

	@Override
	public CustomResponseDto getItemListBybidderId(Long bidderId) {
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("bidderId", bidderId);
		params.put("status1", AppBaseConstant.BIDDER_STATUS_DEVIATION_OPENED);
		params.put("status2", AppBaseConstant.SCRUTINY_PASSED);
		List<ItemBidDto> itemBidList=itemBidService.findDtos("getItemListByBidderIdAndStatus",params);
	
		Map<String, String> deviationTypeCode = refListService.getReferenceListMap(CoreReferenceConstants.RESPONSE_TYPE_CODE);
		response.addObject("ItemList", itemBidList);
		response.addObject("deviationTypeCode", deviationTypeCode);
		return response;
	}

	@Override
	public CustomResponseDto saveFinalTechnicalDeviationResponse(ItemScrutinyLineDto itemScrutinyLine) {
		CustomResponseDto response=new CustomResponseDto();
		if(itemScrutinyLine.getItemScrutinyLineId() != null){
			Map<String, Object> params= new HashMap<>();		
			params.put("itemScrutinyLineId", itemScrutinyLine.getItemScrutinyLineId());
			itemScrutinyLineService.updateFinalTechnicalDeviationResponseInfo(itemScrutinyLine);
			itemScrutinyService.resetFinalScrutinySatus(itemScrutinyLine.getItemScrutiny().getItemScrutinyId());
			itemScrutinyLine=itemScrutinyLineService.findDto("getItemScrtunyLineById", params);
			response.addObject("Status", "Final Scrutiny Updated Successfully,Please Re-Submit Final Comfirmation");
			response.addObject("itemScrutinyLine", itemScrutinyLine);
			response.addObject("resultStatus", true);
		}
		else{
			response.addObject("Status", "Final Scrutiny Updated Successfully");
			response.addObject("resultStatus", false);
		}
		return response;
	}

	@Override
	public CustomResponseDto saveFinalCommercialDeviationResponse(ItemScrutinyLineDto itemScrutinyLine) {
		CustomResponseDto response=new CustomResponseDto();
		if(itemScrutinyLine.getItemScrutinyLineId() != null){
			Map<String, Object> params= new HashMap<>();		
			params.put("itemScrutinyLineId", itemScrutinyLine.getItemScrutinyLineId());
			itemScrutinyLineService.updateFinalTechnicalDeviationResponseInfo(itemScrutinyLine);
			/*itemScrutinyService.resetFinalScrutinySatus(itemScrutinyLine.getItemScrutiny().getItemScrutinyId());*/
			itemScrutinyLine=itemScrutinyLineService.findDto("getItemScrtunyLineById", params);
			response.addObject("Status", "Final Scrutiny Updated Successfully");
			response.addObject("itemScrutinyLine", itemScrutinyLine);
			response.addObject("resultStatus", true);
		}
		else{
			response.addObject("Status", "Final Scrutiny Updated Successfully");
			response.addObject("resultStatus", false);
		}
		return response;
	}

	@Override
	public CustomResponseDto saveTechnicalFinalDocumentDeviationResponse(ItemScrutinyLineDto itemScrutinyLine) {
		CustomResponseDto response=new CustomResponseDto();
		if(itemScrutinyLine.getItemScrutinyLineId() != null){
			Map<String, Object> params= new HashMap<>();		
			params.put("itemScrutinyLineId", itemScrutinyLine.getItemScrutinyLineId());
			itemScrutinyLineService.updateFinalTechnicalDeviationResponseInfo(itemScrutinyLine);
			itemScrutinyService.resetFinalScrutinySatus(itemScrutinyLine.getItemScrutiny().getItemScrutinyId());
			itemScrutinyLine=itemScrutinyLineService.findDto("getItemScrtunyLineById", params);
			response.addObject("Status", "Final Scrutiny Updated Successfully,Please Re-Submit Final Comfirmation");
			response.addObject("itemScrutinyLine", itemScrutinyLine);
			response.addObject("resultStatus", true);
		}
		else{
			response.addObject("Status", "Final Scrutiny Not Updated");
			response.addObject("resultStatus",false);
		}
		return response;
	}

	@Override
	public CustomResponseDto saveCommercialFinalDocumentDeviationResponse(ItemScrutinyLineDto itemScrutinyLine) {
		CustomResponseDto response=new CustomResponseDto();
		if(itemScrutinyLine.getItemScrutinyLineId() != null){
			Map<String, Object> params= new HashMap<>();		
			params.put("itemScrutinyLineId", itemScrutinyLine.getItemScrutinyLineId());
			itemScrutinyLineService.updateFinalCommercialDeviationResponseInfo(itemScrutinyLine);
		/*	itemScrutinyService.resetPreliminaryScrutinySatus(itemScrutinyLine.getItemScrutiny().getItemScrutinyId());*/
			itemScrutinyLine=itemScrutinyLineService.findDto("getItemScrtunyLineById", params);
			response.addObject("Status", "Final Scrutiny Updated Successfully");
			response.addObject("itemScrutinyLine", itemScrutinyLine);
			response.addObject("resultStatus", true);
		}
		else{
			response.addObject("Status", "Final Scrutiny Not Updated");
			response.addObject("resultStatus",false);
		}
		return response;
	}

	@Override
	public CustomResponseDto confirmFinalTechnicalScrutiny(ItemScrutinyDto itemScrutiny) {
    CustomResponseDto response=new CustomResponseDto();
		if(itemScrutiny.getItemScrutinyId() != null){
			Map<String, Object> params= new HashMap<>();		
			params.put("itemBidId", itemScrutiny.getItemBid().getItemBidId());
			ItemBidDto itemBid=itemBidService.findDto("getItemBidWithBidder",params);
			
			if(itemBid!=null){
				Map<String, Object> map= new HashMap<>();		
				map.put("itemScrutinyId", itemScrutiny.getItemScrutinyId());
				map.put("scrutinyType", "TECHSCR");
				List<ItemScrutinyLineDto> isAllDeviationScrutinized=itemScrutinyLineService.findDtos("getNonScrutinizedDeviationFinalList", map);
				if(CommonUtil.isCollectionEmpty(isAllDeviationScrutinized)){
					response=confirmTechicalScrutiny(itemScrutiny, itemBid);
				}else{
					response.addObject("Status", "Few final Technical Scrutiny is pending");
					response.addObject("resultStatus", false);
				}
			}else{
				response.addObject("Status", "Something went wrong !");
				response.addObject("resultStatus", false);
			}
		}
		else{
			response.addObject("Status", "Something went wrong!");
			response.addObject("resultStatus", false);
		}
		return response;
	}

	@Override
	public CustomResponseDto confirmFinalCommercialScrutiny(ItemScrutinyDto itemScrutiny) {
		CustomResponseDto response=new CustomResponseDto();
		if(itemScrutiny.getItemScrutinyId() != null){
			Map<String, Object> map= new HashMap<>();		
			map.put("itemScrutinyId", itemScrutiny.getItemScrutinyId());
			map.put("scrutinyType", "COMMSCR");
			List<ItemScrutinyLineDto> isAllDeviationScrutinized=itemScrutinyLineService.findDtos("getNonScrutinizedDeviationFinalList", map);
			if(CommonUtil.isCollectionEmpty(isAllDeviationScrutinized)){
				response=confirmCommercialScrutiny(itemScrutiny);
			}else{
				response.addObject("Status", "Few final Commercial Scrutiny is pending");
				response.addObject("resultStatus",false);
			}
		}
		else{
			response.addObject("Status", "Something went Wrong !");
			response.addObject("resultStatus",false);
		}
		return response;
	}

	@Override
	public CustomResponseDto getFinalScrutinyStatus(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		TAHDRDto tahdr=tAHDRService.findDto(tahdrId);
		if(tahdr!=null){
			Map<String, Object> params= new HashMap<>();		
			params.put("tahdrId", tahdrId);
			List<ItemScrutinyLineDto> itemScrutinyLineDtoList=itemScrutinyLineService.findDtos("getAuditingStatusBytahdrId",params);
			if(CommonUtil.isCollectionEmpty(itemScrutinyLineDtoList)){
				response.addObject("result", true);
				response.addObject("resultObject", tahdr);
			}else{
				response.addObject("result", false);
				response.addObject("resultMessage", "Auditing has not completed!");
			}
			
		}else{
			response.addObject("result", false);	
			response.addObject("resultMessage", "Tender not Found");
		}
		return response;
	}

	@Override
	public CustomResponseDto confirmFinalScrutiny(TAHDRDto tahdrDto) {
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> map= new HashMap<>();		
		map.put("tahdrId", tahdrDto.getTahdrId());
		List<ItemScrutinyLineDto> nonScrutinizedBidder=itemScrutinyLineService.findDtos("getNonFinalScrutinizedBidderByTahdrId", map);
		if(CommonUtil.isCollectionEmpty(nonScrutinizedBidder)){
			Map<String, Object> newMap= new HashMap<>();		
			newMap.put("tahdrId", tahdrDto.getTahdrId());
			List<ItemScrutinyDto> notSubmittedScrutinyList=itemScrutinyService.findDtos("getNotUploadedScrutinyFileByTahdrId", newMap);
			if(CommonUtil.isCollectionEmpty(notSubmittedScrutinyList)){
				if(tahdrDto.getTahdrId() != null){
					Map<String, Object> params= new HashMap<>();		
					/*params.put("tahdrId", tahdrDto.getTahdrId());*/
					params.put("tahdrStatusCode",AppBaseConstant.SCRUTINY_FINISHED );
					int result=tAHDRService.updateByJpql(params, "tahdrId", tahdrDto.getTahdrId());
					if(result>0){
						
						/*Map<Long, String> bidderScrutinyStatus=bidderService.getBidderFinalStatus(tahdrDto.getTahdrId(),"FINAL");*/
						/*bidderService.setBidderStatus(bidderScrutinyStatus);*/
						
						sendFinalStatusToBidder(tahdrDto.getTahdrId(),tahdrDto.getTahdrCode());
						response.addObject("Status", true);;
						response.addObject("resultMessage", "Final Scrutiny  Is Complete");
					}
					else{
						response.addObject("Status", false);
						response.addObject("resultMessage", "Error in Updating Tender");
					}
				}
				else{
					response.addObject("Status", false);
					response.addObject("resultMessage", "Invalid Tender");
				}	
			}else{
				response.addObject("Status", false);
				response.addObject("resultMessage", "For Few Bidder Final Scrutiny File Not Uploaded");
			}
		}
		else{
			response.addObject("Status", false);
			response.addObject("resultMessage", "Final Scrutiny of Few deviation is not done");
		}
		return response;
	}

	@Override
	public CustomResponseDto saveAuditorComment(ItemScrutinyLineDto dto) {
		CustomResponseDto response =new CustomResponseDto();
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		
		/* Map<String,Object> map= new HashMap<>();*/
    	 /*int result=0;*/
		 boolean result=false;
 		if(AppBaseConstant.ROLE_AUDITOR_USER.equals(userRole)){
 			if(dto.getItemScrutinyLineId() != null){
 				/* map.put("finalAuditorStatus",dto.getFinalAuditorStatus());
 		 		 map.put("finalAuditorComment",dto.getFinalAuditorComment());
 				 result =itemScrutinyLineService.updateByJpql(map, "itemScrutinyLineId",dto.getItemScrutinyLineId());*/
 				result=itemScrutinyLineService.updateFinalScrutinyAuditorResponseInfo(dto);
 				 if(result){
 					 response.addObject("resultMessage", "Auditor Comment Saved Successfully");
 					 response.addObject("resultStatus", true);
 				 }else{
 					 response.addObject("resultMessage", "Auditor Comment Not Updated");
 					 response.addObject("resultStatus", false);
 				 }
 			}
 			else{
 				response.addObject("resultMessage", "Auditor Comment Not Updated");
 				response.addObject("resultStatus", false);
 			}
 		}
 		else{
				response.addObject("resultMessage", "Authorization Denied");
				response.addObject("resultStatus", false);
		}
		return response;
	}

	@Override
	public CustomResponseDto confirmAuditorComment(ItemScrutinyDto dto) {
		CustomResponseDto response =new CustomResponseDto();
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		
		 Map<String,Object> map= new HashMap<>();
		 int result=0;
		 if(AppBaseConstant.ROLE_AUDITOR_USER.equals(userRole)){
	 			if(dto.getItemScrutinyId() != null && dto.getBidder()!=null){
	 				 map.put("finalAuditorStatus",dto.getFinalAuditorStatus());
	 				 map.put("finalAuditorComment",dto.getFinalAuditorComment());
	 				 map.put("isFinalAudited",dto.getIsFinalAudited());
	 				 map.put("itemScrutinyId",dto.getItemScrutinyId());
	 		 		 
	 		 		Map<String,Object> params= new HashMap<>();
	 		 		params.put("bidderId", dto.getBidder().getBidderId());
	 		 		
	 		 		List<ItemScrutinyLineDto> nonAuditedList=itemScrutinyLineService.findDtos("getNonFinalAuditedItemScrtunyLineByBidderId", params); 
	 		 		 if(CommonUtil.isCollectionEmpty(nonAuditedList) ){
	 		 			List<ItemScrutinyLineDto> clarificationList=itemScrutinyLineService.findDtos("getFinalClarificationItemScrtunyLineByBidderId", params); 
	 		 			 if(CommonUtil.isCollectionEmpty(clarificationList) 
	 		 					 && dto.getFinalAuditorStatus().toUpperCase().equals("APPROVED")){
	 		 				result =itemScrutinyService.updateByJpql(map, "itemScrutinyId",dto.getItemScrutinyId());
	 	 	 				 if(result>0){
	 	 	 					 response.addObject("resultMessage", "Auditor Final Comment (Approve) Comfirmed Successfully");
	 	 	 					 response.addObject("resultStatus", true);
	 	 	 				 }else{
	 	 	 					 response.addObject("resultMessage", "Auditor FinalComment Not Comfirmed");
	 	 	 					 response.addObject("resultStatus", false);
	 	 	 				 } 
	 		 			 }else if(!CommonUtil.isCollectionEmpty(clarificationList) && dto.getFinalAuditorStatus().toUpperCase().equals("CLARIFICATION")){
	 		 				result =itemScrutinyService.updateByJpql(map, "itemScrutinyId",dto.getItemScrutinyId());
		 	 				 if(result>0){
		 	 					 response.addObject("resultMessage", "Auditor Final Comment (Clarification) Comfirmed Successfully");
		 	 					 response.addObject("resultStatus", true);
		 	 					 /*commercialStatusMail( dto.getItemScrutinyId());*/
		 	 					 
		 	 				 }else{
		 	 					 response.addObject("resultMessage", "Auditor Final Comment Not Comfirmed");
		 	 					 response.addObject("resultStatus", false);
		 	 				 } 
	 		 			 }else{
	 		 				 response.addObject("resultMessage", "For Approval or Clarification All Parameter Should be Approved or Any one is Clarify respectively");
	 		 				 response.addObject("resultStatus", false);
	 		 			 }
	 		 			
	 		 		 }else{
	 		 			 response.addObject("resultMessage", "Final Auditing for few parameter is pending");
		 				 response.addObject("resultStatus", false);
		 				 }
	 		 		 }else{
	 	 				response.addObject("resultMessage", "Something went wrong");
	 	 				response.addObject("resultStatus", false);
	 	 			}
	 			}
 		/*if(AppBaseConstant.ROLE_AUDITOR_USER.equals(userRole)){
 			if(dto.getItemScrutinyId() != null){
 				
 				result=itemScrutinyService.updateFinalScrutinyAuditorResponseInfo(dto);
 				 if(result){
 			
 					 
 					 response.addObject("resultMessage", "Auditor Final Comment Comfirmed Successfully");
 					 response.addObject("resultStatus", true);
 				 }else{
 					 response.addObject("resultMessage", "Auditor Final  Comment Not Comfirmed");
 					 response.addObject("resultStatus", false);
 				 }
 			}
 			else{
 				response.addObject("resultMessage", "Auditor Final Comment Not Updated");
 				response.addObject("resultStatus", false);
 			}
 		}*/
 		else{
				response.addObject("resultMessage", "Authorization Denied");
				response.addObject("resultStatus", false);
			}
		return response;
	}

	@Override
	public CustomResponseDto intimidateFinalAuditing(TAHDRDto tahdr) {
		CustomResponseDto response=new CustomResponseDto();
		boolean result=false;
		if(tahdr!=null){
				if(tahdr.getTahdrCode()!=null || tahdr.getTahdrId()!=null){
					Map<String, Object> params= new HashMap<>();		
					params.put("tahdrId", tahdr.getTahdrId());
					List<ItemScrutinyDto> finalNotAuditedList=itemScrutinyService.findDtos("getFinalNotAuditedList", params);
					if(CommonUtil.isCollectionEmpty(finalNotAuditedList)){
			            Map<String, Object> newParam= new HashMap<>();
			            newParam.put("isAuditing", "N");
						int resultCount=tahdrService.updateByJpql(newParam, "tahdrId", tahdr.getTahdrId());
						if(resultCount>0){
							result=intimidatingAuditingMail(tahdr.getTahdrCode(),tahdr.getTahdrId());
							if(result){
								response.addObject("resultStatus", true);
								response.addObject("resultMessage", "Scrutiny Engr are notified Successfully ! ");
							}else{
								response.addObject("resultStatus", false);
								response.addObject("resultMessage", "Cannot Notify Scrutiny Engr! ");
							}
						}else{
							response.addObject("resultStatus", false);
							response.addObject("resultMessage", "Cannot Notify Scrutiny Engr,As Auditing Flag is not saved in tender ! ");
						}
					}else{
						response.addObject("resultStatus", false);
						response.addObject("resultMessage", "Final Auditing is pending for few commercial parameter");
					}
				}else{
					response.addObject("resultStatus", false);
					response.addObject("resultMessage", "Something went wrong !");
				}
		}else{
			response.addObject("resultStatus", false);
			response.addObject("resultMessage", "Something went wrong !");
		}
	    return response;
	}
	
	private boolean intimidatingAuditingMail(String tahdrName,Long tahdrId){
		 boolean result=false;
		 if(!tahdrName.equals("") || tahdrId!=null){
			 List<String> emailList=itemScrutinyService.getScrutinyEngrEmailList(tahdrId);
			 String sub="Final Scrutiny Auditing ";
			 String message="<p>Hi </p><br><h1><p>"
			 		+ "The Final Scrutiny of Tender/Auction: <b>: "+tahdrName+"</b> has been re-scrutunized by Auditor."
			 		+ "Kindly Check the same for Respective Tender. </h1><p> "
			 		+ "<br><br><p><h1> Regards,<br><b> Mahavitran State Electricity Board</b></h1><p> ";
			 result=sendMail(emailList, sub, message);
			 return result;
		 } 
		 return result;
	 }

	@Override
	public CustomResponseDto notifyAuditor(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		if(tahdrId!=null){
			Map<String, Object> params= new HashMap<>();
			params.put("tahdrId", tahdrId);
			TAHDRDto tender=tAHDRService.findDto("getAuditoUserBytahdrId", params);
			if(tender!=null){
				Long auditorId=tender.getAuditorUser()==null?0:tender.getAuditorUser().getUserId();
				UserDto user=userService.findDto(auditorId);
				if(user!=null){
					List<ItemScrutinyLineDto> itemScrutinyLineDtoList=itemScrutinyLineService.findDtos("getAuditingStatusBytahdrId",params);
					if(!CommonUtil.isCollectionEmpty(itemScrutinyLineDtoList)){
						Map<String, Object> paramMap= new HashMap<>();
						paramMap.put("isAuditing", "Y");
						int resultCount=tahdrService.updateByJpql(paramMap, "tahdrId", tahdrId);
						if(resultCount>0){
							String tahdrName=tender.getTahdrCode()==null?" ":tender.getTahdrCode();
							String sub="Final Scrutiny Auditing ";
							 String auditorName=user.getName()==null?" ":user.getName();
							 String message="<p>Hi "+auditorName.toUpperCase()+"</p><br><h1><p>"
							 		+ "The Final Scrutiny of Tender/Auction: <b>"+tahdrName+"</b> has been re-scrutunized by SCRUTINY ENGR in Final Scrutiny."
							 	    + "Kindly re-audit Final Commercial Scrutiny Of all Bidder of Respective Tender. </h1>"
							 		+ "<p><br><br><p><h1> Regards,<br><b> Mahavitran State Electricity Board</b></h1><p> ";
							 sendMail(user.getEmail(), sub, message);
							response.addObject("resultStatus", true);
							response.addObject("resultMessage", auditorName+" has been notified Successfully!");
						}else{
							response.addObject("resultStatus", false);
							response.addObject("resultMessage", "Auditor has not been notified!");
						}
					}else{
						response.addObject("resultStatus", false);
						response.addObject("resultMessage", "No Need to Notify,Everything is Fine !");
					}
				}else{
					response.addObject("resultStatus", false);
					response.addObject("resultMessage", "Auditor not found to notify !");
				}
			}else{
				response.addObject("resultStatus", false);
				response.addObject("resultMessage", "Tender not found to notify respective Auditor !");
			}
		}else{
			response.addObject("resultStatus", false);
			response.addObject("resultMessage", "Something went wrong !");
		}
	  return response;
	}
	
	public boolean sendMail(List<String> emailList,String sub,String message){
		if(!CommonUtil.isCollectionEmpty(emailList)){
			MailDto mailDto = new MailDto();
			mailDto.setSubject(sub);
			mailDto.setMailContent(message);
			mailDto.setRecipientList(emailList);
			mailService.sendEmail(mailDto,true);
			return true;
		}
		return false;
	}
	
	public boolean sendMail(String email,String sub,String message){
		if(!"".equals(email)){
			MailDto mailDto = new MailDto();
			mailDto.setSubject(sub);
			mailDto.setMailContent(message);
			mailDto.setSingleRecipient(email);
			mailService.sendSingleEmailWithResult(mailDto,true);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean sendFinalStatusToBidder(Long tahdrId,String tenderNo) {
		boolean result=false;
		Map<String, Object> params= new HashMap<>();
		params.put("tahdrId", tahdrId);
		params.put("scrutinyType", "TECHSCR");
		List<ItemScrutinyDto> getAllTechItemScrutiny=itemScrutinyService.findDtos("getItemScrutinyByTahdrId", params);
		params.put("scrutinyType", "COMMSCR");
		List<ItemScrutinyDto> getAllCommItemScrutiny=itemScrutinyService.findDtos("getItemScrutinyByTahdrId", params);
		StringBuilder messageString=new StringBuilder("<p>Hi</p><br><h1><p>The Final Scrutiny of Tender/Auction: <b>'"+tenderNo+"'</b> has been completed.</h1></p>");
		Map<String, StringBuilder> mailMap= new HashMap<>();
		if(!CommonUtil.isCollectionEmpty(getAllTechItemScrutiny)){
			messageString.append("<br>Technical Scrutiny Result:");
			for(ItemScrutinyDto dto:getAllTechItemScrutiny){
				if(dto!=null){
					String status=dto.getFinalScrutinyStatus()==null?"Not_Final _SCRUTINIZED":dto.getFinalScrutinyStatus();
					String scrutinyType=dto.getScrutinyType();
					String mailId=dto.getBidder()==null?"":dto.getBidder().getCreatedBy()==null?"":
						dto.getBidder().getCreatedBy().getEmail();
					if(scrutinyType.equals("TECHSCR")){
												String itemName=dto.getItemBid()==null?"":dto.getItemBid().getTahdrMaterial()==null?"":
							dto.getItemBid().getTahdrMaterial().getMaterial()==null?"":dto.getItemBid().getTahdrMaterial().getMaterial().getName();
						StringBuilder itemStatus= new StringBuilder(" <br> "+itemName+" : "+status+".");
						if(mailMap.containsKey(mailId)){
							StringBuilder oldMsg=new StringBuilder(mailMap.get(mailId));
							oldMsg.append(itemStatus);
							mailMap.put(mailId, oldMsg);
						}else{
							StringBuilder newMsg=new StringBuilder(messageString);
							newMsg.append(itemStatus);
							mailMap.put(mailId, newMsg);
						}
					}
				}
			}
			}
			if(!CommonUtil.isCollectionEmpty(getAllCommItemScrutiny)){
				for(ItemScrutinyDto dto:getAllCommItemScrutiny){
					if(dto!=null){
						String status=dto.getFinalScrutinyStatus()==null?"Not_Final _SCRUTINIZED":dto.getFinalScrutinyStatus();
						String scrutinyType=dto.getScrutinyType();
						String mailId=dto.getBidder()==null?"":dto.getBidder().getCreatedBy()==null?"":
							dto.getBidder().getCreatedBy().getEmail();
						 if(scrutinyType.equals("COMMSCR")){
							 StringBuilder itemStatus= new StringBuilder("<p><br>Commercial Scrutiny Result : "+status+".</p>");
							 if(mailMap.containsKey(mailId)){
									StringBuilder msg=new StringBuilder(mailMap.get(mailId));
									msg.append(itemStatus);
									mailMap.put(mailId, msg);
								}else{
									mailMap.put(mailId, itemStatus);
								}
						}
					}
				}
			}
			if(!CommonUtil.isCollectionEmpty(getAllCommItemScrutiny) || !CommonUtil.isCollectionEmpty(getAllTechItemScrutiny)){
				String sub="Tender Final Scrutiny";
				result=sendFinalStatusToBidder(mailMap,sub);
			}
		return result;
	}
	
	private boolean sendFinalStatusToBidder(Map<String,StringBuilder> mailMap,String sub){
		boolean result=bidderService.sendCustomMailToAllBidder(mailMap, sub);
		return result;
	}

	@Override
	public ItemScrutinyDto getTechnicalScrutinyDeviation(Long itemBidId) {
		return itemScrutinyService.findDto("getItemscrutinyByItemBidId", AbstractContextServiceImpl.getParamMap("itemBidId", itemBidId));
	}

	@Override
	public ItemScrutinyDto getCommercialScrutinyDeviation(Long bidderId) {
		return itemScrutinyService.findDto("getItemscrutinyByBidderId", AbstractContextServiceImpl.getParamMap("bidderId", bidderId));
	}


}
