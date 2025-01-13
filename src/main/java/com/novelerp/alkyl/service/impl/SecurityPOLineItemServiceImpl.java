package com.novelerp.alkyl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.SecurityPOHeaderDao;
import com.novelerp.alkyl.dao.SecurityPOItemDao;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.SecurityPOHeaderDto;
import com.novelerp.alkyl.dto.SecurityPOItemDto;
import com.novelerp.alkyl.entity.AdvanceShipmentNoticeLine;
import com.novelerp.alkyl.entity.SecurityPOHeader;
import com.novelerp.alkyl.entity.SecurityPOItem;

import com.novelerp.alkyl.service.SecurityPOLineItemService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;

@Service
public class SecurityPOLineItemServiceImpl extends AbstractContextServiceImpl<SecurityPOItem, SecurityPOItemDto> implements SecurityPOLineItemService {
	
	
	@Autowired
	private SecurityPOItemDao securityPOItemDao;
	
	@PostConstruct
	protected void init() {
		super.init(SecurityPOLineItemServiceImpl.class, securityPOItemDao, SecurityPOItem.class, SecurityPOItemDto.class);
		setByPassProxy(true);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<SecurityPOItemDto> save(List<SecurityPOItemDto> asnLines,SecurityPOHeaderDto asn){
		if(CommonUtil.isCollectionEmpty(asnLines)){
			return asnLines;
		}
		
		List<SecurityPOItemDto> asnLineList = new ArrayList<>();
		for(SecurityPOItemDto asnLine : asnLines){
			if(asnLine!=null){
				asnLine.setSecurityPOHeaderDto(asn);
				
		
				if(null==asnLine.getsecurityAsnLineId()){
					asnLine = super.save(asnLine);
				}
				else{
					asnLine = super.updateDto(asnLine);
				}
				asnLineList.add(asnLine);
			    }				
			}
		
		
		return asnLineList;
	}
}
