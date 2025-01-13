package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dto.SessionKeyDto;
import com.novelerp.appbase.master.service.MaterialSpecificationService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.util.AuthManager;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.dto.TenderCommitteeParticipantDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.CommercialBidService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.ItemScrutinyService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.SMSService;
import com.novelerp.eat.service.TAHDRMaterialService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TechnicalBidService;
import com.novelerp.eat.service.TenderCommitteeParticipantService;
import com.novelerp.eat.service.TenderCommitteeService;
import com.novelerp.eat.service.TenderOpeningService;
import com.novelerp.eat.util.TenderContext;

@Service
public class TenderOpeningServiceImpl implements TenderOpeningService{

	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private TAHDRService tAHDRService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private BidderService  bidderService;

	@Autowired
	private TenderContext  tenderContext;
	
	@Autowired
	private TechnicalBidService  technicalBidService;
	
	@Autowired
	private CommercialBidService  commercialBidService;
	
	@Autowired
	private PriceBidService  priceBidService;
	
	@Autowired
	private ItemBidService  itemBidService;
	
	@Autowired
	private ItemScrutinyService itemScrutinyService;
	
	@Autowired
	private TAHDRMaterialService  tahdrMaterialService;
	
	@Autowired
	private AuthManager authManager;
	
	@Autowired
	private TenderCommitteeParticipantService  tenderCommitteeParticipantService;
	
	@Autowired
	private TenderCommitteeService  tenderCommitteeService;
	
	@Autowired
	private MaterialSpecificationService  materialSpeicificationService;
	
	@Autowired
	private SMSService smsService;
	
	
	@Override
	public CustomResponseDto getOpeningTenderList() {
		CustomResponseDto response=new CustomResponseDto();
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		Map<String, String> openingTypeCode = refListService.getReferenceListMap(AppBaseConstant.TAHDR_OPENING_TYPES);
		response.addObject("openingType", openingTypeCode);
		if(userRole.equals("VENADM"))
			response.addObject("role", userRole);
		else
			response.addObject("role", userRole);
		return response;
	}

	@Override
	public CustomResponseDto getOpeningMyTenderList(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		Map<String, String> openingTypeCode = refListService.getReferenceListMap(AppBaseConstant.TAHDR_OPENING_TYPES);
		response.addObject("openingType", openingTypeCode);
		response.addObject("role", userRole);
		
		Map<String, Object> params= new HashMap<>();		
		params.put("tahdrId",tahdrId);
		TAHDRDto tahdr=tAHDRService.findDto("getOpeningTenderByTahdrId",params);
		
		response.addObject("tahdrId", tahdr);
		return response;
	}

	@Override
	public CustomResponseDto searchOpeningTenderList(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		UserDto userDto= contextService.getUser();
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		
		BPartnerDto partner=contextService.getPartner();
		
		if(partner !=null && userDto!=null){
			if(userRole.equals("VENADM")){
				Map<String, Object> params= new HashMap<>();		
				params.put("bpartnerId", partner.getbPartnerId());
				params.put("tahdrId", tahdrId);
				List<BidderDto> bidderTenderList=new ArrayList<BidderDto>();
				BidderDto bidder=bidderService.findDto("getBidderAssociatedMyTendersQuery", params);
				bidderTenderList.add(bidder);
				response.addObject("bidderTenderList", bidderTenderList);
			}
			else{
				Map<String, Object> params= new HashMap<>();		
				params.put("userId", userDto.getUserId());
				params.put("tahdrId", tahdrId);
				
				List<TAHDRDto>  tenderList=new ArrayList<TAHDRDto>();
				TAHDRDto tender=tAHDRService.findDto("getOpeningMyTenderByUserId", params);
				tenderList.add(tender);
				response.addObject("tenderCommittee", tenderList);
			}
		}else{
			
				response.addObject("bidderTenderList", null);
				response.addObject("tenderCommittee", null);
				response.addObject("resultMessage", "Partner or User not found in session !");
			
		}
		Map<String, String> bidderStatusList = refListService.getReferenceListMap(AppBaseConstant.BIDDER_STATUS);
		response.addObject("bidderStatusList", bidderStatusList);
		
		Map<String, String> tenderStatusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		response.addObject("tenderStatusList", tenderStatusList);
		response.addObject("viewBidDifference", ContextConstant.VIEW_BID_DELAY);
		return response;
	}

