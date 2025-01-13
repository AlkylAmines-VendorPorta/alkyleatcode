package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.PartnerOrgLicenseDao;
import com.novelerp.appbase.master.dto.PartnerOrgLicenseDto;
import com.novelerp.appbase.master.entity.PartnerOrgLicense;
import com.novelerp.appbase.master.service.PartnerOrgLicenseService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
/**
 * 
 * @author Aman
 */
@Service
public class PartnerOrgLicenseServiceImpl extends AbstractContextServiceImpl<PartnerOrgLicense, PartnerOrgLicenseDto> implements PartnerOrgLicenseService{

	
	@Autowired
	private PartnerOrgLicenseDao  partnerOrgLicenseDao;
	
	/*@Autowired
	private PartnerOrgLicenseConverterPlain partnerOrgLicenseConverter;*/
	
	@PostConstruct
	public void init(){
		super.init(PartnerOrgLicenseServiceImpl.class, partnerOrgLicenseDao, PartnerOrgLicense.class, PartnerOrgLicenseDto.class);
		/*setObjectConverter(partnerOrgLicenseConverter);*/
	}	
}

