package com.novelerp.alkyl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.WinnerSelectionDao;
import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.service.WinnerSelectionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.entity.WinnerSelection;

@Service
public class WinnerSelectionServiceImpl extends AbstractContextServiceImpl<WinnerSelection, WinnerSelectionDto> implements WinnerSelectionService{

	@Autowired
	private WinnerSelectionDao winnerSelectionDao;
	
	@PostConstruct
	protected void init() {
		// TODO Auto-generated method stub
		super.init(WinnerSelectionServiceImpl.class, winnerSelectionDao, WinnerSelection.class, WinnerSelectionDto.class);
		setByPassProxy(true);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<WinnerSelectionDto> saveWinnerSelection(List<WinnerSelectionDto> winnerSelectionSet,AnnexureDto annexureDto) {
		List<WinnerSelectionDto> winnerSelectionList=new ArrayList<WinnerSelectionDto>();
		for(WinnerSelectionDto dto:winnerSelectionSet){
			dto.setAnnexure(annexureDto);
			if(null==dto.getWinnerSelectionId()){
				dto=super.save(dto);
			}else{
				dto=super.updateDto(dto);
			}
			winnerSelectionList.add(dto);
		}
		return winnerSelectionList;

	}
}
