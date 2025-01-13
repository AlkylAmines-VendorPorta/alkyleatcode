package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.util.AuthGenerator;
import com.novelerp.core.util.AuthManager;
import com.novelerp.eat.dao.TenderCommitteeParticipantDao;
import com.novelerp.eat.dto.TenderCommitteeParticipantDto;
import com.novelerp.eat.entity.TenderCommitteeParticipant;
import com.novelerp.eat.service.TenderCommitteeParticipantService;
import com.novelerp.eat.util.TenderContext;

/**
 * 
 * @author varsha
 *
 */
@Service
public class TenderCommitteeParticipantServiceImpl extends AbstractContextServiceImpl<TenderCommitteeParticipant, TenderCommitteeParticipantDto> implements TenderCommitteeParticipantService{
    
	@Autowired
	private TenderCommitteeParticipantDao tenderCommitteeParticipantDao;
	
	 @Autowired
	 private AuthManager authManager;
		
	 @Autowired
	 private MailService mailService;
    
    @Autowired
	private TenderContext  tenderContext;
	@PostConstruct
	void init(){
		super.init(TenderCommitteeParticipantServiceImpl.class, tenderCommitteeParticipantDao, TenderCommitteeParticipant.class, TenderCommitteeParticipantDto.class);
		setByPassProxy(true);
	}
	@Override
	public boolean checkTenderOppening(Long tenderCommiteeId){
		boolean result=false;
		List<Long> participantIdList=new ArrayList<Long>();
		Long tahdrId=0l;
		if(tenderCommiteeId!=null){
			Map<String, Object> map= new HashMap<>();
			map.put("tenderCommitteId", tenderCommiteeId);
			List<TenderCommitteeParticipantDto> committeeParticipantList=findDtos("getQueryForCommitteeParticipant", map);
			if(!CommonUtil.isCollectionEmpty(committeeParticipantList)){
				int count=0;
				tahdrId=committeeParticipantList.get(0).getTenderCommittee().getTahdr().getTahdrId();
				for(TenderCommitteeParticipantDto dto:committeeParticipantList){
					if(count==0){
						result=tenderContext.checkLoggedChairPerson(tahdrId, dto.getTenderCommittee().getChairPerson().getUserId());
					}
					count++;
					if(result){
						participantIdList.add(dto.getUser().getUserId());
					}
					else{
						return false;
					}
				}
				result=tenderContext.checkLoggedUser(tahdrId, participantIdList);
				return result;
			}
		}
		
		return false;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean sendSessionKeyToParticipant(List<TenderCommitteeParticipantDto> participantList,String tenderNo){
		boolean sessionKeyUpdated=false;
		if(!CommonUtil.isCollectionEmpty(participantList)){
			for(TenderCommitteeParticipantDto participant:participantList){
				String participantMailId=participant.getUser()==null?"":participant.getUser().getEmail();
				String isMailed=participant.getIsMailed()==null?"N":participant.getIsMailed();
				Long committeParticipantId=participant.getCommitteeParticipantId()==null?null:participant.getCommitteeParticipantId();
				if(!"".equals(participantMailId) && isMailed.equals("N")){
					String key=getKey();
					String encryptedKey=authManager.getSaltedHash(key, AppBaseConstant.SESSION_KEY);
					sessionKeyUpdated=updateSessionKey(committeParticipantId,encryptedKey);
					if(sessionKeyUpdated){
						MailDto mailDto = new MailDto();
						mailDto.setSubject("Opening Session Key");
						String message="<p>Hi</p><br><h1><p>The Session Key  for Tender: <b>"+tenderNo+"</b> is <b>'"+key+"'</b>.Use these key while tender opening. </h1><p>"
								+ " <br><h1><p> Regards,<br>Mahavitran State Electricity Board</p></h1>";
						mailDto.setMailContent(message);
						if(!"".equals(participantMailId)){
									mailDto.getRecipientList().add(participantMailId);
									boolean result=mailService.sendEmailWithResult(mailDto,true);
									if(result){
										/*isMailed(committeParticipantId);*/
									}
					      }
					}
				}
			}
			return true;
		}
		return sessionKeyUpdated;
	}
	private String getKey(){
		String sessionKey= AuthGenerator.generateToken(8, 8, 2, 2, 1);
		return sessionKey;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean updateSessionKey(Long committeeParticipantId,String sessionKey){
		int result=0;
		result =tenderCommitteeParticipantDao.updateSessionKey(committeeParticipantId, sessionKey);
		if(result>0){
			return true;
		}
		return false;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private boolean isMailed(Long committeeParticipantId){
		int result=0;
		result =tenderCommitteeParticipantDao.isSessionkeyMailed(committeeParticipantId);
		if(result>0){
			return true;
		}
		return false;
		
	}
}
