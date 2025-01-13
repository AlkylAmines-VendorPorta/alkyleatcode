package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.MaterialTypeTestDetailsDao;
import com.novelerp.appbase.master.dto.MaterialTypeTestDetailsDto;
import com.novelerp.appbase.master.entity.MaterialTypeTestDetails;
import com.novelerp.appbase.master.service.MaterialTypeTestDetailsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

/**
 * 
 * @author Varsha Patil
 *
 */
@Service
public class MaterialTypeTestDetailsServiceImpl extends AbstractContextServiceImpl<MaterialTypeTestDetails, MaterialTypeTestDetailsDto>implements MaterialTypeTestDetailsService{
    @Autowired
	private MaterialTypeTestDetailsDao materialTypeTestDetailsDao;
	@PostConstruct
	private void init() {
		super.init(MaterialTypeTestDetailsServiceImpl.class, materialTypeTestDetailsDao, MaterialTypeTestDetails.class, MaterialTypeTestDetailsDto.class);
		setByPassProxy(true);
	}
}
