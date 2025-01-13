package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.util.AuthGenerator;
import com.novelerp.core.util.AuthManager;
import com.novelerp.eat.dao.TenderCommitteeDao;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.dto.TenderCommitteeParticipantDto;
import com.novelerp.eat.entity.TenderCommittee;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TenderCommitteeParticipantService;
import com.novelerp.eat.service.TenderCommitteeService;

/**
 * 
 * @author varsha
 *
 */
@Service
public class TenderCommitteeServiceImpl extends AbstractContextServiceImpl<TenderCommittee, TenderCommitteeDto> implements TenderCommitteeService{
    @Autowired
	private TenderCommitteeDao tenderCommitteeDao;
    
    @Autowired
	private AuthManager authManager;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private TAHDRService tAHDRService;
	
	@Autowired
	private TenderCommitteeParticipantService  tenderCommitteeParticipantService;
	
	@PostConstruct
	void init(){
		super.init(TenderCommitteeServiceImpl.class, tenderCommitteeDao, TenderCommittee.class, TenderCommitteeDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional
	public TenderCommitteeDto saveTenderCommittee(TenderCommitteeDto tenderCommittee,TenderCommitteeDto dto) {
			
		if(null!=tenderCommittee && !CommonUtil.isEqual(dto.getTenderCommitteeId(), tenderCommittee.getTenderCommitteeId()))
		{
			dto.setResponse(new ResponseDto(true, "Committee Already Exists..!"));
		}else if(null!=tenderCommittee && CommonUtil.isEqual(dto.getTenderCommitteeId(), tenderCommittee.getTenderCommitteeId()))
                {
			            dto=updateDto(dto);
			           /* setOtherTenderCommitteeInActive(dto);*/
                }else if(null==tenderCommittee && dto.getTenderCommitteeId()==null){
                	dto.setIsProcessed("N");
                	dto=save(dto);
                }else{
                	dto.setResponse(new ResponseDto(true, "Error in porocessing"));
                }

		return dto;
	}
	/*private boolean setOtherTenderCommitteeInActive(TenderCommitteeDto tenderCommittee){

		int result=0;
		Long tahdrId=tenderCommittee.getTahdr().getTahdrId();
		Long tahdrDetailId=tenderCommittee.getTenderVersion().getTahdrDetailId();
		String bidOpeningType=tenderCommittee.getBidOpeningType();
		result =tenderCommitteeDao.setOtherTenderCommitteeInActive(tahdrId, tahdrDetailId, bidOpeningType);
		if(result>0){
			return true;
		}
		return false;
	}*/
	@Override
	public List<TenderCommitteeDto> getAssociatedTenders(TenderCommitteeDto tenderCommitteeDto,UserDto user){
		 List<TenderCommitteeDto> tenderCommittee=new ArrayList<TenderCommitteeDto>();
		Map<String, Object> params=getParameterMap(tenderCommitteeDto,user);
		/*String query = tenderCommitteeDao.getAssociatedTendersQuery(tenderCommitteeDto);*/
		String query = tAHDRService.getOpeningTenderByUserId(tenderCommitteeDto);
		tenderCommittee=findDtosByQuery(query, params);
		return tenderCommittee;
	}
	
	private Map<String,Object> getParameterMap(TenderCommitteeDto tenderCommitteeDto,UserDto user){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isActive", "Y");
		if( user!=null){
			params.put("userId", user.getUserId());
		}
		if(tenderCommitteeDto.getTahdr().getTahdrTypeCode()!=null){
			params.put("tahdrTypeCode",tenderCommitteeDto.getTahdr().getTahdrTypeCode());
		}
		if(tenderCommitteeDto.getTenderVersion().getTechBidOpenningDate()!=null){
			Date technicalOpening =  tenderCommitteeDto.getTenderVersion().getTechBidOpenningDate() ;
			params.put("technicalOpening", technicalOpening);
		}
		if(tenderCommitteeDto.getTenderVersion().getDeviationOpenningDate()!=null ){
			Date deviationOpening =tenderCommitteeDto.getTenderVersion().getDeviationOpenningDate();
			params.put("deviationOpening", deviationOpening );
		}	
		if(tenderCommitteeDto.getTenderVersion().getC1OpenningDate()!=null){
			Date c1Opening = tenderCommitteeDto.getTenderVersion().getC1OpenningDate();
			params.put("c1Opening", c1Opening);
		}
		if(tenderCommitteeDto.getTenderVersion().getPriceBidOpenningDate()!=null){
			Date priceBidOpening = tenderCommitteeDto.getTenderVersion().getPriceBidOpenningDate();
			params.put("priceBidOpening", priceBidOpening);
		}
		if(tenderCommitteeDto.getTahdr()!=null){
			if(!tenderCommitteeDto.getTahdr().getTahdrCode().equals("")){
				params.put("tahdrCode","%"+tenderCommitteeDto.getTahdr().getTahdrCode()+"%");
			}
		}
		
		return params;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean generateSessionKey(Long tenderCommitteId,TenderCommitteeDto tenderCommittee,List<TenderCommitteeParticipantDto> participantList){
			boolean isChairpersonMailed=sendSessionKeyToChairperson(tenderCommitteId,tenderCommittee);
			String tenderNo=tenderCommittee.getTahdr()==null?"XXX":tenderCommittee.getTahdr().getTahdrCode();
			boolean isParticipantMailed=tenderCommitteeParticipantService.sendSessionKeyToParticipant(participantList,tenderNo);
			boolean result= (isChairpersonMailed && isParticipantMailed);
			return result;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private boolean sendSessionKeyToChairperson(Long tenderCommitteId,TenderCommitteeDto tenderCommittee){
		boolean sessionKeyUpdated=false;
		if(tenderCommittee!=null){
			String isMailed=tenderCommittee.getIsMailed()==null?"N":tenderCommittee.getIsMailed();
			if( isMailed.equals("N")){
				String key=getKey();
				String encryptedKey=authManager.getSaltedHash(key, AppBaseConstant.SESSION_KEY);
				sessionKeyUpdated=updateSessionKey(tenderCommitteId,encryptedKey);
				if(sessionKeyUpdated){
					String tenderNo=tenderCommittee.getTahdr()==null?"XXX":tenderCommittee.getTahdr().getTahdrCode();
					String chairPersonEmail=tenderCommittee.getChairPerson()==null?"":tenderCommittee.getChairPerson().getEmail();
					MailDto mailDto = new MailDto();
					mailDto.setSubject("Opening Session Key");
					String message="<p>Hi</p><br><h1><p>The Session Key  for Tender: <b>"+tenderNo+"</b> is <b>'"+key+"'</b>.Use these key while tender opening. </h1><p>"
							+ " <br><h1><p> Regards,<br>Mahavitran State Electricity Board</p></h1>";
					mailDto.setMailContent(message);
					if(!"".equals(chairPersonEmail)){
								/*System.out.println(chairPersonEmail);*/
								mailDto.getRecipientList().add(chairPersonEmail);
								boolean result=mailService.sendEmailWithResult(mailDto,true);
								if(result){
									/*isMailed(tenderCommitteId);*/
								}
				      }
				}
			}else
				return true;
		}
		return sessionKeyUpdated;
	}
	
	private String getKey(){
		String sessionKey= AuthGenerator.generateToken(8, 8, 2, 2, 1);
		return sessionKey;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private boolean updateSessionKey(Long tenderCommitteeId,String sessionKey){
		int result=0;
		result =tenderCommitteeDao.updateSessionKey(tenderCommitteeId, sessionKey);
		if(result>0){
			return true;
		}
		return false;
		
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private boolean isMailed(Long tenderCommitteeId){
		int result=0;
		result =tenderCommitteeDao.isSessionkeyMailed(tenderCommitteeId);
		if(result>0){
			return true;
		}
		return false;
		
	}
	@Override
	public boolean isChairPerson(Long tenderCommitteId,Long userId){
		Map<String, Object> map= new HashMap<>();
		map.put("tenderCommitteId", tenderCommitteId);
		map.put("userId", userId);
		TenderCommitteeDto result=findDto("getChairPerson", map);
		if(result!=null){
			return true;
		}
		return false;
	}
	
	@Override
 	public List<TenderCommitteeDto> getTenderCommitteeByBidopeningType(String openingType){
 		List<TenderCommitteeDto> tenderCommitteeList=new ArrayList<TenderCommitteeDto>();
		Map<String, Object> params= new HashMap<>();
		params.put("status3", null);
		params.put("openingType", openingType);
		if(openingType.equals(AppBaseConstant.TENDER_COMMITTEE_TechnoCommercial_Opening)){
			params.put("status1", AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
			params.put("status2", AppBaseConstant.TENDER_TECHNO_COMMERCIAL_OPENNING);
		}else if(openingType.equals(AppBaseConstant.TENDER_COMMITTEE_Deviation_Opening)){
			params.put("status1", AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING);
			params.put("status2", AppBaseConstant.TENDER_DEVIATION_OPENNING);
		}else if(openingType.equals(AppBaseConstant.TENDER_COMMITTEE_PriceBid_Opening)){
			params.put("status3", AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
			params.put("status1", AppBaseConstant.TENDER_PRICE_BID_SCHEDULING);
			params.put("status2", AppBaseConstant.TENDER_PRICE_BID_OPENNING);
		}else if(openingType.equals(AppBaseConstant.TENDER_COMMITTEE_RevisedBid_Opening)){
			params.put("status1", AppBaseConstant.TENDER_REVISED_BID_SCHEDULING);
			params.put("status2", AppBaseConstant.TENDER_REVISED_BID_OPENNING);
		}else if(openingType.equals(AppBaseConstant.TENDER_COMMITTEE_Annexure_Opening)){
			params.put("status1", AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING);
			params.put("status2", AppBaseConstant.TENDER_ANNEXURE_C1_OPENNING);
		}else{
			return tenderCommitteeList;
		}
		tenderCommitteeList=findDtos("getTenderCommitteesByOpeningType", params);
		return tenderCommitteeList;
	}
	public long getCommitteeCount(String openingType,String typeCode,Map<String, Object> params,String searchColumn, String searchValue){
		long resultCount=0l;
		String searchParam="";
		if(typeCode !=null){
			if (!"none".equalsIgnoreCase(searchValue)) {
				params.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				searchParam=" AND c.tahdr."+searchColumn+" LIKE :searchValue";
			}
			String whereCondition=" c.bidOpeningType=:openingType AND c.tahdr.tahdrTypeCode IN (:typeCode1,:typeCode2) "+searchParam+" ";
			resultCount= getRecordCount(whereCondition, params);
		}else{
			return resultCount;
		}
		return resultCount;
	}
	
	public List<TenderCommitteeDto> getCommittee(String queryMethodName,Map<String, Object> params,int pageNumber, int pageSize,String searchColumn, String searchValue){
		List<TenderCommitteeDto> tenderCommitteeList=new ArrayList<TenderCommitteeDto>();
		String query = invokeQueryMethod(tenderCommitteeDao, queryMethodName);
		StringBuilder customeQuery=new StringBuilder(query);
		if (!"none".equalsIgnoreCase(searchValue)) {
			query=customeQuery.append(" AND c.tahdr."+searchColumn+" LIKE :searchValue ORDER BY c.updated DESC").toString();
			params.put("searchValue",
					"%" + searchValue.toUpperCase() + "%");
		}else{
			query=customeQuery.append("  ORDER BY c.updated DESC").toString();
		}
		tenderCommitteeList = findDtosByQuery(query, params, pageNumber, pageSize);
		return tenderCommitteeList;
	}
	
}
