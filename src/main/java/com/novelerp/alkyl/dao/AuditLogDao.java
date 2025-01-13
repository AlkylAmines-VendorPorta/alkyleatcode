package com.novelerp.alkyl.dao;

import java.util.List;

import com.novelerp.alkyl.dto.AuditLogDto;
import com.novelerp.alkyl.entity.AuditLog;
import com.novelerp.core.dao.CommonDao;

public interface AuditLogDao extends CommonDao<AuditLog, AuditLogDto>{

	List<AuditLogDto> getAuditLog(String uniqueCode);

	



	

}
