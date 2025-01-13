package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.BOMVersionDao;
import com.novelerp.appbase.master.dto.BomVersionDto;
import com.novelerp.appbase.master.entity.BOMVersion;
import com.novelerp.appbase.master.service.BOMVersionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Service
public class BOMVersionServiceImpl extends AbstractContextServiceImpl<BOMVersion, BomVersionDto> implements BOMVersionService{
	
	@Autowired
	private BOMVersionDao bomVersionDao;
	
	@PostConstruct
	private void init() {
		super.init(BOMVersionServiceImpl.class, bomVersionDao, BOMVersion.class, BomVersionDto.class);
		setByPassProxy(true);
	}

	/*@Override
	public boolean deleteBomVersion(Long id) {
		// TODO Auto-generated method stub
		return false;
	}*/
	@Override
	@Transactional
	public boolean deleteBomVersion(Long id) {
		// TODO Auto-generated method stub
		return deleteById(id);
		}
	   @Transactional
		public void removeBomVersion(Long id)
		{
		   bomVersionDao.deleteById(id);
		}
}


