package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.appbase.master.dto.MaterialSpecificationDto;
import com.novelerp.appbase.master.entity.MaterialSpecification;
import com.novelerp.core.dao.CommonDao;

/**
 *  @author Aman
 */
public interface MaterialSpecificationDao extends CommonDao<MaterialSpecification, MaterialSpecificationDto> {

	public List<MaterialSpecification> getMaterialSpecificationList();
	public List<MaterialSpecification> getMaterialSpecificationListById(Long id);
	
}
