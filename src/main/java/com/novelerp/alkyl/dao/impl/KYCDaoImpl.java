package com.novelerp.alkyl.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.KYCDao;
import com.novelerp.alkyl.dto.KYCDto;
import com.novelerp.alkyl.entity.KYC;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class KYCDaoImpl extends AbstractJpaDAO<KYC, KYCDto> implements KYCDao {

	@PostConstruct
	void init(){
		setClazz(KYC.class, KYCDto.class);
	}

	public String getKYCDetailsByPartnerId(){
		StringBuilder sb = new StringBuilder("")
				.append("SELECT A FROM KYC A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.gst1 C ")
				.append(" LEFT JOIN FETCH A.gst2 D ")
				.append(" LEFT JOIN FETCH A.gst3 E ")
				.append(" LEFT JOIN FETCH A.threeB1 F ")
				.append(" LEFT JOIN FETCH A.threeB2 G ")
				.append(" LEFT JOIN FETCH A.threeB3 H ")
				.append(" LEFT JOIN FETCH A.cancelledCheque I ")
				.append(" LEFT JOIN FETCH A.msmeCertificate J ")
				.append(" LEFT JOIN FETCH A.lowerDeductionCert K ")
				.append(" LEFT JOIN FETCH B.panCardCopy M ")
				.append(" LEFT JOIN FETCH B.gstinCopy N ")
				.append(" WHERE B.bPartnerId= :partnerId ");
		return sb.toString();
	}
	public String getKYCByPartnerId(){
		StringBuilder sb = new StringBuilder("")
				.append("SELECT A FROM KYC A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" WHERE B.bPartnerId= :partnerId ");
		return sb.toString();
	}

}
