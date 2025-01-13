package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.InfraApprovalLevelDao;
import com.novelerp.appbase.master.dto.InfraApprovalLevelDto;
import com.novelerp.appbase.master.entity.InfraApprovalLevel;
import com.novelerp.appbase.master.service.InfraApprovalLevelService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Service
public class InfraApprovalLevelServiceImpl extends AbstractContextServiceImpl<InfraApprovalLevel, InfraApprovalLevelDto>  implements InfraApprovalLevelService{

	@Autowired
	private InfraApprovalLevelDao infraApprovalLevelDao;
	
	@PostConstruct
	public void init(){
		init(PartnerBankDetailServiceImpl.class, infraApprovalLevelDao, InfraApprovalLevel.class, InfraApprovalLevelDto.class);
		setByPassProxy(true);
	}
	@Override
	public boolean isRoleAuthorise(String roleName){
		boolean result=false;
		
		return result;
	}
}
