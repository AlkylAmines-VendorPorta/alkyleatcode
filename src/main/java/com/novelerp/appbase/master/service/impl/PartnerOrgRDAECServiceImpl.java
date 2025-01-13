package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.PartnerOrgRDAECDao;
import com.novelerp.appbase.master.dto.PartnerOrgRDAECDto;
import com.novelerp.appbase.master.entity.PartnerOrgRDAEC;
import com.novelerp.appbase.master.service.PartnerOrgRDAECService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
/**
 * @author Aman
 * @author Vivek Birdi
 */
@Service
public class PartnerOrgRDAECServiceImpl  extends AbstractContextServiceImpl<PartnerOrgRDAEC, PartnerOrgRDAECDto> implements PartnerOrgRDAECService{

	
	@Autowired
	private PartnerOrgRDAECDao  partnerOrgRDAECDao;
	
	@PostConstruct
	public void init(){
		super.init(PartnerOrgRDAECServiceImpl.class, partnerOrgRDAECDao, PartnerOrgRDAEC.class, PartnerOrgRDAECDto.class);
		/*setObjectConverter(partnerOrgRDAECConverter);*/
		setByPassProxy(true);
	}	
	
}
