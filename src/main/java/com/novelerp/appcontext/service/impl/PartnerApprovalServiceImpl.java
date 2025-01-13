package com.novelerp.appcontext.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.dao.PartnerApprovalDao;
import com.novelerp.appcontext.dto.BpartnerApprovalDto;
import com.novelerp.appcontext.entity.BpartnerApproval;
import com.novelerp.appcontext.service.PartnerApprovalService;

/**
 * 
 * @author Varsha Patil
 *
 */
@Service
public class PartnerApprovalServiceImpl extends AbstractContextServiceImpl<BpartnerApproval, BpartnerApprovalDto> implements PartnerApprovalService{
    @Autowired
	private PartnerApprovalDao partnerApprovalDao;
	@PostConstruct
	public void init(){
		super.init(PartnerApprovalServiceImpl.class, partnerApprovalDao, BpartnerApproval.class, BpartnerApprovalDto.class);
		setByPassProxy(true);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updatePartnerApprovalForNewApproval(Long bpartnerId,Map<String, Object> updateColumnsMap) {
		boolean updateStatus=false;
		Map<String, Object> params=new HashMap<>();
		params.put("bpartnerId", bpartnerId);
		BpartnerApprovalDto partnerApproval=findDto("getQueryForPartnerApprovalByPartnerId", params);
		if(partnerApproval==null)
		{
			return true;
		}
		int updateValue=updateByJpql(updateColumnsMap, "bpartnerApprovalId", partnerApproval.getBpartnerApprovalId());
		if(updateValue>0)
		{
			updateStatus=true;
		}
		return updateStatus;
	}
}
