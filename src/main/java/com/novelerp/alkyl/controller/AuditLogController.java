package com.novelerp.alkyl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.dao.AuditLogDao;
import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.dto.AuditLogDto;
import com.novelerp.alkyl.entity.AuditLog;
import com.novelerp.alkyl.service.AuditLogService;
import com.novelerp.core.dto.CustomResponseDto;

@Controller
@RequestMapping("/rest")
public class AuditLogController {

	@Autowired
	private AuditLogService auditLogService;
	
	@Autowired
	private AuditLogDao auditLogdao;
	
	@PostMapping(value="/getLog/{uniqueCode}")
	public @ResponseBody CustomResponseDto getLog(@PathVariable("uniqueCode") String uniqueCode){
		CustomResponseDto resp=new CustomResponseDto();
		List<AuditLogDto> logs = new ArrayList<>();
		logs=auditLogdao.getAuditLog(uniqueCode);
		
		resp.addObject("logList", logs);
		return resp;
		
	}
		
}
