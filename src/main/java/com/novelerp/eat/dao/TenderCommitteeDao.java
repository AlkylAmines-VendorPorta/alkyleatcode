package com.novelerp.eat.dao;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.entity.TenderCommittee;

/**
 * 
 * @author vasrha
 *
 */
public interface TenderCommitteeDao extends CommonDao<TenderCommittee, TenderCommitteeDto>{

	public String getAssociatedTendersQuery(TenderCommitteeDto tenderCommitteeDto);
	
	public int updateSessionKey(Long tenderCommitteeId,String sessionKey);
	
	public int isSessionkeyMailed(Long tenderCommitteeId);
	
	/*public int setOtherTenderCommitteeInActive(Long tahdrId,Long tahdrDetailId,String bidOpeningType );*/
	
}

