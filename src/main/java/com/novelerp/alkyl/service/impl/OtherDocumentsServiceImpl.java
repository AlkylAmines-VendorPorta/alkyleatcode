package com.novelerp.alkyl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ranges.RangeException;

import com.novelerp.alkyl.dao.OtherDocumentsDao;
import com.novelerp.alkyl.dto.KYCDto;
import com.novelerp.alkyl.dto.OtherDocumentsDto;
import com.novelerp.alkyl.entity.OtherDocuments;
import com.novelerp.alkyl.service.OtherDocumentsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Service
public class OtherDocumentsServiceImpl  extends AbstractContextServiceImpl<OtherDocuments, OtherDocumentsDto> implements OtherDocumentsService{

	@Autowired
	private OtherDocumentsDao otherdocuments;
	
	@PostConstruct
	public void init(){
		
		super.init(OtherDocumentsServiceImpl.class, otherdocuments, OtherDocuments.class, OtherDocumentsDto.class);
		setByPassProxy(true);
	}
	
	@Override
	public List<OtherDocumentsDto> save(List<OtherDocumentsDto> otherDocuments,KYCDto kyc) {
		if(null == kyc|| null==kyc.getKycId()){
			throw new RuntimeException("Error occured while saving KYC Details");
		}
		List<OtherDocumentsDto> newODList = new ArrayList<>();
		updateByJpql(AbstractContextServiceImpl.getParamMap("isActive", "N"), AbstractContextServiceImpl.getParamMap("kycDetails.kycId", kyc.getKycId()));
		for(OtherDocumentsDto od : otherDocuments){
			if(null==od){
				continue;
			}
			od.setKycDetails(kyc);
			od = super.save(od);
			newODList.add(od);
		}
		return newODList;
	}
}
