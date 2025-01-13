package com.novelerp.eat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.appbase.master.dto.TilesRecordCountDto;
import com.novelerp.appbase.master.service.LocationTypeService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleAccessMasterDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TileRecordCountService;

@Service
public class TileRecordCountServiceImpl implements TileRecordCountService {
	@Autowired
	private BPartnerService bPartnerService;
	@Autowired
	private TAHDRService tahdrService;
	@Autowired
	private TAHDRDetailService tAHDRDetailService;
	@Autowired
	private LocationTypeService locationTypeService;
	
	@Autowired
	private PaymentDetailService paymentDetailService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private BPartnerService partnerService;

	@Override
	public TilesRecordCountDto getTilesRecordCounts(Map<Long, String> tileList) {
		TilesRecordCountDto recordCountDto = new TilesRecordCountDto();

		recordCountDto.setPartnersCount(bPartnerService.getRecordCount());
		recordCountDto.setMyTendersCount(tahdrService.getMyTendersCount());
		return recordCountDto;
	}

	@Override
	public TilesRecordCountDto getTenderTilesRecordCounts(List<String> tileList) {
		TilesRecordCountDto recordCountDto = new TilesRecordCountDto();
		recordCountDto.addCount("tenderApprovalCount", tahdrService.getRecordCount());
		recordCountDto.addCount("winnerSelectionCount", tahdrService.getTahdrWithDetailsForAwardWinnerCount());
		recordCountDto.addCount("preliminaryScrutinyCount", tAHDRDetailService.getPreliminaryScrutinyCount());
		return recordCountDto;
	}
	@Override
	public TilesRecordCountDto getTilesRecordCounts(List<RoleAccessMasterDto> tileList) {
		TilesRecordCountDto recordCountDto = new TilesRecordCountDto();
		for(RoleAccessMasterDto accessMasterDto : tileList){
			if(accessMasterDto.getTile().getEntity()!= null){
				Long recordCount = tahdrService.getRecordCount(accessMasterDto.getTile().getEntity(), accessMasterDto.getTile().getCountWhereCondition(), null);
				recordCountDto.addCount(accessMasterDto.getTile().getElementId(), recordCount);
			}else{
				
			}
		}
		return recordCountDto;
	}
	@Override
	public TilesRecordCountDto getTilesRecordCountsByTile(List<TileMasterDto> tileList) {
		TilesRecordCountDto recordCountDto = new TilesRecordCountDto();
		for(TileMasterDto tileMasterDto : tileList){
			if(tileMasterDto.getEntity()!= null){
				Long recordCount = tahdrService.getRecordCount(tileMasterDto.getEntity(),tileMasterDto.getCountWhereCondition(), null);
				recordCountDto.addCount(tileMasterDto.getElementId(), recordCount);
			}
		}
		return recordCountDto;
	}

	@Override
	public TilesRecordCountDto getWorkflowTilesRecordCounts(List<RoleAccessMasterDto> list, Map<String, Object> param) {
		TilesRecordCountDto recordCountDto = new TilesRecordCountDto();
		for(RoleAccessMasterDto accessMasterDto : list){
			if("Tender Approval".equals(accessMasterDto.getTile().getName()))
			{
				Long recordCount =getTenderApprovalCount(param);
				recordCountDto.addCount(accessMasterDto.getTile().getElementId(), recordCount);
			}
			else if("Auction Approval".equals(accessMasterDto.getTile().getName()))
			{
				Long recordCount =getAuctionApprovalCount(param);
				recordCountDto.addCount(accessMasterDto.getTile().getElementId(), recordCount);
			}
			else if("Payment Approval".equals(accessMasterDto.getTile().getName()))
			{
				Long recordCount =getPaymentApprovalCount();
				recordCountDto.addCount(accessMasterDto.getTile().getElementId(), recordCount);
			}
			else if("Registration".equals(accessMasterDto.getTile().getName()))
			{
				Long recordCount =getPartnerApprovalCount();
				recordCountDto.addCount(accessMasterDto.getTile().getElementId(), recordCount);
			}
			else if("Infra Approval".equals(accessMasterDto.getTile().getName()))
			{
				Long recordCount =getInfraApprovalCount();
				recordCountDto.addCount(accessMasterDto.getTile().getElementId(), recordCount);
			}
			else if("Preliminary Scrutiny".equals(accessMasterDto.getTile().getName()))
			{
				Long recordCount =getPreliminaryScrutinyCount();
				recordCountDto.addCount(accessMasterDto.getTile().getElementId(), recordCount);
			}
			else if("Final Scrutiny".equals(accessMasterDto.getTile().getName()))
			{
				Long recordCount =getFinalScrutinyCount();
				recordCountDto.addCount(accessMasterDto.getTile().getElementId(), recordCount);
			}
			else if("Auction Preliminary Scrutiny".equals(accessMasterDto.getTile().getName()))
			{
				Long recordCount =getAuctionPreliminaryScrutinyCount();
				recordCountDto.addCount(accessMasterDto.getTile().getElementId(), recordCount);
			}
			else if("Auction Final Scrutiny".equals(accessMasterDto.getTile().getName()))
			{
				Long recordCount =getAuctionFinalScrutinyCount();
				recordCountDto.addCount(accessMasterDto.getTile().getElementId(), recordCount);
			}
			
		}
		return recordCountDto;
	}
	
