package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.HSNDao;
import com.novelerp.appbase.master.dto.HSNDto;
import com.novelerp.appbase.master.entity.HSN;
import com.novelerp.appbase.master.service.HSNService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Ankita
 *
 */
@Service
public class HSNServiceImpl extends AbstractServiceImpl<HSN, HSNDto> implements HSNService{

	@Autowired
	private HSNDao hsnDao;
	
	/*@Autowired
	private HSNConverter hsnConverter;*/
	
	@PostConstruct
	private void init() {
		super.init(HSNServiceImpl.class, hsnDao, HSN.class, HSNDto.class);
		/*setObjectConverter(hsnConverter);*/
		setByPassProxy(true);
	}

	@Override
	@Transactional
	public boolean deleteHSN(Long id) {
		// TODO Auto-generated method stub
		return deleteById(id);
		}
		@Transactional
		public void removeTax(Long id)
		{
			hsnDao.deleteById(id);
		}
}
