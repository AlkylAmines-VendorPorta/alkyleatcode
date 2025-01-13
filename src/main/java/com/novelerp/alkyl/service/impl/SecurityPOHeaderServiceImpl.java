package com.novelerp.alkyl.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.novelerp.alkyl.component.ASNComponent;
import com.novelerp.alkyl.component.SecurityPOHeaderComponent;
import com.novelerp.alkyl.dao.SecurityPOHeaderDao;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.PrintGateEntryDetailDto;
import com.novelerp.alkyl.dto.SecurityPOHeaderDto;
import com.novelerp.alkyl.dto.SecurityPOItemDto;
import com.novelerp.alkyl.entity.SecurityPOHeader;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.alkyl.service.SecurityPOHeaderService;
import com.novelerp.alkyl.service.SecurityPOLineItemService;
import com.novelerp.appbase.master.dto.PartnerItemDrawingDocDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.service.impl.ContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.ResponseDto;

@Service
public class SecurityPOHeaderServiceImpl extends AbstractContextServiceImpl<SecurityPOHeader, SecurityPOHeaderDto> implements SecurityPOHeaderService {

	@Autowired
	private SecurityPOHeaderDao securityPOHeaderDao;
	
	@Autowired
	private SecurityPOHeaderComponent SecurityComponent;
	
	@Autowired
	private SecurityPOLineItemService securityLineService;
	
	@Autowired
	private SecurityPOHeaderComponent securityPOHeaderComponent;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	
	@Autowired
	private AdvanceShipmentNoticeService asnService;
	
	
	
	
	
	
	@PostConstruct
	protected void init() {
		super.init(SecurityPOHeaderServiceImpl.class, securityPOHeaderDao, SecurityPOHeader.class, SecurityPOHeaderDto.class);
		setByPassProxy(true);
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public SecurityPOHeaderDto save(SecurityPOHeaderDto dto) {
		List<SecurityPOItemDto> asnLines=dto.getAsnLineList() ;
		String status="";
		
		if(null==dto.getAsnLineList()){
		return dto;
	      }
	
	    if(null==asnLines.get(0) || null==asnLines.get(0).getPoLine() || CommonUtil.isStringEmpty(asnLines.get(0).getPoLine().getPlant())) {
		return dto;
	      }
	     dto.setAsnLineList(null); 
		
		if(null==dto.getAsnHeaderId()){
			
			
			status=AppBaseConstant.ASN_STATUS_REPORTED;
			dto.setStatus(status);
			dto.setReportedDate(new Date());
			dto.setReportedBy(contextService.getUser());
//			dto.setAsnNumber(SecurityComponent.getNewASNNumber(asnLines.get(0).getPoLine().getPlant()));
//			dto.setAsnNumber(dto.getAsnNumber());
			dto.setAdvanceShipmentNoticeNo(dto.getAdvanceShipmentNoticeNo());
	    	dto=super.save(dto);
		}
//		else{
//		dto.setReportedDate(new Date());
//		dto.setReportedBy(contextService.getUser());
//		dto.setStatus(status);
//		dto = super.updateDto(dto);
//		}

		dto.setAsnLineList(securityLineService.save(asnLines, dto));
		
		return dto;
		
	}
	
	
	@Override
	public Integer getNewASNNumber(String plant){
		return securityPOHeaderDao.getASNNumber(plant);
	}


//	@Override
//	@Transactional(propagation=Propagation.REQUIRED)
//	public ResponseDto insertinheaderdto(SecurityPOHeaderDto dto) {
//		HttpSession session=ContextServiceImpl.getCurrentSession();
//		dto.setCreatedBy(contextService.getUser());
//		dto.setCreatedSessionId(session.getId());
//		dto.setPartner(contextService.getPartner());
//		int count=securityPOHeaderDao.insertintoheadertable(getEntity(dto));
//		if(count>0)
//		{
//			return new ResponseDto(false, "Record Inserted Successfully");
//		}
//		return new ResponseDto(true, "No Record Inserted");
//	}







	

	
}
