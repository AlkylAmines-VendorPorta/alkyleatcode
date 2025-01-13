package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ItemScrutinyLineDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.BidderStatusService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.ItemScrutinyLineService;
import com.novelerp.eat.service.ItemScrutinyService;
import com.novelerp.eat.service.PreliminaryScrutinyService;
import com.novelerp.eat.service.SMSService;
import com.novelerp.eat.service.ScrutinyFileService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TechnicalBidService;
import com.novelerp.eat.validator.TAHDRDatesValidator;

@Service
public class PreliminaryScrutinyServiceImpl implements PreliminaryScrutinyService{
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private TAHDRDetailService tAHDRDetailService;
	
	@Autowired
	private ItemScrutinyLineService itemScrutinyLineService;
	
	@Autowired
	private TAHDRDatesValidator tahdrDatesValidation;
	
	@Autowired
	private BidderStatusService bidderStatusService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private ItemScrutinyService itemScrutinyService;
	
	@Autowired
	private TechnicalBidService technicalBidService;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private SMSService smsService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private ScrutinyFileService scrutinyFileService;
	
	@Autowired
	private BPartnerService partnerService;
	
	@Override
	public CustomResponseDto getMyTender(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		 RoleDto role =contextService.getDefaultRole();
		 UserDto user=contextService.getUser();
		 if(role!=null && user!=null){
			 List<TAHDRDetailDto> tahdrDetailList=new ArrayList<TAHDRDetailDto>();
			 TAHDRDetailDto tenderDetail=null;
			 Map<String, Object> params= new HashMap<>();
			  params.put("tahdrId", tahdrId);
					if(AppBaseConstant.ROLE_AUDITOR_USER.equals(role.getValue())){
						  params.put("userId", user.getUserId());
						  tenderDetail=tAHDRDetailService.findDto("getTadhrDetailByTahdrIdAndAuditorId",params);
							
					   }else{
						   tenderDetail=tAHDRDetailService.findDto("getMyTadhrDetailByTahdrId",params);
							
					   }
			tahdrDetailList.add(tenderDetail);
			response.addObject("tahdrDetailList", tahdrDetailList);   
		}
		Map<String, String> statusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		response.addObject("statusList", statusList);
		response.addObject("role", role.getValue());
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
			  params.put("status", AppBaseConstant.DOCUMENT_STATUS_TC_BID_OPENED);
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
	public CustomResponseDto getBidderListByTahdrDetailId(Long tahdrDetailId) {
		CustomResponseDto response=new CustomResponseDto();
		RoleDto role =contextService.getDefaultRole();
		Map<String, Object> params= new HashMap<>();		
		params.put("tahdrDetailId", tahdrDetailId);
		List<BidderDto> bidderList=new ArrayList<BidderDto>();
		if(role.getValue()!=null && AppBaseConstant.ROLE_AUDITOR_USER.equals(role.getValue())){
			bidderList=bidderService.findDtos("getBiddersForAuditing",params);
		}
		else{
			bidderList=bidderService.findDtos("getBiddersForScrutiny",params);
		}
		Map<String, String> deviationTypeCode = refListService.getReferenceListMap(CoreReferenceConstants.RESPONSE_TYPE_CODE);
		Map<String, String> bidderStatusList = refListService.getReferenceListMap(AppBaseConstant.BIDDER_STATUS);
		response.addObject("bidderList", bidderList);
		response.addObject("deviationTypeCode", deviationTypeCode);
		response.addObject("role", role.getValue());
		response.addObject("bidderStatusList", bidderStatusList);
		return response;
	}

	@Override
	public CustomResponseDto getItemListBybidderId(Long bidderId) {
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("bidderId", bidderId);
		RoleDto role=contextService.getDefaultRole();
		List<TechnicalBidDto> itemBidList=new ArrayList<TechnicalBidDto>();
		if(role.getValue().equals("VENADM")){
			params.put("status1", AppBaseConstant.BIDDER_STATUS_DEVIATION_SUBMITTED);
			params.put("status2", AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED);
			itemBidList=technicalBidService.findDtos("getDeviationTechnicalBidByBidderIdAndStatus",params);
		}
		else{
			params.put("status1", AppBaseConstant.TENDER_TECHNO_COMMERCIAL_OPENNING);
			params.put("status2", AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED);
			itemBidList=technicalBidService.findDtos("getTechnicalBidByBidderIdAndStatus",params);
		}
		Map<String, String> deviationTypeCode = refListService.getReferenceListMap(CoreReferenceConstants.RESPONSE_TYPE_CODE);
		response.addObject("ItemList", itemBidList);
		response.addObject("deviationTypeCode", deviationTypeCode);
		return response;
	}

	@Override
	public boolean sendPreliminaryStatusToBidder(Long tahdrId,String tenderNo) {
		boolean result=false;
		Map<String, Object> params= new HashMap<>();
		params.put("tahdrId", tahdrId);
		params.put("scrutinyType", "TECHSCR");
		List<ItemScrutinyDto> getAllTechItemScrutiny=itemScrutinyService.findDtos("getItemScrutinyByTahdrId", params);
		params.put("scrutinyType", "COMMSCR");
		List<ItemScrutinyDto> getAllCommItemScrutiny=itemScrutinyService.findDtos("getItemScrutinyByTahdrId", params);
		StringBuilder messageString=new StringBuilder("<p>Hi</p><br><h1><p>Preliminary Scrutiny of 'Tender: "+tenderNo+"'  has been completed.</h1></p>");
		Map<String, StringBuilder> mailMap= new HashMap<>();
		if(!CommonUtil.isCollectionEmpty(getAllTechItemScrutiny)){
			messageString.append("<br>Technical Scrutiny Result:");
			for(ItemScrutinyDto dto:getAllTechItemScrutiny){
				if(dto!=null){
					String status=dto.getPreliminaryScrutinyStatus();
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
						String status=dto.getPreliminaryScrutinyStatus();
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
				String sub="Tender Preliminary Scrutiny";
				result=sendPreliminaryStatusToBidder(mailMap,sub);
			}
		return result;
	}
	
	private boolean sendPreliminaryStatusToBidder(Map<String,StringBuilder> mailMap,String sub){
		boolean result=bidderService.sendCustomMailToAllBidder(mailMap, sub);
		return result;
	}

	@Override
	public CustomResponseDto savePreliminaryTechnicalStatus(ItemScrutinyDto newItemScrutinyDto) {
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("itemBidId", newItemScrutinyDto.getItemBid().getItemBidId());
		
		ItemBidDto itemBid=itemBidService.findDto("getItemBidWithBidder",params);
		Map<String, Object> map= new HashMap<>();
		map.put("bidderId", itemBid.getBidder().getBidderId());
		
		
		BidderDto bidder=bidderService.findDto("getQueryForMailByBidderId", map);
		String statusResult="";
		
		String tenderNo=bidder==null?"Tender_NULL":bidder.getTahdr()==null?"":bidder.getTahdr().getTahdrCode();
		boolean isAuditing=bidder==null?false:bidder.getTahdr()==null?false:bidder.getTahdr().getIsAuditing()==null?false:
			bidder.getTahdr().getIsAuditing().equalsIgnoreCase("Y")?true:false;
		if(!isAuditing){
			if(newItemScrutinyDto.getItemScrutinyId() != null && itemBid!=null){ 
				boolean updateItemScrutiny= itemScrutinyService.savePreliminarySatus(newItemScrutinyDto);
				if(updateItemScrutiny){
					scrutinyFileService.unhookPreviousScrutinyFile(newItemScrutinyDto.getItemScrutinyId(),"PRELIMINARY","TECHSCR");
					if(newItemScrutinyDto.getPreliminaryScrutinyStatus().toUpperCase().equals("REJECTED")){
						String status=AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED;
						statusResult="REJECTED";
						technicalBidService.updateTechnicalBidStatus(status,newItemScrutinyDto.getItemBid().getItemBidId());
						Long itemBidId=itemBid.getItemBidId();
						itemBidService.updateItemBidStatus(status,itemBidId);
						bidderService.updateBidderPreliminaryScrutinyStatus(status, bidder);
						bidderStatusService.saveBidderStatus(newItemScrutinyDto.getBidder(), 
								status, newItemScrutinyDto.getPreliminaryScrutinyComment());
						
					}
					else if(newItemScrutinyDto.getPreliminaryScrutinyStatus().toUpperCase().equals("APPROVED")){
						String status=AppBaseConstant.PRELIMINARY_TECHNICAL_PASSED;
						statusResult="APPROVED";
						technicalBidService.updateTechnicalBidStatus(status,newItemScrutinyDto.getItemBid().getItemBidId());
						itemBidService.updateItemBidScrutinyStatus(status,itemBid);
					    bidderService.updateBidderPreliminaryScrutinyStatus(status, bidder);
						bidderStatusService.saveBidderStatus(newItemScrutinyDto.getBidder(), 
								status,newItemScrutinyDto.getPreliminaryScrutinyComment());
					}
					else if(newItemScrutinyDto.getPreliminaryScrutinyStatus().toUpperCase().equals("DEVIATION")){
						String status=AppBaseConstant.DEVIATION_CALLED;
						statusResult="CALLED FOR DEVIATION";
						technicalBidService.updateTechnicalBidStatus(status,newItemScrutinyDto.getItemBid().getItemBidId());
						itemBidService.updateItemBidScrutinyStatus(status,itemBid);
						bidderService.updateBidderPreliminaryScrutinyStatus(status, bidder);
						bidderStatusService.saveBidderStatus(newItemScrutinyDto.getBidder(), 
								status,newItemScrutinyDto.getPreliminaryScrutinyComment());
					}
					
					Map<String, String> smsParam = new HashMap<String, String>();
					smsParam.put(AppBaseConstant.SMS_PARAMETER_1, tenderNo.replaceAll(" ", "%20"));
					smsParam.put(AppBaseConstant.SMS_PARAMETER_2, statusResult.replaceAll(" ", "%20"));
					if(bidder.getCreatedBy() != null )
						if(bidder.getCreatedBy().getUserDetails() != null)
							smsService.sendSMS(bidder.getCreatedBy().getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_PRELIMINARY_SCRUTIINY_COMPLETED, smsParam);

					/*String a=bidderService.getBidderUpdatedScrutinyStatus(bidder.getBidderId());*/
					
					bidder=bidderService.findDto(bidder.getBidderId());
					response.addObject("bidderStatus", bidder.getStatus());
					response.addObject("ItemScrutiny", newItemScrutinyDto);
					response.addObject("resultStatus", true);
					response.addObject("bidderStatus", bidder.getStatus());
					response.addObject("result", "Confirmation Status Updated Succesfully,Please Re-Submit Scrutiny File");
				}else{
					String reasonMsg="Status not Updated";
					if(newItemScrutinyDto.getPreliminaryScrutinyStatus().toUpperCase().equals("APPROVED")){
						reasonMsg="Cannot submit Approve as all parameter is not Approved";
					}else if(newItemScrutinyDto.getPreliminaryScrutinyStatus().toUpperCase().equals("DEVIATION")){
						reasonMsg="Cannot submit Deviation as all parameter is Approved";
					}
					response.addObject("result", reasonMsg);
					response.addObject("resultStatus", false);
				}
			}else{
				response.addObject("resultStatus", false);
				response.addObject("result", "Status not Updated");
			}
		}else{
			response.addObject("resultStatus", false);
			response.addObject("result", "Cannot save,Scrutiny is under Auditing");
		}
		return response;
	}

	@Override
	public CustomResponseDto savePreliminaryCommercialStatus(ItemScrutinyDto newItemScrutinyDto) {
		CustomResponseDto response=new CustomResponseDto();
		Long bidderId=newItemScrutinyDto.getBidder().getBidderId();
		Map<String, Object> map= new HashMap<>();
		map.put("bidderId", bidderId);
		BidderDto bidder=bidderService.findDto("getQueryForMailByBidderId", map);
		/*BidderDto bidder=bidderService.findDto(bidderId);*/
		boolean isAuditing=bidder==null?false:bidder.getTahdr()==null?false:bidder.getTahdr().getIsAuditing()==null?false:
			bidder.getTahdr().getIsAuditing().equalsIgnoreCase("Y")?true:false;
		if(!isAuditing){
			if(newItemScrutinyDto.getItemScrutinyId() != null ){ 
				boolean updateItemScrutiny= itemScrutinyService.savePreliminarySatus(newItemScrutinyDto);
				if(updateItemScrutiny){
					scrutinyFileService.unhookPreviousScrutinyFile(newItemScrutinyDto.getItemScrutinyId(),"PRELIMINARY","COMMSCR");
					if(newItemScrutinyDto.getPreliminaryScrutinyStatus().toUpperCase().equals("REJECTED")){
						String status=AppBaseConstant.PRELIMINARY_COMMERCIAL_FAILED;
						bidderService.updateStatus(status, bidderId);
						bidderStatusService.saveBidderStatus(newItemScrutinyDto.getBidder(), 
								status,newItemScrutinyDto.getPreliminaryScrutinyComment());
						
					}
					else if(newItemScrutinyDto.getPreliminaryScrutinyStatus().toUpperCase().equals("APPROVED")){
						String status=AppBaseConstant.PRELIMINARY_COMMERCIAL_PASSED;
						bidderService.updateBidderPreliminaryScrutinyStatus(status, bidder);
						bidderStatusService.saveBidderStatus(newItemScrutinyDto.getBidder(), 
								status,newItemScrutinyDto.getPreliminaryScrutinyComment());
						
					}
					else if(newItemScrutinyDto.getPreliminaryScrutinyStatus().toUpperCase().equals("DEVIATION")){
						String status=AppBaseConstant.DEVIATION_CALLED;
						bidderService.updateBidderPreliminaryScrutinyStatus(status, bidder);
						bidderStatusService.saveBidderStatus(newItemScrutinyDto.getBidder(), 
								status,newItemScrutinyDto.getPreliminaryScrutinyComment());
					}
					/*String a=bidderService.getBidderUpdatedScrutinyStatus(bidder.getBidderId());*/
					
					bidder=bidderService.findDto(bidderId);
					response.addObject("bidderStatus", bidder.getStatus());
					response.addObject("ItemScrutiny", newItemScrutinyDto);
					response.addObject("result", "Comfirmation Status Updated Succesfully,Please Re-Submit Scrutiny File");
					response.addObject("resultStatus", true);
				}
				else{
					String reasonMsg="Status not Updated";
					if(newItemScrutinyDto.getPreliminaryScrutinyStatus().toUpperCase().equals("APPROVED")){
						reasonMsg="Cannot submit Approve as all parameter is not Approved";
					}else if(newItemScrutinyDto.getPreliminaryScrutinyStatus().toUpperCase().equals("DEVIATION")){
						reasonMsg="Cannot submit Deviation as all parameter is Approved";
					}
					response.addObject("result", reasonMsg);
					response.addObject("resultStatus", false);
				}
			}else{
				response.addObject("result", "Status not Updated");
				response.addObject("resultStatus", false);
			}
		}else{
			response.addObject("result", "Cannot save,Scrutiny is under Auditing");
			response.addObject("resultStatus", false);
		}
		
		return response;
	}

	@Override
	public CustomResponseDto saveDeviationSchedule(TAHDRDetailDto dto) {
		CustomResponseDto response=new CustomResponseDto();
		boolean result=false;
		if(dto!=null && dto.getTahdrDetailId() != null ){
			
			Map<String, Object> params= new HashMap<>();		
			params.put("tahdrDetailId", dto.getTahdrDetailId());
			Long tahdrId=dto.getTahdr()==null?0l:dto.getTahdr().getTahdrId();
			
			
			String bidType=dto.getTahdr()==null?"":dto.getTahdr().getBidTypeCode();
			List<ItemScrutinyLineDto> nonAuditorApprovedList=itemScrutinyLineService.findDtos("getRejectItemScrtunyLingTahdrDetailId", params);
			List<BidderDto> bidderList=bidderService.findDtos("getScrutinisedBidderList",params); 
			
			if(CommonUtil.isCollectionEmpty(nonAuditorApprovedList) && CommonUtil.isCollectionEmpty(bidderList)){
				Map<String, Object> newMap= new HashMap<>();		
				newMap.put("tahdrId", tahdrId);
				List<ItemScrutinyDto> notSubmittedScrutinyList=itemScrutinyService.findDtos("getNotUploadedScrutinyFileByTahdrId", newMap);
				if(CommonUtil.isCollectionEmpty(notSubmittedScrutinyList)){
					if("Y".equals(dto.getIsNoDeviation())){
						Map<String, Object> map= new HashMap<>();		
						map.put("tahdrDetailId", dto.getTahdrDetailId());
						map.put("status",AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED );
						
						List<BidderDto> biddersList=bidderService.findDtos("getBidderListByStatus",map);
						if(CommonUtil.isCollectionEmpty(biddersList)){
							result=tahdrService.setTenderStatus(AppBaseConstant.SCRUTINY_FINISHED,tahdrId);
							
							response.addObject("Status", "No Deviation Called as all bidder has passed !");
							response.addObject("resultStatus", true);
							return response;
						}else{
							response.addObject("Status", "Bidder Scrutiny is not completed");
						    response.addObject("resultStatus", false);
						    return response;
						}
					}
					else{
						Map<String, Object> map= new HashMap<>();		
						map.put("tahdrDetailId", dto.getTahdrDetailId());
						map.put("status",AppBaseConstant.BIDDER_STATUS_TECHNO_COMMERCIAL_OPENED );
					
						List<BidderDto> biddersList=bidderService.findDtos("getBidderListByItemBidStatus",map);
						if(CommonUtil.isCollectionEmpty(biddersList)){
							Errors errors =  new Errors();
							tahdrDatesValidation.validateSchedulingDate(dto, errors);
							if(errors.getErrorCount()>0){
								ResponseDto errResponse=new ResponseDto();
								errResponse.setHasError(true);
								errResponse.setErrors(errors.getErrorList());
								
								response.addObject("dateValidatioError", errResponse);
								response.addObject("resultStatus", false);
								response.addObject("dateValidationResult", false);
								response.addObject("resultMessage","Date Validation Error !");
								return response;
								
							}
						result=tAHDRDetailService.updateDeviationScheduleInfo(dto);
						if(result){
							response=tahdrService.scheduleTender(AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING,tahdrId,bidType);
							if((boolean) response.getObjectMap().get("result")){

								
								Map<Long, String> bidderScrutinyStatus=bidderService.getBidderFinalStatus( tahdrId,"PRELIMINARY");
								bidderService.setBidderStatus(bidderScrutinyStatus);
								
								response.addObject("Status", "Deviation Schedule Done");
								response.addObject("resultStatus", true);
								if(tahdrId!=0l || tahdrId!=null){
									String tenderNo=dto.getTahdr().getTahdrCode()==null?"Tender_XX":dto.getTahdr().getTahdrCode();
									sendPreliminaryStatusToBidder(tahdrId, tenderNo);
									String openinngdate=dto.getDeviationOpenningDate().toString();
									String submissionDate=dto.getDeviationToDate().toString();
									Map<String, Object> param= new HashMap<>();
									param.put("tahdrId", tahdrId);
									param.put("status", AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED);
									List<BidderDto> bidderData=bidderService.findDtos("getQueryForMailListByTahdrIdAndStatus", param);
									for(BidderDto bidder : bidderData){
										MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_DEVIATION);
										Map<String, Object> mapList=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
										UserDto internalUser=userService.findDto("getUserByRoleCode", mapList);
										if(mailData!=null  && internalUser!=null){
											/*String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
											Map<String, Object> paramData=new HashMap<>();
											paramData.put("@sourceCompanyName@",internalUser.getPartner().getName());
											paramData.put("@honour@","");
											paramData.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
											paramData.put("@emailCorrCode@","Renewal");
											paramData.put("@generalDetailDate@",today);
											if(bidder.getPartner()!=null && bidder.getPartner().getName()!=null){
												paramData.put("@vendorComapnyName@",bidder.getPartner().getName());	
											}
											paramData.put("@factoryName@","");
											paramData.put("@factoryCity@","");
											paramData.put("@vendorEmail@",bidder.getCreatedBy().getEmail());
											paramData.put("@sourcingcompanyshortname@",internalUser.getPartner().getValue());
											paramData.put("@tenderCode@", tenderNo);
											paramData.put("@submissionDate@",submissionDate);
											paramData.put("@openingDate@",openinngdate);
											paramData.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
											paramData.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
											paramData.put("@companyFax@",internalUser.getUserDetails().getFax1());*/
											if(bidder.getCreatedBy().getEmail()!=null){
												/*mailService.sentMailByTemplate(mailData, paramData, bidder.getCreatedBy().getEmail());*/
												String email=bidder.getCreatedBy().getEmail();
											     String sub="Deviation Sechuduling";
											     String message="YOUR BID OF TENDER: "+bidder==null?"":bidder.getTahdr()==null?"":bidder.getTahdr().getTahdrCode()+" HAS DEVIATION.";
											     partnerService.sendEmailToPartner(email, sub, message);
											}
										}
										}
								}
							}else{
								response.addObject("Status", "Scheduling Already Done !");
								response.addObject("resultStatus", false);
							}
						}else{
							response.addObject("Status", "Scheduling Failed !");
							response.addObject("resultStatus", false);
						}
						}else{
							response.addObject("Status", "Bidder Scrutiny is not completed");
						    response.addObject("resultStatus", false);
						    return response;
						}
					}
				}else{
					response.addObject("resultStatus", false);
					response.addObject("Status", "For Few Bidder Preliminary Scrutiny File Not Uploaded");
					return response;
				}
				
			}else{
				response.addObject("Status", "Auditor Approval is pending/ few bidder scrutiny is pending");
			    response.addObject("resultStatus", false);
			}
				
		}
		else{
			response.addObject("Status", "Something went wrong");
		    response.addObject("resultStatus", false);
		}
		return response;
	}
	

	@Override
	public CustomResponseDto updateTechnicalItemScrutinyLine(ItemScrutinyLineDto dto) {
		CustomResponseDto response=new CustomResponseDto();
		if(dto.getItemScrutinyLineId() != null && dto.getItemScrutiny()!=null){
			TAHDRDto tahdr=tahdrService.findDto("getTahdrByBidderId", 
					AbstractContextServiceImpl.getParamMap("bidderId", dto.getItemScrutiny().getBidder().getBidderId()));
			if(tahdr!=null && (tahdr.getIsAuditing()==null || !tahdr.getIsAuditing().equalsIgnoreCase("Y"))){
				Map<String, Object> params= new HashMap<>();		
				params.put("itemScrutinyLineId", dto.getItemScrutinyLineId());
				if(dto.getIsDeviation().equals("Y")){
					String status=AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED;
					bidderStatusService.saveBidderStatus(dto.getItemScrutiny().getBidder(), 
							status,dto.getDeviationComment());
				}
				dto= itemScrutinyLineService.updateDto(dto);
				itemScrutinyService.resetPreliminaryScrutinySatus(dto.getItemScrutiny().getItemScrutinyId());
				scrutinyFileService.unhookPreviousScrutinyFile(dto.getItemScrutiny().getItemScrutinyId(),"PRELIMINARY","TECHSCR");
				dto=itemScrutinyLineService.findDto("getItemScrtunyLineById", params);
				response.addObject("itemScrutinyLine", dto);
				response.addObject("resultMessage", "Technical Scrutiny Saved Succesfully,Please Re-Submit Comfirmation");
				response.addObject("result", true);
			}else{
				response.addObject("resultMessage", "Cannot Save,Scrutiny is under auditing");
				response.addObject("result", false);
			}
		}else{
			response.addObject("resultMessage", "Technical Scrutiny Not Saved");
			response.addObject("result", false);
		}
		return response;
	}

	@Override
	public CustomResponseDto updateCommercialItemScrutinyLine(ItemScrutinyLineDto dto) {
		CustomResponseDto response=new CustomResponseDto();
		if(dto.getItemScrutinyLineId() != null && dto.getItemScrutiny()!=null){
			TAHDRDto tahdr=tahdrService.findDto("getTahdrByBidderId", 
					AbstractContextServiceImpl.getParamMap("bidderId", dto.getItemScrutiny().getBidder().getBidderId()));
			if(tahdr!=null && (tahdr.getIsAuditing()==null || !tahdr.getIsAuditing().equalsIgnoreCase("Y"))){
				Map<String, Object> params= new HashMap<>();		
				params.put("itemScrutinyLineId", dto.getItemScrutinyLineId());
				if(dto.getIsDeviation().equals("Y")){
					String status=AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED;
					bidderStatusService.saveBidderStatus(dto.getItemScrutiny().getBidder(), 
							status,dto.getDeviationComment());
				}
				dto= itemScrutinyLineService.updateDto(dto);
				itemScrutinyService.resetPreliminaryScrutinySatus(dto.getItemScrutiny().getItemScrutinyId());
				scrutinyFileService.unhookPreviousScrutinyFile(dto.getItemScrutiny().getItemScrutinyId(),"PRELIMINARY","COMMSCR");
				dto=itemScrutinyLineService.findDto("getItemScrtunyLineById", params);
				response.addObject("itemScrutinyLine", dto);
				response.addObject("resultMessage", "Commercial Scrutiny Saved Succesfully,Please Re-Submit Comfirmation");
				response.addObject("result", true);
			}else{
				response.addObject("resultMessage", "Cannot Save,Scrutiny is under auditing");
				response.addObject("result", false);
			}
		}else{
			response.addObject("resultMessage", "Commercial Scrutiny Not Saved");
			response.addObject("result", false);
		}
		return response;
	}

	@Override
	public CustomResponseDto saveAuditorComment(ItemScrutinyLineDto dto) {
		CustomResponseDto response=new CustomResponseDto();
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		
		 Map<String,Object> map= new HashMap<>();
    	 int result=0;
 		if(AppBaseConstant.ROLE_AUDITOR_USER.equals(userRole)){
 			if(dto.getItemScrutinyLineId() != null){
 				 map.put("auditorComment",dto.getAuditorComment());
 		 		 map.put("auditorStatus",dto.getAuditorStatus());
 				 result =itemScrutinyLineService.updateByJpql(map, "itemScrutinyLineId",dto.getItemScrutinyLineId());
 				 if(result>0){
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
		CustomResponseDto response=new CustomResponseDto();
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		 Map<String,Object> map= new HashMap<>();
    	 int result=0;
 		if(AppBaseConstant.ROLE_AUDITOR_USER.equals(userRole)){
 			if(dto.getItemScrutinyId() != null){
 				 map.put("preliminaryAuditorComment",dto.getPreliminaryAuditorComment());
 		 		 map.put("preliminaryAuditorStatus",dto.getPreliminaryAuditorStatus());
 		 		 map.put("isAudited",dto.getIsAudited());
 		 		 
 		 		Map<String,Object> params= new HashMap<>();
 		 		params.put("bidderId", dto.getBidder().getBidderId());
 		 		List<ItemScrutinyLineDto> nonAuditedList=itemScrutinyLineService.findDtos("getNonAuditedItemScrtunyLineByBidderId", params); 
 		 		 if(CommonUtil.isCollectionEmpty(nonAuditedList) ){
 		 			List<ItemScrutinyLineDto> clarificationList=itemScrutinyLineService.findDtos("getClarificationItemScrtunyLineByBidderId", params); 
 		 			 if(CommonUtil.isCollectionEmpty(clarificationList) 
 		 					 && dto.getPreliminaryAuditorStatus().toUpperCase().equals("APPROVED")){
 		 				result =itemScrutinyService.updateByJpql(map, "itemScrutinyId",dto.getItemScrutinyId());
 	 	 				 if(result>0){
 	 	 					 response.addObject("resultMessage", "Auditor Comment (Approve) Comfirmed Successfully");
 	 	 					 response.addObject("resultStatus", true);
 	 	 					/* commercialStatusMail( dto.getItemScrutinyId());*/
 	 	 					 
 	 	 				 }else{
 	 	 					 response.addObject("resultMessage", "Auditor Comment Not Comfirmed");
 	 	 					 response.addObject("resultStatus", false);
 	 	 				 } 
 		 			 }else if(!CommonUtil.isCollectionEmpty(clarificationList) && dto.getPreliminaryAuditorStatus().toUpperCase().equals("CLARIFICATION")){
 		 				result =itemScrutinyService.updateByJpql(map, "itemScrutinyId",dto.getItemScrutinyId());
	 	 				 if(result>0){
	 	 					 response.addObject("resultMessage", "Auditor Comment (Clarification) Comfirmed Successfully");
	 	 					 response.addObject("resultStatus", true);
	 	 					 /*commercialStatusMail( dto.getItemScrutinyId());*/
	 	 					 
	 	 				 }else{
	 	 					 response.addObject("resultMessage", "Auditor Comment Not Comfirmed");
	 	 					 response.addObject("resultStatus", false);
	 	 				 } 
 		 			 }else{
 		 				 response.addObject("resultMessage", "For Approval or Clarification All Parameter Should be Approved or Any one is Clarify respectively");
 		 				 response.addObject("resultStatus", false);
 		 			 }
 		 			
 		 		 }else{
 		 			 response.addObject("resultMessage", "Auditing for few parameter is pending");
	 				 response.addObject("resultStatus", false);
	 				 }
 		 		 }else{
 	 				response.addObject("resultMessage", "Something went wrong");
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
	public CustomResponseDto checkAuditingStatus(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("tahdrId", tahdrId);
		List<ItemScrutinyLineDto> nonAuditedList=itemScrutinyLineService.findDtos("getNonAuditedItemScrutinyByTahdrId", params); 
		
		List<ItemScrutinyDto> commItemScrutinyList=itemScrutinyService.findDtos("getCommercialItemscrutinyByTahdrId", params);
		
		/*params.put("status", AppBaseConstant.BIDDER_STATUS_TECHNO_COMMERCIAL_OPENED);*/
		List<BidderDto> bidderList=bidderService.findDtos("getBidderByTahdrIdAndStatus", params);
		boolean isAllItemScrutinyCreated= (bidderList.size()==commItemScrutinyList.size())?true:false;
		if(isAllItemScrutinyCreated){
			if(CommonUtil.isCollectionEmpty(nonAuditedList)){
				Map<String ,Object> param=AbstractServiceImpl.getParamMap("roleValue", AppBaseConstant.ROLE_AUDITOR_USER);
				param.put("tenderId", tahdrId);
			    List<UserDto> auditorList=userService.findDtos("getQueryForInternalUser", param);
			    response.addObject("auditorList", auditorList);
			    TAHDRDto tender=tahdrService.findDto("getAuditoUserBytahdrId", params);
			    response.addObject("tender", tender);
				response.addObject("resultStatus", true);
			 }else{
				 response.addObject("resultMessage", "Scrutiny of all bidder is not finished!");
				 response.addObject("resultStatus", false);
			 }
		}else{
			 response.addObject("resultMessage", "Scrutiny of all bidder is Not created!");
			 response.addObject("resultStatus", false);
		}
		return response;
	}

	@Override
	public CustomResponseDto addAuditor(TAHDRDto tahdr) {
		CustomResponseDto response=new CustomResponseDto();
		if(tahdr!=null && tahdr.getAuditorUser()!=null){
			Map<String, Object> params= new HashMap<>();		
			params.put("auditorUser.userId", tahdr.getAuditorUser().getUserId());
			params.put("isAuditing", "Y");
			int result=tahdrService.updateByJpql(params, "tahdrId", tahdr.getTahdrId());
			if(result>0){
				 UserDto user=userService.findDto(tahdr.getAuditorUser().getUserId());
				 String tahdrName=tahdr.getTahdrCode();
				 if(user!=null){
					 String sub="Preliminary Scrutiny Auditing ";
					 String message="<p>Hi "+user.getName()+"</p><br><h1><p>"
					 		+ "The Preliminary Scrutiny of Tender/Auction:: <b>"+tahdrName+"</b> has been scrutunized by SCRUTINY ENGR."
					 				+ " Kindly Audit Commercial Scrutiny Of all Bidder of Respective Tender. </h1><p><br><br><p><h1> Regards,<br> <b>Mahavitran State"
		 				+ " Electricity Board</b></h1><p> ";
					 sendMail(user.getEmail(), sub, message); 
				 }
				 response.addObject("resultMessage", "Auditor Added to Tender");
				 response.addObject("resultStatus", true);
			 }else{
				 response.addObject("resultMessage", "Auditor Not Added to Tender!");
				 response.addObject("resultStatus", false);
			 }
		}
		return response;
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

	@Override
	public CustomResponseDto checkDeviationSchedule(Long tahdrDetailId) {
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("tahdrDetailId", tahdrDetailId);
		
		List<ItemScrutinyLineDto> nonAuditorApprovedList=itemScrutinyLineService.findDtos("getRejectItemScrtunyLingTahdrDetailId", params); 
		if(CommonUtil.isCollectionEmpty(nonAuditorApprovedList)){
			TAHDRDetailDto tahdrDetail=tAHDRDetailService.findDto(tahdrDetailId);
			
			response=bidderService.checkDeviationSchedule(tahdrDetailId);
			response.addObject("auditorStatus", true);
			response.addObject("tahdrDetail", tahdrDetail);
		}
		else{
			response.addObject("auditorStatus", false);
			response.addObject("resultMessage", "Auditor approval is Pending. ");
		}
		return response;
	}

	@Override
	public ItemScrutinyLineDto saveGtpScrutiny(ItemScrutinyLineDto dto) {
		if(dto.getItemScrutinyLineId() == null){
			return itemScrutinyLineService.save(dto);
		}
		if(dto.getIsDeviation().equals("Y")){
				String status=AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED;
				bidderStatusService.saveBidderStatus(dto.getItemScrutiny().getBidder(), 
						status,dto.getDeviationComment());
			}
		return itemScrutinyLineService.updateDto(dto);
	}

	@Override
	public CustomResponseDto intimidateAuditor(Long tahdrId,String tahdrName,Long auditorId) {
		CustomResponseDto response=new CustomResponseDto();
		if(tahdrName!=null && auditorId!=null){
			Map<String, Object> newParam= new HashMap<>();
			newParam.put("tahdrId",tahdrId);
			List<ItemScrutinyDto> clarifiedScruty=itemScrutinyService.findDtos("getPreliminaryClarifiedAuditedList", newParam);
			if(!CommonUtil.isCollectionEmpty(clarifiedScruty)){
				Map<String, Object> params= new HashMap<>();
				params.put("isAuditing", "Y");
				int result=tahdrService.updateByJpql(params, "tahdrId", tahdrId);
				if(result>0){
					UserDto user=userService.findDto(auditorId);
					 String sub="Preliminary Scrutiny Auditing ";
					 String auditorName=user.getName()==null?" ":user.getName();
					 String message="<p>Hi "+auditorName.toUpperCase()+"</p><br><h1><p>"
					 		+ " The Preliminary Scrutiny of Tender/Auction: <b>: "+tahdrName+"</b> has been scrutunized/re-scrutunized by SCRUTINY ENGR."
					 				+ " Kindly audit/re-audit Commercial Scrutiny Of all Bidder of Respective Tender. </h1></p><br><br><p><h1> Regards,<br><b> Mahavitran State"
				 				+ " Electricity Board</b></h1></p> ";
					 sendMail(user.getEmail(), sub, message);
					response.addObject("resultStatus", true);
					response.addObject("resultMessage", auditorName+" has been notified Successfully!");
				}else{
					response.addObject("resultStatus", false);
					response.addObject("resultMessage", "Auditor not notified!");
				}
			}else{
				response.addObject("resultStatus", true);
				response.addObject("resultMessage", "No Need To Notify Auditor,As everything is fine!");
			}
		}else{
			response.addObject("resultStatus", false);
			response.addObject("resultMessage", "Something went wrong !");
		}
	  return response;
	}

	@Override
	public CustomResponseDto intimidateAuditing(TAHDRDto tahdr) {
		CustomResponseDto response=new CustomResponseDto();
		boolean result=false;
		if(tahdr!=null){
				if(tahdr.getTahdrCode()!=null || tahdr.getTahdrId()!=null){
						Map<String, Object> params= new HashMap<>();		
						params.put("tahdrId", tahdr.getTahdrId());
						List<ItemScrutinyDto> preliminaryNotAuditedList=itemScrutinyService.findDtos("getPreliminaryNotAuditedList", params);
						if(CommonUtil.isCollectionEmpty(preliminaryNotAuditedList)){
							Map<String, Object> paramMap= new HashMap<>();
							paramMap.put("isAuditing", "N");
							int resultCount=tahdrService.updateByJpql(paramMap, "tahdrId", tahdr.getTahdrId());
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
								response.addObject("resultMessage", "Scrutiny Engr Not notified ");
							}
						}else{
							response.addObject("resultStatus", false);
							response.addObject("resultMessage", "Auditing is pending for few commercial parameter");
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
			 String sub="Preliminary Scrutiny Auditing ";
			 String message="<p>Hi </p><br><h1><p>"
					 + "The Preliminary Scrutiny of Tender/Auction: <b>"+tahdrName+"</b> has been AUDITED by Auditor."
		 				+ " Kindly Check the same for Respective Tender. </h1><p> <br><br><p><h1> Regards,<br> <b>Mahavitran State"
		 				+ " Electricity Board</b></h1><p> ";
			 result=sendMail(emailList, sub, message);
			 return result;
		 } 
		 return result;
	 }
	 
}
