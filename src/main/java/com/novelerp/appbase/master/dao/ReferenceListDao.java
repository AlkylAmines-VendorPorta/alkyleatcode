package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.entity.ReferenceList;
import com.novelerp.core.dao.CommonDao;

public interface ReferenceListDao extends CommonDao<ReferenceList, ReferenceListDto> {

	public List<ReferenceList> getReferenceList(Long referenceId);
	public List<ReferenceList> getReferenceListByType(String type);
}