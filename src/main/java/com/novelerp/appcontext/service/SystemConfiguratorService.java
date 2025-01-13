package com.novelerp.appcontext.service;

import java.util.List;
import java.util.Map;

import com.novelerp.appcontext.dto.SystemConfiguratorDto;
import com.novelerp.appcontext.entity.SystemConfigurator;
import com.novelerp.core.service.CommonService;

public interface SystemConfiguratorService extends CommonService<SystemConfigurator, SystemConfiguratorDto> {

	public List<SystemConfiguratorDto> getSystemConfigurator(String queryMethodName,Map<String, Object> params,int pageNumber, int pageSize,String searchColumn, String searchValue);
	public List<SystemConfiguratorDto> getPartnerSystemConfigurator(String queryMethodName,Map<String, Object> params,int pageNumber, int pageSize,String searchColumn, String searchValue,Long partnerId);
	public long getconfiguratorCount(Map<String, Object> params,String searchColumn, String searchValue);
	
	public String getPropertyConfigurator(String value);
	public String getSystemPropertyConfigurator(String value);
	
	public String getAppTempDir();
	public String getAppDocDir();
	
	public String getPartnerPropertyConfigurator(String value,Long partnerId) ;

	public boolean addPartnerAllRequiredSysConfig(List<SystemConfiguratorDto> systemConfigDtoList);
}