	@Override
	public CustomResponseDto searchOpeningTenderList(TenderCommitteeDto tenderCommitteeDto) {
		CustomResponseDto response=new CustomResponseDto();
		UserDto userDto= contextService.getUser();
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		
		BPartnerDto partner=contextService.getPartner();
		
		if(partner !=null && userDto!=null){
			Map<String, Object> params= new HashMap<>();		
			params.put("bpartnerId", partner.getbPartnerId());
			
			if(userRole.equals("VENADM")){
				List<BidderDto> bidderTenderList=bidderService.getBidderAssociatedTenders(tenderCommitteeDto, partner);
				response.addObject("bidderTenderList", bidderTenderList);
			}
			else{
				/*List<TenderCommitteeDto>  tenderCommittee=tenderCommitteeService.getAssociatedTenders(tenderCommitteeDto,userDto);*/
				List<TAHDRDto>  tenderList=tAHDRService.getAssociatedTenders(tenderCommitteeDto,userDto);
				response.addObject("tenderCommittee", tenderList);
			}
		}else{
			
				response.addObject("bidderTenderList", null);
				response.addObject("tenderCommittee", null);
				response.addObject("resultMessage", "Partner or User not found in session !");
			
		}
		Map<String, String> bidderStatusList = refListService.getReferenceListMap(AppBaseConstant.BIDDER_STATUS);
		response.addObject("bidderStatusList", bidderStatusList);
		
		Map<String, String> tenderStatusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		response.addObject("tenderStatusList", tenderStatusList);
		response.addObject("viewBidDifference", ContextConstant.VIEW_BID_DELAY);
		return response;
	}

	@Override
	public CustomResponseDto checkSessionKey(SessionKeyDto sessionKeyDto) {
		CustomResponseDto response=new CustomResponseDto();
		UserDto userDto= contextService.getUser();
		if(sessionKeyDto!=null && userDto!=null){
			Long tahdrId=sessionKeyDto.getTahdrId();
			String sessionKey=sessionKeyDto.getSessionKey();
		
			
			Map<String, Object> param= new HashMap<>();
			param.put("tahdrId", tahdrId);
			
			Map<String, Object> map= new HashMap<>();
			map.put("tahdrId", tahdrId);
			map.put("userId", userDto.getUserId());
			
			Map<String, Object> params= new HashMap<>();
			params.put("tahdrId", tahdrId);
			String openingType=sessionKeyDto.getOpeningType();
			if(openingType.equals("PBOP") || openingType.equals("SBPBOP")){
				params.put("openingType", AppBaseConstant.TENDER_COMMITTEE_PriceBid_Opening);
			}else if(openingType.equals("TCOP")){
				params.put("openingType", AppBaseConstant.TENDER_COMMITTEE_TechnoCommercial_Opening);
			}else if(openingType.equals("DBOP")){
				params.put("openingType", AppBaseConstant.TENDER_COMMITTEE_Deviation_Opening);
			}else if(openingType.equals("C1OP")){
				params.put("openingType", AppBaseConstant.TENDER_COMMITTEE_Annexure_Opening);
			}else if(openingType.equals("RBOP")){
				params.put("openingType", AppBaseConstant.TENDER_COMMITTEE_RevisedBid_Opening);
			}
			
			TenderCommitteeDto tenderCommittee=tenderCommitteeService.findDto("getTenderCommitteeByTahdrIdAndOpeningType", params);
			if(tenderCommittee!=null){
				String encodedsessionKey="";
					if(CommonUtil.isEqual(userDto.getUserId(), tenderCommittee.getChairPerson().getUserId())){
						encodedsessionKey=tenderCommittee.getSessionKey()==null?"":tenderCommittee.getSessionKey();
					}
					else{
						Set<TenderCommitteeParticipantDto> participant=tenderCommittee.getParticipant();
						if(!CommonUtil.isCollectionEmpty(participant)){
							for(TenderCommitteeParticipantDto p:participant){
								if(CommonUtil.isEqual(userDto.getUserId(), p.getUser().getUserId())){
									encodedsessionKey=p.getSessionKey()==null?"":p.getSessionKey();
									break;
								}
							}
						}
					}
				
				if(!encodedsessionKey.equals("")){
					 boolean validatesessionKey=authManager.check(sessionKey, encodedsessionKey);
						
						if(userDto!=null && validatesessionKey){
							tenderContext.addTenderForCommittee(tahdrId, userDto);
							
							List<BidderDto> bidderList=bidderService.findDtos("getBidderListByTahdrId", param);
							Map<Long,UserDto> loggedInVender=tenderContext.getBidderJoinee(tahdrId);
							Map<Long,UserDto> loggedInMember=tenderContext.getCommitteeJoinee(tahdrId);
							boolean isChairPerson=tenderCommitteeService.isChairPerson(tenderCommittee.getTenderCommitteeId(), userDto.getUserId());
							response.addObject("result", true);
							response.addObject("isChairPerson", isChairPerson);
							response.addObject("bidderList", bidderList);
							response.addObject("loggedInbidderList", loggedInVender);
							response.addObject("loggedInMember", loggedInMember);
									
						}else{
							response.addObject("result", false);
							response.addObject("resultMessage", "Session Key INVALID");
						}	
						
						}
				else{
					response.addObject("result", false);
					response.addObject("resultMessage", "Session Key has not been generated");
				}
				return response;
				}else{
					response.addObject("result", false);
					response.addObject("resultMessage", "Committee has not been Created");
				}
			
		}else{
			response.addObject("result", false);
			response.addObject("resultMessage", "Something went wrong");
		}
		return response;
	}

