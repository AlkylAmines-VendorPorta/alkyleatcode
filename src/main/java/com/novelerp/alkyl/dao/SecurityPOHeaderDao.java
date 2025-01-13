package com.novelerp.alkyl.dao;

import com.novelerp.alkyl.dto.SecurityPOHeaderDto;
import com.novelerp.alkyl.entity.SecurityPOHeader;
import com.novelerp.core.dao.CommonDao;

public interface SecurityPOHeaderDao extends CommonDao<SecurityPOHeader, SecurityPOHeaderDto> {



	Integer getASNNumber(String plant);

	String getASNByASNNoforCommercial();

	//String printASNByASNNOforSecurity();
	//int insertintoheadertable(SecurityPOHeader entity);

	

	





}