	public long getTenderApprovalCount( Map<String, Object> param){
		long countResult = tahdrService.getTahdrApprovalListQueryCount(param);
		
		
		return countResult;
		
	}
	public long getAuctionApprovalCount( Map<String, Object> param){
		long countResult = tahdrService.getAuctionApprovalListQueryCount(param);
		
		
		return countResult;
		
	}
	public long getPaymentApprovalCount( ){
		UserDetailsDto userDetail=contextService.getUserDetails();
		String locationType=userDetail.getLocationTypeRef();
		UserDetailsDto officeLocation=userDetailsService.findDto("getOfficeLocationFromUserDetailsID", AbstractServiceImpl.getParamMap("userDetailsId",userDetail.getUserDetailsId()));
	
		long countResult = paymentDetailService.getPaymentStatusCount(null,"none", "none",locationType,officeLocation.getOfficeLocation().getName());
		
		
		return countResult;
		
	}
	public long getPartnerApprovalCount( ){
		BPartnerDto partnerDto = contextService.getPartner();
		Long partnerId = partnerDto.getbPartnerId();
		
	
		long countResult = partnerService.getCountForPrtnrAprvl(partnerId,"none","none");
		
		
		return countResult;
		
	}
	public long getInfraApprovalCount( ){
		BPartnerDto partnerDto = contextService.getPartner();
	
		
	
		long countResult = 0l;/*partnerService.getInfraApprovalVendorsCount(partnerDto);*/		
		
		return countResult;
		
	}
	public long getPreliminaryScrutinyCount( ){
		Map<String, Object> params= new HashMap<>();
		RoleDto role = contextService.getDefaultRole();
		UserDto user=contextService.getUser();
		if(AppBaseConstant.ROLE_AUDITOR_USER.equals(role.getValue())){
		 params.put("userId", user.getUserId());
		}
		 params.put("status", AppBaseConstant.TENDER_TECHNO_COMMERCIAL_OPENNING);
		
	
		long countResult = tAHDRDetailService.getPreliminaryScrutinyTenderCountForWorkflow(role.getValue(), null, params,"none","none");	
	
		return countResult;
		
	}
	public long getFinalScrutinyCount( ){
		Map<String, Object> params= new HashMap<>();
		RoleDto role = contextService.getDefaultRole();
		UserDto user=contextService.getUser();
		if(AppBaseConstant.ROLE_AUDITOR_USER.equals(role.getValue())){
		 params.put("userId", user.getUserId());
		}
		 params.put("status", AppBaseConstant.TENDER_DEVIATION_OPENNING);
		
	
		long countResult = tAHDRDetailService.getPreliminaryScrutinyTenderCountForWorkflow(role.getValue(), null, params,"none","none");	
	
		return countResult;
		
	}
	public long getAuctionPreliminaryScrutinyCount( ){
		Map<String, Object> params= new HashMap<>();
		RoleDto role = contextService.getDefaultRole();
		UserDto user=contextService.getUser();
		if(AppBaseConstant.ROLE_AUDITOR_USER.equals(role.getValue())){
		 params.put("userId", user.getUserId());
		}
		 params.put("status", AppBaseConstant.TENDER_TECHNO_COMMERCIAL_OPENNING);
		
	
		long countResult = tAHDRDetailService.getAuctionPreliminaryScrutinyTenderCountForWorkflow(role.getValue(), null, params,"none","none");	
	
		return countResult;
		
	}
	public long getAuctionFinalScrutinyCount( ){
		Map<String, Object> params= new HashMap<>();
		RoleDto role = contextService.getDefaultRole();
		UserDto user=contextService.getUser();
		if(AppBaseConstant.ROLE_AUDITOR_USER.equals(role.getValue())){
		 params.put("userId", user.getUserId());
		}
		 params.put("status", AppBaseConstant.TENDER_DEVIATION_OPENNING);
		
	
		long countResult = tAHDRDetailService.getAuctionPreliminaryScrutinyTenderCountForWorkflow(role.getValue(), null, params,"none","none");	
	
		return countResult;
		
	}

	
	
}
