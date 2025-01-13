package com.novelerp.alkyl.dao;

import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.alkyl.dto.PRLineFilterDto;
import com.novelerp.alkyl.entity.PRLine;
import com.novelerp.core.dao.CommonDao;

public interface PRLineDao extends CommonDao<PRLine, PRLineDto> {

	String getPRLineByFilterQuery(PRLineFilterDto prLineDto);



}
