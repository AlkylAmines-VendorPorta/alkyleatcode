package com.novelerp.alkyl.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.ASNLineCostCenterDao;
import com.novelerp.alkyl.dto.ASNLineCostCenterDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.entity.ASNLineCostCenter;
import com.novelerp.alkyl.service.ASNLIneCostCenterService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
@Service
public class ASNLineCostCenterServiceImpl extends AbstractContextServiceImpl<ASNLineCostCenter, ASNLineCostCenterDto> implements ASNLIneCostCenterService{
	@Autowired
	private ASNLineCostCenterDao asnLineCostCenterDao;
	@Autowired
	private ASNLIneCostCenterService asnLIneCostCenterService;
	@PostConstruct
	protected void init() {
		// TODO Auto-generated method stub
		super.init(ASNLineCostCenterServiceImpl.class, asnLineCostCenterDao, ASNLineCostCenter.class, ASNLineCostCenterDto.class);
		setByPassProxy(true);
	}
	
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ASNLineCostCenterDto saveCostCenter(ASNLineCostCenterDto dto) {
	
		AdvanceShipmentNoticeLineDto asnLineList= dto.getAsnLine();
		ASNLineCostCenterDto oldDto = findDto("getasnLineCostCenter", AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeLineId", asnLineList.getAdvanceShipmentNoticeLineId()));
	
		if(oldDto!=null){
		
			oldDto = getNewDtoFromOldDto(oldDto,dto);
			oldDto = super.updateDto(oldDto);
		
		}else{
			
			oldDto = super.save(dto);
		}
		return dto;
	}
	
	public ASNLineCostCenterDto getNewDtoFromOldDto(ASNLineCostCenterDto oldDto,ASNLineCostCenterDto newDto){
		oldDto.setCode(newDto.getCode());
		oldDto.setCostCenter(newDto.getCostCenter());
		oldDto.setStorageLocation(newDto.getStorageLocation());
		oldDto.setQuantity(newDto.getQuantity());
		//oldDto.setFundCenter(newDto.getFundCenter());
		return oldDto;
	}

}
