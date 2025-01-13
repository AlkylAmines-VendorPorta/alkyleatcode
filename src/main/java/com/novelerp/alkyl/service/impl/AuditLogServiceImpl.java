package com.novelerp.alkyl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.AuditLogDao;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.AuditLogDto;
import com.novelerp.alkyl.entity.AuditLog;
import com.novelerp.alkyl.service.AuditLogService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Service
public class AuditLogServiceImpl extends AbstractContextServiceImpl<AuditLog, AuditLogDto> implements AuditLogService {
	
	@Autowired
	private AuditLogDao auditLogDao;
	
	@Autowired
	private AuditLogService auditLogService;
	
	@PostConstruct
	protected void init() {
		// TODO Auto-generated method stub
		super.init(AuditLogServiceImpl.class, auditLogDao, AuditLog.class, AuditLogDto.class);
		setByPassProxy(true);
	}
	

//	@Transactional(propagation=Propagation.REQUIRED)
//	public List<AuditLogDto> getAuditLogReport(String uniqueCode) {
//		
//		List<AuditLogDto> logs = new ArrayList<>();
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("uniqueKey", uniqueCode);
//		logs=auditLogService.findDtos("getAuditLog", params);
//		return logs;
//	
//	
//	}

}
