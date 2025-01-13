/**
 * @author Ankush
 */
package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.LocationTypeService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.eat.dao.TAHDRDao;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.MyTenderService;
import com.novelerp.eat.service.TAHDRService;

@Service
public class MyTenderServiceImpl  implements MyTenderService {

	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private TAHDRDao tahdrDao;
	
	@Autowired
	private LocationTypeService locationTypeService;
	
	
	@Override
	public List<ReferenceListDto> getMyTenderLinks(String role) {
		Map<String,ReferenceListDto> myTenderButtons=refListService.getRefListCollection(AppBaseConstant.MY_TENDER_BUTTON);
		List<ReferenceListDto> accessAction=new ArrayList<>();
		if(!(role==null) && !role.equals("")){
			switch (role) {
			case ContextConstant.USER_TYPE_VENDOR_ADMIN:{
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_BID_SUBMISSION));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_C1_BID));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_DVTN_BID));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_REVISED_BID));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_TC_OPENING));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_PB_OPENING));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_C1_OPENING));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_DV_OPENING));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_WINNER));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_LIVE_BID));
				break;
			}
				
			
			case ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER:{
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_TC_SCHEDULING));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_PB_SCHEDULING));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_C1_SCHEDULING));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_RB_SCHEDULING));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_TC_OPENING));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_PB_OPENING));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_C1_OPENING));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_DV_OPENING));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_WINNER));
			    break;
			}
				
	/*Added By AMAN	*/		
			case ContextConstant.USER_TYPE_SCRUTINY_ENGINEER:{
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_DV_SCHEDULING));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_PRELIMINARY_SCRUTINY));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_FINAL_SCRUTINY));
				break;
			}
				
			case ContextConstant.USER_TYPE_AUDITOR:{
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_PRELIMINARY_SCRUTINY));
				accessAction.add(myTenderButtons.get(AppBaseConstant.MY_TENDER_FINAL_SCRUTINY));
				break;
			}
			
			default:
				break;
				
		}
	}
 return accessAction;
	}

	@Override
	public boolean validateAccessAction(String action){
		List<ReferenceListDto> accessAction= getMyTenderLinks(contextService.getDefaultRole().getValue());
		for(ReferenceListDto actionAllowed: accessAction){
			if(actionAllowed.getCode().equals(action)){
				return true;
			}
		}
		return false;
	}

	@Override
	public List<TAHDRDto> getAuctionByTypeCode(String tenderTypeCode,Long partnerId,int pageNumber,int pageSize,String searchMode,String searchValue){
		List<TAHDRDto> tahdrList=null;
		RoleDto roleDto= contextService.getDefaultRole();
		String role=roleDto.getValue();
		try{
			
			Map<String,Object> listParam=new HashMap<String,Object>();
			listParam.put("typeCode",tenderTypeCode);
			
			if(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role)){
				UserDto loggedInUser=contextService.getUser();
				listParam.put("userId",loggedInUser.getUserId());
				
				UserDetailsDto userDetail=contextService.getUserDetails();
				String locationType=userDetail.getLocationTypeRef();
				Map<String,Object> param=new HashMap<>();
				param.put("code",locationType);
				LocationTypeDto locationTypeDto=locationTypeService.findDto("getLocationTypeOnType", param);
				
				listParam.put("levels", locationTypeDto.getLevels());
				String queryString = tahdrDao.getAuctionListByRoleANDLoc(searchMode,searchValue);
				if (!"none".equalsIgnoreCase(searchValue)) {
					listParam.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				} 
				tahdrList= tahdrService.findDtosByQuery(queryString,listParam, pageNumber, pageSize);
			}else if(ContextConstant.USER_TYPE_VENDOR_ADMIN.equals(role)){
				Map<String,Object> map=new HashMap<>();
				map.put("partnerId", partnerId);
				map.put("typeCode", tenderTypeCode);
				String queryString = tahdrDao.getMyAuctionForBidder(searchMode,searchValue);
				if (!"none".equalsIgnoreCase(searchValue)) {
					map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				} 
				tahdrList= tahdrService.findDtosByQuery(queryString,map, pageNumber, pageSize);
			}else{
				List<String> listOfTenders = tahdrService.getAccessClause(roleDto.getValue());
				listParam.put("tenderStatusList", listOfTenders);
				String queryString = tahdrDao.getAuctionListsByRole(searchMode,searchValue);
				if (!"none".equalsIgnoreCase(searchValue)) {
					listParam.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				}
				tahdrList= tahdrService.findDtosByQuery(queryString,listParam, pageNumber, pageSize);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return tahdrList;
	}
	@Override
	public List<TAHDRDto> getTahdrByTypeCode(String tenderTypeCode,Long partnerId,int pageNumber,int pageSize,String searchMode,String searchValue){
		List<TAHDRDto> tahdrList=null;
		RoleDto roleDto= contextService.getDefaultRole();
		String role=roleDto.getValue();
		try{
			
			Map<String,Object> listParam=new HashMap<String,Object>();
			listParam.put("typeCode",tenderTypeCode);
			UserDto loggedInUser=contextService.getUser();
			if(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role)){
				
				listParam.put("userId",loggedInUser.getUserId());
				
				UserDetailsDto userDetail=contextService.getUserDetails();
				String locationType=userDetail.getLocationTypeRef();
				Map<String,Object> param=new HashMap<>();
				param.put("code",locationType);
				LocationTypeDto locationTypeDto=locationTypeService.findDto("getLocationTypeOnType", param);
				
				listParam.put("levels", locationTypeDto.getLevels());
				String queryString = tahdrDao.getTahdrAndTahdrDetailListByRoleANDLoc(searchMode,searchValue);
				if (!"none".equalsIgnoreCase(searchValue)) {
					listParam.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				} 
				tahdrList= tahdrService.findDtosByQuery(queryString,listParam, pageNumber, pageSize);
			}else if(ContextConstant.USER_TYPE_VENDOR_ADMIN.equals(role)){
				Map<String,Object> map=new HashMap<>();
				map.put("partnerId", partnerId);
				map.put("typeCode", tenderTypeCode);
				String queryString = tahdrDao.getMyTenderForBidder(searchMode,searchValue);
				if (!"none".equalsIgnoreCase(searchValue)) {
					map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				} 
				tahdrList= tahdrService.findDtosByQuery(queryString,map, pageNumber, pageSize);
			}else if(ContextConstant.USER_TYPE_AUDITOR.equals(role)){
				List<String> listOfTenders = tahdrService.getAccessClause(roleDto.getValue());
				listParam.put("tenderStatusList", listOfTenders);
				listParam.put("userId",loggedInUser.getUserId());
				String queryString = tahdrDao.getTenderListsAuditorId(searchMode,searchValue);
				if (!"none".equalsIgnoreCase(searchValue)) {
					listParam.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				} 
				tahdrList= tahdrService.findDtosByQuery(queryString,listParam, pageNumber, pageSize);
			}
			else{
				List<String> listOfTenders = tahdrService.getAccessClause(roleDto.getValue());
				listParam.put("tenderStatusList", listOfTenders);
				String queryString = tahdrDao.getTenderListsByRole(searchMode,searchValue);
				if (!"none".equalsIgnoreCase(searchValue)) {
					listParam.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				} 
				tahdrList= tahdrService.findDtosByQuery(queryString,listParam, pageNumber, pageSize);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return tahdrList;
	}
	
	@Override
	public long getAuctionByTypeCount(String tenderTypeCode,Long partnerId,String searchColumn, String searchValue) {
		long totalCount=0;
		RoleDto roleDto= contextService.getDefaultRole();
		String role=roleDto.getValue();
		try{
			Map<String,Object> listParam=new HashMap<String,Object>();
			listParam.put("typeCode",tenderTypeCode);
			
			if(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role)){
				UserDto loggedInUser=contextService.getUser();
				/*listParam.put("userId",loggedInUser.getUserId());*/
				
				UserDetailsDto userDetail=contextService.getUserDetails();
				String locationType=userDetail.getLocationTypeRef();
				Map<String,Object> param=new HashMap<>();
				param.put("code",locationType);
				LocationTypeDto locationTypeDto=locationTypeService.findDto("getLocationTypeOnType", param);
				
				/*listParam.put("levels", locationTypeDto.getLevels());*/
				/*String queryString =tahdrDao.getAuctionListByRoleANDLocCountQry(searchColumn, searchValue);
				if (!"none".equalsIgnoreCase(searchValue)) {
					listParam.put("searchValue", "%" + searchValue.toUpperCase() + "%");
					totalCount = tahdrService.getRecordCount(queryString, listParam);
				}else{
					totalCount = tahdrService.getRecordCount(queryString, listParam);
				}
				*/
				totalCount =tahdrDao.getAuctionListByRoleANDLocCountQry(tenderTypeCode,loggedInUser.getUserId(),locationTypeDto.getLevels(),searchColumn,searchValue);
			}else if(ContextConstant.USER_TYPE_VENDOR_ADMIN.equals(role)){
				/*Map<String,Object> map=new HashMap<>();
				map.put("partnerId", partnerId);
				map.put("typeCode", tenderTypeCode);*/
				totalCount =tahdrDao.getMyAuctionForBidderCountQry(tenderTypeCode,partnerId,searchColumn,searchValue);
				/*if (!"none".equalsIgnoreCase(searchValue)) {
					map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
					totalCount = tahdrService.getRecordCount(queryString, map);
				}else{
					totalCount = tahdrService.getRecordCount(queryString, map);
				}*/
			}
			else{
				List<String> listOfTenders = tahdrService.getAccessClause(roleDto.getValue());
				listParam.put("tenderStatusList", listOfTenders);
				/*String queryString = tahdrDao.getAuctionListsByRole(searchMode,searchValue);*/
				String queryString =tahdrDao.getAuctionListsByRoleCountQry(searchColumn, searchValue);
				if (!"none".equalsIgnoreCase(searchValue)) {
					listParam.put("searchValue", "%" + searchValue.toUpperCase() + "%");
					totalCount = tahdrService.getRecordCount(queryString, listParam);
				}else{
					totalCount = tahdrService.getRecordCount(queryString, listParam);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		return totalCount;
	}
	
	@Override
	public long getTahdrByTypeCodeCount(String tenderTypeCode,Long partnerId,String searchColumn, String searchValue) {
		long totalCount = 0;
		RoleDto roleDto= contextService.getDefaultRole();
		String role=roleDto.getValue();
		try{
			Map<String,Object> listParam=new HashMap<String,Object>();
			listParam.put("typeCode",tenderTypeCode);
			UserDto loggedInUser=contextService.getUser();
			if(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role)){
				
				/*listParam.put("userId",loggedInUser.getUserId());*/
				
				UserDetailsDto userDetail=contextService.getUserDetails();
				String locationType=userDetail.getLocationTypeRef();
				Map<String,Object> param=new HashMap<>();
				param.put("code",locationType);
				LocationTypeDto locationTypeDto=locationTypeService.findDto("getLocationTypeOnType", param);
				
				/*listParam.put("levels", locationTypeDto.getLevels());*/
				totalCount =tahdrDao.getTahdrAndTahdrDetailListByRoleANDLocCountQry(tenderTypeCode,loggedInUser.getUserId(),locationTypeDto.getLevels(),searchColumn, searchValue);
				/*if (!"none".equalsIgnoreCase(searchValue)) {
					listParam.put("searchValue", "%" + searchValue.toUpperCase() + "%");
					totalCount = tahdrService.getRecordCount(queryString, listParam);
				}else{
					totalCount = tahdrService.getRecordCount(queryString, listParam);
				}*/
			}else if(ContextConstant.USER_TYPE_VENDOR_ADMIN.equals(role)){
				totalCount =tahdrDao.getMyTenderForBidderCountQry(tenderTypeCode,partnerId,searchColumn, searchValue);
			}else if(ContextConstant.USER_TYPE_AUDITOR.equals(role)){
					List<String> listOfTenders = tahdrService.getAccessClause(roleDto.getValue());
					listParam.put("tenderStatusList", listOfTenders);
					listParam.put("userId",loggedInUser.getUserId());
					String queryString =tahdrDao.getTenderListsAuditorIdCountQry(searchColumn, searchValue);
					if (!"none".equalsIgnoreCase(searchValue)) {
						listParam.put("searchValue", "%" + searchValue.toUpperCase() + "%");
						totalCount = tahdrService.getRecordCount(queryString, listParam);
					}else{
						totalCount = tahdrService.getRecordCount(queryString, listParam);
					}
			}else{
				List<String> listOfTenders = tahdrService.getAccessClause(roleDto.getValue());
				listParam.put("tenderStatusList", listOfTenders);
				String queryString =tahdrDao.getTenderListsByRoleCountQry(searchColumn, searchValue);
				if (!"none".equalsIgnoreCase(searchValue)) {
					listParam.put("searchValue", "%" + searchValue.toUpperCase() + "%");
					totalCount = tahdrService.getRecordCount(queryString, listParam);
				}else{
					totalCount = tahdrService.getRecordCount(queryString, listParam);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return totalCount;
	}
}
