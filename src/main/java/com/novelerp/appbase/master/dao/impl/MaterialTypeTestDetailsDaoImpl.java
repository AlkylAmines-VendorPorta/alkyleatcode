package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.MaterialTypeTestDetailsDao;
import com.novelerp.appbase.master.dto.MaterialTypeTestDetailsDto;
import com.novelerp.appbase.master.entity.MaterialTypeTestDetails;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Varsha Patil
 *
 */
@Repository
public class MaterialTypeTestDetailsDaoImpl extends AbstractJpaDAO<MaterialTypeTestDetails, MaterialTypeTestDetailsDto> implements MaterialTypeTestDetailsDao{

	@PostConstruct
	private void init() {
		setClazz(MaterialTypeTestDetails.class,MaterialTypeTestDetailsDto.class);
	}
}
