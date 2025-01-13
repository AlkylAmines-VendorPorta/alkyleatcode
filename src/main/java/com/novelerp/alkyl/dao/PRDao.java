package com.novelerp.alkyl.dao;

import java.util.List;

import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.PRReadDto;
import com.novelerp.alkyl.entity.PR;
import com.novelerp.core.dao.CommonDao;

public interface PRDao extends CommonDao<PR, PRDto> {

	public String getPRByPRNumber();

	public String getPR();

	public List<Object> getQCFReport(Long prId);

	public String getPRByFilter(PRReadDto dto);

}
