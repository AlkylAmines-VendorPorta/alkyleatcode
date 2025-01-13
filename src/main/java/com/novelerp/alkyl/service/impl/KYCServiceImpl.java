package com.novelerp.alkyl.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.KYCDao;
import com.novelerp.alkyl.dto.KYCDto;
import com.novelerp.alkyl.dto.OtherDocumentsDto;
import com.novelerp.alkyl.entity.KYC;
import com.novelerp.alkyl.service.KYCService;
import com.novelerp.alkyl.service.OtherDocumentsService;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Service
public class KYCServiceImpl extends AbstractContextServiceImpl<KYC, KYCDto> implements KYCService {

	@Autowired
	private KYCDao kycDao;
	
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private OtherDocumentsService odService;
	
	@PostConstruct
	public void init(){
		super.init(KYCServiceImpl.class, kycDao, KYC.class, KYCDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public KYCDto save(KYCDto dto){
		dto.setPartner(partnerService.updateDto(dto.getPartner()));
		List<OtherDocumentsDto> otherDocuments = dto.getOtherDocuments();
		dto.setOtherDocuments(null);
		if(dto.getKycId()==null){
			dto = super.save(dto);
		}else{
			dto = super.updateDto(dto);
		}
		otherDocuments = odService.save(otherDocuments, dto);
		dto.setOtherDocuments(otherDocuments);
		return dto;
	}
}