	@Override
	public CustomResponseDto vendorSessionKey(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		UserDto userDto= contextService.getUser();
		if( tahdrId!=null){
			tenderContext.addTenderForBidder(tahdrId, userDto);
			Map<String, Object> params= new HashMap<>();
			params.put("tahdrId", tahdrId);
			TenderCommitteeDto tenderCommittee=tenderCommitteeService.findDto("getTenderCommitteeByTahdrId", params);
			if(tenderCommittee!=null){
				List<BidderDto> bidderList=bidderService.findDtos("getBidderListByTahdrId", params);
				Map<Long,UserDto> loggedInVender=tenderContext.getBidderJoinee(tahdrId);
				Map<Long,UserDto> loggedInMember=tenderContext.getCommitteeJoinee(tahdrId);
				boolean isChairPerson=tenderCommitteeService.isChairPerson(tenderCommittee.getTenderCommitteeId(), userDto.getUserId());
				response.addObject("result", true);
				response.addObject("isChairPerson", isChairPerson);
				response.addObject("bidderList", bidderList);
				response.addObject("loggedInbidderList", loggedInVender);
				response.addObject("loggedInMember", loggedInMember);
			}else{
				response.addObject("result", false);
				response.addObject("resultMessage", "Committee For Tender Not created yet.");
			}
			
		}else{
			response.addObject("result", false);
			response.addObject("resultMessage", "Error in Request !");
		}
		return response;
	}

