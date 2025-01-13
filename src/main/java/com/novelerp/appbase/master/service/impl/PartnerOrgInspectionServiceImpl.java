package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.PartnerOrgInspectionDao;
import com.novelerp.appbase.master.dto.PartnerOrgInspectionDto;
import com.novelerp.appbase.master.entity.PartnerOrgInspection;
import com.novelerp.appbase.master.service.PartnerOrgInspectionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

/**
 * 
 * @author Varsha Patil
 *
 */
@Service
public class PartnerOrgInspectionServiceImpl extends AbstractContextServiceImpl<PartnerOrgInspection, PartnerOrgInspectionDto> implements PartnerOrgInspectionService{

	@Autowired
	private PartnerOrgInspectionDao partnerOrgInspectionDao;
	
	@PostConstruct
	public void init(){
		super.init(PartnerOrgInspectionServiceImpl.class, partnerOrgInspectionDao, PartnerOrgInspection.class, PartnerOrgInspectionDto.class);
		setByPassProxy(true);
	}
}
