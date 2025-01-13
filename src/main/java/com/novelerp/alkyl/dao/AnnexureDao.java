package com.novelerp.alkyl.dao;

import java.util.List;

import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.entity.Annexure;
import com.novelerp.core.dao.CommonDao;

public interface AnnexureDao extends CommonDao<Annexure, AnnexureDto>{

	List<Annexure> getPrForQcf(String where);

	String getAnnexureById();
	
	

}
