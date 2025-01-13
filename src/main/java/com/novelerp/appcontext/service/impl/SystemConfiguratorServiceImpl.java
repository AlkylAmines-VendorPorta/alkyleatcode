package com.novelerp.appcontext.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.dao.SystemConfiguratorDao;
import com.novelerp.appcontext.dto.SystemConfiguratorDto;
import com.novelerp.appcontext.entity.SystemConfigurator;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.OSValidator;

@Service
public class SystemConfiguratorServiceImpl extends AbstractContextServiceImpl<SystemConfigurator, SystemConfiguratorDto> implements SystemConfiguratorService{ 
	
	private static final Logger log = LoggerFactory.getLogger(SystemConfiguratorServiceImpl.class);

	@Autowired
	private SystemConfiguratorDao systemConfiguratorDao;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@PostConstruct
	public void init(){
		super.init(SystemConfiguratorServiceImpl.class, systemConfiguratorDao,SystemConfigurator.class, SystemConfiguratorDto.class);
		setByPassProxy(true);
	}
	
	@Override
	public long getconfiguratorCount(Map<String, Object> params,String searchColumn, String searchValue){
		long resultCount=0l;
		 String whereCondition=null;
		 whereCondition=" partner.bPartnerId=:partnerId ";
			if (!"none".equalsIgnoreCase(searchValue)) {
				params.put("searchValue", "'%" + searchValue.toUpperCase() + "%'");
				whereCondition=" upper(c."+searchColumn+") LIKE upper(:searchValue) ";
			}
		resultCount= getRecordCount(whereCondition, params);
		return resultCount;
	}
	
	@Override
	public List<SystemConfiguratorDto> getSystemConfigurator(String queryMethodName,Map<String, Object> params,int pageNumber, int pageSize,String searchColumn, String searchValue){
		List<SystemConfiguratorDto> systemConfiguratorList=new ArrayList<SystemConfiguratorDto>();
		String query = invokeQueryMethod(systemConfiguratorDao, queryMethodName);
		StringBuilder customeQuery=new StringBuilder(query);
		if (!"none".equalsIgnoreCase(searchValue)) {
			query=customeQuery.append(" WHERE upper(c."+searchColumn+") LIKE upper(:searchValue) ORDER BY c.updated DESC").toString();
			params.put("searchValue","'%" + searchValue.toUpperCase() + "%'");
		} else{
			query=customeQuery.append("  ORDER BY c.updated DESC").toString();
		}
		systemConfiguratorList = findDtosByQuery(query, params, pageNumber, pageSize);
		return systemConfiguratorList;
	}
	
	@Override
	public List<SystemConfiguratorDto> getPartnerSystemConfigurator(String queryMethodName,Map<String, Object> params,int pageNumber, int pageSize,String searchColumn, String searchValue,Long partnerId){
		List<SystemConfiguratorDto> systemConfiguratorList=new ArrayList<SystemConfiguratorDto>();
		String query = invokeQueryMethod(systemConfiguratorDao, queryMethodName);
		StringBuilder customeQuery=new StringBuilder(query);
		query=customeQuery.append(" WHERE  partner.bPartnerId=:partnerId ").toString();
		params.put("partnerId",partnerId);
		if (!"none".equalsIgnoreCase(searchValue)) {
			query=customeQuery.append("  AND upper(c."+searchColumn+") LIKE upper(:searchValue) ORDER BY c.updated DESC").toString();
			params.put("searchValue","'%" + searchValue.toUpperCase() + "%'");
		} else{
			query=customeQuery.append("  ORDER BY c.updated DESC").toString();
		}
		systemConfiguratorList = findDtosByQuery(query, params, pageNumber, pageSize);
		return systemConfiguratorList;
	}
	
	@Override
	@Cacheable("SystemConfigurator")
	public String getSystemPropertyConfigurator(String value) {
		Map<String, Object> params=new HashMap<>();
		SystemConfigurator sysconf =systemConfiguratorDao.findOne(" value='"+value+"' AND isSystemAdmin='Y' ", params, null);
		String name ="";
		if(sysconf!=null){
			name =sysconf.getName();
		}
		return name;
	}

	@Override
	@Cacheable("SystemConfigurator")
	public String getPropertyConfigurator(String value) {
		SystemConfigurator sysconf=new SystemConfigurator();
		Map<String, Object> params=new HashMap<>();
		sysconf =systemConfiguratorDao.findOne(" value='"+value+"' AND isSystemAdmin='Y' ", params, null);
		String name ="";
		if(sysconf!=null){
			name =sysconf.getName();
		}
		return name;
	}
	
	@Override
	@Cacheable("SystemConfigurator")
	public String getPartnerPropertyConfigurator(String value,Long partnerId) {
		Map<String, Object> params=new HashMap<>();
		params.put("value", value);
		SystemConfigurator sysconf =systemConfiguratorDao.findOne(" upper(c.value)= upper(:value) AND partner.bPartnerId=1", params, null);
		
		params.put("partnerId", partnerId);
		SystemConfigurator sysPartnerconf =systemConfiguratorDao.findOne(" upper(c.value)= upper(:value) AND partner.bPartnerId=:partnerId", params, null);
		String name ="";
		if(sysPartnerconf!=null){
			sysPartnerconf=sysconf;
			name =sysPartnerconf.getName();
		}
		return name;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean addPartnerAllRequiredSysConfig(List<SystemConfiguratorDto> systemConfigDtoList){
		if(!CommonUtil.isCollectionEmpty(systemConfigDtoList)){
			for(SystemConfiguratorDto dto:systemConfigDtoList){
				SystemConfiguratorDto sysconfig=new SystemConfiguratorDto();
				
				sysconfig.setName(dto.getName());
				sysconfig.setValue(dto.getValue());
				sysconfig.setConfigType(dto.getConfigType());
				sysconfig.setDescription(dto.getDescription());
				sysconfig.setCode(dto.getCode());
				sysconfig.setIsSystemConfig(dto.getIsSystemConfig());
				sysconfig.setReferenceList(dto.getReferenceList());
				
				sysconfig= updateDto(sysconfig);
			}	
		}else{
			return false;
		}
		return true;
	}

	@Override
	public String getAppTempDir() {
		if(OSValidator.isWindows()){
			return getSystemPropertyConfigurator("eat.tmp.location.windows");
		}
		return getSystemPropertyConfigurator("eat.tmp.location.linux");
	}

	@Override
	public String getAppDocDir() {
		if(OSValidator.isWindows()){
			return getSystemPropertyConfigurator("eat.file.location.windows");
		}
		return getSystemPropertyConfigurator("eat.file.location.linux");
	}
}
