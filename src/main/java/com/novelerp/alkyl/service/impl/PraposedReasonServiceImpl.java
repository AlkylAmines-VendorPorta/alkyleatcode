package com.novelerp.alkyl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.PraposedReasonDao;
import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.dto.PraposedReasonDto;
import com.novelerp.alkyl.entity.PraposedReason;
import com.novelerp.alkyl.service.PraposedReasonService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
@Service
public class PraposedReasonServiceImpl extends AbstractContextServiceImpl<PraposedReason, PraposedReasonDto> implements PraposedReasonService{
	
	@Autowired
	private PraposedReasonDao praposedReasonDao;
	
	@PostConstruct
	protected void init() {
		// TODO Auto-generated method stub
		super.init(PraposedReasonServiceImpl.class, praposedReasonDao, PraposedReason.class, PraposedReasonDto.class);
		setByPassProxy(true);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<PraposedReasonDto> savePraposedReason(List<PraposedReasonDto> praposedReasonSet, AnnexureDto annexureDto) {
		List<PraposedReasonDto> praposedReasonList= new ArrayList<PraposedReasonDto>();
		for(PraposedReasonDto dto:praposedReasonSet){
			dto.setAnnexure(annexureDto);
			
			if(dto.getCode().equals("") || dto.getCode()==null) {
				throw new RuntimeException("Praposed reason cannot be null");
			}
			
			if(null==dto.getPraposedReasonId()){
				dto=super.save(dto);
			}else{
				dto=super.updateDto(dto);
			}
			
			praposedReasonList.add(dto);
		}
		return praposedReasonList;
	}
	

}
