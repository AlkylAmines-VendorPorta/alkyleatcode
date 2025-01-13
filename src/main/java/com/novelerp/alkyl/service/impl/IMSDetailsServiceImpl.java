package com.novelerp.alkyl.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.IMSDetailsDao;
import com.novelerp.alkyl.dto.IMSDetailsDto;
import com.novelerp.alkyl.entity.IMSDetails;
import com.novelerp.alkyl.service.IMSDetailsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Service
public class IMSDetailsServiceImpl extends AbstractContextServiceImpl<IMSDetails, IMSDetailsDto>
		implements IMSDetailsService {
	
	@Autowired
	private IMSDetailsDao imsDao;
	
	@PostConstruct
	public void init(){
		super.init(IMSDetailsServiceImpl.class, imsDao, IMSDetails.class, IMSDetailsDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public IMSDetailsDto save(IMSDetailsDto dto) {
		if(dto.getImsId()==null){
			return super.save(dto);
		}else{
			return super.updateDto(dto);
		}
	}
	
	
}
