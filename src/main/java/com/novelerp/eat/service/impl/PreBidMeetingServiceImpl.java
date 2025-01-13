package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appbase.master.service.LocationTypeService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.PreBidMeetingService;
import com.novelerp.eat.service.SMSService;
import com.novelerp.eat.service.TAHDRService;

@Service
public class PreBidMeetingServiceImpl implements PreBidMeetingService{

	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private LocationTypeService locationTypeService;
	
	@Autowired
	private TAHDRService tenderService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private SMSService smsService;
	
	
	@Override
	public CustomResponseDto getTenderListForPreBidMeeting(String typeCode) {
		CustomResponseDto response=new CustomResponseDto();
		Map<String,Object> param=new HashMap<String,Object>();
		Map<String,Object> params=new HashMap<String,Object>();		
		UserDetailsDto userDetail=contextService.getUserDetails();
		UserDto userDto=contextService.getUser();
		param.put("userId", userDto.getUserId());
		param.put("typeCode", typeCode);
		
		String locationType=userDetail.getLocationTypeRef();
		params.put("code",locationType);
		List<TAHDRDto> tahdrList=new ArrayList<TAHDRDto>();
		LocationTypeDto locationTypeDto=locationTypeService.findDto("getLocationTypeOnType", params);
		param.put("levels", locationTypeDto.getLevels()); 
		if(typeCode.equals("FA") || typeCode.equals("RA") ){
			if(typeCode.equals("RA")){
				param.put("typeCode", "PT");
			}
			tahdrList=tenderService.findDtos("getPublishedAuctionListByRoleANDLoc",param);
		}else{
			tahdrList=tenderService.findDtos("getPublishedTahdrAndTahdrDetailListByRoleANDLoc",param);
		}
		response.addObject("tahdrList", tahdrList);
		return response;
	}

	@Override
	public CustomResponseDto getTenderList(int pageNumber, int pageSize, String searchColumn, String searchValue,
			String typeCode) {
		CustomResponseDto response=new CustomResponseDto();
		UserDetailsDto userDetail=contextService.getUserDetails();
		 Map<String, Object> newObjectMap= new HashMap<>();
		 Map<String, Object> objectMap = new HashMap<>();
		 long countResult = 0l;
		Map<String, Object> params=new HashMap<>();
		Map<String, Object> map=new HashMap<>();

		params.put("typeCode", typeCode);
		
		UserDto userDto=contextService.getUser();
		params.put("userId", userDto.getUserId());
		
		String locationType=userDetail.getLocationTypeRef();
		map.put("code",locationType);
		List<TAHDRDto> tahdrList=new ArrayList<TAHDRDto>();
		LocationTypeDto locationTypeDto=locationTypeService.findDto("getLocationTypeOnType", map);
		params.put("levels", locationTypeDto.getLevels());
		if(typeCode.equals("RA") || typeCode.equals("FA")){
			if(typeCode.equals("RA")){
				params.put("typeCode", "PT");
			}
			tahdrList=tenderService.getTenders("getPublishedAuctionListByRoleANDLoc", params, pageNumber, pageSize, searchColumn, searchValue);
		}else{
			
			tahdrList=tenderService.getTenders("getPublishedTahdrAndTahdrDetailListByRoleANDLoc", params, pageNumber, pageSize, searchColumn, searchValue);
		}
		countResult=tenderService.getPreBidTenderCount(typeCode,params,searchColumn,searchValue);
		newObjectMap.put("tahdrList", tahdrList);
		 int LastPage=(int) (countResult / pageSize);
		 LastPage = (int) ((LastPage)==1?LastPage:LastPage + 1);
		 newObjectMap.put("tahdrList", tahdrList);
		 objectMap.put("LastPage", LastPage);
		 response.setData( newObjectMap);
		 response.setObjectMap(objectMap);
		return response;
	}

	@Override
	public CustomResponseDto uploadMOM(TAHDRDto tahdr) {
		CustomResponseDto response=new CustomResponseDto();
		if(tahdr.getTahdrId()!=null ){
			String tenderNo=tahdr.getTahdrCode()==null?"Tender_XX":tahdr.getTahdrCode();
			if(tahdr.getMom().getAttachmentId()!=null){
				Map<String, Object> params= new HashMap<>();	
				params.put("tahdrId", tahdr.getTahdrId());
				AttachmentDto mom=new AttachmentDto();
		 		mom.setAttachmentId(tahdr.getMom().getAttachmentId());
		 		tahdr.setMom(mom);
		 		
				boolean result=tenderService.updateTahdrMom(tahdr);
				if(result){
					tahdr=tenderService.findDto("getTahdrWithAllDetail", params);
					sendNotification(tenderNo,params);
					response.addObject("TAHDR", tahdr);
					response.addObject("resultMessage", "Minutes Of Meeting Uploaded Successfully");
					response.addObject("resultStatus", true);
				}else{
					response.addObject("resultMessage", "Minutes Of Meeting Uploaded Failed");
					response.addObject("resultStatus", false);
				}
			}
			else{
				response.addObject("resultMessage", "Minutes Of Meeting not added");
				response.addObject("resultStatus", false);
			}
			
			
		}else{
			response.addObject("resultMessage", "Minutes Of Meeting not Uploaded");
			response.addObject("resultStatus", false);
		}
		return response;
	}
	
	
	private boolean sendNotification(String tenderNo,Map<String, Object> params){
		boolean result=false;
		List<String> mailList=new ArrayList<String>();
		List<BidderDto> bidderList=bidderService.findDtos("getQueryForMailListByTahdrId", params);
		if(!CommonUtil.isCollectionEmpty(bidderList)){
			for(BidderDto bidder:bidderList){
				UserDto user=bidder.getCreatedBy();
				if(user!=null){
					mailList.add(user.getEmail());
				}
			}
		}
		String sub="Tender Pre Bid meeting";
		String message="<p>Hi</p><br><h1><p>The Pre Bid Meeting of  'Tender: "+tenderNo+"'  has been completed successfully. Check the MOM in the portal. </h1><p>";
		result=bidderService.sendMailToAllBidder(mailList, sub, message);
		
		Map<String, String> smsParam = new HashMap<String, String>();
		smsParam.put(AppBaseConstant.SMS_PARAMETER_1, tenderNo.replaceAll(" ", "%20"));
		for(BidderDto bidderDto : bidderList){
			
			if(bidderDto.getCreatedBy() != null || bidderDto.getCreatedBy().getUserDetails() != null)
				result=smsService.sendSMS(bidderDto.getCreatedBy().getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_PREBID_MEETING_COMPLETED, smsParam);
		}
		return result;
	}
	

}
