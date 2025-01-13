package com.novelerp.alkyl.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.PrintGateEntryDetailDao;
import com.novelerp.alkyl.dto.PrintGateEntryDetailDto;
import com.novelerp.alkyl.entity.PrintGateEntryDetail;
import com.novelerp.alkyl.service.PrintGateEntryDetailService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
@Service
public class PrintGateEntryDetailServiceImpl extends AbstractContextServiceImpl<PrintGateEntryDetail, PrintGateEntryDetailDto> implements PrintGateEntryDetailService{

	@Autowired
	private PrintGateEntryDetailDao printGateEntryDetailDao;
	
	
	@PostConstruct
	protected void init() {
		super.init(PrintGateEntryDetailServiceImpl.class, printGateEntryDetailDao, PrintGateEntryDetail.class, PrintGateEntryDetailDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PrintGateEntryDetailDto save(PrintGateEntryDetailDto dto) {
		if(null==dto.getPrintGateEntryDetialsId()){
		dto=super.save(dto);
		}else{
		update(dto);
		}
		return dto;
		
	}
		
}
