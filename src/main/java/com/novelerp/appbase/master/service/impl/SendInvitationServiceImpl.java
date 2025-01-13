package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.SendInvitationDao;
import com.novelerp.appbase.master.dto.MBPartnerInvitationDto;
import com.novelerp.appbase.master.entity.MBPartnerInvitation;
import com.novelerp.appbase.master.service.SendInvitationService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
@Service
public class SendInvitationServiceImpl extends AbstractContextServiceImpl<MBPartnerInvitation, MBPartnerInvitationDto> implements SendInvitationService {
@Autowired
private SendInvitationDao sendInvitationDao;

@Autowired
 @Qualifier("jwtUserContext") private ContextService contextService;


@PostConstruct
private void init() {
	super.init(SendInvitationServiceImpl.class, sendInvitationDao, MBPartnerInvitation.class, MBPartnerInvitationDto.class);
	setByPassProxy(true);
}
	
@Override
@Transactional(propagation=Propagation.REQUIRED)
	public MBPartnerInvitationDto saveInvitationDetails(BPartnerDto bpartnerdto,BPartnerDto requestedPartner, UserDto userdto,boolean isAutoApproved) {
		MBPartnerInvitationDto mBpartnerInvitationdto = new MBPartnerInvitationDto();
		mBpartnerInvitationdto.setIsInvitationApproved(isAutoApproved?"Y":"N");
		mBpartnerInvitationdto.setIsInvitationSend("Y");
		mBpartnerInvitationdto.setVendorEmailId(userdto.getEmail());
		//mBpartnerInvitationdto.setVendorPancardNo(userdto.getPartner().getPanNumber());
		mBpartnerInvitationdto.setCompanyName(userdto.getPartner().getName());
		mBpartnerInvitationdto.setFirstName(userdto.getUserDetails().getFirstName());
		mBpartnerInvitationdto.setLastName(userdto.getUserDetails().getLastName());
		mBpartnerInvitationdto.setMobileNo(userdto.getUserDetails().getMobileNo());
		mBpartnerInvitationdto.setPartner(bpartnerdto);
		mBpartnerInvitationdto.setRequestedPartner(requestedPartner);
		mBpartnerInvitationdto=save(mBpartnerInvitationdto);
		return mBpartnerInvitationdto;
	}

@Override
public List<MBPartnerInvitation> getPendingRequestList() {
	List<MBPartnerInvitation> mBPartnerInvitation = new ArrayList<MBPartnerInvitation>();
	mBPartnerInvitation =sendInvitationDao.findAll();
	return mBPartnerInvitation;
}

@Override
@Transactional(propagation=Propagation.REQUIRED)
public CustomResponseDto updateInvitationStation(MBPartnerInvitationDto invitation) {
	CustomResponseDto response=new CustomResponseDto();
	if(invitation!=null){
		Map<String, Object> propertyValueMap=AbstractServiceImpl.getParamMap("isInvitationApproved",invitation.getIsInvitationApproved());
		propertyValueMap.put("requestedPartner.bPartnerId", contextService.getPartner().getbPartnerId());
		int count=sendInvitationDao.updateByJpql(propertyValueMap, "mBPartnerInvitationId", invitation.getmBPartnerInvitationId());
		if(count>0){
			response.addObject("RESULT_MESSAGE", "Update Successfully !");
			response.addObject("RESULT_STATUS",true);
			BPartnerDto bPartnerDto =contextService.getPartner();
			Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("PanNo", bPartnerDto.getPanNumber());
			List<MBPartnerInvitationDto> invitationList = findDtos("getInvitationBPartnerByPan", params);
			response.addObject("invitationList",invitationList);
		}else{
			response.addObject("RESULT_MESSAGE", "Update Failed !");
			response.addObject("RESULT_STATUS",false);
		}
	}else{
		response.addObject("RESULT_MESSAGE", "Something went wrong !");
		response.addObject("RESULT_STATUS",false);
	}
	return response;
}

}
