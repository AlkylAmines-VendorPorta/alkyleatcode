package com.novelerp.appbase.master.dao;

import com.novelerp.appbase.master.dto.PartnerItemDrawingDocDto;
import com.novelerp.appbase.master.entity.PartnerItemDrawingDoc;
import com.novelerp.core.dao.CommonDao;

/**
 * 
 * @author Varsha Patil
 *
 */
public interface PartnerItemDrawingDocDao extends CommonDao<PartnerItemDrawingDoc, PartnerItemDrawingDocDto>{
    public int insertDrawingDocByMaterial(PartnerItemDrawingDoc itemDoc);
    
    public int resetInfraDrawingDocs(Long infraItemId,Long partnerId);
    	
    	
}
