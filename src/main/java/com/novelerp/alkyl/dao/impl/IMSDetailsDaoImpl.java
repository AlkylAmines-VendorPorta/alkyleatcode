package com.novelerp.alkyl.dao.impl;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.IMSDetailsDao;
import com.novelerp.alkyl.dto.IMSDetailsDto;
import com.novelerp.alkyl.entity.IMSDetails;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class IMSDetailsDaoImpl extends AbstractJpaDAO<IMSDetails, IMSDetailsDto> implements IMSDetailsDao {

	@Override
	public void postConstruct() {
		setClazz(IMSDetails.class, IMSDetailsDto.class);
	}

	public String getIMSDetails(){
		StringBuilder sb =new StringBuilder()
				.append(" SELECT A FROM IMSDetails A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.iso14001Attachment C ")
				.append(" LEFT JOIN FETCH A.iso9001Attachment D ")
				.append(" LEFT JOIN FETCH A.iso50001Attachment E ")
				.append(" LEFT JOIN FETCH A.isRCLogoAttachment F ")
				.append(" LEFT JOIN FETCH A.ohsms45001Attachment G")
				.append(" WHERE B.bPartnerId= :partnerId ");
		return sb.toString();
	}
	
}