	@Override
	public CustomResponseDto tenderLogout(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		UserDto userDto= contextService.getUser();
		RoleDto role=contextService.getDefaultRole();
		if(tahdrId!=null){
			Map<String, Object> params= new HashMap<>();
			params.put("tahdrId", tahdrId);
			if(AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue())){
				tenderContext.removeBidderJoinee(tahdrId, userDto.getUserId());
			}else{
				tenderContext.removeCommitteeJoinee(tahdrId, userDto.getUserId());
			}
			response.addObject("result", true);
			response.addObject("resultMesssage", "Logout Successfully");
		}else{
			response.addObject("result", false);
			response.addObject("resultMesssage", "Not Logged Out !");
		}
		return response;
	}

	@Override
	public CustomResponseDto openingTenderByOpeningType(TAHDRDto tAHDRDto) {
		CustomResponseDto response=new CustomResponseDto();
		UserDto userDto= contextService.getUser();
		if(userDto!=null){
			 Long tahdrId=tAHDRDto.getTahdrId();
			String tenderNo=tAHDRDto.getTahdrCode()==null?"TENDER_XX":tAHDRDto.getTahdrCode();
			TAHDRDto selectedTender=tAHDRService.findDto(tahdrId);
			
			String selectedTenderTypeCode=selectedTender.getTahdrTypeCode();
			String selectedTenderIsAuction=selectedTender.getIsAuction();
			tAHDRDto.setIsAuction(selectedTenderIsAuction);
			tAHDRDto.setTahdrTypeCode(selectedTenderTypeCode);
			
			String status=tAHDRDto.getTahdrStatusCode();
			String openinngType="";
			Map<String, Object> map= new HashMap<>();
			map.put("tahdrId", tahdrId);
			map.put("userId", userDto.getUserId());
			
			Map<String, Object> param= new HashMap<>();
			param.put("tahdrId", tahdrId);
			
			if(status.equals("PBOP") || status.equals("SBPBOP") ){
				param.put("openingType", AppBaseConstant.TENDER_COMMITTEE_PriceBid_Opening);
			}else if(status.equals("TCOP")){
				param.put("openingType", AppBaseConstant.TENDER_COMMITTEE_TechnoCommercial_Opening);
			}else if(status.equals("DBOP")){
				param.put("openingType", AppBaseConstant.TENDER_COMMITTEE_Deviation_Opening);
			}else if(status.equals("C1OP")){
				param.put("openingType", AppBaseConstant.TENDER_COMMITTEE_Annexure_Opening);
			}else if(status.equals("RBOP")){
				param.put("openingType", AppBaseConstant.TENDER_COMMITTEE_RevisedBid_Opening);
			}
			
			TenderCommitteeDto tenderCommittee=tenderCommitteeService.findDto("getTenderCommitteeByTahdrIdAndOpeningType", param);
			
			if(tenderCommittee!=null){
				Long tenderCommitteId=tenderCommittee.getTenderCommitteeId();
				
				boolean checkTenderOpenning=tenderCommitteeParticipantService.checkTenderOppening(tenderCommitteId);
				if(checkTenderOpenning){
					List<ItemBidDto> itemBidList=new ArrayList<ItemBidDto>();
					
					Map<String,Object> params= new HashMap<>();
					params.put("tahdrId",tahdrId);
					
					 if(status.toUpperCase().equals("VO")){
						 openinngType="CANCELLED";
						}
					else if(status.toUpperCase().equals("PBOP") || status.toUpperCase().equals("SBPBOP")){
						openinngType="PRICE BID";
							itemBidList=itemBidService.findDtos("getItemBidForPBOpening", params);
					}
					else if(status.toUpperCase().equals("TCOP")){
						openinngType="TECHNO-COMMERCIAL BID OPENED";
						params.put("status", AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
						itemBidList=itemBidService.findDtos("getItemBidByBidSubmitted", params);
					}
					else if(status.toUpperCase().equals("DBOP")){
						openinngType="DEVIATION BID OPENED";
						params.put("status", AppBaseConstant.BIDDER_STATUS_DEVIATION_SUBMITTED);
						itemBidList=itemBidService.findDtos("getItemBidByBidSubmitted", params);
					}
					else if(status.toUpperCase().equals("C1OP")){
						openinngType="ANNEXURE C1 OPENED";
						params.put("status", AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_SUBMITED);
						itemBidList=itemBidService.findDtos("getItemBidByBidSubmitted", params);
					}
							
					 try{
						 List<PriceBidDto> pbList=null;
						 if(!selectedTenderTypeCode.equals("WT") && (status.toUpperCase().equals("PBOP") || status.toUpperCase().equals("SBPBOP"))){
							 pbList=priceBidService.findDtos("getPriceBidAndParts",AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId)); 
							 response= tAHDRService.openingTender(tAHDRDto, itemBidList,pbList);
						 }else{
							 response= tAHDRService.openingTender(tAHDRDto, itemBidList,pbList);
						 }
						 priceBidService.decryptPriceBidDoc(pbList);
					 }catch(Exception ex){
						 response.addObject("resultMessage", ex.getMessage());
						 response.addObject("result", false);
					 }
						
						if((boolean) response.getObjectMap().get("result")){
							
							boolean isTenderOpened=response.getObjectMap().get("result")==null?false:(boolean) response.getObjectMap().get("result");
							if(isTenderOpened){
								//List<String> emailList=bidderService.getBidderMailListByTahdrId(tahdrId);
								List<String> mailList=new ArrayList<String>();
								Map<String,Object> parameter = new HashMap<>();
								params.put("tahdrId",tahdrId);
								List<BidderDto> bidderList= bidderService.findDtos("getQueryForMailListByTahdrId", parameter);
								if(!CommonUtil.isCollectionEmpty(bidderList)){
									for(BidderDto bidder:bidderList){
										UserDto user=bidder.getCreatedBy();
										if(user!=null){
											mailList.add(user.getEmail());
										}
									}
								}
								
								String sub="Tender Opening";
								String message="<p>Hi</p><br><p>The Tender: "+tenderNo+" has been "+openinngType+".<p>";
								bidderService.sendMailToAllBidder(mailList, sub, message);
								
								Map<String, String> smsParam = new HashMap<String, String>();
								smsParam.put(AppBaseConstant.SMS_PARAMETER_1, tenderNo.replaceAll(" ", "%20"));
								smsParam.put(AppBaseConstant.SMS_PARAMETER_2, openinngType.replaceAll(" ", "%20"));
								for(BidderDto bidderDto : bidderList){
									if(bidderDto.getCreatedBy() != null || bidderDto.getCreatedBy().getUserDetails() != null)
									smsService.sendSMS(bidderDto.getCreatedBy().getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_TENDER_OPENED, smsParam);
								}
							}
						}else{
							response.addObject("result", false);
							response.addObject("resultMessage", "Tender not Opened");
						}
						
				}else{
					response.addObject("result", false);
					response.addObject("resultMessage", "One Chairperson and Atleast One Auditor Must be Logged In");
				}
			}else{
				response.addObject("result", false);
				response.addObject("resultMessage", "You are not Authorised User to Proceed !");
			}
			
		}else{
			response.addObject("result", false);
			response.addObject("resultMessage", "Session Declined ");
		}
		
		
		return response;
	}

	@Override
	public CustomResponseDto viewTenderByOpeningType(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		UserDto userDto= contextService.getUser();
		Map<String, Object> params= new HashMap<>();		
		params.put("tahdrId", tahdrId);
		
		tenderContext.addTenderForCommittee(tahdrId, userDto);
		List<BidderDto> bidderList=bidderService.findDtos("getBidderListByTahdrId", params);
		Map<Long,UserDto> loggedInMember=tenderContext.getCommitteeJoinee(tahdrId);
		response.addObject("bidderList", bidderList);
		response.addObject("loggedInMember", loggedInMember);
		return response;
	}

	@Override
	public CustomResponseDto openAllBid(Long tahdrId, Long tahdrMaterialId) {
		CustomResponseDto response=new CustomResponseDto();
		response=tAHDRService.getBidderByOpeningType(tahdrId,tahdrMaterialId);
		return response;
	}

	@Override
	public CustomResponseDto openAllBid(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		response=tAHDRService.getBidderCommercialBid(tahdrId);
		return response;
	}

	@Override
	public CustomResponseDto getLoggedInAuditorList(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		if(tahdrId!=null){
			Map<Long,UserDto> loggedInMember=tenderContext.getCommitteeJoinee(tahdrId);
			response.addObject("loggedInMember", loggedInMember);
			response.addObject("resultStatus", true);
		}
		else{
			response.addObject("resultStatus", false);
		}
		return response;
	}

	@Override
	public CustomResponseDto getLoggedInBidderList(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		if(tahdrId!=null){
			Map<String, Object> params= new HashMap<>();		
			params.put("tahdrId", tahdrId);
			
			Map<Long,UserDto> loggedInVender=tenderContext.getBidderJoinee(tahdrId);
			response.addObject("loggedInbidderList", loggedInVender);
			response.addObject("resultStatus", true);
		}
		else{
			response.addObject("resultStatus", false);
		}
		return response;
	}

	@Override
	public CustomResponseDto getTAHDRMaterialList(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		List<TAHDRMaterialDto> materialList=tahdrMaterialService.getTahdrMaterialListByTahdrId(tahdrId); 
		/*List<MaterialSpecificationDto> specificationList=materialSpeicificationService.findDtos("getMaterialSpecificationListByTahdrId", AbstractContextServiceImpl.getParamMap("", tahdrId));
		response.addObject("specificationList", specificationList);*/
		response.addObject("responseList", materialList);
		return response;
	}

	@Override
	public CustomResponseDto getBidderListByTahdrId(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		if(tahdrId!=null){
			Map<String, Object> params= new HashMap<>();		
			params.put("tahdrId", tahdrId);
			List<BidderDto> bidderList=bidderService.findDtos("getBidderListOnlyByTahdrId",params); 
			response.addObject("responseList", bidderList);
		}
		return response;
	}

	@Override
	public CustomResponseDto checkTenderOpened(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		if(tahdrId!=null){
			Map<String, Object> params= new HashMap<>();		
			params.put("tahdrId", tahdrId);
			TAHDRDto tahdr=tAHDRService.findDto(tahdrId);
			if(tahdr!=null){
				response.addObject("result", true);
				response.addObject("tenderStatus", tahdr.getTahdrStatusCode());
			}else{
				response.addObject("result", false);
			}
		}else{
			response.addObject("result", false);
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.TenderOpeningService#getOpenedBids(java.lang.Long)
	 */
	@Override
	public CustomResponseDto getOpenedBids(Long bidderId,Long tahdrMaterialId,String status,String bidType) {
		CustomResponseDto resp=new CustomResponseDto();
		Map<String, Object> params=new HashMap<>();
		params.put("bidderId", bidderId);
		params.put("bidderStatus", status);
		
		if(AppBaseConstant.BIDDER_STATUS_TECHNO_COMMERCIAL_OPENED.equals(status) 
				|| AppBaseConstant.BID_TYPE_CODE_SINGLE_BID.equals(bidType)){
			CommercialBidDto cb=commercialBidService.findDto("getOpenedCommercialBid",params);
			
			params.put("tahdrMaterialId", tahdrMaterialId);
			params.put("itemBidStatus", status);
			TechnicalBidDto tb=technicalBidService.findDto("getOpenedTechnicalBid",params);
			resp.addObject("technicalBid", tb);
			resp.addObject("commercialBid", cb);
			
		}
		
		if(AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED.equals(status)
				|| AppBaseConstant.BID_TYPE_CODE_SINGLE_BID.equals(bidType)){
			params.put("tahdrMaterialId", tahdrMaterialId);
			params.put("itemBidStatus", status);
			PriceBidDto pb=priceBidService.findDto("getOpenedPriceBid",params);
			resp.addObject("priceBid", pb);
		}
		
		if(AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_OPENED.equals(status)){
			params.put("tahdrMaterialId", tahdrMaterialId);
			params.put("itemBidStatus", status);
			PriceBidDto pb=priceBidService.findDto("getOpenedC1Bid",params);
			resp.addObject("annexureC1Bid", pb);
		}
		
		if(AppBaseConstant.BIDDER_STATUS_DEVIATION_OPENED.equals(status)){
			
			ItemScrutinyDto commercialDeviationBid=itemScrutinyService.findDto("getOpenedCommercialDvtnBid",params);
			resp.addObject("commecialDeviationBid", commercialDeviationBid);
			
			params.put("tahdrMaterialId", tahdrMaterialId);
			params.put("itemBidStatus", status);
			ItemScrutinyDto techDeviationBid=itemScrutinyService.findDto("getOpenedTechDvtnBid",params);
			resp.addObject("techDeviationBid", techDeviationBid);
		}
		
		return resp;
	}

}
