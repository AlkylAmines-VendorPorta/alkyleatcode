package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.ScrutinyPointDao;
import com.novelerp.appbase.master.dto.ScrutinyPointDto;
import com.novelerp.appbase.master.entity.ScrutinyPoint;
import com.novelerp.appbase.master.service.ScrutinyPointService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class ScrutinyPointServiceImpl extends AbstractContextServiceImpl<ScrutinyPoint, ScrutinyPointDto> implements ScrutinyPointService {

	@Autowired
	private ScrutinyPointDao scrutinyPointDao;

	
	@PostConstruct
	private void init() {
		super.init(ScrutinyPointServiceImpl.class, scrutinyPointDao, ScrutinyPoint.class, ScrutinyPointDto.class);
		setByPassProxy(true);
	}
	/*@Override
	public List<ScrutinyPointDto> getAllScrutinyPoint()
	{
		return scrutinyPointDao
	}*/
}
