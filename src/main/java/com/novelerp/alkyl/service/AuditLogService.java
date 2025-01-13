package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.AuditLogDto;
import com.novelerp.alkyl.entity.AuditLog;
import com.novelerp.core.service.CommonService;

public interface AuditLogService extends CommonService<AuditLog, AuditLogDto>{

	//List<AuditLogDto> getAuditLogReport(String uniqueCode);

}
