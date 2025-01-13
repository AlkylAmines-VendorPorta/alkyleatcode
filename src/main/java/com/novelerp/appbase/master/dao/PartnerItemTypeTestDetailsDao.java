package com.novelerp.appbase.master.dao;
/***
 * 
 * @author Varsha Patil
 *
 */

import com.novelerp.appbase.master.dto.PartnerItemTypeTestDetailsDto;
import com.novelerp.appbase.master.entity.PartnerItemTypeTestDetails;
import com.novelerp.core.dao.CommonDao;

public interface PartnerItemTypeTestDetailsDao extends CommonDao<PartnerItemTypeTestDetails, PartnerItemTypeTestDetailsDto>{
	public int insertDrawingDocByMaterial(PartnerItemTypeTestDetails typeTest);
	
	public int resetInfraTypeTest(Long infraItemId,Long partnerId);
}
